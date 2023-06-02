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


/**
 * 
 * @author 1DAM&&1DAW
 *Esta clase representa un empleado de la empresa
 */
public class Empleado {
    
    private String idEmpleado; 
    private String apellidos;
    private String nombre; 
    private String email; 
    private String contraseña; 
    private GruposEmpleados grupo; 
    private Float coeficienteParcialidad; // representa el porcentaje de horas semanales con respecto a una jornada completa siendo 0.5 20h semanales
    private EnumSet<Funcion> funciones; // Conjunto de funciones que el empleado puede realiza
  
    /**
     * 	Construye un objeto Empleado con un identificador de empleado.
     * esta version del constructor se usa sacar a un empleado de la base de datos.
     * lo primero que hace es hacer un query en la base de datos con DAO.Select, despues coje los elementos de la query y los añade al objeto empleado
     * @param idEmpleado el identificador único para el empleado.
     * @throws SQLException  si hay un error con la operación SQL.
     */
    public Empleado(String idEmpleado) throws SQLException {
	    if (idEmpleado == null || idEmpleado.equalsIgnoreCase("null")) { //este condicional se usa para evitar un error con los parse de abajo
	        
	    } else {
	        ArrayList<String> consulta = DAO.select("select * from empleados where id_empleado ='" + idEmpleado + "'");
	        this.idEmpleado=consulta.get(1);
	        this.apellidos=consulta.get(2);
	        this.nombre=consulta.get(3);
	        this.email=consulta.get(4);
	        this.contraseña=consulta.get(5);
	        this.grupo=GruposEmpleados.valueOf(consulta.get(6));
	        this.coeficienteParcialidad=Float.parseFloat(consulta.get(7));
	        
	        /*PARA LAS FUNCIONES*
	         * las funciones se guardan como un enumSet. En la tabla de la base de datos cada funcion tiene su columa.
	         * En la base de datos no se admiten booleans  por lo que true es 1 y false 0. 
	         * las siguientes lineas se ocupan de comprobar y añadir las funciones al empleado
	         */
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

	        
	        
	        this.funciones=EnumSet.copyOf(funcionesList);
	    }
	}

    
    /**
     * La version clasica del constructor con todos sus argumentos. se genera un objeto empleado y por ultimo se inserta en la base de datos
     * @param idEmpleado
     * @param apellidos
     * @param nombre
     * @param email
     * @param contraseña
     * @param grupo
     * @param coeficienteParcialidad
     * @param funciones
     * @throws SQLException
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
	DAO.insert("insert into empleados  (id_empleado, Apellidos, Nombre, Email, Contraseña, Grupo_Empleados, Coeficiente_Parcialidad, Funcion_caja, Funcion_almacen, Funcion_atencion_publico, Funcion_supervisor, Usuario, Operacion)" 
		+ " values('" + idEmpleado + "','" + apellidos + "','" + nombre + "','" + email+"','" + contraseña +"','" + grupo +"','" + coeficienteParcialidad +"','" + tieneFuncion(Funcion.CAJA)+ "','" +tieneFuncion(Funcion.ALMACEN)+"','" +tieneFuncion(Funcion.ATTPUBLICO)+"','" +tieneFuncion(Funcion.SUPERVISOR)+"','"+"user"+"','"+"Alta"+"')");                        
    }
    
    
    
    /**
     * Esta version del constructor se usa para hacer login en la aplicacion.
     * primero hace una consulta en la base de datos para comprobar si el usuario introducido existe. Si no hace un throw. UsuarioNoExisteExcepcion
     * Si existe, comprueba si la contraseña introducida coincide con la de la base de datos. Si no,hace un throw ContraseñaInvalidaExcepcion
     * @param email
     * @param pass
     * @throws SQLException
     * @throws UsuarioNoExisteExcepcion
     * @throws ContraseñaInvalidaExcepcion
     */
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
   	
   /*todo esto no hace falta en este constructor
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
   	*/
       }
    
    /**
     * metodo para comprobar si el empleado tiene una determinada funcion.
     * @param funcion la funcion, puede ser cualquier valor del enum funciones.
     * @return este metodo esta adaptado para insertar los datos en a db. Como no admite boolean, devuelve 1 o 0
     */
    public byte tieneFuncion(Funcion funcion) {
        if (funciones.contains(funcion)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * el mismo metodo anterior pero preparado para que devuelva un boolean. Esta version del meto es para su uso en java.
     * @param funcion
     * @return
     */
    public boolean tieneFuncionBoolean(Funcion funcion) {
        if (funciones.contains(funcion)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * metodo para comprobar si el empleado pertenece a un grupo de empleados u a otro
     * @param grupoEmpleado
     * @return
     */
    public boolean perteneceAGrupo(GruposEmpleados grupoEmpleado) {
        if (this.getGrupo().equals(grupoEmpleado)) {
            return true;
        } else {
            return false;
        }
    }

    
  
    
    
   
/**
 * metodo para calcular cuantas horas complementaria el trabajador podria realizar como maximo en base a sus horas bases
 * @param coeficienteParcialidad
 * @return
 */
    public float getHorasComplementarias(Float coeficienteParcialidad) {
	float ret = 0;
	return ret;
    }

   /**
    *  metodo para calcular cuantas horas de vacaciones le corresponden al trabajador en base a sus horas bases
    * @param coeficienteParcialidad
    * @return
    */
    public float getHorasVacaciones(Float coeficienteParcialidad) {
	float ret = 0;
	return ret;
    }

    public Float getHorasBaseContrato() {
        Float horasBase =(40*coeficienteParcialidad);
	return horasBase;
    }

    /**
     * este metodo sirve para comprobar si al empleado le corresponderia trabajar en turno de mañana dada la fecha
     * la idea general sobre los grupos y los turnos trabajados se escuentran en en el enum GruposEMpleados
     * Si es domingo devuelve false. El domingo no abre el negocio. Despues hace un switch con el dia de la semana que corresponde
     * a la fecha seleccionada. El return es una comparacion del grupo actual del empleado vs el grupo al que le toca trabajar que devuelve true or false
     * 
     * @param fecha
     * @return
     */
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
    /**
     * este metodo sirve para comprobar si al empleado le corresponderia trabajar en turno de tarde dada la fecha
     * la idea general sobre los grupos y los turnos trabajados se escuentran en en el enum GruposEMpleados
     * Si es domingo devuelve false. El domingo no abre el negocio. Despues hace un switch con el dia de la semana que corresponde
     * a la fecha seleccionada. El return es una comparacion del grupo actual del empleado vs el grupo al que le toca trabajar que devuelve true or false
     * 
     * @param fecha
     * @return
     */
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
     * Este metodo comprueba que el trabajador  no tenga ningun turno asignado ya en la base de datos para el parametro fecha
     * primero hace una consulta. Si la consulta sale vacia devuelve true 
     * @param fecha
     * @return
     */
    public boolean puedeTrabajar(LocalDate fecha) {
	    String query = "SELECT id_empleado FROM turnos WHERE id_empleado = '" + idEmpleado + "' AND fecha_turno = '" + fecha + "'";
	    
	    
	    ArrayList<String> resultadoQuery;
	    try {
	        resultadoQuery = DAO.selectAndPrint(query);
	    } catch (SQLException e) {
	      
	        e.printStackTrace();
	        return false; 
	    }
	    
	    // Verificar si el resultado de la consulta contiene algún empleado asignado al turno en la fecha especificada
	    if (resultadoQuery.isEmpty()) {
	        return true;
	    } else {
	        return false;
	    }
	}


    public String getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public GruposEmpleados getGrupo() {
		return grupo;
	}


	public void setGrupo(GruposEmpleados grupo) {
		this.grupo = grupo;
	}


	public Float getCoeficienteParcialidad() {
		return coeficienteParcialidad;
	}


	public void setCoeficienteParcialidad(Float coeficienteParcialidad) {
		this.coeficienteParcialidad = coeficienteParcialidad;
	}


	public EnumSet<Funcion> getFunciones() {
		return funciones;
	}


	public void setFunciones(EnumSet<Funcion> funciones) {
		this.funciones = funciones;
	}


	@Override
    public String toString() {
	return "Empleado [idEmpleado=" + idEmpleado + ", apellidos=" + apellidos + ", nombre=" + nombre + ", grupo="
		+ grupo + ", funciones=" + funciones + "]\n";
    }
    
    
    
    
    
    
    
    
    
}
