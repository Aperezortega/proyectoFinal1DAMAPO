package enums;



/**
 * 
 * @author 1DAM&&1DAW
 *Este enum sirve para separar a los empleados en grupos. 
 *El proyecto esta planteado como si fuera destinado a un comercio de retail en el que los empleados trabajan en turnos rotativos
 *de mañanas y tardes de lunes a sabado siguiendo el siguiente patron:
 *		   L M X J V S D
 *GRUPO A |M M T T L L D
 *GRUPO B |T T L L M M D
 *GRUPO C |L L M M T T D
 *
 *Siendo LMXJVSD los dias de la semana, M = mañanas, T = tardes, L = libres y D = domingo.
 *
 */
public enum GruposEmpleados {
        GRUPO_A,
        GRUPO_B,
        GRUPO_C
    
}
