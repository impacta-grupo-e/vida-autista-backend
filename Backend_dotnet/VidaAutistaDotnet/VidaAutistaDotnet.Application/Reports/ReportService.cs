using DinkToPdf;
using DinkToPdf.Contracts;
using System.Data.Common;
using System.Drawing;
using System.Threading;
using System;
using System.Reflection;
using static System.Net.Mime.MediaTypeNames;
using System.Collections.Generic;
using System.Reflection.Metadata;
using VidaAutistaDotnet.Application.Interfaces;

namespace VidaAutistaDotnet.Application.Reports
{
    public class ReportService : IReportService
    {
        private readonly IConverter _converter;
        private readonly ICalendarioService _calendarioService;
        public ReportService(IConverter converter, ICalendarioService calendarioService)
        {
            _converter = converter;
            _calendarioService = calendarioService;
        }
        public byte[] GetRelatorioAgenda(int idUsuario)
        {
            var css = @"<style>html,body{height:100%}body{margin:0;background:linear-gradient(45deg,#49a09d,#5f2c82);font-family:sans-serif;font-weight:100}.container{position:absolute;top:50%;left:50%;transform:translate(-50%,-50%)}table{width:800px;border-collapse:collapse;overflow:hidden;box-shadow:0 0 20px rgba(0,0,0,.1)}th,td{padding:15px;background-color:rgba(255,255,255,.2);color:#fff}th{text-align:left}thead{th{background-color:#55608f}}tbody{tr{&:hover{background-color:rgba(255,255,255,.3)}}td{position:relative;&:hover{&:before{content:"";position:absolute;left:0;right:0;top:-9999px;bottom:-9999px;background-color:rgba(255,255,255,.2);z-index:-1}}}}</style>";
            var html = RetornaHtmlRelatorioAgenda(idUsuario);

            GlobalSettings globalSettings = new GlobalSettings();
            globalSettings.ColorMode = ColorMode.Color;
            globalSettings.Orientation = Orientation.Portrait;
            globalSettings.PaperSize = PaperKind.A4;
            globalSettings.Margins = new MarginSettings { Top = 25, Bottom = 25 };
            ObjectSettings objectSettings = new ObjectSettings();
            objectSettings.PagesCount = true;
            objectSettings.HtmlContent = html;
            WebSettings webSettings = new WebSettings();
            webSettings.DefaultEncoding = "utf-8";
            //HeaderSettings headerSettings = new HeaderSettings();
            //headerSettings.FontSize = 15;
            //headerSettings.FontName = "Ariel";
            //headerSettings.Right = "Page [page] of [toPage]";
            //headerSettings.Line = true;

            FooterSettings footerSettings = new FooterSettings();
            footerSettings.FontSize = 12;
            footerSettings.FontName = "Trebuchet MS";
            footerSettings.Right = "Page [page] of [toPage]";
            //footerSettings.Line = true;
            //objectSettings.HeaderSettings = headerSettings;
            objectSettings.FooterSettings = footerSettings;
            objectSettings.WebSettings = webSettings;
            HtmlToPdfDocument htmlToPdfDocument = new HtmlToPdfDocument()
            {
                GlobalSettings = globalSettings,
                Objects = { objectSettings },
            };
            return _converter.Convert(htmlToPdfDocument);
        }


        private string GeraHtml()
        {
            return @"<html>

                <head>
                <style>
                  table.blueTable {
                  font-family: ""Trebuchet MS"", Helvetica, sans-serif;
                  border: 0px solid #70A487;
                  background - color: #FFFFFF;
                  width: 80 %;
                text - align: center;
                border - collapse: collapse;
        }
        table.blueTable td, table.blueTable th
        {
            border: 1px solid #AAAAAA;
                  padding: 3px 1px;
        }
        table.blueTable tbody td {
                  font-size: 12px;
                }
    table.blueTable tr:nth-child(even)
    {
    background: #D0E4F5;
                }
    table.blueTable thead
    {
        background: #5D87C1;
                  background: -moz-linear-gradient(top, #85a5d0 0%, #6d93c7 66%, #5D87C1 100%);
                  background: -webkit-linear-gradient(top, #85a5d0 0%, #6d93c7 66%, #5D87C1 100%);
                  background: linear-gradient(to bottom, #85a5d0 0%, #6d93c7 66%, #5D87C1 100%);
                }
    table.blueTable thead th{
                  font-size: 14px;
                  font-weight: bold;
                  color: #FFFFFF;
                  border-left: 2px solid #D0E4F5;
                }
table.blueTable thead th:first - child {
    border - left: none;
}


				.demo - wrap {
overflow: hidden;
position: relative;
}

                .demo - bg {
opacity: 0.6;
position: absolute;
left: 0;
top: 0;
width: 100 %;
height: auto;
}

                .demo - content {
position: relative;
}

                </ style >
                </ head >
                < body style = ""font-family: 'Trebuchet MS', Helvetica, sans-serif;"" >



                    < div class= ""demo-wrap"" >

                    < img class= ""demo-bg"" src = ""https://assets.digitalocean.com/labs/images/community_bg.png"" >
                    < div class= ""demo-content"" >
                    < p >
                    Olá, @nome! < br >< br >
                    Segue resumo dos itens consumidos na lanchonete por você e/ou sua família.
                    <br>
                    <br>
                        <b>Data:</ b > @data
                    < br >
                    @escala
                    </ br >


                  </ p >


                  < table class= ""blueTable"" >
                    < thead >
                        < tr >
                            < th > Produto </ th >
                            < th > Preço Unitário </ th >
                            < th > Quantidade </ th >
                            < th > Valor Total </ th >
                        </ tr >
                    </ thead >
                    < tbody >

                        @produtos


                        <tr>
                            < th style = 'border:none;' ></ th >
                            < th style = 'border:none;' ></ th >
                            < th style = 'background: #5D87C1;font-size: 15px;font-weight: bold;color: #FFFFFF;' > TOTAL </ th >
                            < th style = 'background: #5D87C1;font-size: 15px;font-weight: bold;color: #FFFFFF;' > @total </ th >
                         </ tr >
                    </ tbody >


                  </ table >


                    < br >
                    < br >


                    < p >
                      Caso encontre alguma inconsistência,
                       <br>
                      por gentileza, entre em contato com os responsáveis pela lanchonete,
                      <br>
                       no máximo até: < b > @contestacao </ b >
                      < br >
                      < br >
                Fraternalmente,
                < br >
                < br >
                Equipe da Lanchonete São Joao Batista
                    </p>
                </div>
                </div>
                </body>
                </html>";
        }



        private string RetornaHtmlRelatorioAgenda(int idUsuario)
        {
            string produtos = "";

            //var repasse = _financeiroService.ListarItensRepasseFinanceiro(idEscala, idSocio).ToList();//_bll.ListarItensRepasseFinanceiro(idEscala,idSocio);
            var calendarios = _calendarioService.GetCalendarioUsuario(idUsuario);
            foreach (var item in calendarios)
            {
                produtos = produtos +
                    "<tr>" +
                        "<td>" + item.DataHoraEvento + "</td>" +
                        "<td>" + item.TipoEvento + "</td>" +
                        "<td>" + item.Anotacoes + "</td>" +
                    "</tr>";
            }
            string email = BuscaHtml()
                .Replace("@nome", "Jackson")
                .Replace("@calendarios", produtos);


            return email;
        }
        private string BuscaHtml()
        {
            return @"<html>

                <head>
                <style>
                  table.blueTable {
                  font-family: ""Trebuchet MS"", Helvetica, sans-serif;
                  border: 0px solid #70A487;
                  background-color: #FFFFFF;
                  width: 80%;
                  text-align: center;
                  border-collapse: collapse;
                }
                table.blueTable td, table.blueTable th {
                  border: 1px solid #AAAAAA;
                  padding: 3px 1px;
                }
                table.blueTable tbody td {
                  font-size: 12px;
                }
                table.blueTable tr:nth-child(even) {
                  background: #D0E4F5;
                }
                table.blueTable thead {
                  background: #5D87C1;
                  background: -moz-linear-gradient(top, #85a5d0 0%, #6d93c7 66%, #5D87C1 100%);
                  background: -webkit-linear-gradient(top, #85a5d0 0%, #6d93c7 66%, #5D87C1 100%);
                  background: linear-gradient(to bottom, #85a5d0 0%, #6d93c7 66%, #5D87C1 100%);
                }
                table.blueTable thead th{
                  font-size: 14px;
                  font-weight: bold;
                  color: #FFFFFF;
                  border-left: 2px solid #D0E4F5;
                }
                table.blueTable thead th:first-child {
                  border-left: none;
                }
				.demo-wrap {
                  overflow: hidden;
                  position: relative;
                }

                .demo-bg {
                  opacity: 0.1;
                  position: absolute;
                  left: 0;
                  top: 0;
                  width: 100%;
                  height: auto;
                }

                .demo-content {
                  position: relative;
                }
                </style>
                </head>
                <body style=""font-family: 'Trebuchet MS', Helvetica, sans-serif;"">
					<img class=""demo-bg"" src=""https://localhost:7092/images/logonew.png"">
                  <p>
                    Olá, @nome!<br><br>
                    Abaixo você encontra o relatório detalhado da sua agenda.
                    <br>
                    <br>
                        <b>De:</b> @dataInicio 
                        <b>Até:</b> @dataFim 
                    <br>
       
                    </br>
                
                  </p>
                
                  <table class=""blueTable"">
                    <thead>
                        <tr>
                            <th>Data e Hora</th>
                            <th>Tipo de Evento</th>
                            <th>Anotações</th>
                        </tr>
                    </thead>
                    <tbody>     

                        @calendarios 


                    </tbody>
                   
                  </table>


                    <br>
                    <br>
                    
                    <p>
                      Aqui talvez de para colocar algum texto, 
                       <br>
                      Aqui tambem pode ser,
                      <br>
                       Aqui eu nao sei
                      <br>
                      <br>
                Fraternalmente,
                <br>
                <br>
                Equipe Vida Autista!
                    </p>
                
                </body>
                </html>";
        }
    }
}
