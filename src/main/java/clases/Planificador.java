    package clases;
    
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.ArrayList;
    
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
        
        
        
    		
    		        
    		           
    		            
    		  
        
        
        
        
        
        
        
        
        }
        


