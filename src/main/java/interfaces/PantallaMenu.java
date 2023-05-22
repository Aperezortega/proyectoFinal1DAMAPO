package interfaces;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class PantallaMenu extends JPanel{
    private Ventana ventana;
    private PantallaLogin pantallaLogin;
    public PantallaMenu(Ventana v, PantallaLogin pantallalogin) {
	this.ventana = v;
	this.pantallaLogin = pantallalogin;
	this.setPreferredSize(new Dimension(600, 600));
	JTextField idEmpleadoField = pantallaLogin.getTxtIdEmpleado();
	String idEmpleado = idEmpleadoField.getText();
	setLayout(null);
	
	JMenuBar menuBar = new JMenuBar();
	menuBar.setBounds(0, 0, 600, 22);
	add(menuBar);
	
	
	
	JMenu mnNewMenu = new JMenu("Empleados");
	menuBar.add(mnNewMenu);
	
	JMenuItem mntmNewMenuItem = new JMenuItem("Alta Empleado");
	mnNewMenu.add(mntmNewMenuItem);
	
	JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ver Empleados");
	mnNewMenu.add(mntmNewMenuItem_1);
	
	JMenu mnNewMenu_1 = new JMenu("Planning");
	menuBar.add(mnNewMenu_1);
	
	JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cargar Prevision");
	mnNewMenu_1.add(mntmNewMenuItem_2);
	
	JMenuItem mntmNewMenuItem_3 = new JMenuItem("Requisitos");
	mnNewMenu_1.add(mntmNewMenuItem_3);
	
	JMenuItem mntmNewMenuItem_4 = new JMenuItem("Turnos");
	mnNewMenu_1.add(mntmNewMenuItem_4);
	
	JMenu mnNewMenu_2 = new JMenu("T&A");
	menuBar.add(mnNewMenu_2);
	
	JMenu mnNewMenu_3 = new JMenu("Informes");
	menuBar.add(mnNewMenu_3);
	
	JLabel lblNewLabel_1 = new JLabel("HOLA "+ idEmpleado);
	lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
	lblNewLabel_1.setBounds(10, 26, 93, 14);
	add(lblNewLabel_1);
	
	
	
	
	
    }
}