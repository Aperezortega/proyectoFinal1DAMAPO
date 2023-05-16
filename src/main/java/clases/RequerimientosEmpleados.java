package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
    public RequerimientosEmpleados(String csvFile) throws IOException {
	super();
	String line;
	String csvSplitBy = ";";
	BufferedReader br = new BufferedReader(new FileReader(csvFile));
	br.readLine();
	while ((line = br.readLine()) != null) {
            // Usa punto y coma como separador
            String[] values = line.split(csvSplitBy);

            // Añade los valores a los campos de la clase
            this.visitas = Integer.parseInt(values[0]);
            this.numeroCajeros = Integer.parseInt(values[1]);
            this.numeroAlmacen = Integer.parseInt(values[2]);
            this.numeroAttPublico = Integer.parseInt(values[3]);
        }
	
	
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
		+ numeroAlmacen + ", numeroAttPublico=" + numeroAttPublico + "]";
    }
    
    
    
    
}


