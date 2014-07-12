package packstueckverwaltung.dao;

import java.util.ArrayList;

import packstueckverwaltung.model.Lagerwegdaten;
import packstueckverwaltung.model.Packstueck;

public interface IPackstueckManager
{
	ArrayList<Packstueck> getAllPackstuecke();
	public Packstueck getPackstückByBarcode(String barcode);
	ArrayList<Lagerwegdaten> getAllLagerwegdaten();
	ArrayList<Lagerwegdaten> getLagerwegdatenByBarcode(String barcode);
	public Packstueck deletePackstückById(int Id);
	Lagerwegdaten deleteLagerwegdatenById(int Id);
	void saveOrUpdatePackstück(Packstueck packstück);
	void saveOrUpdateWegedaten(Lagerwegdaten lagerwegdaten);
}
