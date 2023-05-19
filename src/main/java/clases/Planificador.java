    package clases;
    
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import enums.Funcion;
    
    public class Planificador {
        
        private PrevisionVisitas prevision;
        private ArrayList<Empleado> listaEmpleados;
        private List<Turno> listaTurnos;
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
    
        public Planificador (PrevisionVisitas prevision) throws IOException {
        super();
        if(requerimientos==null) {
    	    requerimientos= generarRequerimientos(csvFile);
    	}
    	this.prevision = prevision;
    	this.prevision = prevision;
    	this.listaTurnos = this.generarTurnos(prevision);
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
            int duracionMaximaTurno = 5;
            LocalTime horaInicio = LocalTime.of(10, 0);
            LocalTime horaFin = horaInicio.plusHours(duracionMaximaTurno);
            for (Integer visitas : previsiones.getPrevision()) {
                byte numCajeros = 0;
                byte numAlmacen = 0;
                byte numAttPublico = 0;
                //contadores para  id
                byte countCajeros = 0;
                byte countAlmacen = 0;
                byte countAttPublico = 0;

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

                 //Genera los turnos para cada función.
                for (int i = 0; i < numCajeros; i++) {
                    countCajeros++;
                    String idTurno = previsiones.getFecha().toString() + "Caja" + countCajeros;
                    Turno turno = new Turno(idTurno,previsiones.getFecha(), horaInicio, horaFin, Funcion.CAJA);
                    turnos.add(turno);
                }
                for (int i = 0; i < numAlmacen; i++) {
                    countAlmacen++;
                    String idTurno = previsiones.getFecha().toString() + "Almacen" + countCajeros;
                    Turno turno = new Turno(idTurno,previsiones.getFecha(), horaInicio, horaFin, Funcion.ALMACEN);;
                    turnos.add(turno);
                }
                for (int i = 0; i < numAttPublico; i++) {
                    countAttPublico++;
                    String idTurno = previsiones.getFecha().toString() + "AttPublico" + countCajeros;
                    Turno turno = new Turno(idTurno,previsiones.getFecha(), horaInicio, horaFin, Funcion.ATTPUBLICO);
                    turnos.add(turno);
                }

                // Incrementa la hora de inicio para el siguiente ciclo.
                horaInicio = horaInicio.plusHours(1);
                
            }

            return turnos;
        }

        
        
        
        
        /**
	 * @return the listaTurnos
	 */
	public List<Turno> getListaTurnos() {
	    return listaTurnos;
	}
	public String imprimirListaTurnos() {
		    StringBuilder sb = new StringBuilder();
		    for (Turno turno : listaTurnos) {
		        sb.append(turno.toString());
		        sb.append("\n");
		    }
		    return sb.toString();
		}

	    
	

	/**
	 * @param listaTurnos the listaTurnos to set
	 */
	public void setListaTurnos(List<Turno> listaTurnos) {
	    this.listaTurnos = listaTurnos;
	}

	@Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append("Planificador\n");

            LocalTime horaInicio = LocalTime.of(10, 0);

            for (Integer prevision : this.prevision.getPrevision()) {
                // Contadores de turnos asignados
                int numCajeros = 0;
                int numAlmacen = 0;
                int numAttPublico = 0;
                
                // Contadores de turnos requeridos
                int numCajerosRequeridos = 0;
                int numAlmacenRequeridos = 0;
                int numAttPublicoRequeridos = 0;

                // Obtén los requisitos de empleados para la previsión actual
                RequerimientosEmpleados reqPrev = null;
                for (RequerimientosEmpleados req : requerimientos) {
                    if (req.getVisitas() <= prevision) {
                        reqPrev = req;
                    } else {
                        break;
                    }
                }
                if (reqPrev != null) {
                    numCajerosRequeridos = reqPrev.getNumeroCajeros();
                    numAlmacenRequeridos = reqPrev.getNumeroAlmacen();
                    numAttPublicoRequeridos = reqPrev.getNumeroAttPublico();
                }

                for (Turno turno : listaTurnos) {
                    if (turno.getHoraInicio().equals(horaInicio)) {
                        switch (turno.getFuncion()) {
                            case CAJA:
                                numCajeros++;
                                break;
                            case ALMACEN:
                                numAlmacen++;
                                break;
                            case ATTPUBLICO:
                                numAttPublico++;
                                break;
                        }
                    }
                }

                str.append("Hora: ").append(horaInicio.toString()).append("   Previsión: ").append(prevision)
                   .append("   Cajeros: ").append(numCajeros).append("/").append(numCajerosRequeridos)
                   .append("   Almacen: ").append(numAlmacen).append("/").append(numAlmacenRequeridos)
                   .append("   AttPublico: ").append(numAttPublico).append("/").append(numAttPublicoRequeridos)
                   .append("\n");

                horaInicio = horaInicio.plusHours(1);
            }

            return str.toString();
        }

   }

