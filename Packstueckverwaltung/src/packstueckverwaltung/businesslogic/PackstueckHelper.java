package packstueckverwaltung.businesslogic;

import javax.servlet.http.HttpServletRequest;

import packstueckverwaltung.model.Benutzer;
import packstueckverwaltung.model.Packstueck;
import packstueckverwaltung.model.PackstueckForm;

public class PackstueckHelper
{
	public static String packstuecklisteladen(HttpServletRequest request)
	{
		String contentpage = "packstuecklisteMaster";

		if (request.getParameter("barcodesuchfeld") == null)
		{
			// Alle Daten abrufen
			request.setAttribute("packstueckliste", DaoHelper.getPackstueckManager().getAllPackstuecke());
		}
		// Suchbutton wurde ausgeführt
		else
		{
			String barcode = request.getParameter("barcodesuchfeld");
			contentpage = "packstuecklisteTabelle";

			if (barcode.equals("") || barcode.equals("*"))
			{
				// Alle Daten abrufen
				request.setAttribute("packstueckliste", DaoHelper.getPackstueckManager().getAllPackstuecke());
			}
			else
			{
				// Gefilterte Daten abrufen
				request.setAttribute("packstueckliste", DaoHelper.getPackstueckManager()
						.getPackstueckByBarcode(barcode));
			}
		}

		return contentpage;
	}

	public static String updatePackstueck(HttpServletRequest request)
	{
		// contentpage --> Updateform
		String contentpage = "updatepackstueck";
		PackstueckForm form = null;

		if (request.getParameter("id") != null)
		{
			if (request.getParameter("saveaction") == null)
			{
				// editieren starten
				int id = Integer.valueOf(request.getParameter("id"));

				// Packstück aus der DB abfragen
				Packstueck packstueck = DaoHelper.getPackstueckManager().getPackstueckById(id);

				// Alte Daten an die Session speichern, um Änderungen nachverfolgen zu können
				request.getSession().setAttribute("old_values", packstueck.getAllFieldsToString());

				// Nur manuell angelegte Packstücke können komplett bearbeitet werden
				if (packstueck.isManuellAngelegt())
				{
					request.getSession().setAttribute("bearbeitungsmodus", "complete");
				}
				else
				{
					request.getSession().setAttribute("bearbeitungsmodus", "reduced");
				}

				// Form mit den entsprechenden vorbelegten Packstückdaten laden
				form = new PackstueckForm(packstueck, request);
			}
			// Speichern-Button auf der Packstueckform wurde geklickt --> speichern/updaten
			else
			{
				return saveDataToDatabase(request);
			}
		}
		else
		{
			// neues Packstück anlegen
			form = new PackstueckForm();
			request.getSession().setAttribute("bearbeitungsmodus", "complete");
		}
		request.setAttribute("form", form);
		return contentpage;
	}

	private static String saveDataToDatabase(HttpServletRequest request)
	{
		PackstueckForm form = new PackstueckForm(request);
		Packstueck packstueck = new Packstueck();
		form.validate(packstueck, request);

		// Update/Insert versuchen. Wenn es nicht klappt, wird eine Fehlermeldung ausgegeben
		if (DaoHelper.getPackstueckManager().saveOrUpdatePackstueck(packstueck))
		{
			// Änderungslog anlegen
			String alteDaten = (String) request.getSession().getAttribute("old_values");
			if (alteDaten == null)
				alteDaten = "Daten wurden neu angelegt";
			String neueDaten = packstueck.getAllFieldsToString();
			Benutzer user = (Benutzer) request.getSession().getAttribute("session_person");

			DaoHelper.getReportManager().insertReport(user.getEmail(), alteDaten, neueDaten, packstueck.getBarcode());

			request.getSession().setAttribute("global_message", "Daten erfolgreich gespeichert");
		}
		else
		{
			request.getSession().setAttribute("global_error", "Fehler beim Speichern der Daten!");
		}

		// Wieder zurück zur Liste navigieren
		return packstuecklisteladen(request);
	}

	public static String deletePackstueck(HttpServletRequest request)
	{
		if (request.getParameter("id") != null)
		{
			int id = Integer.valueOf(request.getParameter("id"));
			DaoHelper.getPackstueckManager().deletePackstueckById(id);
		}

		request.getSession().setAttribute("global_message", "Datensatz wurde gelöscht!");

		// Wieder zurück zur Liste navigieren
		return packstuecklisteladen(request);
	}
}
