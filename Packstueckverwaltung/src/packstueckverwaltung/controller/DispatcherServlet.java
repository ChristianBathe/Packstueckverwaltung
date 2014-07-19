package packstueckverwaltung.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packstueckverwaltung.businesslogic.LagerwegedatenHelper;
import packstueckverwaltung.businesslogic.PackstueckHelper;
import packstueckverwaltung.businesslogic.ReportHelper;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.html")
public class DispatcherServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String contentpage = null;
		String servletPath = request.getServletPath();

		// Mögliche Info- und Fehlernachrichten zurücksetzen
		request.getSession().setAttribute("global_message", "");
		request.getSession().setAttribute("global_error", "");

		switch (servletPath)
		{
			case "/login.html":
				contentpage = "login";
				break;
			case "/index.html":
				contentpage = "index";
				break;
			case "/packstueckliste.html":
				contentpage = PackstueckHelper.packstuecklisteladen(request);
				break;
			case "/updatepackstueck.html":
				contentpage = PackstueckHelper.updatePackstueck(request);
				break;
			case "/deletepackstueck.html":
				contentpage = PackstueckHelper.deletePackstueck(request);
				break;
			case "/lagerwegedatenliste.html":
				contentpage = LagerwegedatenHelper.lagerwegedatenlisteLaden(request);
				break;
			case "/updatelagerwegedaten.html":
				contentpage = LagerwegedatenHelper.updateLagerwegedaten(request);
				break;
			case "/deletelagerwegedaten.html":
				contentpage = LagerwegedatenHelper.deleteLagerwegedaten(request);
				break;
			case "/reportliste.html":
				contentpage = ReportHelper.reportlisteLaden(request);
				break;
			default:
				break;
		}
		if (contentpage != null)
		{
			request.setAttribute("contentpage", contentpage);

			// x-requested-with signalisiert, ob es sich um einen AJAX Aufruf handelt. Dieser Parameter wird automatisch
			// bei AJAX-Aufrufen über JQuery gesetz.
			String requestType = request.getHeader("x-requested-with");

			// Der Wert des Parameters ist bei AJAX-Aufrufen immer "XMLHttpRequest"
			if (!"XMLHttpRequest".equals(requestType))
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
			// Fehlermeldung, dass die Seite noch nicht implementiert wurde
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

}
