package clases;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 
 * No estoy seguro si esta clase se usa
 * Clase que representa las previsiones para un rango de fechas.
 */
public class Previsiones {
    private ArrayList<PrevisionHora> prevision = new ArrayList<PrevisionHora>();
    private SortedMap<LocalDate, PrevisionFecha> previsiones;

    /**
     * Constructor de Previsiones.
     *
     * @param inicio La fecha de inicio del rango de previsiones.
     * @param fin    La fecha de fin del rango de previsiones.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public Previsiones(LocalDate inicio, LocalDate fin) throws SQLException {
        this.previsiones = new TreeMap<>();

        // Rellena el mapa con fechas desde 'inicio' hasta 'fin', con un objeto vacío de PrevisionFecha
        LocalDate fechaActual = inicio;
        while (!fechaActual.isAfter(fin)) {
            this.previsiones.put(fechaActual, new PrevisionFecha(fechaActual, prevision));
            fechaActual = fechaActual.plusDays(1);
        }
    }

    /**
     * Obtiene las previsiones por fecha.
     *
     * @return El mapa de previsiones ordenadas por fecha.
     */
    public SortedMap<LocalDate, PrevisionFecha> getPrevisiones() {
        return previsiones;
    }

    /**
     * Añade una previsión a una fecha específica.
     *
     * @param fecha     La fecha de la previsión.
     * @param prevision La previsión a añadir.
     */
    public void addPrevision(LocalDate fecha, PrevisionFecha prevision) {
        if (this.previsiones.containsKey(fecha)) {
            this.previsiones.put(fecha, prevision);
        }
    }

    @Override
    public String toString() {
        return "Previsiones{" +
                "previsiones=" + previsiones +
                '}';
    }
}
