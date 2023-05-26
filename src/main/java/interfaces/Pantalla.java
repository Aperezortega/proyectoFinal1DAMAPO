package interfaces;

    import javax.swing.JPanel;

import clases.Empleado;
import enums.Funcion;
import utils.Session;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;

    public abstract class Pantalla extends JPanel {
        protected JMenuBar menuBar;
        protected Ventana ventana;
        public Pantalla(Ventana v) {
            this.ventana = v;
            Empleado empleadoActual = Session.getInstance().getEmpleadoActual();
            if(!(this instanceof PantallaLogin)) {
        	menuBar = new MenuBar(ventana);
            }
            
            
        }
        
        public JMenuBar getMenuBar() {
            return menuBar;
        }

    }



