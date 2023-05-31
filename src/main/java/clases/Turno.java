package clases;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import enums.Funcion;
import enums.TipoTurno;
import utils.DAO;

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
    public Turno(String idTurno) throws SQLException {
	    ArrayList<String> consulta = DAO.selectAndPrint("select * from turnos where id_turno ='"+idTurno +"'");
	    this.idTurno = consulta.get(1);
	    this.fechaTurno = LocalDate.parse(consulta.get(2));
	    this.horaInicio = LocalTime.parse(consulta.get(3));
	    this.horaFin = LocalTime.parse(consulta.get(4));
	    String empleadoId = consulta.get(5);
	    if (empleadoId != null && !empleadoId.equalsIgnoreCase("null")) {
	        this.empleado = new Empleado(empleadoId);
	    }
	    this.empleado = new Empleado(consulta.get(5));
	    this.funcion = Funcion.valueOf(consulta.get(6));
	    if (horaInicio.getHour() >= 10 && horaInicio.getHour() < 14) {
		    this.tipoTurno = TipoTurno.MAÑANA;
		} else {
		    this.tipoTurno = TipoTurno.TARDE;
		}
	}

    
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
	    Funcion funcion) throws SQLException {
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
	DAO.insert("INSERT INTO turnos (id_turno, fecha_turno, hora_inicio, hora_fin, funcion) VALUES ('"+idTurno+"', '"+fechaTurno+"', '"+horaInicio+"', '"+horaFin+"', '"+funcion+"')"); 
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
            return idTurnoFormateado + ","+ horaInicio + "- "+ horaFin ;   
        } else {
            return idTurnoFormateado + empleado.getNombre() + "-" + horaInicio + "-"+ horaFin ; 
        }
    }

    }
    

