package datos;

import java.util.ArrayList;
import entidades.Reloj;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatosReloj {
	
	private Connection conn;

	public ArrayList<Reloj> getRelojes()
	{
		ArrayList<Reloj> relojes = new ArrayList<Reloj>();
		String consulta = "SELECT m.ID, m.MachineAlias FROM Machines m;";
		conn = null;
		Conexion con = new Conexion();
		con.conectar();
		
		//REALIZO LA CONSULTA
		
		try
		{
			PreparedStatement st = conn.prepareStatement( consulta );
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				Reloj reloj = new Reloj();
				reloj.setNombre(rs.getString("MachineAlias"));
				reloj.setNumero(rs.getString("MachineNumber"));
				relojes.add(reloj);
			}	
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return(relojes);	
	}

}
