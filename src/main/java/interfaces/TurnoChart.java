package interfaces;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import clases.Turno;
import enums.Funcion;
import enums.TipoTurno;
import utils.ColoredTask;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TurnoChart extends JPanel {

    private TaskSeriesCollection taskSeriesCollection;
    private JFreeChart chart;
    private List<Turno> todosLosTurnos;
    private LocalDate fechaInicial; 
    
    public TurnoChart(String applicationTitle, List<Turno> todosLosturnos, LocalDate fechaInicial) {
        this.todosLosTurnos = todosLosturnos;
        this.fechaInicial = fechaInicial; // agregar esto
        this.taskSeriesCollection = new TaskSeriesCollection();
        this.chart = createChart(taskSeriesCollection, applicationTitle);
        actualizarChart(fechaInicial); // usar fechaInicial aquí en lugar de LocalDate.now()
    }
    
    
    private JFreeChart createChart(TaskSeriesCollection taskSeriesCollection, String title) {
        JFreeChart chart = ChartFactory.createGanttChart(
            title,
            "Turno",
            "Hora",
            taskSeriesCollection,
            true, 
            true, 
            false
        );
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        
        return chart;
    }
    public void actualizarTurnos(List<Turno> nuevosTurnos) {
	    setDatos(fechaInicial, nuevosTurnos);
	}

    public void setDatos(LocalDate fecha, List<Turno> turnos) {
	    TaskSeriesCollection filteredSeriesCollection = new TaskSeriesCollection();
	    TaskSeries cajaSeries = new TaskSeries("Caja");
	    TaskSeries almacenSeries = new TaskSeries("Almacén");
	    TaskSeries attClienteSeries = new TaskSeries("Atención al Cliente");

	    // Mapa para almacenar los colores asociados a cada tipo de turno
	    Map<Funcion, Paint> colorMap = new HashMap<>();
	    colorMap.put(Funcion.CAJA, Color.BLUE);
	    colorMap.put(Funcion.ALMACEN, Color.GREEN);
	    colorMap.put(Funcion.ATTPUBLICO, Color.RED);

	    for (Turno turno : turnos) {
	        if (turno.getFechaTurno().isEqual(fecha)) {
	            ColoredTask task = new ColoredTask(
	                turno.getIdTurno(),
	                Date.from(turno.getHoraInicio().atDate(fecha).atZone(ZoneId.systemDefault()).toInstant()),
	                Date.from(turno.getHoraFin().atDate(fecha).atZone(ZoneId.systemDefault()).toInstant()),
	                colorMap.get(turno.getFuncion())
	            );

	            // Agregar la tarea a la serie correspondiente según el tipo de turno
	            if (turno.getFuncion() == Funcion.CAJA) {
	                cajaSeries.add(task);
	            } else if (turno.getFuncion() == Funcion.ALMACEN) {
	                almacenSeries.add(task);
	            } else if (turno.getFuncion() == Funcion.ATTPUBLICO) {
	                attClienteSeries.add(task);
	            }
	        }
	    }

	    if (!cajaSeries.isEmpty()) {
	        filteredSeriesCollection.add(cajaSeries);
	    }
	    if (!almacenSeries.isEmpty()) {
	        filteredSeriesCollection.add(almacenSeries);
	    }
	    if (!attClienteSeries.isEmpty()) {
	        filteredSeriesCollection.add(attClienteSeries);
	    }

	    taskSeriesCollection = filteredSeriesCollection;
	    chart.getCategoryPlot().setDataset(taskSeriesCollection);

	    // Configurar el renderizador para utilizar los colores personalizados
	    GanttRenderer renderer = (GanttRenderer) chart.getCategoryPlot().getRenderer();
	    for (int i = 0; i < taskSeriesCollection.getSeriesCount(); i++) {
	        Paint color = ((ColoredTask) taskSeriesCollection.getSeries(i).get(0)).getColor();
	        renderer.setSeriesPaint(i, color);
	    }
	}
    
    public ChartPanel getChartPanel() {
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        return chartPanel;
    
     
        
    }
    public void actualizarChart(LocalDate fecha) {
        // Filtra los turnos para que solo se incluyan los de la fecha seleccionada
        List<Turno> turnosDelDia = todosLosTurnos.stream()
            .filter(turno -> turno.getFechaTurno().equals(fecha))
            .collect(Collectors.toList());

        // Llama a setDatos() con la nueva fecha para actualizar el gráfico
        setDatos(fecha, turnosDelDia);
        chart.fireChartChanged(); // actualiza el gráfico
    }
    public JFreeChart getChart() {
	    return chart;
	}
}

   

    

