package packstueckverwaltung.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packstueckverwaltung.businesslogic.DaoHelper;
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
			default:
				break;
		}
		if (contentpage != null)
		{
			request.setAttribute("contentpage", contentpage);
			request.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(request, response);
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
		String contentpage;
		contentpage = "packstueckliste";
		
		if(request.getParameter("barcodesuche") == null)
		{
			//Alle Daten abrufen
			request.setAttribute("packstueckliste", DaoHelper.getPackstueckManager().getAllPackstuecke());
		}
		//Suchbutton wurde ausgeführt
		else
		{
			String barcode = request.getParameter("barcodesuchfeld");
			
			if(barcode.equals("") || barcode.equals("*"))
			{
				//Alle Daten abrufen
				request.setAttribute("packstueckliste", DaoHelper.getPackstueckManager().getAllPackstuecke());
			}
			else
			{
				//Gefilterte Daten abrufen
				request.setAttribute("packstueckliste", DaoHelper.getPackstueckManager().getPackstueckByBarcode(barcode));
			}		
		}
		
		return contentpage;
	}

	private String updatePackstueck(HttpServletRequest request)
	{
		//defaultwert um auf die liste zurückfallen
		String contentpage = "packstueckliste";
		PackstueckForm form = null;
		
		if (request.getParameter("id") != null)
		{
			if (request.getParameter("saveaction") == null)
			{
				// editieren starten
				int id = Integer.valueOf(request.getParameter("id"));
				
				Packstueck packstueck = DaoHelper.getPackstueckManager().getPackstueckById(id);
				
				//TODO Userberechtigung prüfen
				//Nur manuell angelegte Packstücke können komplett bearbeitet werden
				if(packstueck.isManuellAngelegt())
				{
					contentpage = "updatepackstueckcomplete";
				}
				else
				{
					contentpage = "updatepackstueckreduced";
				}
				
				//Form mit den entsprechenden vorbelegten Packstückdaten laden
				form = new PackstueckForm(packstueck, request);
			}
			// Speichern-Button auf der Packstueckform wurde geklickt --> speichern/updaten
			else
			{				
				form = new PackstueckForm(request);
				Packstueck packstueck = new Packstueck();
				form.validate(packstueck, request);
				DaoHelper.getPackstueckManager().saveOrUpdatePackstueck(packstueck);
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
		String contentpage;
		contentpage = "packstueckliste";
		
		if (request.getParameter("id") != null)
		{
			int id = Integer.valueOf(request.getParameter("id"));
			Packstueck packstueck = DaoHelper.getPackstueckManager().deletePackstueckById(id);
		}
		
		return contentpage;
	}

}
