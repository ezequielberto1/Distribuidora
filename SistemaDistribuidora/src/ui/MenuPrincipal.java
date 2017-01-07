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


public class MenuPrincipal {

	private JFrame frame;
	private ABMVentas abmv;
	private MenuReportes mr;

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
		frame.setBounds(100, 100, 174, 124);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGestionDeVentas = new JButton("Gesti\u00F3n de ventas");
		btnGestionDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gestionVentas();
			}
		});
		btnGestionDeVentas.setBounds(0, 32, 168, 33);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reportes();
			}
		});
		btnReportes.setBounds(0, 64, 168, 33);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnGestionDeVentas);
		frame.getContentPane().add(btnReportes);
		
		JLabel lblMenPrincipal = new JLabel("Men\u00FA principal");
		lblMenPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenPrincipal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMenPrincipal.setBounds(0, 0, 168, 33);
		frame.getContentPane().add(lblMenPrincipal);
	}
	
	public void gestionVentas(){
		if(abmv==null){
			abmv = new ABMVentas();
		}
		abmv.cargarZonas();
		abmv.cargarListaVentas();
		abmv.setCaller(this);
		abmv.show(true);
		}
	
	public void reportes(){
		if(mr==null)
			mr = new MenuReportes();
		mr.setCaller(this);
		mr.show(true);
	}
	
	public void setNullABMV(){
		abmv = null;
	}
	
	public void setNullMR(){
		mr = null;
	}
}
