package interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import clases.Empleado;
import utils.DAO;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaVerEmpleados extends JPanel {
    private Ventana ventana;
    private JTable table;
    private JTable table_1;
    public PantallaVerEmpleados (Ventana v) {
	this.setPreferredSize(new Dimension(800, 600));
	setLayout(null);
	
	JMenuBar menuBar = new JMenuBar();
	menuBar.setBounds(0, 0, 800, 22);
	add(menuBar);
	
	JMenu mnNewMenu = new JMenu("Empleados");
	menuBar.add(mnNewMenu);
	
	JMenuItem mntmNewMenuItem = new JMenuItem("Alta Empleado");
	mntmNewMenuItem.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaAltaEmpleado.class);
	        } catch (IOException e1) {
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
	        }
	    }
	});

	mnNewMenu.add(mntmNewMenuItem_1);
	
	JMenu mnNewMenu_1 = new JMenu("Planning");
	menuBar.add(mnNewMenu_1);
	
	JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cargar Prevision");
	mntmNewMenuItem_2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaCargarPrevision.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_1.add(mntmNewMenuItem_2);
	
	JMenuItem mntmNewMenuItem_3 = new JMenuItem("Requisitos");
	mntmNewMenuItem_3.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaRequisitosEmpleado.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_1.add(mntmNewMenuItem_3);
	
	JMenuItem mntmNewMenuItem_4 = new JMenuItem("Turnos");
	mntmNewMenuItem_4.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaTurnos.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_1.add(mntmNewMenuItem_4);
	
	JMenu mnNewMenu_2 = new JMenu("T&A");
	menuBar.add(mnNewMenu_2);
	
	JMenuItem mntmNewMenuItem_5 = new JMenuItem("Abrir T&A");
	mntmNewMenuItem_5.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaTAndA.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_2.add(mntmNewMenuItem_5);
	
	JMenu mnNewMenu_3 = new JMenu("Informes");
	menuBar.add(mnNewMenu_3);
	JMenuItem mntmNewMenuItem_6 = new JMenuItem("Generar Informe");
	mntmNewMenuItem_6.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaInformes.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_3.add(mntmNewMenuItem_6);
	//FIN DEL MENU
	
	
	
	JLabel lblNewLabel = new JLabel("Vista Empleados");
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setBounds(10, 33, 114, 22);
	add(lblNewLabel);
	
	
	
	

	String[] columnNames = {
		    "ID Empleado", 
		    "Apellidos", 
		    "Nombre", 
		    "Grupo_Empleados", 
		    "Coeficiente_Parcialidad", 
		    "Caja", 
		    "Almacen", 
		    "Atencion_Publico", 
		    "Supervisor", 
		    "fecha"
		};

		// Crear el modelo de la tabla con los nombres de las columnas
		DefaultTableModel model = new DefaultTableModel(null, columnNames) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // Todas las celdas no son editables
		        return false;
		}
		
		    };
		// Crear la tabla con el modelo
		table_1 = new JTable(model);

		// Añadir la tabla a un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table_1);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    System.out.println("funciona");
			    if(e.getClickCount()==2) {
				
			    
			    int row = table_1.rowAtPoint(e.getPoint());
			        if (row >= 0) {
			            String idEmpleado = (String)table_1.getValueAt(row, 0);
			            try {
					Empleado empleadoSeleccionado = new Empleado(idEmpleado);
					v.cambiarAPantalla(PantallaAltaEmpleado.class,empleadoSeleccionado);
				    } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				    } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				    }
			        }
			    }      
		}
		});
		scrollPane.setBounds(32, 66, 729, 481);
		add(scrollPane);
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_1.getModel());
		table_1.setRowSorter(sorter);
	ArrayList<String> listaEmpleados = new ArrayList<>();
	try {
	    listaEmpleados = DAO.select("SELECT `ID Empleado`, `Apellidos`, `Nombre`, `Grupo_Empleados`, `Coeficiente_Parcialidad`, `Funcion: Caja`, `Funcion: Almacen`, `Funcion: Atencion_Publico`, `Funcion: Supervisor`, `fecha` FROM empleados");
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}

	for (int i = 0; i < listaEmpleados.size(); i += 10) {
	    String idEmpleado = listaEmpleados.get(i);
	    String apellidos = listaEmpleados.get(i + 1);
	    String nombre = listaEmpleados.get(i + 2);
	    String grupoEmpleados = listaEmpleados.get(i + 3);
	    String coeficienteParcialidad = listaEmpleados.get(i + 4);
	    String funcionCaja = listaEmpleados.get(i + 5);
	    String funcionAlmacen = listaEmpleados.get(i + 6);
	    String funcionAtencionPublico = listaEmpleados.get(i + 7);
	    String funcionSupervisor = listaEmpleados.get(i + 8);
	    String fecha = listaEmpleados.get(i + 9);

	    Object[] data = {idEmpleado, apellidos, nombre, grupoEmpleados, coeficienteParcialidad, funcionCaja, funcionAlmacen, funcionAtencionPublico, funcionSupervisor, fecha};
	    model.addRow(data);
	}

	
	

    }
}
