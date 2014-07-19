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
		// defaultwert um auf die liste zurückfallen
		String contentpage = "updatepackstueckcomplete";
		PackstueckForm form = null;

		if (request.getParameter("id") != null)
		{
			if (request.getParameter("saveaction") == null)
			{
				// editieren starten
				int id = Integer.valueOf(request.getParameter("id"));

				Packstueck packstueck = DaoHelper.getPackstueckManager().getPackstueckById(id);
				request.getSession().setAttribute("old_values", packstueck.getAllFieldsToString());

				// TODO Userberechtigung prüfen
				// Nur manuell angelegte Packstücke können komplett bearbeitet werden
				if (packstueck.isManuellAngelegt())
				{
					contentpage = "updatepackstueckcomplete";
				}
				else
				{
					contentpage = "updatepackstueckreduced";
				}

				// Form mit den entsprechenden vorbelegten Packstückdaten laden
				form = new PackstueckForm(packstueck, request);
			}
			// Speichern-Button auf der Packstueckform wurde geklickt --> speichern/updaten
			else
			{
				form = new PackstueckForm(request);
				Packstueck packstueck = new Packstueck();
				form.validate(packstueck, request);
				DaoHelper.getPackstueckManager().saveOrUpdatePackstueck(packstueck);

				// Änderungslog anlegen
				String alteDaten = request.getSession().getAttribute("old_values").toString();
				String neueDaten = packstueck.getAllFieldsToString();
				Benutzer user = (Benutzer) request.getSession().getAttribute("session_person");

				DaoHelper.getReportManager().insertReport(user.getEmail(), alteDaten, neueDaten,
						packstueck.getBarcode());
			}
		}
		else
		{
			// neues Packstück anlegen
			form = new PackstueckForm();
		}
		request.setAttribute("form", form);
		return contentpage;
	}

	public static String deletePackstueck(HttpServletRequest request)
	{
		String contentpage = "packstuecklisteTabelle";

		if (request.getParameter("id") != null)
		{
			int id = Integer.valueOf(request.getParameter("id"));
			DaoHelper.getPackstueckManager().deletePackstueckById(id);
		}

		return contentpage;
	}
}
