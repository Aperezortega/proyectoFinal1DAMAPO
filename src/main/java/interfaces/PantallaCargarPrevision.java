package interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PantallaCargarPrevision extends JPanel {
    private Ventana ventana;
    
    public PantallaCargarPrevision(Ventana v) {
	this.setPreferredSize(new Dimension(600, 600));
	setLayout(null);
	
	JMenuBar menuBar = new JMenuBar();
	menuBar.setBounds(0, 0, 600, 22);
	add(menuBar);
	
	JMenu mnNewMenu = new JMenu("Empleados");
	menuBar.add(mnNewMenu);
	
	JMenuItem mntmNewMenuItem = new JMenuItem("Alta Empleado");
	mntmNewMenuItem.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaAltaEmpleado.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu.add(mntmNewMenuItem);
	
	JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ver Empleados");
	mntmNewMenuItem_1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaVerEmpleados.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});

	mnNewMenu.add(mntmNewMenuItem_1);
	
	JMenu mnNewMenu_1 = new JMenu("Planning");
	menuBar.add(mnNewMenu_1);
	
	JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cargar Prevision");
	mntmNewMenuItem_2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaCargarPrevision.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_1.add(mntmNewMenuItem_2);
	
	JMenuItem mntmNewMenuItem_3 = new JMenuItem("Requisitos");
	mntmNewMenuItem_3.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaRequisitosEmpleado.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_1.add(mntmNewMenuItem_3);
	
	JMenuItem mntmNewMenuItem_4 = new JMenuItem("Turnos");
	mntmNewMenuItem_4.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaTurnos.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_1.add(mntmNewMenuItem_4);
	
	JMenu mnNewMenu_2 = new JMenu("T&A");
	menuBar.add(mnNewMenu_2);
	
	JMenuItem mntmNewMenuItem_5 = new JMenuItem("Abrir T&A");
	mntmNewMenuItem_5.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaTAndA.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_2.add(mntmNewMenuItem_5);
	
	JMenu mnNewMenu_3 = new JMenu("Informes");
	menuBar.add(mnNewMenu_3);
	JMenuItem mntmNewMenuItem_6 = new JMenuItem("Generar Informe");
	mntmNewMenuItem_6.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaInformes.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_3.add(mntmNewMenuItem_6);
	
	JLabel lblNewLabel = new JLabel("Cargar Prevision ");
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setBounds(10, 33, 114, 22);
	add(lblNewLabel);
	
    	}
    
    }


