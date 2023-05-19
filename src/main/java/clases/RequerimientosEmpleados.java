package clases;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RequerimientosEmpleados {

    private int visitas;
    private int numeroCajeros;
    private int numeroAlmacen;
    private int numeroAttPublico;
    /**
     * @param visitas
     * @param numeroCajeros
     * @param numeroAlmacen
     * @param numeroAttPublico
     * @throws IOException 
     */
    public RequerimientosEmpleados(Integer visitas, Integer numeroCajeros, Integer numeroAlmacen, Integer numAttPublico) {
	super();
	
            this.visitas = visitas;
            this.numeroCajeros = numeroCajeros;
            this.numeroAlmacen = numeroAlmacen;
            this.numeroAttPublico = numAttPublico;
        }
	
    public static String imprimirRequerimientos(ArrayList<RequerimientosEmpleados> requerimientos) {
	    String ret ="";
	    for (RequerimientosEmpleados req : requerimientos) {
	        ret+=" " + req.toString();
	    }
	    return ret;
	}

    
    /**
     * @return the visitas
     */
    public int getVisitas() {
        return visitas;
    }
    /**
     * @param visitas the visitas to set
     */
    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }
    /**
     * @return the numeroCajeros
     */
    public int getNumeroCajeros() {
        return numeroCajeros;
    }
    /**
     * @param numeroCajeros the numeroCajeros to set
     */
    public void setNumeroCajeros(int numeroCajeros) {
        this.numeroCajeros = numeroCajeros;
    }
    /**
     * @return the numeroAlmacen
     */
    public int getNumeroAlmacen() {
        return numeroAlmacen;
    }
    /**
     * @param numeroAlmacen the numeroAlmacen to set
     */
    public void setNumeroAlmacen(int numeroAlmacen) {
        this.numeroAlmacen = numeroAlmacen;
    }
    /**
     * @return the numeroAttPublico
     */
    public int getNumeroAttPublico() {
        return numeroAttPublico;
    }
    /**
     * @param numeroAttPublico the numeroAttPublico to set
     */
    public void setNumeroAttPublico(int numeroAttPublico) {
        this.numeroAttPublico = numeroAttPublico;
    }
    @Override
    public String toString() {
	return "RequerimientosEmpleados [visitas=" + visitas + ", numeroCajeros=" + numeroCajeros + ", numeroAlmacen="
		+ numeroAlmacen + ", numeroAttPublico=" + numeroAttPublico + "]\n";
    }
    
    
    
    
}


