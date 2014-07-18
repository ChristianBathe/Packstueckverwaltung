package packstueckverwaltung.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packstueckverwaltung.businesslogic.DaoHelper;
import packstueckverwaltung.model.Benutzer;
import packstueckverwaltung.model.Lagerwegedaten;
import packstueckverwaltung.model.LagerwegedatenForm;
import packstueckverwaltung.model.Packstueck;
import packstueckverwaltung.model.PackstueckForm;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.html")
public class DispatcherServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	// private Logger logger = Logger.getLogger(DispatcherServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String contentpage = null;
		String servletPath = request.getServletPath();

		// logger.debug("verarbeite servletPath: " + servletPath);
		// switch case mit String erst seit Java 7 möglich, davor nur über if - else if - else if ... zu realisieren.

		switch (servletPath)
		{
			case "/login.html":
				contentpage = "login";
				break;
			case "/index.html":
				contentpage = "index";
				break;
			case "/packstueckliste.html":
				contentpage = packstuecklisteladen(request);
				break;
			case "/updatepackstueck.html":
				contentpage = updatePackstueck(request);
				break;
			case "/deletepackstueck.html":
				contentpage = deletePackstueck(request);
				break;
			case "/lagerwegedatenliste.html":
				contentpage = lagerwegedatenlisteLaden(request);
				break;
			case "/updatelagerwegedaten.html":
				contentpage = updateLagerwegedaten(request);
				break;
			case "/deletelagerwegedaten.html":
				contentpage = deleteLagerwegedaten(request);
				break;
			case "/reportliste.html":
				contentpage = reportlisteLaden(request);
				break;
			default:
				break;
		}
		if (contentpage != null)
		{
			request.setAttribute("contentpage", contentpage);

			if (contentpage != "reportliste")
			{
				request.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("/WEB-INF/views/" + contentpage + ".jsp").forward(request, response);
			}
		}
		else
		{
			// optional in der web.xml mit aussagekräftiger error page konfigurieren
			response.sendError(404, "servletPath: " + servletPath + " wurde noch nicht implementiert.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		this.doGet(request, response);
	}

	private String packstuecklisteladen(HttpServletRequest request)
	{
		String contentpage = "packstueckliste";

		if (request.getParameter("barcodesuche") == null)
		{
			// Alle Daten abrufen
			request.setAttribute("packstueckliste", DaoHelper.getPackstueckManager().getAllPackstuecke());
		}
		// Suchbutton wurde ausgeführt
		else
		{
			String barcode = request.getParameter("barcodesuchfeld");

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

	private String updatePackstueck(HttpServletRequest request)
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

	private String deletePackstueck(HttpServletRequest request)
	{
		String contentpage = "packstueckliste";

		if (request.getParameter("id") != null)
		{
			int id = Integer.valueOf(request.getParameter("id"));
			DaoHelper.getPackstueckManager().deletePackstueckById(id);
		}

		return contentpage;
	}

	private String lagerwegedatenlisteLaden(HttpServletRequest request)
	{
		String contentpage = "lagerwegedatenliste";

		if (request.getParameter("barcodesuche") == null)
		{
			// Alle Daten abrufen
			request.setAttribute("lagerwegedatenliste", DaoHelper.getPackstueckManager().getAllLagerwegdaten());
		}
		// Suchbutton wurde ausgeführt
		else
		{
			String barcode = request.getParameter("barcodesuchfeld");

			if (barcode.equals("") || barcode.equals("*"))
			{
				// Alle Daten abrufen
				request.setAttribute("lagerwegedatenliste", DaoHelper.getPackstueckManager().getAllLagerwegdaten());
			}
			else
			{
				// Gefilterte Daten abrufen
				request.setAttribute("lagerwegedatenliste",
						DaoHelper.getPackstueckManager().getLagerwegdatenByBarcode(barcode));
			}
		}

		return contentpage;
	}

	private String updateLagerwegedaten(HttpServletRequest request)
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

				Lagerwegedaten lagerwegedaten = DaoHelper.getPackstueckManager().getLagerwegedatenById(id);
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
				DaoHelper.getPackstueckManager().saveOrUpdateLagerwegedaten(lagerwegedaten);

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

	private String deleteLagerwegedaten(HttpServletRequest request)
	{
		String contentpage = "lagerwegedatenliste";

		if (request.getParameter("id") != null)
		{
			int id = Integer.valueOf(request.getParameter("id"));
			DaoHelper.getPackstueckManager().deleteLagerwegedatenById(id);
		}

		return contentpage;
	}

	private String reportlisteLaden(HttpServletRequest request)
	{
		String contentpage = "reportliste_master";

		if (request.getParameter("barcodesuchfeld") == null)
		{
			// Alle Daten abrufen
			request.setAttribute("reportliste", DaoHelper.getReportManager().getAllReport());

		}
		// Suchbutton wurde ausgeführt
		else
		{
			String barcode = request.getParameter("barcodesuchfeld");
			contentpage = "reportliste";

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
