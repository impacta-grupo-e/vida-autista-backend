using Microsoft.AspNetCore.Mvc;
using VidaAutistaDotnet.Application.Reports;

namespace VidaAutistaDotnet.Controllers
{
  [ApiController]
  [Route("[controller]")]
  public class WeatherForecastController : ControllerBase
  {
        private readonly IReportService _reportService;
        public WeatherForecastController(IReportService reportService)
        {
            _reportService = reportService;
        }
        [HttpGet]
        public IActionResult Get()
        {
            
            var pdfFile = _reportService.GetRelatorioAgenda(1);
            return File(pdfFile,
            "application/octet-stream", "SimplePdf.pdf");
        }
    }
}