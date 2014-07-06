package packstueckverwaltung.dao;

import java.util.ArrayList;
import packstueckverwaltung.model.Benutzer;
import packstueckverwaltung.model.Berechtigung;

public interface IBenutzerManager
{
	public Benutzer getBenutzerByEmail(String email);
	public ArrayList<Berechtigung> getBenutzerRechte(int id);
}
