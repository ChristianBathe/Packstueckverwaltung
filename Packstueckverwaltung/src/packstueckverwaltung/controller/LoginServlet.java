package packstueckverwaltung.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packstueckverwaltung.businesslogic.DaoHelper;
import packstueckverwaltung.model.Benutzer;
import packstueckverwaltung.model.Berechtigung;
import packstueckverwaltung.model.Constants;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		Benutzer nutzer = DaoHelper.getBenutzerManager().getBenutzerByEmail(request.getParameter("email"));

		// Prüfen, ob Daten gefunden wurden und falls ja, ob das eingegebene Passwort stimmt.
		if (nutzer != null && request.getParameter("passwort").equals(nutzer.getPasswort()))
		{
			// zugehörigen Rechte des Nutzers abfragen
			ArrayList<Berechtigung> berechtigungen = DaoHelper.getBenutzerManager().getBenutzerRechte(nutzer.getId());
			nutzer.setBerechtigungen(berechtigungen);

			// Rechte des Nutzer standardmäßig auf lesen beschränken --> schreibrecht = false
			request.getSession().setAttribute("schreibrecht", false);
			for (Berechtigung berechtigung : berechtigungen)
			{
				if (berechtigung.getBerechtigung().equals(Constants.SCHREIB_BERECHTIGUNG))
				{
					// Hat der Nutzer die entsprechende Berechtigung, darf er Daten anlegen, editieren und löschen.
					request.getSession().setAttribute("schreibrecht", true);
					break;
				}
			}

			request.getSession().setAttribute("session_person", nutzer);
			request.getSession().setAttribute("global_message",
					"Willkommen: " + nutzer.getVorname() + " " + nutzer.getNachname());

			// Fehlermeldung zurücksetzen
			request.getSession().setAttribute("global_error", "");
		}

		else
		{
			request.getSession().setAttribute("global_error", "E-Mail/Passwort Kombination nicht bekannt");
			// Nutzer aus der Session löschen, für den Fall, dass er vorher angemeldet war.
			request.getSession().setAttribute("session_person", null);
		}
		// Auf Startseite weiterleiten
		response.sendRedirect(request.getContextPath() + "/packstueckliste.html");
	}
}
