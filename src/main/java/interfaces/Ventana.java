package interfaces;

import java.io.IOException;

import javax.swing.JFrame;

public class Ventana extends JFrame{
    public Ventana() throws IOException {
	this.setSize(400,400);
	this.setTitle("EasyPlanner");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setContentPane(new PantallaLogin(this));
	this.setVisible(true);
    }

}
