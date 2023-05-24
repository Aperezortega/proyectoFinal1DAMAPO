package interfaces;

    import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;

    public abstract class Pantalla extends JPanel {
        protected JMenuBar menuBar;
        protected Ventana ventana;
        public Pantalla(Ventana v) {
            this.ventana = v;
            menuBar = new MenuBar(ventana);
            
        }
        
        public JMenuBar getMenuBar() {
            return menuBar;
        }

    }



