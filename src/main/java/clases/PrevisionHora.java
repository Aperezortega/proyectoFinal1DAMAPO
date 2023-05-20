package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;



public class PrevisionHora {
    private LocalTime hora;
    private int prevision;
    
    
    
    /**
     * @param hora
     * @param prevision
     */
    public PrevisionHora(LocalTime hora, int prevision) {
	super();
	this.hora = hora;
	this.prevision = prevision;
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
    public int getPrevision() {
        return prevision;
    }
    /**
     * @param prevision the prevision to set
     */
    public void setPrevision(int prevision) {
        this.prevision = prevision;
    }
    @Override
    public String toString() {
	return "Prevision" + hora + ": "  + prevision ;
    }
    
    
  
}