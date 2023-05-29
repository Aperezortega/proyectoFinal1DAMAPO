package clases;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PrevisionFecha {

    private LocalDate fecha;
    private ArrayList<PrevisionHora> prevision;
    
    
    public PrevisionFecha(LocalDate fecha, ArrayList<PrevisionHora> prevision) {
	this.fecha =fecha;
	this.prevision=prevision;
    }
    /*
    public PrevisionFecha(String csvFile, LocalDate fecha) throws IOException {
        this.fecha = fecha;
        this.prevision=new ArrayList<PrevisionHora>();
        String line;
        String cvsSplitBy = ";";

        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        br.readLine();
        while ((line = br.readLine()) != null) {
            // Usa coma como separador
            String[] values = line.split(cvsSplitBy);

            PrevisionHora prevision = new PrevisionHora(LocalTime.parse(values[0], formatter),Integer.parseInt(values[1]));
            this.prevision.add(prevision);
 

        }
        
        
        
    }

*/

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
    public ArrayList<PrevisionHora> getPrevision() {
        return prevision;
    }



    /**
     * @param prevision the prevision to set
     */
    public void setPrevision(ArrayList<PrevisionHora> prevision) {
        this.prevision = prevision;
    }



    @Override
    public String toString() {
	return "PrevisionFecha [fecha=" + fecha + ", prevision=" + prevision + "]";
    }

   
    
    
    
    
    
    
    
    
}