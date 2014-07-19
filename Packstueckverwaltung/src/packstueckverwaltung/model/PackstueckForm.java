package packstueckverwaltung.model;

import javax.servlet.http.HttpServletRequest;

public class PackstueckForm
{
	private int id = Constants.NEW_DATA_SET_ID; // standardwert bei Neuanlage, wird bei Update/Insert Statement geprüft
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
	private String letztesupdate;
	private String bearbeitungsstation; // PC, an dem das Packstück zuletzt bearbeitet wurde.
	private int sapgebucht;
	private String buchungsdatum;
	private String scandatum;
	private String erstellungsdatum;
	private boolean manuellAngelegt;

	// Für neu angelegte Packstücke
	public PackstueckForm()
	{
		manuellAngelegt = true;
	}

	public PackstueckForm(HttpServletRequest request)
	{
		id = Integer.valueOf(request.getParameter("id"));
		barcode = request.getParameter("barcode");
		kartonid = request.getParameter("kartonid");
		lagernummer = request.getParameter("lagernummer");
		mandant = request.getParameter("mandant");
		istgewicht = request.getParameter("istgewicht");
		minimalgewicht = request.getParameter("minimalgewicht");
		maximalgewicht = request.getParameter("maximalgewicht");
		lieferscheinnummer = request.getParameter("lieferscheinnummer");
		transportauftragsnummer = request.getParameter("transportauftragsnummer");
		versandart = request.getParameter("versandart");
		bearbeitungsstation = request.getParameter("bearbeitungsstation");
		sapgebucht = Integer.valueOf(request.getParameter("sapgebucht"));
		buchungsdatum = request.getParameter("buchungsdatum");
		scandatum = request.getParameter("scandatum");
		erstellungsdatum = request.getParameter("erstellungsdatum");
		manuellAngelegt = Boolean.parseBoolean(request.getParameter("manuellAngelegt"));
	}

	public PackstueckForm(Packstueck packstueck, HttpServletRequest request)
	{
		if (packstueck.getId() != Constants.NEW_DATA_SET_ID)
		{
			id = packstueck.getId();
		}

		barcode = packstueck.getBarcode();
		kartonid = packstueck.getKartonid();
		lagernummer = packstueck.getLagernummer();
		mandant = packstueck.getMandant();
		istgewicht = packstueck.getIstgewicht();
		minimalgewicht = packstueck.getMinimalgewicht();
		maximalgewicht = packstueck.getMaximalgewicht();
		lieferscheinnummer = packstueck.getLieferscheinnummer();
		transportauftragsnummer = packstueck.getTransportauftragsnummer();
		versandart = packstueck.getVersandart();
		letztesupdate = packstueck.getLetztesupdate();
		bearbeitungsstation = packstueck.getBearbeitungsstation();
		sapgebucht = packstueck.getSapgebucht();
		buchungsdatum = packstueck.getBuchungsdatum();
		scandatum = packstueck.getScandatum();
		erstellungsdatum = packstueck.getErstellungsdatum();
		manuellAngelegt = packstueck.isManuellAngelegt();
	}

	public void validate(Packstueck packstueck, HttpServletRequest request)
	{

		packstueck.setId(id);
		packstueck.setBarcode(barcode);
		packstueck.setBearbeitungsstation(bearbeitungsstation);
		packstueck.setBuchungsdatum(buchungsdatum);
		packstueck.setErstellungsdatum(erstellungsdatum);
		packstueck.setIstgewicht(istgewicht);
		packstueck.setKartonid(kartonid);
		packstueck.setLagernummer(lagernummer);
		packstueck.setLetztesupdate(letztesupdate);
		packstueck.setLieferscheinnummer(lieferscheinnummer);
		packstueck.setMandant(mandant);
		packstueck.setManuellAngelegt(manuellAngelegt);
		packstueck.setMaximalgewicht(maximalgewicht);
		packstueck.setMinimalgewicht(minimalgewicht);
		packstueck.setSapgebucht(sapgebucht);
		packstueck.setScandatum(scandatum);
		packstueck.setTransportauftragsnummer(transportauftragsnummer);
		packstueck.setVersandart(versandart);
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
		return istgewicht;
	}

	public void setIstgewicht(String istgewicht)
	{
		this.istgewicht = istgewicht;
	}

	public String getMinimalgewicht()
	{
		return minimalgewicht;
	}

	public void setMinimalgewicht(String minimalgewicht)
	{
		this.minimalgewicht = minimalgewicht;
	}

	public String getMaximalgewicht()
	{
		return maximalgewicht;
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

	public String getScandatum()
	{
		return scandatum;
	}

	public void setScandatum(String scandatum)
	{
		this.scandatum = scandatum;
	}

	public String getErstellungsdatum()
	{
		return erstellungsdatum;
	}

	public void setErstellungsdatum(String erstellungsdatum)
	{
		this.erstellungsdatum = erstellungsdatum;
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
