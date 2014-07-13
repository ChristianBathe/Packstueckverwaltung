package packstueckverwaltung.model;

public class Gebucht
{
	static int ungebucht = 0;
	static int dbgebucht = 1;
	static int sapgebucht = 2;
	
	public static int[] values()
	{
		return new int[]{ ungebucht, dbgebucht, sapgebucht};
	}

}
