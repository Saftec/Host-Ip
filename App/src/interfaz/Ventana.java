package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
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
	private JTextField textUrl;
	private JTextField textActual;
	private JTextField textHost;

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
				//ESTO NO SE EJECUTA
				Controlador controlador = new Controlador();
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
		
		
		
		
		
		comboRelojes.setBounds(148, 92, 143, 20);
		frame.getContentPane().add(comboRelojes);
		
		textUrl = new JTextField();
		textUrl.setBounds(143, 34, 270, 20);
		frame.getContentPane().add(textUrl);
		textUrl.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Direccion del Host:");
		lblNewLabel.setBounds(23, 37, 110, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione reloj");
		lblNewLabel_1.setBounds(47, 95, 91, 14);
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
		
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String host;
				String ip;
				Controlador controlador = new Controlador();
				host=textUrl.getText();
				ip=controlador.actualizaDatos(host);
				textHost.setText(ip);
				if (textHost.getText().equals(textActual.getText())) {btnActualizar.setEnabled(false);} else
				{btnActualizar.setEnabled(true);}
				
		
			}
		});
		btnRefresh.setBounds(348, 91, 89, 23);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();	
			}
		});
		btnCancelar.setBounds(282, 266, 112, 38);
		frame.getContentPane().add(btnCancelar);
	}
}
