package datos;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import entidades.Reloj;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatosReloj {

	public ArrayList<Reloj> getRelojes(String consulta)
	{
		ArrayList<Reloj> relojes = new ArrayList<Reloj>();
		Conexion con = new Conexion();
		
		//REALIZO LA CONSULTA
		
		try
		{
			con.conectar();
			PreparedStatement st = con.getConexion().prepareStatement(consulta);
			ResultSet rs = st.executeQuery();
			con.desconectar();
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
			JOptionPane.showMessageDialog(null, "Error en la conexión a al base de datos");
		}
		return(relojes);	
	}
	
	public int setNewIp(String ipAnt, String ipNueva)
	{
		int m= 0;
		String sentencia = "UPDATE Machines SET IP='"+ipNueva+"' WHERE IP='"+ipAnt+"'";
		Conexion con = new Conexion();
		
		//REALIZO LA ACTUALIZACI�N
		
		try
		{
			con.conectar();
			PreparedStatement st = con.getConexion().prepareStatement(sentencia);
			m = st.executeUpdate();
			con.desconectar(); //CIERRO LA CONEXION
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Error en al conexión a la base de datos");
		}
		
		return(m);	
	}

}
