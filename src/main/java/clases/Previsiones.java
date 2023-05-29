package clases;



    import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
    import java.util.TreeMap;

    public class Previsiones {
	private ArrayList<PrevisionHora> prevision = new ArrayList<PrevisionHora>();
        private SortedMap<LocalDate, PrevisionFecha> previsiones;

        public Previsiones(LocalDate inicio, LocalDate fin) throws SQLException {
            this.previsiones = new TreeMap<>();

            // Rellena el mapa con fechas desde 'inicio' hasta 'fin', con un objeto vacío de PrevisionFecha
            LocalDate fechaActual = inicio;
            while (!fechaActual.isAfter(fin)) {
                this.previsiones.put(fechaActual, new PrevisionFecha(fechaActual,prevision));
                fechaActual = fechaActual.plusDays(1);
            }
        }

        public SortedMap<LocalDate, PrevisionFecha> getPrevisiones() {
            return previsiones;
        }

        public void addPrevision(LocalDate fecha, PrevisionFecha prevision) {
            if (this.previsiones.containsKey(fecha)) {
                this.previsiones.put(fecha, prevision);
            }
        }

        @Override
        public String toString() {
            return "PrevisionQuincenal{" +
                    "previsiones=" + previsiones +
                    '}';
        }
    }

    
    
