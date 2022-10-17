namespace VidaAutistaDotnet.Application.Reports
{
    public interface IReportService
    {
        public byte[] GetRelatorioAgenda(int idUsuario);
    }
}
