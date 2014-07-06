package packstueckverwaltung.dao;

import java.util.ArrayList;

import packstueckverwaltung.model.Druckdaten;
import packstueckverwaltung.model.Lagerwegdaten;
import packstueckverwaltung.model.Packstueck;

public interface IPackstueckManager
{
	ArrayList<Packstueck> getAllPackstücke();
	public Packstueck getPackstückByBarcode(String barcode);
	Packstueck deletePackstückByBarcode(String barcode);
	void saveOrUpdatePackstück(Packstueck packstück);
	ArrayList<Druckdaten> getDruckdatenByBarcode(String barcode);
	ArrayList<Lagerwegdaten> getLagerwegdatenByBarcode(String barcode);
}
