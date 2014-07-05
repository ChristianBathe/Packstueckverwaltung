package packstueckverwaltung.dao;

import java.util.Collection;

import packstueckverwaltung.model.Packstueck;

public interface IPackstueckManager
{
	Collection<Packstueck> getAllPackstücke();
	Packstueck getPackstückById(Integer id);
	Packstueck deletePackstückById(Integer id);
	void saveOrUpdatePackstück(Packstueck packstück);
	Packstueck getPackstückByCredentials(String email, String passwort);
}
