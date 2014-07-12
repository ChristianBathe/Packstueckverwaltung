package packstueckverwaltung.dao;

import java.util.ArrayList;

import packstueckverwaltung.model.Lagerwegdaten;
import packstueckverwaltung.model.Packstueck;

public interface IPackstueckManager
{
	ArrayList<Packstueck> getAllPackst�cke();
	public Packstueck getPackst�ckByBarcode(String barcode);
	Packstueck deletePackst�ckByBarcode(String barcode);
	void saveOrUpdatePackst�ck(Packstueck packst�ck);
	ArrayList<Lagerwegdaten> getLagerwegdatenByBarcode(String barcode);
}
