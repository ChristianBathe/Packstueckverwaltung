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
		// packstueck.setId(rs.getInt("id"));
		// packstueck.setEmail(rs.getString("email"));
		// packstueck.setFoto(rs.getBytes("foto"));
		// packstueck.setGeburtsdatum(rs.getDate("geburtsdatum"));
		// String g = rs.getString("geschlecht");

		return benutzer;
	}

	@Override
	public Benutzer getBenutzerByEmail(String email)
	{
		try (Connection c = DatabaseHelper.instance;)
		{
			ResultSet rs = c.createStatement().executeQuery("select * from ArmadaUser where Name = " + email);
			
			if (rs.next())
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
