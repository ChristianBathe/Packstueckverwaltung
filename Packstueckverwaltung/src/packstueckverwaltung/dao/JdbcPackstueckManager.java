package packstueckverwaltung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import packstueckverwaltung.model.Packstueck;

public class JdbcPackstueckManager implements IPackstueckManager
{
	private Packstueck loadPersonFromResultSet(ResultSet rs) throws SQLException
	{
		Packstueck packstueck = new Packstueck();
//		packstueck.setId(rs.getInt("id"));
//		packstueck.setEmail(rs.getString("email"));
//		packstueck.setFoto(rs.getBytes("foto"));
//		packstueck.setGeburtsdatum(rs.getDate("geburtsdatum"));
//		String g = rs.getString("geschlecht");

		return packstueck;
	}
	
	@Override
	public Collection<Packstueck> getAllPackst�cke()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packstueck getPackst�ckById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Packstueck deletePackst�ckById(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdatePackst�ck(Packstueck packst�ck)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Packstueck getPackst�ckByCredentials(String email, String passwort)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
