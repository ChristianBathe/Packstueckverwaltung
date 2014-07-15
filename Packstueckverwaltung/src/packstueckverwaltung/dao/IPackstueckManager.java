package packstueckverwaltung.dao;

import java.util.ArrayList;

import packstueckverwaltung.model.Lagerwegedaten;
import packstueckverwaltung.model.Packstueck;

public interface IPackstueckManager
{
	ArrayList<Packstueck> getAllPackstuecke();
	public ArrayList<Packstueck> getPackstueckByBarcode(String barcode);
	public Packstueck getPackstueckById(int id);
	ArrayList<Lagerwegedaten> getAllLagerwegdaten();
	ArrayList<Lagerwegedaten> getLagerwegdatenByBarcode(String barcode);
	public Lagerwegedaten getLagerwegedatenById(int id);
	public Packstueck deletePackstueckById(int Id);
	Lagerwegedaten deleteLagerwegedatenById(int Id);
	void saveOrUpdatePackstueck(Packstueck packstueck);
	void saveOrUpdateLagerwegedaten(Lagerwegedaten lagerwegdaten);
}
