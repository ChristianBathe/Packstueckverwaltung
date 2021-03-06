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
		// Suchbutton wurde ausgef�hrt
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
		// contentpage --> Updateform
		String contentpage = "updatelagerwegedaten";
		LagerwegedatenForm form = null;

		if (request.getParameter("id") != null)
		{
			if (request.getParameter("saveaction") == null)
			{
				// editieren starten
				int id = Integer.valueOf(request.getParameter("id"));

				// Wegdaten aus der DB abfragen
				Lagerwegedaten lagerwegedaten = DaoHelper.getLagerwegedatenManager().getLagerwegedatenById(id);

				// Alte Daten an die Session speichern, um �nderungen nachverfolgen zu k�nnen
				request.getSession().setAttribute("old_values", lagerwegedaten.getAllFieldsToString());

				// Form mit den entsprechenden vorbelegten Lagerwegedaten laden
				form = new LagerwegedatenForm(lagerwegedaten, request);
			}
			// Speichern-Button auf der Lagerwegedaten wurde geklickt --> speichern/updaten
			else
			{
				return saveDataToDatabase(request);
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

	private static String saveDataToDatabase(HttpServletRequest request)
	{
		LagerwegedatenForm form = new LagerwegedatenForm(request);
		Lagerwegedaten lagerwegedaten = new Lagerwegedaten();
		form.validate(lagerwegedaten, request);

		// Update/Insert versuchen. Wenn es nicht klappt, wird eine Fehlermeldung ausgegeben
		if (DaoHelper.getLagerwegedatenManager().saveOrUpdateLagerwegedaten(lagerwegedaten))
		{
			// �nderungslog anlegen
			String alteDaten = (String) request.getSession().getAttribute("old_values");
			if (alteDaten == null)
				alteDaten = "Daten wurden neu angelegt";

			String neueDaten = lagerwegedaten.getAllFieldsToString();
			Benutzer user = (Benutzer) request.getSession().getAttribute("session_person");

			DaoHelper.getReportManager().insertReport(user.getEmail(), alteDaten, neueDaten,
					lagerwegedaten.getBarcode());

			request.getSession().setAttribute("global_message", "Daten erfolgreich gespeichert");
		}
		else
		{
			request.getSession().setAttribute("global_error", "Fehler beim Speichern der Daten!");
		}

		// Wieder zur�ck zur Liste navigieren
		return lagerwegedatenlisteLaden(request);
	}

	public static String deleteLagerwegedaten(HttpServletRequest request)
	{
		if (request.getParameter("id") != null)
		{
			int id = Integer.valueOf(request.getParameter("id"));
			DaoHelper.getLagerwegedatenManager().deleteLagerwegedatenById(id);
		}

		request.getSession().setAttribute("global_message", "Datensatz wurde gel�scht!");

		// Wieder zur�ck zur Liste navigieren
		return lagerwegedatenlisteLaden(request);
	}
}
