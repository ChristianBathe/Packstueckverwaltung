package packstueckverwaltung.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import packstueckverwaltung.model.Constants;
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

			packstueck.setBearbeitungsstation(resultSet.getString("BDC_Nummer")); // PC, an dem das Packstueck zuletzt
																					// bearbeitet wurde.
			packstueck.setScandatum(resultSet.getString("Scandatum"));
			packstueck.setSapgebucht(resultSet.getInt("SAP_Gemeldet"));
			packstueck.setBuchungsdatum(resultSet.getString("SAP_Gemeldet_Datum"));
			packstueck.setLieferscheinnummer(resultSet.getString("Vertriebsbelegnummer"));
			packstueck.setManuellAngelegt(resultSet.getBoolean("manuell_angelegt"));

			return packstueck;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private PreparedStatement getPackstueckInsertUpdateStatement(Packstueck packstueck, Connection c)
			throws SQLException
	{
		PreparedStatement pst;

		// Packstueck noch nicht in DB vorhanden
		if (packstueck.getId() == Constants.NEW_DATA_SET_ID)
		{
			pst = c.prepareStatement("INSERT INTO ZARM_OUTBOUND "
					+ "(BD_Barcode,Lagernummer,Mandant ,Maxgewicht,Mingewicht ,TA_Nummer,Versandart,Mod_Time,Def_Time,Ist_Gewicht,BDC_Nummer,SAP_Gemeldet,Vertriebsbelegnummer,KartonID, manuell_angelegt) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		}
		// Nur manuell angelegt Packstuecke duerfen komplett editiert werden!
		else if (packstueck.isManuellAngelegt())
		{
			pst = c.prepareStatement("UPDATE ZARM_OUTBOUND SET BD_Barcode=?,Lagernummer=?,Mandant=?,Maxgewicht=?,Mingewicht=?,TA_Nummer=?,Versandart=?,Mod_Time=?,Ist_Gewicht=?,BDC_Nummer=?,SAP_Gemeldet=?,Vertriebsbelegnummer=?,KartonID=? WHERE lfd_nr_wa_daten=?");
		}
		else
		{
			pst = c.prepareStatement("UPDATE ZARM_OUTBOUND SET Maxgewicht=?,Mingewicht=?,Versandart=?,Mod_Time=?,Ist_Gewicht=?,SAP_Gemeldet=?,KartonID=? WHERE lfd_nr_wa_daten=?");
		}

		pst = fillPackstueckPrepareStatementParameter(pst, packstueck);

		return pst;
	}

	private PreparedStatement fillPackstueckPrepareStatementParameter(PreparedStatement pst, Packstueck packstueck)
			throws SQLException
	{
		int counter = 1;

		if (packstueck.getId() == Constants.NEW_DATA_SET_ID)
		{
			// TODO erstellungszeiten usw. fuellen.
			pst.setString(counter++, packstueck.getBarcode());
			pst.setString(counter++, packstueck.getLagernummer());
			pst.setString(counter++, packstueck.getMandant());
			pst.setString(counter++, packstueck.getMaximalgewicht());
			pst.setString(counter++, packstueck.getMinimalgewicht());
			pst.setString(counter++, packstueck.getTransportauftragsnummer());
			pst.setString(counter++, packstueck.getVersandart());
			Date createdDate = new Date(System.currentTimeMillis());
			pst.setTimestamp(counter++, new java.sql.Timestamp(createdDate.getTime())); // letztes Update
			pst.setTimestamp(counter++, new java.sql.Timestamp(createdDate.getTime())); // Erstellungsdatum

			pst.setString(counter++, packstueck.getIstgewicht());
			pst.setString(counter++, packstueck.getBearbeitungsstation());
			pst.setInt(counter++, packstueck.getSapgebucht());
			pst.setString(counter++, packstueck.getLieferscheinnummer());
			pst.setString(counter++, packstueck.getKartonid());
			pst.setBoolean(counter++, true); // manuell angelegt
		}
		else if (packstueck.isManuellAngelegt())
		{
			pst.setString(counter++, packstueck.getBarcode());
			pst.setString(counter++, packstueck.getLagernummer());
			pst.setString(counter++, packstueck.getMandant());
			pst.setString(counter++, packstueck.getMaximalgewicht());
			pst.setString(counter++, packstueck.getMinimalgewicht());
			pst.setString(counter++, packstueck.getTransportauftragsnummer());
			pst.setString(counter++, packstueck.getVersandart());

			Date updateDate = new Date(System.currentTimeMillis());
			pst.setTimestamp(counter++, new java.sql.Timestamp(updateDate.getTime())); // letztes Update
			pst.setString(counter++, packstueck.getIstgewicht());
			pst.setString(counter++, packstueck.getBearbeitungsstation());
			pst.setInt(counter++, packstueck.getSapgebucht());
			pst.setString(counter++, packstueck.getLieferscheinnummer());
			pst.setString(counter++, packstueck.getKartonid());
			pst.setInt(counter++, packstueck.getId());
		}
		else
		{
			pst.setString(counter++, packstueck.getMaximalgewicht());
			pst.setString(counter++, packstueck.getMinimalgewicht());
			pst.setString(counter++, packstueck.getVersandart());

			Date updateDate = new Date(System.currentTimeMillis());
			pst.setTimestamp(counter++, new java.sql.Timestamp(updateDate.getTime())); // letztes Update

			pst.setString(counter++, packstueck.getIstgewicht());
			pst.setInt(counter++, packstueck.getSapgebucht());
			pst.setString(counter++, packstueck.getKartonid());
			// ID-Parameter für WHERE Klausel setzen
			pst.setInt(counter++, packstueck.getId());
		}

		return pst;
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
	public Packstueck getPackstueckById(int id)
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

	@Override
	public ArrayList<Packstueck> getPackstueckByBarcode(String barcode)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ArrayList<Packstueck> packstuecke = new ArrayList<Packstueck>();

			ResultSet packstueckRs = c.createStatement().executeQuery(
					"select * from ZARM_OUTBOUND where bd_barcode = '" + barcode + "'");

			if (packstueckRs != null)
			{
				while (packstueckRs.next())
				{
					packstuecke.add(loadPackstueckFromResultSet(packstueckRs));
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
	public boolean saveOrUpdatePackstueck(Packstueck packstueck)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			getPackstueckInsertUpdateStatement(packstueck, c).executeUpdate();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Packstueck deletePackstueckById(int id)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			Packstueck packstueck = getPackstueckById(id);

			if (packstueck != null)
			{
				String deleteSQL = "delete from ZARM_OUTBOUND where lfd_nr_wa_daten = ?";

				PreparedStatement preparedStatement = c.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
				c.commit();

			}
			return packstueck;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
