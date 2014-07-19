package packstueckverwaltung.dao;

import java.util.ArrayList;

import packstueckverwaltung.model.Lagerwegedaten;

public interface ILagerwegedatenManager
{
	ArrayList<Lagerwegedaten> getAllLagerwegedaten();

	ArrayList<Lagerwegedaten> getLagerwegedatenByBarcode(String barcode);

	public Lagerwegedaten getLagerwegedatenById(int id);

	void saveOrUpdateLagerwegedaten(Lagerwegedaten lagerwegdaten);

	Lagerwegedaten deleteLagerwegedatenById(int Id);
}
