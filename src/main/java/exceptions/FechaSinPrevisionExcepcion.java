package exceptions;
/*
 * esta excepcion hara que no se puedan generar turnos si la fecha de fin seleccionada para generar turnos no tiene una prevision
 */
public class FechaSinPrevisionExcepcion extends Exception{
    public FechaSinPrevisionExcepcion(String message) {
        super(message);
        
    }
}
