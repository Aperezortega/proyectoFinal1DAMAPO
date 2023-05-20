package clases;

import java.time.LocalDate;
import java.time.LocalTime;

import enums.Funcion;
import enums.TipoTurno;

public class Turno {
    
    private String idTurno;
    private LocalDate fechaTurno;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Empleado empleado;
    private Funcion funcion;
    private TipoTurno tipoTurno;
    /**
     * @param idTurno
     * @param fechaTurno
     * @param horaInicio
     * @param horaFin
     * @param empleado
     * @param funcion
     */
    public Turno(String idTurno, LocalDate fechaTurno, LocalTime horaInicio, LocalTime horaFin, Empleado empleado,
	    Funcion funcion) {
	super();
	this.idTurno = idTurno;
	this.fechaTurno = fechaTurno;
	this.horaInicio = horaInicio;
	this.horaFin = horaFin;
	this.empleado = empleado;
	this.funcion = funcion;
	  if (horaInicio.getHour() >= 10 && horaInicio.getHour() < 14) {
	            this.tipoTurno = TipoTurno.MAÑANA;
	        } else {
	            this.tipoTurno = TipoTurno.TARDE;
	        }
    }
    public Turno(String idTurno, LocalDate fechaTurno, LocalTime horaInicio, LocalTime horaFin,
	    Funcion funcion) {
	super();
	this.idTurno = idTurno;
	this.fechaTurno = fechaTurno;
	this.horaInicio = horaInicio;
	this.horaFin = horaFin;
	this.funcion = funcion;
	  if (horaInicio.getHour() >= 10 && horaInicio.getHour() < 14) {
	            this.tipoTurno = TipoTurno.MAÑANA;
	        } else {
	            this.tipoTurno = TipoTurno.TARDE;
	        }
    }
    
    
    
    
    public String getIdTurno() {
        return idTurno;
    }
    /**
     * @param idTurno the idTurno to set
     */
    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }
    /**
     * @return the fechaTurno
     */
    public LocalDate getFechaTurno() {
        return fechaTurno;
    }
    /**
     * @param fechaTurno the fechaTurno to set
     */
    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
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
     * @return the funcion
     */
    public Funcion getFuncion() {
        return funcion;
    }
    /**
     * @param funcion the funcion to set
     */
    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }
    
    
    /**
     * @return the tipoTurno
     */
    public TipoTurno getTipoTurno() {
        return tipoTurno;
    }
    /**
     * @param tipoTurno the tipoTurno to set
     */
    public void setTipoTurno(TipoTurno tipoTurno) {
        this.tipoTurno = tipoTurno;
    }
    @Override
    public String toString() {
        String idTurnoFormateado = String.format("%-22s", idTurno);
        
        if(this.empleado==null) {
            return idTurnoFormateado + ", horaInicio=" + horaInicio + ", horaFin="+ horaFin ;   
        } else {
            return idTurnoFormateado + empleado.getNombre() + ", horaInicio=" + horaInicio + ", horaFin="+ horaFin ; 
        }
    }

    }
    

