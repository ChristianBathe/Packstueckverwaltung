package packstueckverwaltung.dao;

import java.util.ArrayList;

import packstueckverwaltung.model.Packstueck;

public interface IPackstueckManager
{
	ArrayList<Packstueck> getAllPackstuecke();

	public ArrayList<Packstueck> getPackstueckByBarcode(String barcode);

	public Packstueck getPackstueckById(int id);

	public Packstueck deletePackstueckById(int Id);

	boolean saveOrUpdatePackstueck(Packstueck packstueck);

}
