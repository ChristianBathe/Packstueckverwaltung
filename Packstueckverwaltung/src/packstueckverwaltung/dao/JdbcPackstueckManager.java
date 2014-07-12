package packstueckverwaltung.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import packstueckverwaltung.model.Lagerwegdaten;
import packstueckverwaltung.model.Packstueck;

public class JdbcPackstueckManager implements IPackstueckManager
{
	private Packstueck loadPackstueckFromResultSet(ResultSet rs) throws SQLException
	{
		Packstueck packstueck = new Packstueck();
		packstueck.setId(rs.getInt("lfd_nr_wa_daten"));
		packstueck.setBarcode(rs.getString("BD_Barcode"));
		packstueck.setKartonid(rs.getString("KartonID"));
		packstueck.setLagernummer(rs.getString("Lagernummer"));
		packstueck.setMandant(rs.getString("Mandant"));
		packstueck.setMaximalgewicht(rs.getDouble("Maxgewicht"));
		packstueck.setMinimalgewicht(rs.getDouble("Mingewicht"));
		packstueck.setTransportauftragsnummer(rs.getString("TA_Nummer"));
		packstueck.setTrackingnummer(rs.getString("Tracking_Nummer"));
		packstueck.setVersandart(rs.getString("Versandart"));
		packstueck.setLetztesupdate(rs.getString("Mod_Time"));
		packstueck.setErstellungsdatum(rs.getString("Def_Time"));
		packstueck.setIstgewicht(rs.getDouble("Ist_Gewicht"));
		packstueck.setBarcode(rs.getString("Abweichungsgrund"));
		packstueck.setBearbeitungsstation(rs.getString("BDC_Nummer")); //PC, an dem das Packstück zuletzt bearbeitet wurde.
		packstueck.setScandatum(rs.getString("Scandatum"));
		packstueck.setSapgebucht(rs.getInt("SAP_Gemeldet"));
		packstueck.setBuchungsdatum(rs.getString("SAP_Gemeldet_Datum"));
		packstueck.setLieferscheinnummer(rs.getString("Vertriebsbelegnummer"));

		return packstueck;
	}

	private Lagerwegdaten loadLagerwegdatenFromResultSet(ResultSet resultSet) throws SQLException
	{
		Lagerwegdaten lagerwegdaten = new Lagerwegdaten();

		return lagerwegdaten;
	}

	@Override
	public ArrayList<Packstueck> getAllPackstücke()
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ArrayList<Packstueck> packstuecke = new ArrayList<Packstueck>();

			String statement = "select * from ZARM_OUTBOUND as out "
								+ "join ZARM_PRINT as print on out.bd_barcode = print.bd_barcode "
								+ "join ZARM_ROUTING as rout on out.bd_barcode = rout.bd_barcode";

			ResultSet rs = c.createStatement().executeQuery(statement);

			if (rs != null && rs.next())
			{
				packstuecke.add(loadPackstueckFromResultSet(rs));
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
			ResultSet packstueckRs = c.createStatement().executeQuery( "select * from ZARM_OUTBOUND where bd_barcode = '" + barcode + "'");

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
			ResultSet rs = c.createStatement().executeQuery( "select * from ZARM_ROUTING where bd_barcode = '" + barcode + "'");

			if (rs != null && rs.next())
			{
				//return loadDruckdatenFromResultSet(rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Packstueck deletePackstückByBarcode(String barcode)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			String statement = "delete from ZARM_OUTBOUND where bd_barcode = '" + barcode + "'";								

			ResultSet rs = c.createStatement().executeQuery(statement);

			if (rs != null && rs.next())
			{
				Packstueck packstueck = loadPackstueckFromResultSet(rs);
				
				
				
				return packstueck;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveOrUpdatePackstück(Packstueck packstück)
	{
		// TODO Auto-generated method stub

	}
}
