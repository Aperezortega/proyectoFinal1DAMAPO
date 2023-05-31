package clases;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import enums.Funcion;
import enums.GruposEmpleados;
import exceptions.ContraseñaInvalidaExcepcion;
import exceptions.UsuarioNoExisteExcepcion;
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
     * @throws SQLException 
     */
    public Empleado(String idEmpleado) throws SQLException {
	    if (idEmpleado == null || idEmpleado.equalsIgnoreCase("null")) {
	        // Aquí puedes establecer los valores predeterminados para este objeto Empleado cuando el idEmpleado es 'null'.
	        // Dependiendo de cómo quieras manejar este caso, puedes dejar los campos como null, o asignarles algún valor por defecto.
	    } else {
	        ArrayList<String> consulta = DAO.select("select * from empleados where `ID Empleado` ='" + idEmpleado + "'");
	        this.idEmpleado=consulta.get(1);
	        this.apellidos=consulta.get(2);
	        this.nombre=consulta.get(3);
	        this.email=consulta.get(4);
	        this.contraseña=consulta.get(5);
	        this.grupo=GruposEmpleados.valueOf(consulta.get(6));
	        this.coeficienteParcialidad=Float.parseFloat(consulta.get(7));
	        Byte skill1=Byte.parseByte(consulta.get(8));
	        Byte skill2=Byte.parseByte(consulta.get(9));
	        Byte skill3=Byte.parseByte(consulta.get(10));
	        Byte skill4=Byte.parseByte(consulta.get(11));
	        List<Funcion> funcionesList = new ArrayList<Funcion>();
	        if (skill1==1) {
	            funcionesList.add(Funcion.CAJA);
	        }
	        if (skill2==1) {
	            funcionesList.add(Funcion.ALMACEN);
	        }
	        if (skill3==1) {
	            funcionesList.add(Funcion.ATTPUBLICO);
	        }
	        if (skill1==4) {
	            funcionesList.add(Funcion.SUPERVISOR);
	        }

	        // Convertir la lista a un array.
	        
	        this.funciones=EnumSet.copyOf(funcionesList);
	    }
	}

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
    
    public Empleado(String email, String pass) throws SQLException, UsuarioNoExisteExcepcion, ContraseñaInvalidaExcepcion {
   	super();
   	ArrayList<String>consulta = DAO.select("select * from empleados where email ='"+email +"'");
   	
   	if(consulta.isEmpty()) {
   	    throw new UsuarioNoExisteExcepcion("No existe el usuario con el email "+email);
   	}else if(!(consulta.get(5).equals(pass))) {
   	    throw new ContraseñaInvalidaExcepcion("La contraseña "+pass+ " no es valida");
   	}
   	this.idEmpleado=consulta.get(1);
   	this.apellidos=consulta.get(2);
   	this.nombre=consulta.get(3);
   	this.email = consulta.get(4);
   	this.grupo =GruposEmpleados.valueOf(consulta.get(6));
   	this.coeficienteParcialidad=Float.parseFloat(consulta.get(7));
   	Byte skill1=Byte.parseByte(consulta.get(8));
	Byte skill2=Byte.parseByte(consulta.get(9));
	Byte skill3=Byte.parseByte(consulta.get(10));
	Byte skill4=Byte.parseByte(consulta.get(11));
	  List<Funcion> funcionesList = new ArrayList<Funcion>();
             if (skill1==1) {
                 funcionesList.add(Funcion.CAJA);
             }
             if (skill2==1) {
                 funcionesList.add(Funcion.ALMACEN);
             }
             if (skill3==1) {
                 funcionesList.add(Funcion.ATTPUBLICO);
             }
             if (skill1==1) {
                 funcionesList.add(Funcion.SUPERVISOR);
             }

             // Convertir la lista a un array.
             
	this.funciones=EnumSet.copyOf(funcionesList);
   	
       }
    
    public byte tieneFuncion(Funcion funcion) {
	if(funciones.contains(funcion)) {
	    return 1;
	}else {
	return 0;
	}
    }
    public boolean tieneFuncionBoolean(Funcion funcion) {
   	if(funciones.contains(funcion)) {
   	    return true;
   	}else {
   	return false;
   	}
       }
    
    public boolean perteneceAGrupo(GruposEmpleados grupoEmpleado) {
	if(this.getGrupo().equals(grupoEmpleado)) {
	    return true;
	}else {
	return false;
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
	    if (fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
	        return false;
	    }

	    switch (fecha.getDayOfWeek()) {
	        case MONDAY:
	        case TUESDAY:
	            return this.getGrupo() == GruposEmpleados.GRUPO_A;
	        case WEDNESDAY:
	        case THURSDAY:
	            return this.getGrupo() == GruposEmpleados.GRUPO_C;
	        case FRIDAY:
	        case SATURDAY:
	            return this.getGrupo() == GruposEmpleados.GRUPO_B;
	        default:
	            throw new IllegalArgumentException("Día de la semana no válido: " + fecha.getDayOfWeek());
	    }
	}
    
    public boolean isWorkinTardes(LocalDate fecha) {
	    if (fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
	        return false;
	    }

	    switch (fecha.getDayOfWeek()) {
	        case MONDAY:
	        case TUESDAY:
	            return this.getGrupo() == GruposEmpleados.GRUPO_B;
	        case WEDNESDAY:
	        case THURSDAY:
	            return this.getGrupo() == GruposEmpleados.GRUPO_A;
	        case FRIDAY:
	        case SATURDAY:
	            return this.getGrupo() == GruposEmpleados.GRUPO_C;
	        default:
	            throw new IllegalArgumentException("Día de la semana no válido: " + fecha.getDayOfWeek());
	    }
	}
    
    /**
     * @return the idEmpleado
     */
    
    
    
    
    public String getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * @return the coeficienteParcialidad
     */
    public Float getCoeficienteParcialidad() {
        return coeficienteParcialidad;
    }

    /**
     * @param coeficienteParcialidad the coeficienteParcialidad to set
     */
    public void setCoeficienteParcialidad(Float coeficienteParcialidad) {
        this.coeficienteParcialidad = coeficienteParcialidad;
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
        Float horasBase =(40*coeficienteParcialidad);
	return horasBase;
    }

    /**
     * @param horasBaseContrato the horasBaseContrato to set
     */ 
    

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
    @Override
    public String toString() {
	return "Empleado [idEmpleado=" + idEmpleado + ", apellidos=" + apellidos + ", nombre=" + nombre + ", grupo="
		+ grupo + ", funciones=" + funciones + "]\n";
    }
    
    
    
    
    
    
    
    
    
}
