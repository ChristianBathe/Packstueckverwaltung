package packstueckverwaltung.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import packstueckverwaltung.model.Constants;
import packstueckverwaltung.model.Lagerwegdaten;
import packstueckverwaltung.model.Packstueck;

public class JdbcPackstueckManager implements IPackstueckManager
{
	private Packstueck loadPackstueckFromResultSet(ResultSet resultSet) throws SQLException
	{
		try
		{
			Packstueck packstueck = new Packstueck();
			packstueck.setId(resultSet.getInt("lfd_nr_wa_daten"));
			packstueck.setBarcode(resultSet.getString("BD_Barcode"));
			packstueck.setKartonid(resultSet.getString("KartonID"));
			packstueck.setLagernummer(resultSet.getString("Lagernummer"));
			packstueck.setMandant(resultSet.getString("Mandant"));

			packstueck.setMaximalgewicht(resultSet.getString("Maxgewicht"));
			packstueck.setMinimalgewicht(resultSet.getString("Mingewicht"));

			packstueck.setTransportauftragsnummer(resultSet.getString("TA_Nummer"));
			packstueck.setVersandart(resultSet.getString("Versandart"));
			packstueck.setLetztesupdate(resultSet.getString("Mod_Time"));
			packstueck.setErstellungsdatum(resultSet.getString("Def_Time"));

			packstueck.setIstgewicht(resultSet.getString("Ist_Gewicht"));

			packstueck.setBearbeitungsstation(resultSet.getString("BDC_Nummer")); // PC, an dem das Packstück zuletzt bearbeitet wurde.
			packstueck.setScandatum(resultSet.getString("Scandatum"));
			packstueck.setSapgebucht(resultSet.getInt("SAP_Gemeldet"));
			packstueck.setBuchungsdatum(resultSet.getString("SAP_Gemeldet_Datum"));
			packstueck.setLieferscheinnummer(resultSet.getString("Vertriebsbelegnummer"));

			return packstueck;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private Lagerwegdaten loadLagerwegdatenFromResultSet(ResultSet resultSet) throws SQLException
	{
		try
		{
			Lagerwegdaten lagerwegdaten = new Lagerwegdaten();

			lagerwegdaten.setId(resultSet.getInt("lfd_nr_wege_daten"));
			lagerwegdaten.setBarcode(resultSet.getString("BD_Barcode"));
			lagerwegdaten.setKartonid(resultSet.getString("KartonID"));
			lagerwegdaten.setLaufendenummer(resultSet.getString("LFD_Nr"));
			lagerwegdaten.setWegpunkt(resultSet.getString("Device_Ist"));
			lagerwegdaten.setWegeId(resultSet.getString("Wege_ID"));
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

	@Override
	public ArrayList<Packstueck> getAllPackstuecke()
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ArrayList<Packstueck> packstuecke = new ArrayList<Packstueck>();

			String statement = "select * from ZARM_OUTBOUND";

			ResultSet resultSet = c.createStatement().executeQuery(statement);

			if (resultSet != null)
			{
				while (resultSet.next())
				{
					packstuecke.add(loadPackstueckFromResultSet(resultSet));
				}
			}

			return packstuecke;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Packstueck getPackstückByBarcode(String barcode)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ResultSet packstueckRs = c.createStatement().executeQuery(
					"select * from ZARM_OUTBOUND where bd_barcode = '" + barcode + "'");

			if (packstueckRs != null && packstueckRs.next())
			{
				return loadPackstueckFromResultSet(packstueckRs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Lagerwegdaten> getLagerwegdatenByBarcode(String barcode)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ArrayList<Lagerwegdaten> lagerwegdaten = new ArrayList<Lagerwegdaten>();

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
	public ArrayList<Lagerwegdaten> getAllLagerwegdaten()
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ArrayList<Lagerwegdaten> lagerwegdaten = new ArrayList<Lagerwegdaten>();

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
	public void saveOrUpdatePackstück(Packstueck packstueck)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			getPackstueckInsertUpdateStatement(packstueck, c).executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void saveOrUpdateWegedaten(Lagerwegdaten lagerwegdaten)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Packstueck deletePackstückById(int id)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			Packstueck packstueck = getPackstückById(id);

			if (packstueck != null)
			{
				String statement = "delete from ZARM_OUTBOUND where lfd_nr_wa_daten =" + id;
				c.createStatement().executeQuery(statement);
			}
			return packstueck;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Lagerwegdaten deleteLagerwegdatenById(int id)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			Lagerwegdaten lagerwegdaten = getLagerwegdatenById(id);

			if (lagerwegdaten != null)
			{
				String statement = "delete from ZARM_ROUTING where lfd_nr_wa_daten =" + id;
				c.createStatement().executeQuery(statement);
			}
			return lagerwegdaten;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private Packstueck getPackstückById(int id)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ResultSet packstueckRs = c.createStatement().executeQuery(
					"select * from ZARM_OUTBOUND where lfd_nr_wa_daten = '" + id + "'");

			if (packstueckRs != null && packstueckRs.next())
			{
				return loadPackstueckFromResultSet(packstueckRs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private Lagerwegdaten getLagerwegdatenById(int id)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			Lagerwegdaten lagerwegdaten = new Lagerwegdaten();

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

	private PreparedStatement getPackstueckInsertUpdateStatement(Packstueck packstueck, Connection c)
			throws SQLException
	{
		PreparedStatement pst;

		// Packstueck noch nicht in DB vorhanden
		if (packstueck.getId() == Constants.NEW_DATA_SET_ID)
		{
			pst = c.prepareStatement("INSERT INTO ZARM_OUTBOUND "
					+ "(BD_Barcode,Lagernummer,Mandant ,Maxgewicht,Mingewicht ,TA_Nummer,Tracking_Nummer,Versandart,Mod_Time,Def_Time,Ist_Gewicht,BDC_Nummer ,Scandatum ,SAP_Gemeldet,SAP_Gemeldet_Datum,Vertriebsbelegnummer,KartonID) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)");
		}
		// Nur manuell angelegt Packstücke dürfen komplett editiert werden!
		if (packstueck.isManuellAngelegt())
		{
			pst = c.prepareStatement("UPDATE ZARM_OUTBOUND SET BD_Barcode=?,Lagernummer=?,Mandant=?,Maxgewicht=?,Mingewicht=?,TA_Nummer=?,Tracking_Nummer=?,Versandart=?,Mod_Time=?,Def_Time=?,Ist_Gewicht=?,BDC_Nummer=?,Scandatum=?,SAP_Gemeldet=?,SAP_Gemeldet_Datum=?,Vertriebsbelegnummer=?,KartonID=? WHERE id=?");
		}
		else
		{
			pst = c.prepareStatement("UPDATE ZARM_OUTBOUND SET Maxgewicht=?,Mingewicht=?,Versandart=?,Mod_Time=?,Ist_Gewicht=?,SAP_Gemeldet=?,KartonID=? WHERE id=?");
		}

		pst = fillPackstueckPrepareStatementParameter(pst, packstueck);

		return pst;
	}

	private PreparedStatement fillPackstueckPrepareStatementParameter(PreparedStatement pst, Packstueck packstueck)
			throws SQLException
	{
		int counter = 1;
		if (packstueck.isManuellAngelegt())
		{
			pst.setString(counter++, packstueck.getBarcode());
			pst.setString(counter++, packstueck.getLagernummer());
			pst.setString(counter++, packstueck.getMandant());
			pst.setString(counter++, packstueck.getMaximalgewicht());
			pst.setString(counter++, packstueck.getMinimalgewicht());
			pst.setString(counter++, packstueck.getTransportauftragsnummer());
			pst.setString(counter++, packstueck.getVersandart());
			pst.setString(counter++, new Date().toString()); // letztes Update
			pst.setString(counter++, packstueck.getErstellungsdatum());
			pst.setString(counter++, packstueck.getIstgewicht());
			pst.setString(counter++, packstueck.getBearbeitungsstation());
			pst.setString(counter++, packstueck.getScandatum());
			pst.setInt(counter++, packstueck.getSapgebucht());
			pst.setString(counter++, packstueck.getBuchungsdatum());
			pst.setString(counter++, packstueck.getLieferscheinnummer());
			pst.setString(counter++, packstueck.getKartonid());
		}
		else
		{
			pst.setString(counter++, packstueck.getMaximalgewicht());
			pst.setString(counter++, packstueck.getMinimalgewicht());
			pst.setString(counter++, packstueck.getVersandart());
			pst.setString(counter++, new Date().toString()); // letztes Update
			pst.setString(counter++, packstueck.getIstgewicht());
			pst.setInt(counter++, packstueck.getSapgebucht());
			pst.setString(counter++, packstueck.getKartonid());
		}

		return pst;
	}
}
