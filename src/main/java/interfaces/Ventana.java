package interfaces;

import java.io.IOException;

import javax.swing.JFrame;

public class Ventana extends JFrame{
    public Ventana() throws IOException {
	this.setSize(600,600);
	this.setTitle("EasyPlanner");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setContentPane(new PantallaLogin(this));
	this.setVisible(true);
  
    }

    public void cambiarAPantalla(Class<?> clase) throws IOException {
	this.getContentPane().setVisible(false);
	if(clase.equals(PantallaLogin.class)) {
	    this.setContentPane(new PantallaLogin(this));
	}
	if(clase.equals(PantallaMenu.class)) {
	    this.setContentPane(new PantallaMenu(this, (PantallaLogin) this.getContentPane()));
	}
	this.getContentPane().setVisible(true);
    }  
    
}
