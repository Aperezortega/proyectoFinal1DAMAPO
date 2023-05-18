package clases;

import java.util.EnumSet;

import enums.Funcion;

public class Empleado {
    
    private String idEmpleado;
    private String nombre;
    private Float horasBaseContrato;
    private EnumSet<Funcion> funciones;
  
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
	funciones.add(funcion);
    }

    public float getHorasComplementarias(Float horasBase) {
	float ret = 0;
	return ret;
    }

   
    public float getHorasVacaciones(Float horasBase) {
	float ret = 0;
	return ret;
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
    
    
    
    
    
    
    
    
    
}
