package interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class PantallaInformes extends Pantalla{
    
    private Ventana ventana;
    public PantallaInformes(Ventana v) {
	super(v);
	this.setPreferredSize(new Dimension(800, 600));
	setLayout(null);
	
	
	
    }

}
