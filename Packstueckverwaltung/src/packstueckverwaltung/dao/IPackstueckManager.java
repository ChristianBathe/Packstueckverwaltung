package packstueckverwaltung.dao;

import java.util.Collection;

import packstueckverwaltung.model.Packstueck;

public interface IPackstueckManager
{
	Collection<Packstueck> getAllPackst�cke();
	Packstueck getPackst�ckById(Integer id);
	Packstueck deletePackst�ckById(Integer id);
	void saveOrUpdatePackst�ck(Packstueck packst�ck);
	Packstueck getPackst�ckByCredentials(String email, String passwort);
}
