using DinkToPdf;
using DinkToPdf.Contracts;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using VidaAutistaDotnet.Application.Interfaces;
using VidaAutistaDotnet.Application.Mappings;
using VidaAutistaDotnet.Application.Reports;
using VidaAutistaDotnet.Application.Services;
using VidaAutistaDotnet.Domain.Interfaces;
using VidaAutistaDotnet.Infra.Data;
using VidaAutistaDotnet.Infra.Data.Repositories;

namespace VidaAutistaDotnet.Infra.IoC
{
    public static class DependencyInjection
    {

        public static IServiceCollection AddInfrastructure(this IServiceCollection services,
          IConfiguration configuration)
        {
            services.AddScoped<ICalendarioRepository, CalendarioRepository>();
            services.AddScoped<ICalendarioService, CalendarioService>();
            services.AddScoped<IReportService, ReportService>();

            services.AddSingleton(typeof(IConverter), new SynchronizedConverter(new PdfTools()));

            services.AddAutoMapper(typeof(DomainToDTOMappingProfile));

            services.AddScoped<IConnectionFactory, DefaultMySqlConnectionFactory>();

            return services;
        }

    }
}
