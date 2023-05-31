/*
package interfaces;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.swing.JFrame;

import clases.Empleado;

public class Ventana extends JFrame{
    private Class<?> pantallaAnterior = null;
    private Class<?> pantallaSiguiente = null;

    
    public Ventana() throws IOException {
	this.setSize(800,600);
	this.setTitle("EasyPlanner");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setContentPane(new PantallaLogin(this));
	this.setVisible(true);
  
    }

    public void cambiarAPantalla(Class<?> clase) throws IOException {
	Class<?> pantallaActual = this.getContentPane().getClass();

	this.getContentPane().setVisible(false);
	if(clase.equals(PantallaLogin.class)) {
	    this.setContentPane(new PantallaLogin(this));

	}
	if(clase.equals(PantallaMenu.class)) {
	   // this.setContentPane(new PantallaMenu(this, (PantallaLogin) this.getContentPane()));
	    PantallaMenu pantalla = new PantallaMenu(this,(PantallaLogin) this.getContentPane());
	    this.setContentPane(pantalla);
	    this.setJMenuBar(pantalla.getMenuBar());
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
	 if (pantallaAnterior != null && pantallaActual.equals(pantallaSiguiente)) {
	            pantallaSiguiente = null;
	        } else {
	            pantallaAnterior = pantallaActual;
        }
	    
    }  
    
    public void cambiarAPantalla(Class<?> clase,Empleado empleado) throws IOException {
	Class<?> pantallaActual = this.getContentPane().getClass();
	
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
   	    this.setContentPane(new PantallaAltaEmpleado(this, empleado));
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
   	 if (pantallaAnterior != null && pantallaActual.equals(pantallaSiguiente)) {
	            pantallaSiguiente = null;
	        } else {
	            pantallaAnterior = pantallaActual;
 }
	
       }  
    public void navegarAtras() throws IOException {
        if (pantallaAnterior != null) {
            cambiarAPantalla(pantallaAnterior);
            pantallaAnterior = null;
        }
    }

    public void navegarAdelante() throws IOException {
        if (pantallaSiguiente != null) {
            cambiarAPantalla(pantallaSiguiente);
            pantallaSiguiente = null;
        }
    }
}
*/
package interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.swing.JFrame;

import clases.Empleado;

public class Ventana extends JFrame{
    //Empleado empleadoActual; nm 
    Deque<Class<?>> screenHistory = new ArrayDeque<>();

    public Ventana() throws IOException {
	this.setSize(1500,1000);
	this.setTitle("EasyPlanner");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setContentPane(new PantallaLogin(this));
	this.setVisible(true);
  
    }

    public void cambiarAPantalla(Class<?> clase) throws IOException, SQLException {
	Class<?> pantallaActual = this.getContentPane().getClass();

        if(clase.equals(PantallaMenu.class)) {
            screenHistory.clear();
        } else {
            this.screenHistory.push(pantallaActual);
        }
	this.getContentPane().setVisible(false);
	
	if(clase.equals(PantallaLogin.class)) {
	    this.setJMenuBar(null);
	    this.setContentPane(new PantallaLogin(this));

	}
	if(clase.equals(PantallaMenu.class)) {
	    PantallaMenu pantalla = new PantallaMenu(this);
	    this.setContentPane(pantalla);
	    this.setJMenuBar(pantalla.getMenuBar());
	   
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
	if(clase.equals(PantallaVerPrevision.class)) {
	    this.setContentPane(new PantallaVerPrevision(this));
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
	    
    
  
    public void cambiarAPantalla(Class<?> clase,Empleado empleado) throws IOException, SQLException {
	Class<?> pantallaActual = this.getContentPane().getClass();
	
   	this.getContentPane().setVisible(false);
   	if(clase.equals(PantallaMenu.class)) {
            screenHistory.clear();
        } else {
            this.screenHistory.push(pantallaActual);
        }
   	if(clase.equals(PantallaLogin.class)) {
   	    this.setJMenuBar(null);
   	    this.setContentPane(new PantallaLogin(this));
   	}
   	if(clase.equals(PantallaMenu.class)) {
   	 this.setContentPane(new PantallaMenu(this));
   	}
   	if(clase.equals(PantallaVerEmpleados.class)) {
   	    this.setContentPane(new PantallaVerEmpleados(this));
   	}
   	if(clase.equals(PantallaAltaEmpleado.class)) {
   	    this.setContentPane(new PantallaAltaEmpleado(this, empleado));
   	}
   	if(clase.equals(PantallaCargarPrevision.class)) {
   	    this.setContentPane(new PantallaCargarPrevision(this));
   	}
   	if(clase.equals(PantallaVerPrevision.class)) {
	    this.setContentPane(new PantallaVerPrevision(this));
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
   	
    public void navegarAtras() throws IOException, SQLException {
        if (!screenHistory.isEmpty()) {
            Class<?> previousScreen = screenHistory.pop();
            cambiarAPantalla(previousScreen);
        }
    }
    
}
