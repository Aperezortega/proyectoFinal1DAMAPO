package interfaces;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class PantallaMenu extends Pantalla{
    private PantallaLogin pantallaLogin;
    public PantallaMenu(Ventana v, PantallaLogin pantallalogin) {
	super(v);
	this.pantallaLogin =pantallaLogin;	
	this.setPreferredSize(new Dimension(800, 600));
	setLayout(new BorderLayout());
	
	
	
    }
}