package packstueckverwaltung.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packstueckverwaltung.businesslogic.DaoHelper;
import packstueckverwaltung.dao.DatabaseHelper;
import packstueckverwaltung.dao.IBenutzerManager;
import packstueckverwaltung.dao.JdbcBenutzerManager;
import packstueckverwaltung.model.Benutzer;
import packstueckverwaltung.model.Berechtigung;
import packstueckverwaltung.model.Packstueck;

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

		//Pr�fen, ob Daten gefunden wurden und falls ja, ob das eingegebene Passwort stimmt				
		if (nutzer != null && request.getParameter("passwort").equals(nutzer.getPasswort()))
		{
			//zugeh�rigen Rechte des Nutzers abfragen
			nutzer.setBerechtigungen(DaoHelper.getBenutzerManager().getBenutzerRechte(nutzer.getId()));
			
			request.getSession().setAttribute("session_person", nutzer);
			request.getSession().setAttribute("global_message", "Willkommen: " + nutzer.getVorname() + " " + nutzer.getNachname());
			
			//Auf Startseite weiterleiten
			response.sendRedirect(request.getContextPath() + "/index.html");
		}
		else
		{
			request.getSession().setAttribute("global_error", "E-Mail/Passwort Kombination nicht bekannt");
			request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
		}
	}
}
