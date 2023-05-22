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
	if(clase.equals(PantallaVerEmpleados.class)) {
	    this.setContentPane(new PantallaVerEmpleados(this));
	}
	if(clase.equals(PantallaAltaEmpleado.class)) {
	    this.setContentPane(new PantallaAltaEmpleado(this));
	}
	if(clase.equals(PantallaCargarPrevision.class)) {
	    this.setContentPane(new PantallaCargarPrevision(this));
	}
	if(clase.equals(PantallaRequisitosEmpleado.class)) {
	    this.setContentPane(new PantallaRequisitosEmpleado(this));
	}
	if(clase.equals(PantallaTurnos.class)) {
	    this.setContentPane(new PantallaTurnos(this));
	}
	if(clase.equals(PantallaTAndA.class)) {
	    this.setContentPane(new PantallaTAndA(this));
	}
	if(clase.equals(PantallaInformes.class)) {
	    this.setContentPane(new PantallaInformes(this));
	}
	
	this.getContentPane().setVisible(true);
    }  
    
}
