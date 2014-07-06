package packstueckverwaltung.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import packstueckverwaltung.model.Benutzer;

public class JdbcBenutzerManager implements IBenutzerManager
{

	private Benutzer loadPersonFromResultSet(ResultSet rs) throws SQLException
	{
		Benutzer benutzer = new Benutzer();
		benutzer.setId(rs.getInt("id"));
		benutzer.setVorname(rs.getString("Vorname"));
		benutzer.setNachname(rs.getString("Nachname"));
		benutzer.setEmail(rs.getString("Email"));
		benutzer.setPasswort(rs.getString("Passwort"));

		return benutzer;
	}

	@Override
	public Benutzer getBenutzerByEmail(String email)
	{
		try (Connection c = DatabaseHelper.instance;)
		{
			ResultSet rs = c.createStatement().executeQuery("select * from ArmadaUser where Email = " + "'" + email + "'");
			
			if (rs != null && rs.next())
			{
				return loadPersonFromResultSet(rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			// eventuell E-Mail Versand des Fehlers, kann aber auch fehlschlagen
		}
		return null;
	}

}
