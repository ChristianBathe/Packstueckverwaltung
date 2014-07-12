package packstueckverwaltung.dao;

import java.util.ArrayList;

import packstueckverwaltung.model.Lagerwegdaten;
import packstueckverwaltung.model.Packstueck;

public interface IPackstueckManager
{
	ArrayList<Packstueck> getAllPackstuecke();
	public Packstueck getPackst�ckByBarcode(String barcode);
	ArrayList<Lagerwegdaten> getAllLagerwegdaten();
	ArrayList<Lagerwegdaten> getLagerwegdatenByBarcode(String barcode);
	public Packstueck deletePackst�ckById(int Id);
	Lagerwegdaten deleteLagerwegdatenById(int Id);
	void saveOrUpdatePackst�ck(Packstueck packst�ck);
	void saveOrUpdateWegedaten(Lagerwegdaten lagerwegdaten);
}
