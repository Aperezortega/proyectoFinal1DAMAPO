package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;



public class PrevisionHora {
    private LocalTime hora;
    private int visitas;
    
    
    
    /**
     * @param hora
     * @param prevision
     */
    public PrevisionHora(LocalTime hora, int prevision) {
	super();
	this.hora = hora;
	this.visitas = prevision;
    }
    /**
     * @return the hora
     */
    public LocalTime getHora() {
        return hora;
    }
    /**
     * @param hora the hora to set
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    /**
     * @return the prevision
     */
    public int getVisitas() {
        return visitas;
    }
    /**
     * @param prevision the prevision to set
     */
    public void setVisitas(int prevision) {
        this.visitas = prevision;
    }
    @Override
    public String toString() {
	return "Prevision" + hora + ": "  + visitas;
    }
    
    
  
}