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
		// TODO Auto-generated method stub
	}

	private String packstuecklisteladen(HttpServletRequest request)
	{
		String contentpage;
		contentpage = "packstueckliste";

		request.setAttribute("packstueckliste", DaoHelper.getPackstueckManager().getAllPackstuecke());
		return contentpage;
	}

	private String updatePackstueck(HttpServletRequest request)
	{
		String contentpage;
		contentpage = "updatepackstueck";
		PackstueckForm form = null;
		if (request.getParameter("id") != null)
		{
			if (request.getParameter("action") == null)
			{
				// editieren starten
				String barcode = request.getParameter("barcode");
				form = new PackstueckForm(DaoHelper.getPackstueckManager().getPackstückByBarcode(barcode), request);
			}
			else
			{
				// neue oder bestehende Person abspeichern
				form = new PackstueckForm(request);
				Packstueck p = new Packstueck();
				//form.validate(p, request);
				DaoHelper.getPackstueckManager().saveOrUpdatePackstück(p);
			}
		}
		else
		{
			// neue Person starten
			form = new PackstueckForm();
		}
		request.setAttribute("form", form);
		return contentpage;
	}

}
