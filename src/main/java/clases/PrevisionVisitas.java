package clases;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PrevisionVisitas {

    private LocalDate fecha;
    private ArrayList<Integer> prevision;
    
    public PrevisionVisitas(String csvFile, LocalDate fecha) throws IOException {
        this.fecha = fecha;
        this.prevision=new ArrayList<Integer>();
        String line;
        String cvsSplitBy = ";";

        BufferedReader br = new BufferedReader(new FileReader(csvFile));
            
        br.readLine();
        while ((line = br.readLine()) != null) {
            // Usa coma como separador
            String[] values = line.split(cvsSplitBy);

            // Añade la previsión a la lista
            this.prevision.add(Integer.parseInt(values[1]));
 

        }
        
        
        
    }

    /**
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the prevision
     */
    public ArrayList<Integer> getPrevision() {
        return prevision;
    }

    /**
     * @param prevision the prevision to set
     */
    public void setPrevision(ArrayList<Integer> prevision) {
        this.prevision = prevision;
    }

    @Override
    public String toString() {
	return "PrevisionVisitas [fecha=" + fecha + ", prevision=" + prevision.toString() + "]";
    }
    
    
}