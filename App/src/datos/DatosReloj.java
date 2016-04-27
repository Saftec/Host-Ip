package datos;

import java.util.ArrayList;
import entidades.Reloj;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatosReloj {

	public ArrayList<Reloj> getRelojes()
	{
		ArrayList<Reloj> relojes = new ArrayList<Reloj>();
		String consulta = "SELECT * FROM Machines";
		Conexion con = new Conexion();
		
		//REALIZO LA CONSULTA
		
		try
		{
			con.conectar();
			PreparedStatement st = con.getConexion().prepareStatement(consulta);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				Reloj reloj = new Reloj();
				reloj.setNombre(rs.getString("MachineAlias"));
				reloj.setNumero(rs.getString("MachineNumber"));
				reloj.setIp(rs.getString("IP"));
				reloj.setId(Integer.parseInt(rs.getString("ID")));
				relojes.add(reloj);
			}	
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return(relojes);	
	}
	
	public int setNewIp(String ipAnt, String ipNueva)
	{
		int m= 0;
		String sentencia = "UPDATE Machines SET IP="+ipNueva+" WHERE IP="+ipAnt;;
		Conexion con = new Conexion();
		
		//REALIZO LA ACTUALIZACIÓN
		
		try
		{
			con.conectar();
			PreparedStatement st = con.getConexion().prepareStatement(sentencia);
			m = st.executeUpdate();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return(m);	
	}

}
