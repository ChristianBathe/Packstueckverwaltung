package packstueckverwaltung.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import packstueckverwaltung.model.Constants;
import packstueckverwaltung.model.Lagerwegedaten;

public class JdbcLagerwegedatenManager implements ILagerwegedatenManager
{

	private Lagerwegedaten loadLagerwegdatenFromResultSet(ResultSet resultSet) throws SQLException
	{
		try
		{
			Lagerwegedaten lagerwegdaten = new Lagerwegedaten();

			lagerwegdaten.setId(resultSet.getInt("lfd_nr_wege_daten"));
			lagerwegdaten.setBarcode(resultSet.getString("BD_Barcode"));
			lagerwegdaten.setKartonid(resultSet.getString("KartonID"));
			lagerwegdaten.setWegpunkt(resultSet.getString("Device_Ist"));
			lagerwegdaten.setWegeid(resultSet.getString("Wege_ID"));
			lagerwegdaten.setErstellungsdatum(resultSet.getString("Def_Time"));
			lagerwegdaten.setLetztesupdate(resultSet.getString("Mod_Time"));
			lagerwegdaten.setSapgebucht(resultSet.getInt("SAP_Gemeldet"));

			return lagerwegdaten;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private Lagerwegedaten getLagerwegdatenById(int id)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			Lagerwegedaten lagerwegdaten = new Lagerwegedaten();

			ResultSet resultSet = c.createStatement().executeQuery(
					"select * from ZARM_ROUTING where lfd_nr_wege_daten = '" + id + "'");

			if (resultSet != null && resultSet.next())
			{
				return loadLagerwegdatenFromResultSet(resultSet);
			}

			return lagerwegdaten;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement getLagerwegedatenInsertUpdateStatement(Lagerwegedaten lagerwegedaten, Connection c)
			throws SQLException
	{
		PreparedStatement pst;

		// Lagerwegdaten noch nicht in DB vorhanden
		if (lagerwegedaten.getId() == Constants.NEW_DATA_SET_ID)
		{
			pst = c.prepareStatement("INSERT INTO ZARM_ROUTING "
					+ "(BD_Barcode,Device_Ist,Wege_ID,Mod_Time,SAP_Gemeldet,KartonID,Def_Time) "
					+ "VALUES (?,?,?,?,?,?,?)");
		}
		else
		{
			pst = c.prepareStatement("UPDATE ZARM_ROUTING SET BD_Barcode=?,Device_Ist=?,Wege_ID=?,Mod_Time=?,SAP_Gemeldet=?,KartonID=? WHERE lfd_nr_wege_daten=?");
		}
		pst = getLagerwegedatenPrepareStatement(pst, lagerwegedaten);

		return pst;
	}

	private PreparedStatement getLagerwegedatenPrepareStatement(PreparedStatement pst, Lagerwegedaten lagerwegedaten)
			throws SQLException
	{
		int counter = 1;
		pst.setString(counter++, lagerwegedaten.getBarcode());
		pst.setString(counter++, lagerwegedaten.getWegpunkt());
		pst.setString(counter++, lagerwegedaten.getWegeid());

		Date updateDate = new Date(System.currentTimeMillis());
		pst.setTimestamp(counter++, new java.sql.Timestamp(updateDate.getTime())); // letztes Update

		pst.setInt(counter++, lagerwegedaten.getSapgebucht());
		pst.setString(counter++, lagerwegedaten.getKartonid());

		// Neuer Datensatz
		if (lagerwegedaten.getId() == Constants.NEW_DATA_SET_ID)
		{
			// Erstellungsdatum hinzufügen
			Date createDate = new Date(System.currentTimeMillis());
			pst.setTimestamp(counter++, new java.sql.Timestamp(createDate.getTime()));
		}
		else
		{
			// ID-Parameter für WHERE Klausel setzen
			pst.setInt(counter++, lagerwegedaten.getId());
		}

		return pst;
	}

	@Override
	public Lagerwegedaten getLagerwegedatenById(int id)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ResultSet rs = c.createStatement().executeQuery(
					"select * from ZARM_ROUTING where lfd_nr_wege_daten = '" + id + "'");

			if (rs != null && rs.next())
			{
				return loadLagerwegdatenFromResultSet(rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Lagerwegedaten> getLagerwegedatenByBarcode(String barcode)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ArrayList<Lagerwegedaten> lagerwegdaten = new ArrayList<Lagerwegedaten>();

			ResultSet resultSet = c.createStatement().executeQuery(
					"select * from ZARM_ROUTING where bd_barcode = '" + barcode + "'");

			if (resultSet != null)
			{
				while (resultSet.next())
				{
					lagerwegdaten.add(loadLagerwegdatenFromResultSet(resultSet));
				}
			}

			return lagerwegdaten;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Lagerwegedaten> getAllLagerwegedaten()
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ArrayList<Lagerwegedaten> lagerwegdaten = new ArrayList<Lagerwegedaten>();

			ResultSet resultSet = c.createStatement().executeQuery("select * from ZARM_ROUTING");

			if (resultSet != null)
			{
				while (resultSet.next())
				{
					lagerwegdaten.add(loadLagerwegdatenFromResultSet(resultSet));
				}
			}

			return lagerwegdaten;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean saveOrUpdateLagerwegedaten(Lagerwegedaten lagerwegedaten)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			getLagerwegedatenInsertUpdateStatement(lagerwegedaten, c).executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Lagerwegedaten deleteLagerwegedatenById(int id)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			Lagerwegedaten lagerwegdaten = getLagerwegdatenById(id);

			if (lagerwegdaten != null)
			{
				String deleteSQL = "delete from ZARM_ROUTING where lfd_nr_wege_daten = ?";

				PreparedStatement preparedStatement = c.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
				c.commit();
			}
			return lagerwegdaten;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
