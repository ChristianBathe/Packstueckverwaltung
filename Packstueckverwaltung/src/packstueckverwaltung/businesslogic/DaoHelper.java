package packstueckverwaltung.businesslogic;

import packstueckverwaltung.dao.IBenutzerManager;
import packstueckverwaltung.dao.IPackstueckManager;
import packstueckverwaltung.dao.JdbcBenutzerManager;
import packstueckverwaltung.dao.JdbcPackstueckManager;

public class DaoHelper
{
	private static IPackstueckManager packstueckManager;
	private static IBenutzerManager benutzerManager;
	
	public static IPackstueckManager getPackstueckManager()
	{
		if(packstueckManager == null)
		{
			packstueckManager = new JdbcPackstueckManager();
		}
		return packstueckManager;
	}
	
	public static IBenutzerManager getBenutzerManager()
	{
		if(benutzerManager == null)
		{
			benutzerManager = new JdbcBenutzerManager();
		}
		return benutzerManager;
	}
}
