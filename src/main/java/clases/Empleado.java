package clases;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;

import enums.Funcion;
import enums.GruposEmpleados;
import utils.DAO;

public class Empleado {
    
    private String idEmpleado;
    private String apellidos;
    private String nombre;
    private String email;
    private String contraseña;
    private GruposEmpleados grupo;
    private Float coeficienteParcialidad;
    private EnumSet<Funcion> funciones;
  
    /**
     * @param idEmpleado
     * @param nombre
     * @param horasComplementarias
     * @param horasVacaciones
     * @param horasBaseContrato
     * @param funciones
     */
    public Empleado(String idEmpleado, String apellidos, String nombre, String email, String contraseña, GruposEmpleados grupo, Float coeficienteParcialidad, Funcion...funciones) throws SQLException{
	super();
	this.idEmpleado = idEmpleado;
	this.apellidos = apellidos;
	this.nombre = nombre;
	this.email = email;
	this.contraseña = contraseña;
	this.grupo =grupo;
	this.coeficienteParcialidad = coeficienteParcialidad;
	this.funciones = EnumSet.noneOf(Funcion.class);
	for (Funcion funcion : funciones) {
            this.funciones.add(funcion);
        }
	DAO.insert("insert into empleados  (`ID Empleado`, `Apellidos`, `Nombre`, `Email`, `Contraseña`, `Grupo_Empleados`, `Coeficiente_Parcialidad`, `Funcion: Caja`, `Funcion: Almacen`, `Funcion: Atencion_Publico`, `Funcion: Supervisor`, `Usuario:`, `Operacion`)" 
		+ " values('" + idEmpleado + "','" + apellidos + "','" + nombre + "','" + email+"','" + contraseña +"','" + grupo +"','" + coeficienteParcialidad +"','" + tieneFuncion(Funcion.CAJA)+ "','" +tieneFuncion(Funcion.ALMACEN)+"','" +tieneFuncion(Funcion.ATTPUBLICO)+"','" +tieneFuncion(Funcion.SUPERVISOR)+"','"+"user"+"','"+"Alta"+"')");                        
    }
    
    public byte tieneFuncion(Funcion funcion) {
	if(funciones.contains(funcion)) {
	    return 1;
	}else {
	return 0;
	}
    }
    
    public void añadirfuncion(Funcion funcion) {
	funciones.add(funcion);
    }
    
    
    public void eliminarfuncion(Funcion funcion) {
	funciones.remove(funcion);
    }

    public float getHorasComplementarias(Float horasBase) {
	float ret = 0;
	return ret;
    }

   
    public float getHorasVacaciones(Float horasBase) {
	float ret = 0;
	return ret;
    }

    
    public boolean isWorkinMañanas(LocalDate fecha) {
	 Calendar calendar = Calendar.getInstance();
	        calendar.setTime(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	        int dayOfCycle;

	        switch (this.getGrupo()) {
	            case GRUPO_A:
	                dayOfCycle = calendar.get(Calendar.DAY_OF_YEAR) % 6;
	                break;
	            case GRUPO_B:
	                dayOfCycle = (calendar.get(Calendar.DAY_OF_YEAR) + 2) % 6;
	                break;
	            case GRUPO_C:
	                dayOfCycle = (calendar.get(Calendar.DAY_OF_YEAR) + 4) % 6;
	                break;
	            default:
	                throw new IllegalArgumentException("Grupo desconocido: " + this.getGrupo());
	        }

	        switch (dayOfCycle) {
	            case 0:
	            case 1:
	                return true;
	            case 2:
	            case 3:
	            case 4:
	            case 5:
	                return false;
	            default:
	                throw new IllegalArgumentException("Valor de dayOfCycle no válido: " + dayOfCycle);
	        }
	    }
    
    public boolean isWorkinTardes(LocalDate fecha) {
	 Calendar calendar = Calendar.getInstance();
	        calendar.setTime(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	        int dayOfCycle;

	        switch (this.getGrupo()) {
	            case GRUPO_A:
	                dayOfCycle = calendar.get(Calendar.DAY_OF_YEAR) % 6;
	                break;
	            case GRUPO_B:
	                dayOfCycle = (calendar.get(Calendar.DAY_OF_YEAR) + 2) % 6;
	                break;
	            case GRUPO_C:
	                dayOfCycle = (calendar.get(Calendar.DAY_OF_YEAR) + 4) % 6;
	                break;
	            default:
	                throw new IllegalArgumentException("Grupo desconocido: " + this.getGrupo());
	        }

	        switch (dayOfCycle) {
	            case 0:
	            case 1:
	                return false;
	            case 2:
	            case 3:
	        	return true;
	            case 4:
	            case 5:
	                return false;
	            default:
	                throw new IllegalArgumentException("Valor de dayOfCycle no válido: " + dayOfCycle);
	        }
	    }
    
    /**
     * @return the idEmpleado
     */
    public String getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the horasBaseContrato
     */
    public Float getHorasBaseContrato() {
        return coeficienteParcialidad;
    }

    /**
     * @param horasBaseContrato the horasBaseContrato to set
     */
    public void setHorasBaseContrato(Float horasBaseContrato) {
        this.coeficienteParcialidad = horasBaseContrato;
    }

    /**
     * @return the funciones
     */
    public EnumSet<Funcion> getFunciones() {
        return funciones;
    }

    /**
     * @param funciones the funciones to set
     */
    public void setFunciones(EnumSet<Funcion> funciones) {
        this.funciones = funciones;
    }

    /**
     * @return the grupo
     */
    public GruposEmpleados getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(GruposEmpleados grupo) {
        this.grupo = grupo;
    }
    
    
    
    
    
    
    
    
    
}
