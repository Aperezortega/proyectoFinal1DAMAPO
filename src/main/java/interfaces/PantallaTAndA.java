package interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PantallaTAndA  extends Pantalla{
    
    private Ventana ventana;
    public PantallaTAndA(Ventana v) {
	super(v);
	this.setPreferredSize(new Dimension(800, 600));
	setLayout(null);
	
	
    }

}
