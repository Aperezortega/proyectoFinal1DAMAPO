package utils;

import clases.Empleado;

public class Session {
    private static Session instance=null;
    private Empleado empleadoActual;
    
    private Session() {
	
    }

    public static Session getInstance() {
	if(instance==null) {
	    instance= new Session();
	}
	return instance;
    }
    
    public Empleado getEmpleadoActual(){
	return empleadoActual;
    }
    public void setEmpleadoActual(Empleado empleadoActual) {
	this.empleadoActual = empleadoActual;
    }
}
