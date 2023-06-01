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

public class PantallaVerEmpleados extends Pantalla{
    private Ventana ventana;
    private JTable table;
    private JTable table_1;
    private  ArrayList<Empleado> plantilla;
    public PantallaVerEmpleados (Ventana v) {
	super(v);
	
	plantilla = new ArrayList<Empleado>();
	
	
	this.setPreferredSize(new Dimension(800, 600));
	setLayout(null);
	
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
	    listaEmpleados = DAO.select("SELECT `id_empleado`, `Apellidos`, `Nombre`, `Grupo_Empleados`, `Coeficiente_Parcialidad`, `Funcion_caja`, `Funcion_almacen`, `Funcion_atencion_Publico`, `Funcion_supervisor`, `fecha` FROM empleados");
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
