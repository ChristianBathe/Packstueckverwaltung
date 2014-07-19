package packstueckverwaltung.businesslogic;

import javax.servlet.http.HttpServletRequest;

import packstueckverwaltung.model.Benutzer;
import packstueckverwaltung.model.Lagerwegedaten;
import packstueckverwaltung.model.LagerwegedatenForm;

public class LagerwegedatenHelper
{
	public static String lagerwegedatenlisteLaden(HttpServletRequest request)
	{
		String contentpage = "lagerwegedatenlisteMaster";

		if (request.getParameter("barcodesuchfeld") == null)
		{
			// Alle Daten abrufen
			request.setAttribute("lagerwegedatenliste", DaoHelper.getLagerwegedatenManager().getAllLagerwegedaten());
		}
		// Suchbutton wurde ausgeführt
		else
		{
			String barcode = request.getParameter("barcodesuchfeld");
			contentpage = "lagerwegedatenlisteTabelle";

			if (barcode.equals("") || barcode.equals("*"))
			{
				// Alle Daten abrufen
				request.setAttribute("lagerwegedatenliste", DaoHelper.getLagerwegedatenManager().getAllLagerwegedaten());
			}
			else
			{
				// Gefilterte Daten abrufen
				request.setAttribute("lagerwegedatenliste", DaoHelper.getLagerwegedatenManager()
						.getLagerwegedatenByBarcode(barcode));
			}
		}

		return contentpage;
	}

	public static String updateLagerwegedaten(HttpServletRequest request)
	{
		// defaultwert um auf die liste zurückfallen
		String contentpage = "updatelagerwegedaten";
		LagerwegedatenForm form = null;

		if (request.getParameter("id") != null)
		{
			if (request.getParameter("saveaction") == null)
			{
				// editieren starten
				int id = Integer.valueOf(request.getParameter("id"));

				Lagerwegedaten lagerwegedaten = DaoHelper.getLagerwegedatenManager().getLagerwegedatenById(id);
				request.getSession().setAttribute("old_values", lagerwegedaten.getAllFieldsToString());

				contentpage = "updatelagerwegedaten";

				// Form mit den entsprechenden vorbelegten Packstückdaten laden
				form = new LagerwegedatenForm(lagerwegedaten, request);
			}
			// Speichern-Button auf der Packstueckform wurde geklickt --> speichern/updaten
			else
			{
				form = new LagerwegedatenForm(request);
				Lagerwegedaten lagerwegedaten = new Lagerwegedaten();
				form.validate(lagerwegedaten, request);
				DaoHelper.getLagerwegedatenManager().saveOrUpdateLagerwegedaten(lagerwegedaten);

				// Änderungslog anlegen
				String alteDaten = request.getSession().getAttribute("old_values").toString();
				String neueDaten = lagerwegedaten.getAllFieldsToString();
				Benutzer user = (Benutzer) request.getSession().getAttribute("session_person");

				DaoHelper.getReportManager().insertReport(user.getEmail(), alteDaten, neueDaten,
						lagerwegedaten.getBarcode());
			}
		}
		else
		{
			// neue Lagerwegedaten anlegen
			form = new LagerwegedatenForm();
		}
		request.setAttribute("form", form);
		return contentpage;
	}

	public static String deleteLagerwegedaten(HttpServletRequest request)
	{
		String contentpage = "lagerwegedatenliste";

		if (request.getParameter("id") != null)
		{
			int id = Integer.valueOf(request.getParameter("id"));
			DaoHelper.getLagerwegedatenManager().deleteLagerwegedatenById(id);
		}

		return contentpage;
	}
}
