package clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;

import enums.Funcion;
import enums.GruposEmpleados;

public class Empleado {
    
    private String idEmpleado;
    private String nombre;
    private Float horasBaseContrato;
    private EnumSet<Funcion> funciones;
    private GruposEmpleados grupo;
    private String usuario;
    private String contraseña;
  
    /**
     * @param idEmpleado
     * @param nombre
     * @param horasComplementarias
     * @param horasVacaciones
     * @param horasBaseContrato
     * @param funciones
     */
    public Empleado(String idEmpleado, String nombre, Float horasBaseContrato, Funcion...funciones ) {
	super();
	this.idEmpleado = idEmpleado;
	this.nombre = nombre;
	this.horasBaseContrato = horasBaseContrato;
	this.grupo = null;
	this.funciones = EnumSet.noneOf(Funcion.class);
	for (Funcion funcion : funciones) {
            this.funciones.add(funcion);
        }
    }
    
    public boolean tieneFuncion(Funcion funcion) {
	return funciones.contains(funcion);
	
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
        return horasBaseContrato;
    }

    /**
     * @param horasBaseContrato the horasBaseContrato to set
     */
    public void setHorasBaseContrato(Float horasBaseContrato) {
        this.horasBaseContrato = horasBaseContrato;
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
