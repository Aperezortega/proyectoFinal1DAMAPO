package clases;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import utils.DAO;

import java.time.LocalDate;

public class PrevisionFecha {

    private LocalDate fecha;
    private ArrayList<PrevisionHora> prevision;
    
    public PrevisionFecha() {
        // default constructor
    }
    
    public PrevisionFecha(LocalDate fecha, ArrayList<PrevisionHora> prevision) throws SQLException {
	this.fecha =fecha;
	this.prevision=prevision;
	
	 for (PrevisionHora hora : prevision) {
	        DAO.insert("INSERT INTO previsiones (fecha, hora, visitas) VALUES ('" + fecha + "', '" + hora.getHora() + "', '" + hora.getVisitas() + "')");
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