package clases;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RequerimientosEmpleados {

    private int visitas;
    private byte numeroCajeros;
    private byte numeroAlmacen;
    private byte numeroAttPublico;
    /**
     * @param visitas
     * @param numeroCajeros
     * @param numeroAlmacen
     * @param numeroAttPublico
     * @throws IOException 
     */
    public RequerimientosEmpleados(Integer visitas, byte numeroCajeros, byte numeroAlmacen, byte numAttPublico) {
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
    public byte getNumeroCajeros() {
        return (byte) numeroCajeros;
    }
    /**
     * @param numeroCajeros the numeroCajeros to set
     */
    public void setNumeroCajeros(byte numeroCajeros) {
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
    public void setNumeroAlmacen(byte numeroAlmacen) {
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
    public void setNumeroAttPublico(byte numeroAttPublico) {
        this.numeroAttPublico = numeroAttPublico;
    }
    @Override
    public String toString() {
	return "RequerimientosEmpleados [visitas=" + visitas + ", numeroCajeros=" + numeroCajeros + ", numeroAlmacen="
		+ numeroAlmacen + ", numeroAttPublico=" + numeroAttPublico + "]\n";
    }
    
    
    
    
}


