package packstueckverwaltung.model;

public class Report
{
	int id;
	String useremail;
	String altedaten;
	String neuedaten;
	String datum;
	String barcode;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUseremail()
	{
		return useremail;
	}

	public void setUseremail(String useremail)
	{
		this.useremail = useremail;
	}

	public String getAltedaten()
	{
		return altedaten;
	}

	public void setAltedaten(String altedaten)
	{
		this.altedaten = altedaten;
	}

	public String getNeuedaten()
	{
		return neuedaten;
	}

	public void setNeuedaten(String neuedaten)
	{
		this.neuedaten = neuedaten;
	}

	public String getDatum()
	{
		return datum;
	}

	public void setDatum(String datum)
	{
		this.datum = datum;
	}

	public String getBarcode()
	{
		return barcode;
	}

	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}
}
