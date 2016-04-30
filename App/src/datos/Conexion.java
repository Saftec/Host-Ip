package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import negocio.ControladorConfiguraciones;

public class Conexion {
	
	private Connection con = null;

    public boolean conectar()
    { ControladorConfiguraciones controladorCfg = new ControladorConfiguraciones();
        try
        {
            // CONEXION
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://"+controladorCfg.getConfiguracion("urlBD"));
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Error en la conexi�n a la base de datos: "+ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
    public Connection getConexion()
    {
    	return con;
    }
    
    public void desconectar()
    {
    	try{
    		con.close();
    	}
    	catch(SQLException ex)
    	{
    		ex.getMessage();
    	}
    }
}
