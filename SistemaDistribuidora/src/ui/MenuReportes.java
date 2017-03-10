package ui;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.SwingConstants;

import data.FactoryConexion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MenuReportes {

	private JFrame frmMenuDeReportes;
	private GenerarReporteVentasDia grvd;
	private GenerarReportePedidosDia grpd;
	private MenuPrincipal mp;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuReportes window = new MenuReportes();
					window.frmMenuDeReportes.setVisible(true);
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
		frmMenuDeReportes = new JFrame();
		frmMenuDeReportes.setTitle("Menu de reportes");
		frmMenuDeReportes.setResizable(false);
		frmMenuDeReportes.setBounds(100, 100, 174, 159);
		frmMenuDeReportes.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Image logo = (new ImageIcon(this.getClass().getResource("/Logo INT.png")).getImage());
		frmMenuDeReportes.setIconImage(logo);
		
		JButton btnGestionDeVentas = new JButton("Ventas por d\u00EDa");
		btnGestionDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generarRVD();
			}
		});
		btnGestionDeVentas.setBounds(0, 66, 168, 33);
		
		JButton btnPedidosDelDia = new JButton("Pedidos por d\u00EDa");
		btnPedidosDelDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarRPD();
			}
		});
		btnPedidosDelDia.setBounds(0, 34, 168, 33);
		frmMenuDeReportes.getContentPane().setLayout(null);
		frmMenuDeReportes.getContentPane().add(btnGestionDeVentas);
		frmMenuDeReportes.getContentPane().add(btnPedidosDelDia);
		
		JLabel lblMenPrincipal = new JLabel("Generar reporte...");
		lblMenPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenPrincipal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMenPrincipal.setBounds(0, 0, 168, 33);
		frmMenuDeReportes.getContentPane().add(lblMenPrincipal);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(0, 98, 168, 33);
		frmMenuDeReportes.getContentPane().add(btnVolver);
	}
	
	public void generarRPD(){
		if (grpd==null){
			grpd = new GenerarReportePedidosDia();
		}
		grpd.show(true);
	}
	
	public void generarRVD(){
		if (grvd==null){
			grvd = new GenerarReporteVentasDia();
		}
		grvd.show(true);
	}
	
	public void setCaller(MenuPrincipal m){
		mp = m;
	}
	
	public void show (boolean b){
		this.frmMenuDeReportes.setVisible(b);
	}
	
	public void volver(){
		mp.setNullMR();
		show(false);
	}
}
