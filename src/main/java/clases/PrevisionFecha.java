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

/**
 * Clase que representa la previsión para una fecha específica.
 */
public class PrevisionFecha {

    private LocalDate fecha;
    private ArrayList<PrevisionHora> prevision;

    /**
     * Constructor por defecto de PrevisionFecha.
     */
    public PrevisionFecha() {
        // Constructor por defecto
    }

    /**
     * Constructor de PrevisionFecha con una fecha específica.
     *
     * @param fecha La fecha de la previsión.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public PrevisionFecha(LocalDate fecha) throws SQLException {
        ArrayList<String> resultado = DAO.selectAndPrint("select * from previsiones where fecha ='" + fecha + "'");
        prevision = new ArrayList<>();

        for (int i = 0; i < resultado.size(); i += 5) {
            String fechaString = resultado.get(i + 1);
            String horaString = resultado.get(i + 2);
            String visitasString = resultado.get(i + 3);

            LocalDate fechaPrevision = LocalDate.parse(fechaString);
            LocalTime hora = LocalTime.parse(horaString);
            int visitas = Integer.parseInt(visitasString);

            PrevisionHora previsionHora = new PrevisionHora(hora, visitas);
            prevision.add(previsionHora);
            this.fecha = fechaPrevision;
            this.prevision = prevision;
        }
    }

    /**
     * Constructor de PrevisionFecha con una fecha y una lista de PrevisionHora.
     *
     * @param fecha     La fecha de la previsión.
     * @param prevision La lista de previsiones por hora.
     * @param no        No se utiliza.
     */
    public PrevisionFecha(LocalDate fecha, ArrayList<PrevisionHora> prevision, boolean no) {
        this.fecha = fecha;
        this.prevision = prevision;
    }

    /**
     * Constructor de PrevisionFecha con una fecha y una lista de PrevisionHora.
     * Inserta las previsiones en la base de datos.
     *
     * @param fecha     La fecha de la previsión.
     * @param prevision La lista de previsiones por hora.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public PrevisionFecha(LocalDate fecha, ArrayList<PrevisionHora> prevision) throws SQLException {
        this.fecha = fecha;
        this.prevision = prevision;

        for (PrevisionHora hora : prevision) {
            DAO.insert("INSERT INTO previsiones (fecha, hora, visitas) VALUES ('" + fecha + "', '" + hora.getHora() + "', '" + hora.getVisitas() + "')");
        }
    }

    /**
     * Obtiene la fecha de la previsión.
     *
     * @return La fecha de la previsión.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la previsión.
     *
     * @param fecha La fecha de la previsión.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la lista de previsiones por hora.
     *
     * @return La lista de previsiones por hora.
     */
    public ArrayList<PrevisionHora> getPrevision() {
        return prevision;
    }

    /**
     * Establece la lista de previsiones por hora.
     *
     * @param prevision La lista de previsiones por hora.
     */
    public void setPrevision(ArrayList<PrevisionHora> prevision) {
        this.prevision = prevision;
    }

    @Override
    public String toString() {
        return "PrevisionFecha [fecha=" + fecha + ", prevision=" + prevision + "]";
    }
}

    
    
    
    
