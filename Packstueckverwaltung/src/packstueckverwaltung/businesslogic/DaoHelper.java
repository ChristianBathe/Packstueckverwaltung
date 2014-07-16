package packstueckverwaltung.businesslogic;

import packstueckverwaltung.dao.IBenutzerManager;
import packstueckverwaltung.dao.IPackstueckManager;
import packstueckverwaltung.dao.IReportManager;
import packstueckverwaltung.dao.JdbcBenutzerManager;
import packstueckverwaltung.dao.JdbcPackstueckManager;
import packstueckverwaltung.dao.JdbcReportManager;

public class DaoHelper
{
	private static IPackstueckManager packstueckManager;
	private static IBenutzerManager benutzerManager;
	private static IReportManager reportManager;

	public static IPackstueckManager getPackstueckManager()
	{
		if (packstueckManager == null)
		{
			packstueckManager = new JdbcPackstueckManager();
		}
		return packstueckManager;
	}

	public static IBenutzerManager getBenutzerManager()
	{
		if (benutzerManager == null)
		{
			benutzerManager = new JdbcBenutzerManager();
		}
		return benutzerManager;
	}

	public static IReportManager getReportManager()
	{
		if (reportManager == null)
		{
			reportManager = new JdbcReportManager();
		}
		return reportManager;
	}
}
