package packstueckverwaltung.model;

public class Packstueck
{
	private int id = Constants.NEW_DATA_SET_ID; // standardwert bei Neuanlage, wird bei Update/Insert Statement gepr�ft
	private String barcode;
	private String kartonid;
	private String lagernummer;
	private String mandant;
	private String istgewicht;
	private String minimalgewicht;
	private String maximalgewicht;
	private String lieferscheinnummer;
	private String transportauftragsnummer;
	private String versandart;
	private String erstellungsdatum;
	private String letztesupdate;
	private String bearbeitungsstation; // PC, an dem das Packst�ck zuletzt bearbeitet wurde.
	private String scandatum;
	private int sapgebucht; // Reale Buchung des Packst�ckes --> Es hat das Lager verlassen und wird als Warenabgang
							// erfasst.
	private String buchungsdatum;
	private boolean manuellAngelegt;

	public String getAllFieldsToString()
	{
		return id + ";" + barcode + ";" + kartonid + ";" + lagernummer + ";" + mandant + ";" + istgewicht + ";"
				+ minimalgewicht + ";" + maximalgewicht + ";" + lieferscheinnummer + ";" + transportauftragsnummer
				+ ";" + versandart + ";" + erstellungsdatum + ";" + letztesupdate + ";" + bearbeitungsstation + ";"
				+ scandatum + ";" + sapgebucht + ";";
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

	public String getLagernummer()
	{
		return lagernummer;
	}

	public void setLagernummer(String lagernummer)
	{
		this.lagernummer = lagernummer;
	}

	public String getMandant()
	{
		return mandant;
	}

	public void setMandant(String mandant)
	{
		this.mandant = mandant;
	}

	public String getIstgewicht()
	{
		// Umwandlung der Kommastellen n�tig, damit die Oberfl�che den Text als Number erkennt. Ansonsten bleiben die
		// Zahlenfelder leer.
		if (!istgewicht.equals(""))
			return istgewicht.replace(',', '.');
		else
			return "0";
	}

	public void setIstgewicht(String istgewicht)
	{
		this.istgewicht = istgewicht;
	}

	public String getMinimalgewicht()
	{
		// Umwandlung der Kommastellen n�tig, damit die Oberfl�che den Text als Number erkennt. Ansonsten bleiben die
		// Zahlenfelder leer.
		if (!minimalgewicht.equals(""))
			return minimalgewicht.replace(',', '.');
		else
			return "0";
	}

	public void setMinimalgewicht(String minimalgewicht)
	{
		this.minimalgewicht = minimalgewicht;
	}

	public String getMaximalgewicht()
	{
		// Umwandlung der Kommastellen n�tig, damit die Oberfl�che den Text als Number erkennt. Ansonsten bleiben die
		// Zahlenfelder leer.
		if (!maximalgewicht.equals(""))
			return maximalgewicht.replace(',', '.');
		else
			return "0";
	}

	public void setMaximalgewicht(String maximalgewicht)
	{
		this.maximalgewicht = maximalgewicht;
	}

	public String getLieferscheinnummer()
	{
		return lieferscheinnummer;
	}

	public void setLieferscheinnummer(String lieferscheinnummer)
	{
		this.lieferscheinnummer = lieferscheinnummer;
	}

	public String getTransportauftragsnummer()
	{
		return transportauftragsnummer;
	}

	public void setTransportauftragsnummer(String transportauftragsnummer)
	{
		this.transportauftragsnummer = transportauftragsnummer;
	}

	public String getVersandart()
	{
		return versandart;
	}

	public void setVersandart(String versandart)
	{
		this.versandart = versandart;
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

	public String getBearbeitungsstation()
	{
		return bearbeitungsstation;
	}

	public void setBearbeitungsstation(String bearbeitungsstation)
	{
		this.bearbeitungsstation = bearbeitungsstation;
	}

	public String getScandatum()
	{
		return scandatum;
	}

	public void setScandatum(String scandatum)
	{
		this.scandatum = scandatum;
	}

	public int getSapgebucht()
	{
		return sapgebucht;
	}

	public void setSapgebucht(int sapgebucht)
	{
		this.sapgebucht = sapgebucht;
	}

	public String getBuchungsdatum()
	{
		return buchungsdatum;
	}

	public void setBuchungsdatum(String buchungsdatum)
	{
		this.buchungsdatum = buchungsdatum;
	}

	public boolean isManuellAngelegt()
	{
		return manuellAngelegt;
	}

	public void setManuellAngelegt(boolean manuellAngelegt)
	{
		this.manuellAngelegt = manuellAngelegt;
	}
}
