package datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	private Connection con = null;

    public boolean conectar()
    {
        try
        {
            // CONEXION
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://C:/Program Files/ZKTime5.0/miBase.mdb");
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

}
