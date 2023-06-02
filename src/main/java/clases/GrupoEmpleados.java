/* creo que esta clase no se usa
package clases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import enums.GruposEmpleados;

public class GrupoEmpleados {
  
    private GruposEmpleados nombreGrupo;
    private ArrayList<Empleado> empleados;

    
    public GrupoEmpleados(GruposEmpleados nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
        this.empleados = new ArrayList<Empleado>();
    }

    public GruposEmpleados getNombreGrupo() {
        return nombreGrupo;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public String getTurno(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    int dayOfCycle;

	    switch(nombreGrupo) {
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
	            throw new IllegalArgumentException("Grupo desconocido: " + nombreGrupo);
	    }

	    switch(dayOfCycle) {
	        case 0: case 1:
	            return "Mañana";
	        case 2: case 3:
	            return "Tarde";
	        case 4: case 5:
	            return "Libre";
	        default:
	            throw new IllegalArgumentException("Valor de dayOfCycle no válido: " + dayOfCycle);
	    }
	}

    public void addEmpleado(Empleado empleado ) {
	this.empleados.add(empleado);
	empleado.setGrupo(this.getNombreGrupo());
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Grupo ").append(nombreGrupo).append(":\n");

        for (Empleado empleado : empleados) {
            sb.append("\t").append(empleado).append("\n");
        }

        return sb.toString();
    }

}
*/