package ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


public class MenuPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
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
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 174, 158);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGestionDeVentas = new JButton("Gesti\u00F3n de ventas");
		btnGestionDeVentas.setBounds(0, 65, 168, 33);
		
		JButton btnListadoDePedidos = new JButton("Listado de \r\npedidos");
		btnListadoDePedidos.setBounds(0, 33, 168, 33);
		
		JButton btnGestionDeClientes = new JButton("Gesti\u00F3n de clientes");
		btnGestionDeClientes.setBounds(0, 97, 168, 33);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnGestionDeVentas);
		frame.getContentPane().add(btnListadoDePedidos);
		frame.getContentPane().add(btnGestionDeClientes);
		
		JLabel lblMenPrincipal = new JLabel("Men\u00FA principal");
		lblMenPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenPrincipal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMenPrincipal.setBounds(0, 0, 168, 33);
		frame.getContentPane().add(lblMenPrincipal);
	}
}
