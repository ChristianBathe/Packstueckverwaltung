package packstueckverwaltung.businesslogic;

import javax.servlet.http.HttpServletRequest;


public class ReportHelper
{
	public static String reportlisteLaden(HttpServletRequest request)
	{
		String contentpage = "reportlisteMaster";

		if (request.getParameter("barcodesuchfeld") == null)
		{
			// Alle Daten abrufen
			request.setAttribute("reportliste", DaoHelper.getReportManager().getAllReport());

		}
		// Suchbutton wurde ausgef�hrt
		else
		{
			String barcode = request.getParameter("barcodesuchfeld");
			contentpage = "reportlisteTabelle";

			if (barcode.equals("") || barcode.equals("*"))
			{
				// Alle Daten abrufen
				request.setAttribute("reportliste", DaoHelper.getReportManager().getAllReport());
			}
			else
			{
				// Gefilterte Daten abrufen
				request.setAttribute("reportliste", DaoHelper.getReportManager().getReportByBarcode(barcode));
			}
		}

		return contentpage;
	}
}
