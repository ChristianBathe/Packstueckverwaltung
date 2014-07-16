package packstueckverwaltung.model;

public class Lagerwegedaten
{
	private int id = Constants.NEW_DATA_SET_ID; // standardwert bei Neuanlage, wird bei Update/Insert Statement geprüft
	private String barcode;
	private String kartonid;
	private String wegeid;// Richtungscode wohin das Packstück geschleust werden soll
	private String erstellungsdatum;
	private String letztesupdate;
	private String wegpunkt; // Punkt/Station im Lager, auf den sich die Weginformation bezieht
	private int sapgebucht; // Kennzeichnung, ob ein Packstück den Wegpunkt passiert hat

	public String getAllFieldsToString()
	{
		return id + ";" + barcode + ";" + kartonid + ";" + wegeid + ";" + ";" + wegpunkt + ";" + erstellungsdatum + ";"
				+ letztesupdate + ";" + sapgebucht + ";";
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getBarcode()
	{
		return barcode;
	}

	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}

	public String getKartonid()
	{
		return kartonid;
	}

	public void setKartonid(String kartonid)
	{
		this.kartonid = kartonid;
	}

	public String getWegeid()
	{
		return wegeid;
	}

	public void setWegeid(String wegeid)
	{
		this.wegeid = wegeid;
	}

	public String getErstellungsdatum()
	{
		return erstellungsdatum;
	}

	public void setErstellungsdatum(String erstellungsdatum)
	{
		this.erstellungsdatum = erstellungsdatum;
	}

	public String getLetztesupdate()
	{
		return letztesupdate;
	}

	public void setLetztesupdate(String letztesupdate)
	{
		this.letztesupdate = letztesupdate;
	}

	public String getWegpunkt()
	{
		return wegpunkt;
	}

	public void setWegpunkt(String wegpunkt)
	{
		this.wegpunkt = wegpunkt;
	}

	public int getSapgebucht()
	{
		return sapgebucht;
	}

	public void setSapgebucht(int sapgebucht)
	{
		this.sapgebucht = sapgebucht;
	}
}
