    package clases;
    
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import enums.Funcion;
import enums.TipoTurno;
    
    public  class Planificador {
        
        private PrevisionFecha prevision;
        private ArrayList<Empleado> listaEmpleados;
        private List<Turno> listaTurnos;
        private static ArrayList<RequerimientosEmpleados> requerimientos;
        private static final String csvFile = "requisitos.csv";
        
        
        public Planificador() {
            
        }
        /**
         * @param prevision
         * @param listaEmpleados
         * @param listaTurnos
         * @throws IOException 
         */
        public Planificador(PrevisionFecha prevision, ArrayList<Empleado> listaEmpleados, ArrayList<Turno> listaTurnos) throws IOException {
    	super();
    	if(requerimientos==null) {
    	    requerimientos= generarRequerimientos(csvFile);
    	}
    	this.prevision = prevision;
    	this.listaEmpleados = listaEmpleados;
    	this.listaTurnos = listaTurnos;
        }
    
        public Planificador (PrevisionFecha prevision) throws IOException, SQLException {
        super();
        if(requerimientos==null) {
    	    requerimientos= generarRequerimientos(csvFile);
    	}
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
    	            byte numeroCajeros = Byte.parseByte(values[1]);
    	            byte numeroAlmacen = Byte.parseByte(values[2]);
    	            byte numeroAttPublico = Byte.parseByte(values[3]);
    	            RequerimientosEmpleados requerimiento = new RequerimientosEmpleados(visitas, numeroCajeros, numeroAlmacen, numeroAttPublico);
    	            ret.add(requerimiento);
    			}
    	        br.close();
    	       
    	        return ret;
     }
        
        public List<Turno> generarTurnos(PrevisionFecha previsiones) throws IOException, SQLException {
            List<Turno> turnos = new ArrayList<Turno>();
           
            byte numCajerosAnterior = 0;
            byte countCajeros = 0;
            byte numAlmacenAnterior = 0;
            byte countAlmacen = 0;
            byte numAttPublicoAnterior = 0;
            byte countAttPublico = 0;
            // Asume que el supermercado abre a las 10:00
            for(PrevisionHora prevision : previsiones.getPrevision()) {
        	Integer visitas = prevision.getVisitas();
        	LocalTime horaInicio = prevision.getHora();
        	
        	//para calcular cajeros
        	byte numCajerosActual = (byte) calcularNumCajeros(visitas);
        	if(numCajerosActual>numCajerosAnterior) {
        	    for(byte j = numCajerosAnterior; j<numCajerosActual; j++) {
        		LocalTime horaFin = calcularHoraFin(horaInicio);
        		countCajeros++;
        		String idTurno = previsiones.getFecha().toString() + "Caja" + countCajeros;
        		Turno turno = new Turno(idTurno, previsiones.getFecha(), horaInicio, horaFin, Funcion.CAJA);
                        turnos.add(turno);
        	    }
        	}
        	numCajerosAnterior = numCajerosActual;
        	
        	
        	//para calcular operarios almacen
        	byte numAlmacenActual = calcularNumAlmacen(visitas);
        	if(numAlmacenActual>numAlmacenAnterior) {
        	    for(byte j = numAlmacenAnterior; j<numAlmacenActual; j++) {
        		LocalTime horaFin = calcularHoraFin(horaInicio);
        		countAlmacen++;
        		String idTurno = previsiones.getFecha().toString()+ "Almacen" +countAlmacen;
        		Turno turno = new Turno(idTurno, previsiones.getFecha(),horaInicio, horaFin, Funcion.ALMACEN);
        		turnos.add(turno);
        	    }
        	}
        	numAlmacenAnterior = numAlmacenActual;
        	
        	
        	//para Calcular numero de operarios de Atencion al publico
        	
        	byte numAttPublicoActual = calcularNumAttPublico(visitas);
        	if(numAttPublicoActual>numAttPublicoAnterior) {
        	    for(byte j =numAttPublicoAnterior; j<numAttPublicoActual; j++) {
        		LocalTime horaFin = calcularHoraFin(horaInicio);
        		countAttPublico++;
        		String idTurno = previsiones.getFecha().toString()+ "AttPublico"+ countAttPublico;
        		Turno turno = new Turno(idTurno, previsiones.getFecha(), horaInicio, horaFin, Funcion.ATTPUBLICO);
        		turnos.add(turno);
        	    }
        	    
        	}
        	numAttPublicoAnterior = numAttPublicoActual;
            }
            return turnos;
        }
            
      
        
        public String verEmpleadosDisponibles(String idTurno, ArrayList<Empleado> plantilla) {
            StringBuilder nombres = new StringBuilder();
            nombres.append("EMPLEADOS DISPONIBLES \n" +idTurno+ " : \n");
            
            for (Turno turno : listaTurnos) {
                if (turno.getIdTurno().equals(idTurno)) {
                    TipoTurno tipoTurno = turno.getTipoTurno();
                    LocalDate fecha = turno.getFechaTurno();
                    for (Empleado e : plantilla) {
                        switch (tipoTurno) {
                            case MAÑANA:
                                if (e.isWorkinMañanas(fecha)) {
                                    nombres.append(e.getNombre()+", "+e.getGrupo()+", "+e.getFunciones()+", +"+e.getHorasBaseContrato()).append("\n");
                                }
                                break;
                            case TARDE:
                                if (e.isWorkinTardes(fecha)) {
                                    nombres.append(e.getNombre()+", "+e.getGrupo()+", "+e.getFunciones()+", +"+e.getHorasBaseContrato()).append("\n");
                                }
                                break;
                        }
                    }
                }
            }
            // Eliminar la coma y el espacio final si existen
            if (nombres.length() > 0) {
                nombres.setLength(nombres.length() - 2);
            }
            return nombres.toString();
        }

        public ArrayList<Empleado> verEmpleadosDisponiblesArrayList(String idTurno, ArrayList<Empleado> plantilla) {
            ArrayList<Empleado> empleadosDisponibles = new ArrayList<>();
            
            for (Turno turno : listaTurnos) {
                if (turno.getIdTurno().equals(idTurno)) {
                    TipoTurno tipoTurno = turno.getTipoTurno();
                    LocalDate fecha = turno.getFechaTurno();
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
                }
            }
            
            return empleadosDisponibles;
        } 
        
      
        
        private byte calcularNumCajeros(int visitas) throws IOException {
            if(requerimientos==null) {
        	    requerimientos= generarRequerimientos(csvFile);
        	}
            Collections.sort(requerimientos, Comparator.comparing(RequerimientosEmpleados::getVisitas));
            byte numCajeros =1;
            
            for (RequerimientosEmpleados req : requerimientos) {
        	if(visitas>= req.getVisitas()) {
        	    numCajeros =  (byte) req.getNumeroCajeros();
        	    }else {
        		break;
        	    }
        	}
            return numCajeros;
            }
        private byte calcularNumAlmacen(int visitas) {
            Collections.sort(requerimientos, Comparator.comparing(RequerimientosEmpleados::getVisitas));
            byte numAlmacen =1;
            
            for (RequerimientosEmpleados req : requerimientos) {
        	if(visitas>= req.getVisitas()) {
        	    numAlmacen =  (byte) req.getNumeroAlmacen();
        	    }else {
        		break;
        	    }
        	}
            return numAlmacen;
            }    
        private byte calcularNumAttPublico(int visitas) {
            Collections.sort(requerimientos, Comparator.comparing(RequerimientosEmpleados::getVisitas));
            byte numAttPublico =2;
            
            for (RequerimientosEmpleados req : requerimientos) {
        	if(visitas>= req.getVisitas()) {
        	    numAttPublico =  (byte) req.getNumeroAttPublico();
        	    }else {
        		break;
        	    }
        	}
            return numAttPublico;
            }

        private LocalTime calcularHoraFin(LocalTime horaInicio) {
            // Calcula la hora de finalización del turno de 5 horas
            LocalTime horaFin = horaInicio.plusHours(5);

            // Asegúrate de que la hora de finalización no supere la hora de cierre del supermercado
            if (horaFin.isAfter(LocalTime.of(20, 0))) {
                horaFin = LocalTime.of(20, 0);
            }

            return horaFin;
        }

        
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

	    
	

	

	@Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append("Planificador\n");

            LocalTime horaInicio = LocalTime.of(10, 0);

            for (PrevisionHora prevision : this.prevision.getPrevision()) {
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
                    if (req.getVisitas() <= prevision.getVisitas()) {
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
                    if (turno.getHoraInicio().isBefore(horaInicio.plusHours(1)) && turno.getHoraFin().isAfter(horaInicio)) {
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

