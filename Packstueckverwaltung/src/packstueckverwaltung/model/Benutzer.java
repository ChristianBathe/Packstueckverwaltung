package packstueckverwaltung.model;

public class Benutzer
{
	private int id;
	private String email;
	private String passwort;
	private String vorname;
	private String nachname;
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPasswort()
	{
		return passwort;
	}
	public void setPasswort(String passwort)
	{
		this.passwort = passwort;
	}
	public String getVorname()
	{
		return vorname;
	}
	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}
	public String getNachname()
	{
		return nachname;
	}
	public void setNachname(String nachname)
	{
		this.nachname = nachname;
	}	
}
