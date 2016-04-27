package negocio;
import entidades.Reloj;
import datos.DatosReloj;

import java.net.InetAddress;
import java.util.ArrayList;

public class Controlador {

	public String actualizaDatos(String host)
	{   String ip="";
		try
		{
		InetAddress ipHost = InetAddress.getByName(host);
		ip=String.valueOf(ipHost);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		ip=extraeIp(ip);
		return(ip);
	}
	
	private String extraeIp(String ip)
	{ String nuevoIp="";
	for (int i = 0; i < ip.length(); i++) {
		if(ip.charAt(i) == '/') {nuevoIp=ip.substring(i+1);}
	}	
	return(nuevoIp);
	}
	
	public ArrayList<Reloj> getRelojes()
	{   ArrayList<Reloj> todosRelojes = new ArrayList<Reloj>();
		DatosReloj reloj = new DatosReloj();
		todosRelojes=reloj.getRelojes();
		return (todosRelojes);
	}
	
	public boolean actualizaIp(String ipAnt, String ipNueva)
	{	boolean resp=false;
		int n=0;
		DatosReloj datosReloj = new DatosReloj();
		n=datosReloj.setNewIp(ipAnt, ipNueva);
		if(n==1) {resp=true;}
		return(resp);
	}
	
}
