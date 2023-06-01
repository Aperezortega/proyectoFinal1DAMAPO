package clases;

import java.time.LocalDate;
import java.time.LocalTime;

public class Registro {
    private Empleado empleado;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private int horasTrabajadas;
    /**
     * @param empleado
     * @param fecha
     * @param horaInicio
     * @param horaFin
     * @param checkIn
     * @param checkOut
     * @param horasTrabajadas
     */
    public Registro(Empleado empleado, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, LocalTime checkIn,
	    LocalTime checkOut, int horasTrabajadas) {
	super();
	this.empleado = empleado;
	this.fecha = fecha;
	this.horaInicio = horaInicio;
	this.horaFin = horaFin;
	this.checkIn = checkIn;
	this.checkOut = checkOut;
	this.horasTrabajadas = horasTrabajadas;
    }
    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }
    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
     * @return the horaInicio
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    /**
     * @return the horaFin
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }
    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
    /**
     * @return the checkIn
     */
    public LocalTime getCheckIn() {
        return checkIn;
    }
    /**
     * @param checkIn the checkIn to set
     */
    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }
    /**
     * @return the checkOut
     */
    public LocalTime getCheckOut() {
        return checkOut;
    }
    /**
     * @param checkOut the checkOut to set
     */
    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }
    /**
     * @return the horasTrabajadas
     */
    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }
    /**
     * @param horasTrabajadas the horasTrabajadas to set
     */
    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
    @Override
    public String toString() {
	return "Registro [empleado=" + empleado + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", horaFin="
		+ horaFin + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", horasTrabajadas=" + horasTrabajadas
		+ "]";
    }


    
    
    
}
