package packstueckverwaltung.model;

import javax.servlet.http.HttpServletRequest;

public class LagerwegedatenForm
{
	private int id = Constants.NEW_DATA_SET_ID; // standardwert bei Neuanlage, wird bei Update/Insert Statement geprüft
	private String barcode;
	private String kartonid;
	private String wegeid; // Richtungscode wohin das Packstück geschleust werden soll
	private String wegpunkt; // Punkt/Station im Lager, auf den sich die Weginformation bezieht
	private String erstellungsdatum;
	private String letztesupdate;
	private int sapgebucht; // Kennzeichnung, ob ein Packstück den Wegpunkt passiert hat

	public LagerwegedatenForm()
	{

	}

	public LagerwegedatenForm(HttpServletRequest request)
	{
		id = Integer.valueOf(request.getParameter("id"));
		barcode = request.getParameter("barcode");
		kartonid = request.getParameter("kartonid");
		wegeid = request.getParameter("wegeid");
		wegpunkt = request.getParameter("wegpunkt");
		sapgebucht = Integer.valueOf(request.getParameter("sapgebucht"));
		erstellungsdatum = request.getParameter("erstellungsdatum");
		letztesupdate = request.getParameter("letztesupdate");
	}

	// Konstruktur zum vorbelegen der Form mit Werten aus der Datenbank
	public LagerwegedatenForm(Lagerwegedaten lagerwegedaten, HttpServletRequest request)
	{
		if (lagerwegedaten.getId() != Constants.NEW_DATA_SET_ID)
		{
			id = lagerwegedaten.getId();
		}

		barcode = lagerwegedaten.getBarcode();
		kartonid = lagerwegedaten.getKartonid();
		wegeid = lagerwegedaten.getWegeid();
		wegpunkt = lagerwegedaten.getWegpunkt();
		letztesupdate = lagerwegedaten.getLetztesupdate();
		sapgebucht = lagerwegedaten.getSapgebucht();
		erstellungsdatum = lagerwegedaten.getErstellungsdatum();
	}

	public void validate(Lagerwegedaten lagerwegedaten, HttpServletRequest request)
	{
		lagerwegedaten.setId(id);
		lagerwegedaten.setBarcode(barcode);
		lagerwegedaten.setWegeid(wegeid);
		lagerwegedaten.setWegpunkt(wegpunkt);
		lagerwegedaten.setKartonid(kartonid);
		lagerwegedaten.setLetztesupdate(letztesupdate);
		lagerwegedaten.setErstellungsdatum(erstellungsdatum);
		lagerwegedaten.setSapgebucht(sapgebucht);

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
