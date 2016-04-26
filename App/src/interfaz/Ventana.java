package interfaz;

import java.awt.EventQueue;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;
//import javax.swing.JOption;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import entidades.Reloj;
import negocio.Controlador;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; //PARA COMUNICARME CON EL CONTROLADOR
public class Ventana {

	private JFrame frame;
	private JTextField textActual;
	private JTextField textHost;
	private Controlador controlador = new Controlador(); //INSTANCION UN CONTROLADOR
	private JTextField textUrlHost;
	private JTextField textUrlBD;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 581, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);	
		
		
		JComboBox<String> comboRelojes = new JComboBox<String>();
		comboRelojes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<Reloj> relojes = new ArrayList<Reloj>();
				Reloj reloj = new Reloj();
	
				relojes=controlador.getRelojes();
				for (int i = 0; i < relojes.size(); i++) 
				{
					reloj = relojes.get(i);
					comboRelojes.addItem(reloj.getNumero()+" "+reloj.getNombre());
				}
			}
		});
		
		
		
		
		
		comboRelojes.setBounds(156, 115, 143, 20);
		frame.getContentPane().add(comboRelojes);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione reloj");
		lblNewLabel_1.setBounds(23, 118, 91, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textActual = new JTextField();
		textActual.setBounds(108, 155, 143, 20);
		frame.getContentPane().add(textActual);
		textActual.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("IP Actual:");
		lblNewLabel_2.setBounds(47, 158, 67, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textHost = new JTextField();
		textHost.setBounds(348, 155, 143, 20);
		frame.getContentPane().add(textHost);
		textHost.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ip Host:");
		lblNewLabel_3.setBounds(292, 158, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(124, 266, 107, 38);
		frame.getContentPane().add(btnActualizar);
		btnActualizar.setEnabled(false); //BLOQUEO EL BOTÓN HASTA QUE SE CARGUEN LOS DATOS.
		
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String host;
				String ip;
				host=getConfiguracion("UrlHost");
				//host=textUrl.getText();
				ip=controlador.actualizaDatos(host);
				textHost.setText(ip);
				if (textHost.getText().equals(textActual.getText())) {btnActualizar.setEnabled(false);} else
				{btnActualizar.setEnabled(true);}
				
		
			}
		});
		btnRefresh.setBounds(348, 111, 89, 23);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();	
			}
		});
		btnCancelar.setBounds(282, 266, 112, 38);
		frame.getContentPane().add(btnCancelar);
		
		textUrlHost = new JTextField();
		textUrlHost.setBounds(108, 66, 276, 20);
		frame.getContentPane().add(textUrlHost);
		textUrlHost.setColumns(10);
		textUrlHost.setEditable(false);
		textUrlHost.setText(getConfiguracion("UrlHost"));
		
		textUrlBD = new JTextField();
		textUrlBD.setBounds(108, 22, 276, 20);
		frame.getContentPane().add(textUrlBD);
		textUrlBD.setColumns(10);
		textUrlBD.setEditable(false);
		textUrlBD.setText(getConfiguracion("UrlBD"));
		
		JButton btnEditarBD = new JButton("Editar");
		btnEditarBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String host;
				boolean res=false;
				host=click(btnEditarBD, textUrlBD);
				textUrlBD.setText(host);
				res=setConfiguracion("urlBD",textUrlBD.getText());
				if (btnEditarBD.getText().equals("Editar")){
				if (res==true) {JOptionPane.showMessageDialog(null, "Cambios guardados"); }
				else {JOptionPane.showMessageDialog(null, "Error al guardar los cambios"); }
				}
			}
		});
		btnEditarBD.setBounds(394, 21, 89, 23);
		frame.getContentPane().add(btnEditarBD);
		
		JButton btnEditarHost = new JButton("Editar");
		btnEditarHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url;
				boolean res=false;
				url=click(btnEditarHost, textUrlHost);
				textUrlHost.setText(url);
				res=setConfiguracion("urlHost",textUrlHost.getText());
				if (btnEditarHost.getText().equals("Editar")){
				if (res==true) {JOptionPane.showMessageDialog(null, "Cambios guardados"); }
				else {JOptionPane.showMessageDialog(null, "Error al guardar los cambios"); }
				}
				
			}
		});
		btnEditarHost.setBounds(394, 65, 89, 23);
		frame.getContentPane().add(btnEditarHost);
	}
	
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
	
	private String click(JButton btn, JTextField text)
	{
		if (btn.getText().equals("Editar")) {
			text.setEditable(true);
			btn.setText("Guardar");
			return(text.getText());
		}
		else {
			text.setEditable(false);
			btn.setText("Editar");
			return(text.getText());
		}
	}
	
	private boolean setConfiguracion(String propiedad, String nuevaConfig)
	{
		Properties prop = new Properties();
		InputStream entrada=null;
		
		try
		{
			entrada = new FileInputStream("Configuracion.properties");
			prop.load(entrada);
			prop.setProperty(propiedad, nuevaConfig);
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
