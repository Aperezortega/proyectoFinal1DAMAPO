package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import clases.Empleado;
import clases.PrevisionFecha;
import clases.PrevisionHora;


/* si no se pone static no da error
 * al poner static da este error 
 * Cannot make a static reference to the non-static field connection
 */
public abstract class DAO {
    private static Connection connection;// no tiene constructor, tiene una clase fabrica
  
    
    public static int insert(String query) throws SQLException {
	Statement querier=connect();
	System.out.println(query);//!
	int ret=querier.executeUpdate(query);
	disconnect(querier);
	return ret;
    }
    
    public static int delete(String query) throws SQLException {
	Statement querier=connect();
	System.out.println(query);//!
	int ret=querier.executeUpdate(query);
	disconnect(querier);
	return ret;
    }
    
    
    private static Statement connect() {
	try {
	    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proyecto_asistencia","root","root");
	    return connection.createStatement();
	} catch (SQLException e) {
	    
	    e.printStackTrace();
	    return null;
	}
	
    }

    
    public static void disconnect(Statement smt) {
	try {
	    smt.close();
	    connection.close();
	}catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    public static ArrayList<String> selectAndPrint(String query) throws SQLException {
	    Statement querier = connect();
	    System.out.println(query);
	    ResultSet resultSet = querier.executeQuery(query);
	    ArrayList<String>ret = new ArrayList<String>();
	    try {
	        ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
	        int columnCount = metaData.getColumnCount();

	        while (resultSet.next()) {
	            for (int i = 1; i <= columnCount; i++) {
	                String columnName = metaData.getColumnName(i);
	                String value = resultSet.getString(i);
	                ret.add(value);
	                System.out.println(columnName + ": " + value);
	            }
	            System.out.println("---");
	            
	        }
	    } finally {
	        disconnect(querier);
	    }
	    return ret;
	}
    public static ArrayList<String> select(String query) throws SQLException {
	    Statement querier = connect();
	    System.out.println(query);
	    ResultSet resultSet = querier.executeQuery(query);
	    ArrayList<String>ret = new ArrayList<String>();
	    try {
	        ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
	        int columnCount = metaData.getColumnCount();

	        while (resultSet.next()) {
	            for (int i = 1; i <= columnCount; i++) {
	                String value = resultSet.getString(i);
	                ret.add(value);
	                
	            }
	          
	            
	        }
	    } finally {
	        disconnect(querier);
	    }
	    return ret;
	}

    public static List<PrevisionFecha> selectPrevision(String query) throws SQLException {
	    Statement querier = connect();
	    ResultSet resultSet = querier.executeQuery(query);
	    Map<LocalDate, PrevisionFecha> map = new HashMap<>();

	    try {
	        while (resultSet.next()) {
	            LocalDate fecha = resultSet.getObject("fecha", LocalDate.class);
	            LocalTime hora = resultSet.getObject("hora", LocalTime.class);
	            int visitas = resultSet.getInt("visitas");
	            
	            PrevisionFecha previsionFecha = map.get(fecha);
	            if (previsionFecha == null) {
	                previsionFecha = new PrevisionFecha();
	                previsionFecha.setFecha(fecha);
	                previsionFecha.setPrevision(new ArrayList<>());
	                map.put(fecha, previsionFecha);
	            }

	            PrevisionHora previsionHora = new PrevisionHora(hora, visitas);
	            previsionFecha.getPrevision().add(previsionHora);
	        }
	    } finally {
	        disconnect(querier);
	    }

	    return new ArrayList<>(map.values());
	}
    
    public static ArrayList<Empleado> SelectEmpleados() throws SQLException {
	    ArrayList<String> resultadoQuery = new ArrayList<>();
	    ArrayList<Empleado> plantilla = new ArrayList<>();
	    try {
	        resultadoQuery = DAO.select("SELECT `ID Empleado` FROM empleados");
	    } catch (SQLException e1) {
	        e1.printStackTrace();
	    }
	    
	    for (int i = 0; i < resultadoQuery.size(); i ++) {
	        String idEmpleado = resultadoQuery.get(i);
	      
	        
	        Empleado empleado = new Empleado(idEmpleado);
	        plantilla.add(empleado);
	    }
	    
	    return plantilla;
	}


}
