package interfaces;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartPanel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import clases.Empleado;
import clases.Planificador;
import clases.PrevisionFecha;
import clases.PrevisionHora;
import clases.Turno;
import enums.Funcion;
import enums.TipoTurno;
import exceptions.EmpleadoNoDisponibleExcepcion;
import exceptions.FechaSinPrevisionExcepcion;
import exceptions.IdTurnoRepetidaExcepcion;
import utils.DAO;
import utils.Session;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PantallaTurnos extends Pantalla{
    private DatePicker inicioDatePicker;
    private DatePicker finDatePicker;
    private DatePicker turnosDatePicker;
    private Ventana ventana;
    private TurnoChart turnoChart;
    private ArrayList<Turno> turnos = new ArrayList<>();
    PrevisionFecha previsionFecha;
    JComboBox<Turno> turnosComboBox = new JComboBox<>();
    private JTable tablaEmpleadosDisponibles;
    private ArrayList<Empleado>plantilla;
    private Turno selectedTurno;
    private Planificador p = new Planificador();
    private ChronoLocalDate fechaUltimaPrevision;
    private ChronoLocalDate fechaUltimoTurno;
   
   

    
    
    public PantallaTurnos(Ventana v) throws SQLException {
	super(v);
	setFocusable(false);
	this.setPreferredSize(new Dimension(1500, 1000));
	setLayout(null);
	plantilla = Session.getInstance().getPlantilla();
	try {
	    String fechaUltimaPrevisionString = DAO.select("SELECT MAX(fecha) AS ultima_fecha FROM previsiones").toString().replace("[", "").replace("]", "");
	    if (fechaUltimaPrevisionString != null && !fechaUltimaPrevisionString.isEmpty()) {
	        fechaUltimaPrevision = LocalDate.parse(fechaUltimaPrevisionString);
	    } else {
	        fechaUltimaPrevision = null; // Si no hay resultados, asigna null a la variable
	    }
	} catch (DateTimeParseException e) {
	    fechaUltimaPrevision = null; // Si hay un error al parsear la fecha, asigna null a la variable
	}

	
	 try {
		    String fechaUltimoTurnoString = DAO.select("SELECT MAX(fecha_turno) AS ultima_fecha FROM turnos").toString().replace("[", "").replace("]", "");
		    if (fechaUltimoTurnoString != null && !fechaUltimoTurnoString.isEmpty()) {
		        fechaUltimoTurno = LocalDate.parse(fechaUltimoTurnoString);
		    } else {
		        fechaUltimoTurno = null; // Si no hay resultados, asigna null a la variable
		    }
		} catch (DateTimeParseException e) {
		    fechaUltimoTurno = null; // Si hay un error al parsear la fecha, asigna null a la variable
		}
	
	
	 // Crear los componentes de selección de fecha
        inicioDatePicker = new DatePicker();
        inicioDatePicker.setBounds(417, 46, 181, 35);
        inicioDatePicker.getComponentToggleCalendarButton().setBounds(141, 5, 26, 25);
        inicioDatePicker.getComponentDateTextField().setBounds(10, 5, 137, 25);
        add(inicioDatePicker);
        inicioDatePicker.setLayout(null);

        finDatePicker = new DatePicker();
        finDatePicker.getComponentToggleCalendarButton().setBounds(131, 5, 26, 25);
        finDatePicker.getComponentDateTextField().setBounds(0, 5, 137, 25);
        finDatePicker.setBounds(608, 46, 167, 35);
        add(finDatePicker);
        finDatePicker.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Fecha Inicio");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(431, 24, 161, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Fecha Fin");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(608, 24, 167, 14);
        add(lblNewLabel_1);
        
     // Crear el componente JComboBox
        JComboBox<String> accionComboBox = new JComboBox<>();
        accionComboBox.setBounds(787, 51, 116, 25);
        accionComboBox.addItem("Ver Turnos");
        accionComboBox.addItem("Generar Turnos");
        add(accionComboBox);
        
     

        // Manejar el evento de selección del JComboBox
        accionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) accionComboBox.getSelectedItem();
                System.out.println("Opción seleccionada: " + selectedOption);
            }
        });
        JButton btnNewButton = new JButton("Ejecutar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtén las fechas de inicio y fin
                LocalDate fechaInicio = inicioDatePicker.getDate();
                LocalDate fechaFin = finDatePicker.getDate();
               
        	   
               
                if (fechaFin.isBefore(fechaInicio)) {
                    // Muestra un mensaje de error y retorna
                    JOptionPane.showMessageDialog(null, "La fecha de fin no puede ser anterior a la fecha de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Verificar si la fecha de fin es mayor a la fecha de fechaUltimaPrevision
                if (fechaUltimaPrevision == null || fechaFin.isAfter(fechaUltimaPrevision)) {
                    
                    try {
                        throw new FechaSinPrevisionExcepcion("Estas intentando generar o ver turnos para un dia en el que no tenemos cargada la prevision");
                    } catch (FechaSinPrevisionExcepcion ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
                // Genera la lista de todas las fechas en el rango
                long numOfDaysBetween = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
                Stream<LocalDate> fechas = Stream.iterate(fechaInicio, date -> date.plusDays(1))
                        .limit(numOfDaysBetween + 1);
                String selectedAction = (String) accionComboBox.getSelectedItem();
                if (selectedAction.equals("Generar Turnos")) {
                    // Llama a la función generarTurnos() para cada fecha en la lista
                   
                    try {
                	
                	    if (fechaUltimoTurno != null && finDatePicker.getDate().isBefore(fechaUltimoTurno) || fechaUltimoTurno != null &&  finDatePicker.getDate().isEqual(fechaUltimoTurno)||fechaUltimoTurno != null && inicioDatePicker.getDate().isBefore(fechaUltimoTurno)) {
                	        throw new IdTurnoRepetidaExcepcion("Estas intentando generar o ver turnos para un dia en el que no tenemos cargada la prevision");
                	    }
                	} catch (IdTurnoRepetidaExcepcion ex) {
                	    JOptionPane.showMessageDialog(null,
                	        ex.getMessage(),
                	        " Ya hay turnos Generados",
                	        JOptionPane.ERROR_MESSAGE);
                	    return;
                	}     
                    fechas.forEach(date -> {
                        
  
                        try {
                            previsionFecha = new PrevisionFecha(date);
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        // Ahora generamos los turnos
                        try {
                            List<Turno> turnosGenerados = p.generarTurnos(previsionFecha);                    
                            turnos.addAll(turnosGenerados);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                           
                        	 JOptionPane.showMessageDialog(null,
                                         "Selecciona Ver Turnos",
                                         " Ya hay turnos Generados",
                                         JOptionPane.ERROR_MESSAGE);
                        }

                        turnosDatePicker.setDate(fechaInicio);

                        turnoChart.setDatos(fechaInicio, turnos); 

                    
                        turnosComboBox.removeAllItems();
                        for (Turno turno : turnos) {
                            if (turno.getFechaTurno().equals(turnosDatePicker.getDate())) {
                                turnosComboBox.addItem(turno);
                            }
                        }
                        String chartTitle = "Turnos " + fechaInicio.toString();
                        turnoChart.getChart().setTitle(chartTitle);

                        actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno,plantilla));
                        if(fechaUltimoTurno==null) {
                            fechaUltimoTurno = finDatePicker.getDate();
                         
                        }
                    });
                } else {
                    String query = "select id_turno from turnos where fecha_turno between '"
                            + fechaInicio.toString()
                            + "' and '"
                            + fechaFin.toString()
                            + "'";
                    ArrayList<String> turnosIdList;
                    try {
                        turnosIdList = DAO.selectAndPrint(query);
                        if (turnosIdList.isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                    "No hay turnos generados en el rango de fechas seleccionado",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return; 
                        }
                        for (String idTurno : turnosIdList) {
                            try {
                                Turno turno = new Turno(idTurno);

                                turnos.add(turno);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    turnosDatePicker.setDate(fechaInicio);
                    // Actualiza los datos del gráfico con los nuevos turnos generados
                    turnoChart.setDatos(fechaInicio, turnos); 

                    // Actualiza el JComboBox con los IDs de los nuevos turnos
                    turnosComboBox.removeAllItems();
                    for (Turno turno : turnos) {
                        if (turno.getFechaTurno().equals(turnosDatePicker.getDate())) {
                            turnosComboBox.addItem(turno);
                        }
                    }
                    String chartTitle = "Turnos " + fechaInicio.toString();
                    turnoChart.getChart().setTitle(chartTitle);

                    actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno,plantilla));
                    if(fechaUltimoTurno==null) {
                        fechaUltimoTurno = finDatePicker.getDate();
                   }
                    
                }
            } // Cierre de la función actionPerformed
        }); // Cierre de la clase anónima ActionListener
        btnNewButton.setBounds(938, 52, 89, 23);
        add(btnNewButton);

        
        
        turnosDatePicker = new DatePicker();
        turnosDatePicker.setBounds(45, 112, 181, 35);
        turnosDatePicker.getComponentToggleCalendarButton().setBounds(135, 5, 26, 25);
        turnosDatePicker.getComponentDateTextField().setBounds(10, 5, 131, 25);
        add(turnosDatePicker);
        turnosDatePicker.setLayout(null);
        
        JButton leftArrow = new JButton("<="); 
        leftArrow.setBounds(59, 158, 54, 35);
        add(leftArrow);
       
        leftArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate currentDate = turnosDatePicker.getDate();
                LocalDate prevDate = currentDate.minusDays(1);
                if (prevDate.isBefore(inicioDatePicker.getDate())) {
                    prevDate = inicioDatePicker.getDate();
                }

                turnosDatePicker.setDate(prevDate);
                turnoChart.actualizarChart(prevDate);
                String chartTitle = "Turnos " + turnosDatePicker.getDate().toString();
                turnoChart.getChart().setTitle(chartTitle);
                actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno, plantilla));
                turnosComboBox.removeAllItems();
                LocalDate fechaSeleccionada = turnosDatePicker.getDate();
                for (Turno turno : turnos) {
                    if (turno.getFechaTurno().equals(fechaSeleccionada)) {
                        turnosComboBox.addItem(turno);
                    }
                }

                // Actualizar la selección del turno en turnosComboBox
                actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno, plantilla));
            }
        });

        JButton rightArrow = new JButton("=>"); 
        rightArrow.setBounds(149, 158, 54, 35); 
        add(rightArrow);
        // manejador de eventos para la flecha derecha
        rightArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate currentDate = turnosDatePicker.getDate();
                LocalDate nextDate = currentDate.plusDays(1);
                if (nextDate.isAfter(fechaUltimoTurno)) {
                    nextDate = (LocalDate) fechaUltimoTurno;
                }

                turnosDatePicker.setDate(nextDate);
                turnoChart.actualizarChart(nextDate);
                String chartTitle = "Turnos " + turnosDatePicker.getDate().toString();
                turnoChart.getChart().setTitle(chartTitle);
                actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno, plantilla));
                turnosComboBox.removeAllItems();
                LocalDate fechaSeleccionada = turnosDatePicker.getDate();
                for (Turno turno : turnos) {
                    if (turno.getFechaTurno().equals(fechaSeleccionada)) {
                        turnosComboBox.addItem(turno);
                    }
                }

                // Actualizar la selección del turno en turnosComboBox
                actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno, plantilla));
            }
        });
        // Crear un JComboBox para la selección de turnos
        turnosComboBox.setBounds(45, 219, 167, 35); 
        add(turnosComboBox);
        LocalDate fechaSeleccionada = turnosDatePicker.getDate();
        

        for (Turno turno : turnos) {
            if (turno.getFechaTurno().equals(turnosDatePicker.getDate())) {
                turnosComboBox.addItem(turno);
            }
        }
        //para que se vean solamente los ids del turno
        turnosComboBox.setRenderer(new DefaultListCellRenderer() {
   	    @Override
   	    public Component getListCellRendererComponent(JList<?> list, Object value,
   	                                                  int index, boolean isSelected, boolean cellHasFocus) {
   	        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
   	        if (value instanceof Turno) {
   	            Turno turno = (Turno) value;
   	            setText(turno.getIdTurno());
   	        }
   	        return this;
   	    }
   	});

        // Llenar el JComboBox con los turnos
       

     // Crear los TimePickers para la selección de hora de inicio y fin
     TimePicker inicioTimePicker = new TimePicker();
     inicioTimePicker.setBounds(45, 284, 167, 35);
     add(inicioTimePicker);

     TimePicker finTimePicker = new TimePicker();
     finTimePicker.setBounds(45, 350, 167, 35); 
     add(finTimePicker);
     JComboBox<Empleado> empleadosComboBox = new JComboBox();
     empleadosComboBox.setBounds(45, 419, 167, 35);
     empleadosComboBox.addItem(null);
     add(empleadosComboBox);
     empleadosComboBox.setRenderer(new DefaultListCellRenderer() {
	    @Override
	    public Component getListCellRendererComponent(JList<?> list, Object value,
	                                                  int index, boolean isSelected, boolean cellHasFocus) {
	        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        if (value instanceof Empleado) {
	            Empleado empleado = (Empleado) value;
	            setText(empleado.getNombre()+" "+empleado.getApellidos());
	        }else {
	            setText("no asignar empleado");
	        }
	        return this;
	    }
	});

     // Manejar el evento de cambio de turno seleccionado en el JComboBox
     turnosComboBox.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // Obtener el ID del turno seleccionado
	        selectedTurno = ((Turno) turnosComboBox.getSelectedItem());
	        
	        // Si se encontró el turno, actualizar los TimePickers con la hora de inicio y fin del turno seleccionado
	        if (selectedTurno != null) {
	            inicioTimePicker.setTime(selectedTurno.getHoraInicio());
	            finTimePicker.setTime(selectedTurno.getHoraFin());
	            actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno,plantilla));
	         // Limpiar el modelo actual del ComboBox empleadosComboBox
	            DefaultComboBoxModel<Empleado> modeloEmpleados = (DefaultComboBoxModel<Empleado>) empleadosComboBox.getModel();
	            modeloEmpleados.removeAllElements();
	            modeloEmpleados.addElement(null);
	            Empleado empleadoAsignado = selectedTurno.getEmpleado();
	            if (empleadoAsignado != null) {
	                modeloEmpleados.addElement(empleadoAsignado);
	            }
	            // Llenar el modelo del ComboBox empleadosComboBox con los empleados disponibles
	            for (Empleado empleado : verEmpleadosDisponiblesArrayList(selectedTurno,plantilla)) {
	                modeloEmpleados.addElement(empleado);
	            }

	            // Actualizar el ComboBox empleadosComboBox con el nuevo modelo
	            empleadosComboBox.setModel(modeloEmpleados);
	            
	        }
	    }
	});
	    turnoChart = new TurnoChart("Turnos " ,turnos, turnosDatePicker.getDate());
	

 

       
        ChartPanel chartPanel = turnoChart.getChartPanel();
        chartPanel.setBounds(275, 120, 850, 750);  
        add(chartPanel);
        
        JButton actualizarTurnoButton = new JButton("Actualizar turno");
        actualizarTurnoButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    // Obtén el turno seleccionado del JComboBox
        	        Turno selectedTurno = (Turno) turnosComboBox.getSelectedItem();

        	        // Obtén las horas de inicio y fin seleccionadas de los TimePickers
        	        LocalTime horaInicio = inicioTimePicker.getTime();
        	        LocalTime horaFin = finTimePicker.getTime();
        	        Empleado empleadoSeleccionado = (Empleado) empleadosComboBox.getSelectedItem();

        	        // Actualiza las horas de inicio y fin del turno seleccionado
        	        selectedTurno.setHoraInicio(horaInicio);
        	        selectedTurno.setHoraFin(horaFin);

        	        // Actualiza el gráfico con los nuevos datos
        	        selectedTurno.setEmpleado(empleadoSeleccionado);
        	        turnoChart.actualizarChart(turnosDatePicker.getDate());
        	        if (empleadoSeleccionado != null) {
        	            try {
    			    DAO.insert("UPDATE turnos SET id_empleado= '"+ empleadoSeleccionado.getIdEmpleado()+"', hora_inicio = '"+horaInicio+"', hora_fin = '"+horaFin+"' WHERE id_turno = '" + selectedTurno.getIdTurno()+"'");
    			    actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno, plantilla));
            	        } catch (SQLException e1) {
    			    // TODO Auto-generated catch block
    			    e1.printStackTrace();
    			   
    			} 
        	       }else {
        		   try {
       			    DAO.insert("UPDATE turnos SET id_empleado=  null, hora_inicio = '"+horaInicio+"', hora_fin = '"+horaFin+"' WHERE id_turno = '" + selectedTurno.getIdTurno()+"'");
       			    actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno, plantilla));
               	        } catch (SQLException e1) {
       			    // TODO Auto-generated catch block
       			    e1.printStackTrace();
               	        	}
        	
        	       }
        		     
        	}      
        	       
        	

        });
        actualizarTurnoButton.setBounds(45, 477, 167, 23);
        add(actualizarTurnoButton);

	String[] columnNames = {
		     
		    "Nombre", 
		    "Grupo_Empleados", 
		    "Funciones", 
		   
		};

		// Crear el modelo de la tabla con los nombres de las columnas
		DefaultTableModel model = new DefaultTableModel(null, columnNames) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // Todas las celdas no son editables
		        return false;
		}
		
		    };
        tablaEmpleadosDisponibles = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaEmpleadosDisponibles);
        scrollPane.setFocusable(false);
        scrollPane.setViewportBorder(UIManager.getBorder("TextPane.border"));
        scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
        scrollPane.setBounds(1169, 122, 289, 600);
        add(scrollPane);
        //tablaEmpleadosDisponibles.setBounds(1169, 122, 289, 600);
        //add(tablaEmpleadosDisponibles);
        
        JLabel lblNewLabel_2 = new JLabel("Seleccionar Turno");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(45, 204, 165, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Hora Inicio");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(45, 265, 167, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Hora Fin");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(45, 335, 158, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Fecha Turnos");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setBounds(45, 92, 181, 14);
        add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Seleccionar Accion");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_6.setBounds(787, 24, 116, 14);
        add(lblNewLabel_6);
        
        JLabel labelUltimaPrevision = new JLabel("Prevision cargada hasta :"+fechaUltimaPrevision);
        labelUltimaPrevision.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelUltimaPrevision.setBounds(46, 46, 241, 14);
        add(labelUltimaPrevision);
        
        JLabel labelUltimoTurno = new JLabel("Turnos cargados hasta : "+fechaUltimoTurno);
        labelUltimoTurno.setHorizontalAlignment(SwingConstants.CENTER);
        labelUltimoTurno.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelUltimoTurno.setBounds(45, 67, 229, 14);
        add(labelUltimoTurno);
        
        JLabel lblNewLabel_7 = new JLabel("Seleccionar empleado");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_7.setBounds(45, 396, 167, 14);
        add(lblNewLabel_7);
        
        JButton btnNewButton_2 = new JButton("Crear otro turno");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    JTextField idTurnoField = new JTextField();
        	    DatePicker fechaTurnoPicker = new DatePicker();
        	    TimePicker horaInicioPicker = new TimePicker();
        	    TimePicker horaFinPicker = new TimePicker();
        	    JComboBox<Empleado> crearTurnoEmpleadosComboBox = new JComboBox();
        	    JComboBox<Funcion> funcionComboBox = new JComboBox<>(Funcion.values());
        	 
        	        for (Empleado empleado : plantilla) {
        	            crearTurnoEmpleadosComboBox.addItem(empleado);
        	        }
        	        crearTurnoEmpleadosComboBox.setRenderer(new DefaultListCellRenderer() {
        	            @Override
        	            public Component getListCellRendererComponent(JList<?> list, Object value,
        	                                                          int index, boolean isSelected, boolean cellHasFocus) {
        	                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        	                if (value instanceof Empleado) {
        	                    Empleado empleado = (Empleado) value;
        	                    setText(empleado.getNombre() + " " + empleado.getApellidos());
        	                } else {
        	                    setText("no asignar empleado");
        	                }
        	                return this;
        	            }
        	        });
        	        Object[] message = {
        	            "ID del turno:", idTurnoField,
        	            "Fecha del turno:", fechaTurnoPicker,
        	            "Hora de inicio:", horaInicioPicker,
        	            "Hora de fin:", horaFinPicker,
        	            "Empleado", crearTurnoEmpleadosComboBox,
        	            "Funcion:", funcionComboBox
        	        };
        	        
        	        int option = JOptionPane.showConfirmDialog(null, message, "Crear turno", JOptionPane.OK_CANCEL_OPTION);
        	        if (option == JOptionPane.OK_OPTION) {
        	            String idTurno = idTurnoField.getText();
        	            LocalDate fechaTurno = fechaTurnoPicker.getDate();
        	            LocalTime horaInicio = horaInicioPicker.getTime();
        	            LocalTime horaFin = horaFinPicker.getTime();
        	            Empleado empleadoSeleccionado = (Empleado) crearTurnoEmpleadosComboBox.getSelectedItem();
        	            Funcion funcion = (Funcion) funcionComboBox.getSelectedItem();
        	            ArrayList<String> consulta = null;

        	            try {
        	                consulta = DAO.selectAndPrint("SELECT * FROM turnos WHERE id_turno = '" + idTurno + "'");
        	            } catch (SQLException e1) {
        	                e1.printStackTrace();
        	            }

        	            try {
        	                if (consulta != null && !consulta.isEmpty()) {
        	                    throw new IdTurnoRepetidaExcepcion("El ID del turno ya existe en la base de datos.");
        	                }

        	                if (!empleadoSeleccionado.puedeTrabajar(fechaTurno)) {
        	                    throw new EmpleadoNoDisponibleExcepcion("El empleado no puede trabajar en la fecha seleccionada.");
        	                }
        	            } catch (IdTurnoRepetidaExcepcion | EmpleadoNoDisponibleExcepcion e2) {
        	                JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        	                return; // Salir del método sin guardar el turno
        	            }
        	            if (horaFin.isBefore(horaInicio)) {
        	        	    JOptionPane.showMessageDialog(null, "Error: La hora de fin del turno no puede ser anterior a la hora de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
        	        	    return; // Salir del método sin guardar el turno
        	        	}
        	            Turno nuevoTurno = new Turno(idTurno, fechaTurno, horaInicio, horaFin, empleadoSeleccionado, funcion);
        	            
        	        }   
        	}
        });
        btnNewButton_2.setBounds(45, 511, 167, 48);
        add(btnNewButton_2);
        
       
       
        
    	}
 // Método para actualizar la tabla con los empleados disponibles
    public void actualizarTablaEmpleados(ArrayList<Empleado> empleados) {
	    // Crear el modelo de tabla con las columnas correspondientes
	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Nombre");
	    model.addColumn("Grupo");
	    model.addColumn("Funciones");
	

	    // Antes de agregar los empleados, limpiar las filas existentes
	    model.setRowCount(0);

	    // Agregar los empleados al modelo de tabla
	    for (Empleado empleado : empleados) {
	        model.addRow(new Object[]{empleado.getNombre()+" "+empleado.getApellidos(), empleado.getGrupo(), empleado.getFunciones()});
	    }

	    // Actualizar el modelo de tabla con los nuevos datos
	    tablaEmpleadosDisponibles.setModel(model);
	}


    public ArrayList<Empleado> verEmpleadosDisponiblesArrayList(Turno selectedTurno, ArrayList<Empleado> plantilla) {
	    ArrayList<Empleado> empleadosDisponibles = new ArrayList<>();

	    TipoTurno tipoTurno = selectedTurno.getTipoTurno();
	    LocalDate fecha = selectedTurno.getFechaTurno();
	    Funcion funcionTurno = selectedTurno.getFuncion();

	    for (Empleado empleado : plantilla) {
	        switch (tipoTurno) {
	            case MAÑANA:
	                if (empleado.isWorkinMañanas(fecha)&& empleado.tieneFuncionBoolean(funcionTurno) && empleado.puedeTrabajar(fecha)) {
	                    empleadosDisponibles.add(empleado);
	                }
	                break;
	            case TARDE:
	                if (empleado.isWorkinTardes(fecha)&& empleado.tieneFuncionBoolean(funcionTurno)&& empleado.puedeTrabajar(fecha)) {
	                    empleadosDisponibles.add(empleado);
	                }
	                break;
	        }
	    }
	    System.out.println(empleadosDisponibles.toString());
	    return empleadosDisponibles;
	}
}

