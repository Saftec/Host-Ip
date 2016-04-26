package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
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
import negocio.ControladorConfiguraciones;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; //PARA COMUNICARME CON EL CONTROLADOR
public class Ventana {

	private JFrame frame;
	private JTextField textActual;
	private JTextField textHost;
	private Controlador controlador = new Controlador(); //INSTANCION UN CONTROLADOR
	private ControladorConfiguraciones controladorCfg = new ControladorConfiguraciones();
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
				host=controladorCfg.getConfiguracion("urlHost");
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
		textUrlHost.setText(controladorCfg.getConfiguracion("urlHost"));
		
		textUrlBD = new JTextField();
		textUrlBD.setBounds(108, 22, 276, 20);
		frame.getContentPane().add(textUrlBD);
		textUrlBD.setColumns(10);
		textUrlBD.setEditable(false);
		textUrlBD.setText(controladorCfg.getConfiguracion("urlBD"));
		
		JButton btnEditarBD = new JButton("Editar");
		btnEditarBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String host;
				boolean res=false;
				host=click(btnEditarBD, textUrlBD);
				textUrlBD.setText(host);
				res=controladorCfg.setConfiguracion("urlBD",textUrlBD.getText());
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
				res=controladorCfg.setConfiguracion("urlHost",textUrlHost.getText());
				if (btnEditarHost.getText().equals("Editar")){
				if (res==true) {JOptionPane.showMessageDialog(null, "Cambios guardados"); }
				else {JOptionPane.showMessageDialog(null, "Error al guardar los cambios"); }
				}
				
			}
		});
		btnEditarHost.setBounds(394, 65, 89, 23);
		frame.getContentPane().add(btnEditarHost);
		
		JLabel lblUrlBaseDe = new JLabel("Url BD:");
		lblUrlBaseDe.setBounds(47, 25, 51, 14);
		frame.getContentPane().add(lblUrlBaseDe);
		
		JLabel lblNewLabel = new JLabel("Url de Host:");
		lblNewLabel.setBounds(31, 69, 67, 14);
		frame.getContentPane().add(lblNewLabel);
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
}
