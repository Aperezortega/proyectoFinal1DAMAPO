package utils;

import java.util.ArrayList;

import clases.Empleado;

public class Session {
    private static Session instance=null;
    private Empleado empleadoActual;
    private ArrayList<Empleado>plantilla;
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
    
    public void setPlantilla(ArrayList<Empleado> plantilla) {
	this.plantilla=plantilla;
    }
    public ArrayList<Empleado>getPlantilla(){
	return plantilla;
    }
}
