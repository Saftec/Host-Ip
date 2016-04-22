package datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	private Connection con;

    public boolean conectar()
    {
        try
        {
            // CONEXION
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://C:/Program Files/ZKTime5.0/miBase.mdb");
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Error en la conexión a la base de datos: "+ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

}
