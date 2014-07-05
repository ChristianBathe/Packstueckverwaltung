package packstueckverwaltung.dao;

import packstueckverwaltung.model.Benutzer;

public interface IBenutzerManager
{
	public Benutzer getBenutzerByEmail(String email);
}
