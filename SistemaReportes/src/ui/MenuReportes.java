package ui;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MenuReportes {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuReportes window = new MenuReportes();
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
	public MenuReportes() {
		try 
	    { 
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
	    } 
	    catch(Exception e){ 
	    }
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 174, 127);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGestionDeVentas = new JButton("Ventas por d\u00EDa");
		btnGestionDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GenerarReporteVentasDia.main(null);
			}
		});
		btnGestionDeVentas.setBounds(0, 66, 168, 33);
		
		JButton btnPedidosDelDia = new JButton("Pedidos por d\u00EDa");
		btnPedidosDelDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarReportePedidosDia.main(null);
			}
		});
		btnPedidosDelDia.setBounds(0, 34, 168, 33);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnGestionDeVentas);
		frame.getContentPane().add(btnPedidosDelDia);
		
		JLabel lblMenPrincipal = new JLabel("Generar reporte...");
		lblMenPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenPrincipal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMenPrincipal.setBounds(0, 0, 168, 33);
		frame.getContentPane().add(lblMenPrincipal);
	}
}
