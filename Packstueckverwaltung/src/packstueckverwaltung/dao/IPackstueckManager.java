package packstueckverwaltung.dao;

import java.util.ArrayList;

import packstueckverwaltung.model.Lagerwegdaten;
import packstueckverwaltung.model.Packstueck;

public interface IPackstueckManager
{
	ArrayList<Packstueck> getAllPackstuecke();
	public ArrayList<Packstueck> getPackstueckByBarcode(String barcode);
	public Packstueck getPackstueckById(int id);
	ArrayList<Lagerwegdaten> getAllLagerwegdaten();
	ArrayList<Lagerwegdaten> getLagerwegdatenByBarcode(String barcode);
	public Packstueck deletePackstueckById(int Id);
	Lagerwegdaten deleteLagerwegdatenById(int Id);
	void saveOrUpdatePackstueck(Packstueck packstueck);
	void saveOrUpdateWegedaten(Lagerwegdaten lagerwegdaten);
}
