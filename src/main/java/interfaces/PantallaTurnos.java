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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

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
import utils.DAO;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class PantallaTurnos extends Pantalla{
    private DatePicker inicioDatePicker;
    private DatePicker finDatePicker;
    private DatePicker turnosDatePicker;
    private Ventana ventana;
    private TurnoChart turnoChart;
    private ArrayList<Turno> turnos = new ArrayList<>();
    Planificador p = new Planificador();
    PrevisionFecha previsionFecha;
    JComboBox<Turno> turnosComboBox = new JComboBox<>();
    private JTable tableEmpleadosDisponibles;
    private ArrayList<Empleado>plantilla;
    private ArrayList<String> listaEmpleados;
    private Turno selectedTurno;
    public PantallaTurnos(Ventana v) throws SQLException {
	super(v);
	this.setPreferredSize(new Dimension(1200, 1000));
	setLayout(null);
	plantilla = new ArrayList<>();
	try {
	    listaEmpleados = DAO.selectAndPrint("SELECT `ID Empleado`, `Apellidos`, `Nombre`, `Grupo_Empleados`, `Coeficiente_Parcialidad`, `Funcion: Caja`, `Funcion: Almacen`, `Funcion: Atencion_Publico`, `Funcion: Supervisor`, `fecha` FROM empleados");
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}
	Empleado empleado;
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
	    empleado = new Empleado(idEmpleado);
	    plantilla.add(empleado);
	}
	
	 // Crear los componentes de selección de fecha
        inicioDatePicker = new DatePicker();
        inicioDatePicker.setBounds(45, 46, 181, 35);
        inicioDatePicker.getComponentToggleCalendarButton().setBounds(141, 5, 26, 25);
        inicioDatePicker.getComponentDateTextField().setBounds(10, 5, 137, 25);
        add(inicioDatePicker);
        inicioDatePicker.setLayout(null);

        finDatePicker = new DatePicker();
        finDatePicker.getComponentToggleCalendarButton().setBounds(131, 5, 26, 25);
        finDatePicker.getComponentDateTextField().setBounds(0, 5, 137, 25);
        finDatePicker.setBounds(260, 46, 167, 35);
        add(finDatePicker);
        finDatePicker.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Fecha Inicio");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(59, 24, 161, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Fecha Fin");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(260, 24, 167, 14);
        add(lblNewLabel_1);
        
     // Crear el componente JComboBox
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(459, 51, 116, 25);
        comboBox.addItem("Ver Turnos");
        comboBox.addItem("Generar Turnos");
        add(comboBox);
        
     

        // Manejar el evento de selección del JComboBox
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                System.out.println("Opción seleccionada: " + selectedOption);
            }
        });
        JButton btnNewButton = new JButton("Ejecutar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtén las fechas de inicio y fin
                LocalDate fechaInicio = inicioDatePicker.getDate();
                LocalDate fechaFin = finDatePicker.getDate();

                // Genera la lista de todas las fechas en el rango
                long numOfDaysBetween = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
                Stream<LocalDate> fechas = Stream.iterate(fechaInicio, date -> date.plusDays(1))
                        .limit(numOfDaysBetween + 1);

                // Llama a la función generarTurnos() para cada fecha en la lista
                fechas.forEach(date -> {
                    // Aquí tendrías que crear tu objeto PrevisionFecha
                    // Para este ejemplo, asumiré que tienes un método que retorna un objeto PrevisionFecha dado un LocalDate
                    try {
                        previsionFecha = new PrevisionFecha(date);
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    // Ahora generamos los turnos
                    try {
                        List<Turno> turnosGenerados = p.generarTurnos(previsionFecha);
                        // Aquí puedes hacer lo que quieras con los turnos generados
                        // Por ejemplo, podrías agregarlos a tu lista de turnos
                        turnos.addAll(turnosGenerados);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    turnosDatePicker.setDate(fechaInicio);
                    // Actualiza los datos del gráfico con los nuevos turnos generados
                    turnoChart.setDatos(fechaInicio, turnos);  // Asegúrate de pasar la fecha correspondiente aquí

                    // Actualiza el JComboBox con los IDs de los nuevos turnos
                    turnosComboBox.removeAllItems();
                    for (Turno t : turnos) {
                        turnosComboBox.addItem(t);
                    }
                    String chartTitle = "Turnos " + fechaInicio.toString();
                    turnoChart.getChart().setTitle(chartTitle);
                    
                    actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno,plantilla));
                    
                });
            }
        });
        btnNewButton.setBounds(633, 52, 89, 23);
        add(btnNewButton);
        
        
        turnosDatePicker = new DatePicker();
        turnosDatePicker.setBounds(45, 112, 181, 35);
        turnosDatePicker.getComponentToggleCalendarButton().setBounds(135, 5, 26, 25);
        turnosDatePicker.getComponentDateTextField().setBounds(10, 5, 131, 25);
        add(turnosDatePicker);
        turnosDatePicker.setLayout(null);
        
        JButton leftArrow = new JButton("<="); // usa tu propio texto o icono
        leftArrow.setBounds(59, 158, 54, 35); // ajusta la posición y el tamaño según lo necesites
        add(leftArrow);

        JButton rightArrow = new JButton("=>"); // usa tu propio texto o icono
        rightArrow.setBounds(149, 158, 54, 35); // ajusta la posición y el tamaño según lo necesites
        add(rightArrow);

        // manejador de eventos para la flecha izquierda
        leftArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate currentDate = turnosDatePicker.getDate();
                LocalDate prevDate = currentDate.minusDays(1);
                turnosDatePicker.setDate(prevDate);
                turnoChart.actualizarChart(prevDate);
                String chartTitle = "Turnos " + turnosDatePicker.getDate().toString();
                turnoChart.getChart().setTitle(chartTitle);
                actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno,plantilla));
            }
        });


        // manejador de eventos para la flecha derecha
        rightArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate currentDate = turnosDatePicker.getDate();
                LocalDate nextDate = currentDate.plusDays(1);
                turnosDatePicker.setDate(nextDate);
                turnoChart.actualizarChart(nextDate);
                String chartTitle = "Turnos " + turnosDatePicker.getDate().toString();
                turnoChart.getChart().setTitle(chartTitle);
                actualizarTablaEmpleados(verEmpleadosDisponiblesArrayList(selectedTurno,plantilla));
            }
        });

     // Añade esto después de tu turnoDatePicker en tu constructor PantallaTurnos

     // Crear un JComboBox para la selección de turnos
     
     turnosComboBox.setBounds(45, 204, 167, 35);  // ajusta la posición y tamaño según tus necesidades
     add(turnosComboBox);
     
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
     for (Turno t : turnos) {
         turnosComboBox.addItem(t);
     }
     
     

     // Crear los TimePickers para la selección de hora de inicio y fin
     TimePicker inicioTimePicker = new TimePicker();
     inicioTimePicker.setBounds(45, 250, 167, 35);  // ajusta la posición y tamaño según tus necesidades
     add(inicioTimePicker);

     TimePicker finTimePicker = new TimePicker();
     finTimePicker.setBounds(45, 296, 167, 35);  // ajusta la posición y tamaño según tus necesidades
     add(finTimePicker);

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
	        }
	    }
	});
    
	    turnoChart = new TurnoChart("Turnos " ,turnos, turnosDatePicker.getDate());
	

        // Configura los datos de turno en el gráfico
        // Asegúrate de tener una lista válida de turnos para pasársela a setDatos
        // turnoChart.setDatos(tusTurnos);

        // Añadir el ChartPanel del gráfico al panel de la aplicación
        ChartPanel chartPanel = turnoChart.getChartPanel();
        chartPanel.setBounds(275, 120, 850, 750);  // Puedes cambiar estos valores para ajustar la posición y el tamaño del gráfico
        add(chartPanel);
        
        JButton btnNewButton_1 = new JButton("Actualizar turno");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    // Obtén el turno seleccionado del JComboBox
        	        Turno selectedTurno = (Turno) turnosComboBox.getSelectedItem();

        	        // Obtén las horas de inicio y fin seleccionadas de los TimePickers
        	        LocalTime horaInicio = inicioTimePicker.getTime();
        	        LocalTime horaFin = finTimePicker.getTime();

        	        // Actualiza las horas de inicio y fin del turno seleccionado
        	        selectedTurno.setHoraInicio(horaInicio);
        	        selectedTurno.setHoraFin(horaFin);

        	        // Actualiza el gráfico con los nuevos datos
        	        turnoChart.actualizarChart(turnosDatePicker.getDate());
        	    
        	}
        });
        btnNewButton_1.setBounds(45, 670, 167, 23);
        add(btnNewButton_1);
        
        tableEmpleadosDisponibles = new JTable();
        tableEmpleadosDisponibles.setBounds(30, 365, 196, 198);
        add(tableEmpleadosDisponibles);
    	}
    // Método para actualizar la tabla con los empleados disponibles
    public void actualizarTablaEmpleados(List<Empleado> empleadosDisponibles) {
        // Crear el modelo de tabla con las columnas correspondientes
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Grupo");
        model.addColumn("Funciones");
        model.addColumn("Horas");

        // Agregar los empleados disponibles al modelo de tabla
        for (Empleado empleado : empleadosDisponibles) {
            model.addRow(new Object[]{empleado.getNombre(), empleado.getGrupo(), empleado.getFunciones(), empleado.getHorasBaseContrato()});
        }

        // Actualizar el modelo de tabla con los nuevos datos
        tableEmpleadosDisponibles.setModel(model);
    }
    public ArrayList<Empleado> verEmpleadosDisponiblesArrayList(Turno selectedTurno, ArrayList<Empleado> plantilla) {
	    ArrayList<Empleado> empleadosDisponibles = new ArrayList<>();

	    TipoTurno tipoTurno = selectedTurno.getTipoTurno();
	    LocalDate fecha = selectedTurno.getFechaTurno();

	    for (Empleado empleado : plantilla) {
	        switch (tipoTurno) {
	            case MAÑANA:
	                if (empleado.isWorkinMañanas(fecha)) {
	                    empleadosDisponibles.add(empleado);
	                }
	                break;
	            case TARDE:
	                if (empleado.isWorkinTardes(fecha)) {
	                    empleadosDisponibles.add(empleado);
	                }
	                break;
	        }
	    }
	    System.out.println(empleadosDisponibles.toString());
	    return empleadosDisponibles;
	}
    
}

