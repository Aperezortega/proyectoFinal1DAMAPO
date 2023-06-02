package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import clases.Empleado;
import enums.Funcion;
import utils.Session;

public class MenuBar extends JMenuBar{
    private Ventana ventana;
    Empleado empleadoActual = Session.getInstance().getEmpleadoActual();
    
    public MenuBar(Ventana v) {
 
	    this.ventana=v;
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//menuBar.setBounds(0, 0, 600, 22);
		
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			try {
			    try {
				ventana.navegarAtras();
			    } catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			    }
			} catch (IOException e1) {
			    e1.printStackTrace();
			}
		    }
		});
		this.add(btnAtras);
		JMenu mnNewMenu = new JMenu("Empleados");
		this.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Alta Empleado");
		mntmNewMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            v.cambiarAPantalla(PantallaAltaEmpleado.class);
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        } catch (SQLException e1) {
			    // TODO Auto-generated catch block
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
		        } catch (SQLException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    }
		});

		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Planning");
		this.add(mnNewMenu_1);
		
		

		        JMenuItem mntmNewMenuItem_cargar = new JMenuItem("Cargar Prevision");
		        mntmNewMenuItem_cargar.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                try {
		                    v.cambiarAPantalla(PantallaCargarPrevision.class);
		                } catch (IOException e1) {
		                    e1.printStackTrace();
		                } catch (SQLException e1) {
				    // TODO Auto-generated catch block
				    e1.printStackTrace();
				}
		            }
		        });
		        mnNewMenu_1.add(mntmNewMenuItem_cargar);

		        JMenuItem mntmNewMenuItem_ver = new JMenuItem("Ver Prevision");
		        mntmNewMenuItem_ver.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                try {
		                    v.cambiarAPantalla(PantallaVerPrevision.class);
		                } catch (IOException e1) {
		                    e1.printStackTrace();
		                } catch (SQLException e1) {
				    // TODO Auto-generated catch block
				    e1.printStackTrace();
				}
		            }
		        });
		        mnNewMenu_1.add(mntmNewMenuItem_ver);
		
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Turnos");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            v.cambiarAPantalla(PantallaTurnos.class);
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        } catch (SQLException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    }
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_2 = new JMenu("T&A");
		this.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Abrir T&A");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            v.cambiarAPantalla(PantallaTAndA.class);
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        } catch (SQLException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    }
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		

		this.add(Box.createHorizontalGlue());
		JLabel labelEmail = new JLabel(Session.getInstance().getEmpleadoActual().getEmail());
		this.add(labelEmail);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
			    v.cambiarAPantalla(PantallaLogin.class);
			} catch (IOException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			} catch (SQLException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    }
		});
		this.add(btnSalir);   
		
	 
	
    }
 
}
