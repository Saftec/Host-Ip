package datos;

import java.sql.Connection;
import java.sql.DriverManager;

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
            if(con==null) {System.out.println("es nulo");}
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Error en la conexión a la base de datos: "+ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
    public Connection getConexion()
    {
    	return con;
    }

}
