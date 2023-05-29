package interfaces;

import com.github.lgooddatepicker.components.DatePicker;

import clases.PrevisionFecha;
import clases.PrevisionHora;
import utils.DAO;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PantallaVerPrevision extends Pantalla {
    private Ventana ventana;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private DatePicker inicioDatePicker;
    private DatePicker finDatePicker;

    public PantallaVerPrevision(Ventana v) {
        super(v);
        this.setPreferredSize(new Dimension(800, 600));

        // Crear la tabla y el modelo de tabla
        modeloTabla = new DefaultTableModel();
        setLayout(null);
        tabla = new JTable(modeloTabla);
        tabla.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(50, 100, 700, 350);
        add(scrollPane);

        // Agregar columnas a la tabla
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Hora");
        modeloTabla.addColumn("Previsión");
        
        // Crear los componentes de selección de fecha
        inicioDatePicker = new DatePicker();
        inicioDatePicker.setBounds(59, 46, 167, 25);
        inicioDatePicker.getComponentToggleCalendarButton().setBounds(140, 0, 26, 25);
        inicioDatePicker.getComponentDateTextField().setBounds(0, 0, 137, 25);
        add(inicioDatePicker);
        inicioDatePicker.setLayout(null);

        finDatePicker = new DatePicker();
        finDatePicker.getComponentToggleCalendarButton().setBounds(140, 0, 26, 25);
        finDatePicker.getComponentDateTextField().setBounds(0, 0, 137, 25);
        finDatePicker.setBounds(308, 46, 167, 32);
        add(finDatePicker);
        finDatePicker.setLayout(null);

        // Crear botón para mostrar el resultado
        JButton verButton = new JButton("Ver");
        verButton.setBounds(580, 46, 80, 25);
        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate fechaInicio = inicioDatePicker.getDate();
                LocalDate fechaFin = finDatePicker.getDate();
                cargarResultadosEnTabla(fechaInicio, fechaFin);
            }
        });
        add(verButton);
        
        JLabel lblNewLabel = new JLabel("Fecha de inicio");
        lblNewLabel.setBounds(95, 21, 80, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Fecha de fin");
        lblNewLabel_1.setBounds(356, 21, 80, 14);
        add(lblNewLabel_1);
    }

   

    private void cargarResultadosEnTabla(LocalDate fechaInicio,LocalDate fechaFin) {
	try {
	        List<PrevisionFecha> resultados = DAO.selectPrevision("SELECT * FROM previsiones WHERE fecha BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"'");

	        for (PrevisionFecha previsionFecha : resultados) {
	            for (PrevisionHora previsionHora : previsionFecha.getPrevision()) {
	                modeloTabla.addRow(new Object[] {previsionFecha.getFecha(), previsionHora.getHora(), previsionHora.getVisitas()});
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
}
