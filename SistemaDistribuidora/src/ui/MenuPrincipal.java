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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MenuPrincipal {

	private JFrame frmMenuPrincipal;
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
					window.frmMenuPrincipal.setVisible(true);
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
		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.setTitle("Menu principal");
		frmMenuPrincipal.setResizable(false);
		frmMenuPrincipal.setBounds(100, 100, 440, 280);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGestionDeVentas = new JButton("Gesti\u00F3n de ventas");
		btnGestionDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gestionVentas();
			}
		});
		btnGestionDeVentas.setBounds(10, 208, 195, 33);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reportes();
			}
		});
		btnReportes.setBounds(229, 208, 195, 33);
		frmMenuPrincipal.getContentPane().setLayout(null);
		frmMenuPrincipal.getContentPane().add(btnGestionDeVentas);
		frmMenuPrincipal.getContentPane().add(btnReportes);
		Image logo = (new ImageIcon(this.getClass().getResource("/Logo INT.png")).getImage());
		frmMenuPrincipal.setIconImage(logo);
		
		JLabel img = new JLabel("");
		img.setHorizontalAlignment(SwingConstants.CENTER);
		logo = (new ImageIcon(this.getClass().getResource("/Logo INT Ventas.png")).getImage());
		logo = logo.getScaledInstance(290, 153,  java.awt.Image.SCALE_SMOOTH);
		img.setIcon(new ImageIcon (logo));
		img.setBounds(10, 11, 414, 186);
		frmMenuPrincipal.getContentPane().add(img);
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
