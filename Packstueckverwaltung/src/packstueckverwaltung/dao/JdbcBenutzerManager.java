package packstueckverwaltung.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packstueckverwaltung.model.Benutzer;
import packstueckverwaltung.model.Berechtigung;

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

	private Berechtigung loadBerechtigungFromResultSet(ResultSet rs) throws SQLException
	{
		Berechtigung berechtigung = new Berechtigung();

		berechtigung.setId(rs.getInt("id"));
		berechtigung.setBerechtigung(rs.getString("Berechtigung"));

		return berechtigung;
	}

	@Override
	public Benutzer getBenutzerByEmail(String email)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ResultSet rs = c.createStatement().executeQuery(
					"select * from ArmadaUser where Email = " + "'" + email + "'");

			if (rs != null && rs.next())
			{
				return loadPersonFromResultSet(rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Berechtigung> getBenutzerRechte(int id)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ArrayList<Berechtigung> berechtigungen = new ArrayList<Berechtigung>();

			// Berechtigungen werden über einen Join auf die ArmadaUserBerechtigung Tabelle ermittelt
			String statement = "select ab.ID, ab.Beschreibung, ab.Berechtigung " + "from ArmadaBerechtigung as ab "
					+ "inner join ArmadaUserBerechtigung as aub " + "on aub.ArmadaBerechtigungID = ab.ID "
					+ "where aub.ArmadaUserID = " + id;

			ResultSet rs = c.createStatement().executeQuery(statement);

			if (rs != null)
			{
				while (rs.next())
				{
					berechtigungen.add(loadBerechtigungFromResultSet(rs));
				}
			}

			return berechtigungen;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
