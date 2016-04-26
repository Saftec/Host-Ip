package negocio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ControladorConfiguraciones {

	
	public String getConfiguracion(String configuracion)
	{
		Properties prop = new Properties(); //INSTANCIO EL ARCHIVO DE PROPIEDADES
		InputStream entrada=null;
		String valor="<vacío>";
		
		try
		{
			entrada = new FileInputStream("Configuracion.properties");
			prop.load(entrada); //CARGO EL ARCHIVO DE CONFIGURACIONES
			valor=prop.getProperty(configuracion);
			entrada.close();
			return valor;
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			return valor;
		}
	}
	
	public boolean setConfiguracion(String propiedad, String nuevaConfig)
	{
		Properties prop = new Properties();
		InputStream entrada=null;
		
		try
		{
			entrada = new FileInputStream("Configuracion.properties");
			prop.load(entrada);
			prop.setProperty(propiedad, nuevaConfig);
			java.io.FileOutputStream out = new java.io.FileOutputStream("Configuracion.properties");
			//Grabamos las propiedades en el archivo
			prop.store(out, propiedad);
			entrada.close();
			return true;
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			return false;
		}	
}
}
