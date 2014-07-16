package packstueckverwaltung.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import packstueckverwaltung.model.Report;

public class JdbcReportManager implements IReportManager
{
	private Report loadReportFromResultSet(ResultSet resultSet) throws SQLException
	{
		try
		{
			Report report = new Report();

			report.setId(resultSet.getInt("ID"));
			report.setUseremail(resultSet.getString("UserEmail"));
			report.setDatum(resultSet.getString("Datum"));
			report.setAltedaten(resultSet.getString("AlteDaten"));
			report.setNeuedaten(resultSet.getString("NeueDaten"));
			report.setBarcode(resultSet.getString("Barcode"));

			return report;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Report> getAllReport()
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ArrayList<Report> reportListe = new ArrayList<Report>();

			String statement = "select * from ArmadaReporting";

			ResultSet resultSet = c.createStatement().executeQuery(statement);

			if (resultSet != null)
			{
				while (resultSet.next())
				{
					reportListe.add(loadReportFromResultSet(resultSet));
				}
			}

			return reportListe;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Report> getReportByBarcode(String barcode)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			ArrayList<Report> reportListe = new ArrayList<Report>();

			String statement = "select * from ArmadaReporting where Barcode ='" + barcode + "'";

			ResultSet resultSet = c.createStatement().executeQuery(statement);

			if (resultSet != null)
			{
				while (resultSet.next())
				{
					reportListe.add(loadReportFromResultSet(resultSet));
				}
			}

			return reportListe;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertReport(String userEmail, String alteDaten, String neueDaten, String barcode)
	{
		try (Connection c = DatabaseHelper.getInstance();)
		{
			PreparedStatement pst = c.prepareStatement("INSERT INTO ArmadaReporting "
					+ "(UserEmail,Datum,AlteDaten,NeueDaten,Barcode) " + "VALUES (?,?,?,?,?)");

			int counter = 1;
			pst.setString(counter++, userEmail);
			pst.setString(counter++, new Date().toLocaleString());
			pst.setString(counter++, alteDaten);
			pst.setString(counter++, neueDaten);
			pst.setString(counter++, barcode);

			pst.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
