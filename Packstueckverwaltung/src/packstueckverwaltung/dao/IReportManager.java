package packstueckverwaltung.dao;

import java.util.ArrayList;

import packstueckverwaltung.model.Report;

public interface IReportManager
{
	public ArrayList<Report> getAllReport();

	public ArrayList<Report> getReportByBarcode(String barcode);

	public void insertReport(String userEmail, String alteDaten, String neueDaten, String barcode);
}
