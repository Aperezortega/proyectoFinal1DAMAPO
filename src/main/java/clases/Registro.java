package clases;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import utils.DAO;

public class Registro {
    private Empleado empleado;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private float horasBase;
    private float horasComplementarias;
    private float horasVacaciones;
    private float horasAusencia;
    /**
     * @param empleado
     * @param fecha
     * @param horaInicio
     * @param horaFin
     * @param checkIn
     * @param checkOut
     * @param horasTrabajadas
     */
  
    
    
    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }
    /**
     * @param empleado
     * @param fecha
     * @param horaInicio
     * @param horaFin
     * @param checkIn
     * @param checkOut
     * @param horasBase
     * @param horasComplementarias
     * @param horasVacaciones
     * @param horasAusencia
     */
   
    public Registro(Empleado empleado, LocalDate fecha) {
	super();
	
	this.empleado = empleado;
	this.fecha = fecha;
	this.horaInicio = horaInicio;
	this.horaFin = horaFin;
	this.checkIn = checkIn;
	this.checkOut = checkOut;
	this.horasBase = horasBase;
	this.horasComplementarias = horasComplementarias;
	this.horasVacaciones = horasVacaciones;
	this.horasAusencia = horasAusencia;
	try {
	    DAO.insert("INSERT INTO horas (id_empleado, fecha, horas_base, horas_complementarias, horas_vacaciones, horas_ausencia) " +
	            "VALUES ('" +empleado.getIdEmpleado()  + "', '" + fecha+ "', '" + horasBase+ "', '" + horasComplementarias + "', '" + horasVacaciones+ "', '" +horasAusencia + "')");
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public Registro(Empleado empleado, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, LocalTime checkIn,
	    LocalTime checkOut, float horasBase, float horasComplementarias,
	    float horasVacaciones, float horasAusencia) {
	super();
	this.empleado = empleado;
	this.fecha = fecha;
	this.horaInicio = horaInicio;
	this.horaFin = horaFin;
	this.checkIn = checkIn;
	this.checkOut = checkOut;
	this.horasBase = horasBase;
	this.horasComplementarias = horasComplementarias;
	this.horasVacaciones = horasVacaciones;
	this.horasAusencia = horasAusencia;
	try {
	    DAO.insert("INSERT INTO horas (id_empleado, fecha, horas_base, horas_complementarias, horas_vacaciones, horas_ausencia) " +
	            "VALUES ('" +empleado.getIdEmpleado()  + "', '" + fecha+ "', '" + horasBase+ "', '" + horasComplementarias + "', '" + horasVacaciones+ "', '" +horasAusencia + "')");
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
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
     * @return the horasBase
     */
    public float getHorasBase() {
        return horasBase;
    }
    /**
     * @param horasBase the horasBase to set
     */
    public void setHorasBase(float horasBase) {
        this.horasBase = horasBase;
    }
    /**
     * @return the horasComplementarias
     */
    public float getHorasComplementarias() {
        return horasComplementarias;
    }
    /**
     * @param horasComplementarias the horasComplementarias to set
     */
    public void setHorasComplementarias(float horasComplementarias) {
        this.horasComplementarias = horasComplementarias;
    }
    /**
     * @return the horasVacaciones
     */
    public float getHorasVacaciones() {
        return horasVacaciones;
    }
    /**
     * @param horasVacaciones the horasVacaciones to set
     */
    public void setHorasVacaciones(float horasVacaciones) {
        this.horasVacaciones = horasVacaciones;
    }
    /**
     * @return the horasAusencia
     */
    public float getHorasAusencia() {
        return horasAusencia;
    }
    /**
     * @param horasAusencia the horasAusencia to set
     */
    public void setHorasAusencia(float horasAusencia) {
        this.horasAusencia = horasAusencia;
    }
    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    @Override
    public String toString() {
	return "Registro [empleado=" + empleado + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", horaFin="
		+ horaFin + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", horasBase=" + horasBase
		+ ", horasComplementarias=" + horasComplementarias + ", horasVacaciones=" + horasVacaciones
		+ ", horasAusencia=" + horasAusencia + "]";
    }
    
    
    
    
}
