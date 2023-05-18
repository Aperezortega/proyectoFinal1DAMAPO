    package clases;
    
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
    
    public class Planificador {
        
        private PrevisionVisitas prevision;
        private ArrayList<Empleado> listaEmpleados;
        private ArrayList<Turno> listaTurnos;
        private static ArrayList<RequerimientosEmpleados> requerimientos;
        private static final String csvFile = "requisitos.csv";
        
        

        /**
         * @param prevision
         * @param listaEmpleados
         * @param listaTurnos
         * @throws IOException 
         */
        public Planificador(PrevisionVisitas prevision, ArrayList<Empleado> listaEmpleados, ArrayList<Turno> listaTurnos) throws IOException {
    	super();
    	if(requerimientos==null) {
    	    requerimientos= generarRequerimientos(csvFile);
    	}
    	this.prevision = prevision;
    	this.listaEmpleados = listaEmpleados;
    	this.listaTurnos = listaTurnos;
        }
    
        public static ArrayList<RequerimientosEmpleados>generarRequerimientos(String csvFile) throws IOException{
    
    		String line;
    		String csvSplitBy = ";";
    		BufferedReader br = new BufferedReader(new FileReader(csvFile));
    		br.readLine();
    		ArrayList<RequerimientosEmpleados> ret = new ArrayList<RequerimientosEmpleados>();
    		while ((line = br.readLine()) != null) {
    	            String[] values = line.split(csvSplitBy);
    	            Integer visitas = Integer.parseInt(values[0]);
    	            Integer numeroCajeros = Integer.parseInt(values[1]);
    	            Integer numeroAlmacen = Integer.parseInt(values[2]);
    	            Integer numeroAttPublico = Integer.parseInt(values[3]);
    	            RequerimientosEmpleados requerimiento = new RequerimientosEmpleados(visitas, numeroCajeros, numeroAlmacen, numeroAttPublico);
    	            ret.add(requerimiento);
    			}
    	        br.close();
    	       
    	        return ret;
        		}

        public List<Turno> generarTurnos(PrevisionVisitas previsiones) {
            List<Turno> turnos = new ArrayList<Turno>();

            // Asumiendo que las previsiones están ordenadas por hora,
            // y que la hora de inicio de los turnos es a las 10:00.
            LocalTime horaInicio = LocalTime.of(10, 0);

            for (Integer visitas : previsiones.getPrevision()) {
                int numCajeros = 0;
                int numAlmacen = 0;
                int numAttPublico = 0;

                // Comprueba los requisitos para el número de visitas.
                if (visitas >= 600) {
                    numCajeros = 3;
                    numAlmacen = 3;
                    numAttPublico = 3;
                } else if (visitas >= 500) {
                    numCajeros = 3;
                    numAlmacen = 2;
                    numAttPublico = 3;
                } else if (visitas >= 350) {
                    numCajeros = 2;
                    numAlmacen = 2;
                    numAttPublico = 3;
                } else if (visitas >= 200) {
                    numCajeros = 2;
                    numAlmacen = 1;
                    numAttPublico = 2;
                } else { // Menos de 200 visitas
                    numCajeros = 1;
                    numAlmacen = 1;
                    numAttPublico = 2;
                }

                /* Genera los turnos para cada función.
                for (int i = 0; i < numCajeros; i++) {
                    Turno turno = new Turno(..., horaInicio, ..., Funcion.CAJERO);
                    turnos.add(turno);
                }
                for (int i = 0; i < numAlmacen; i++) {
                    Turno turno = new Turno(..., horaInicio, ..., Funcion.ALMACEN);
                    turnos.add(turno);
                }
                for (int i = 0; i < numAttPublico; i++) {
                    Turno turno = new Turno(..., horaInicio, ..., Funcion.ATTPUBLICO);
                    turnos.add(turno);
                }

                // Incrementa la hora de inicio para el siguiente ciclo.
                horaInicio = horaInicio.plusHours(1);
                */
            }

            return turnos;
        }

    		
    		        
    		           
    		            
    		  
        
        
        
        
        
        
        
        
        
        
    }

