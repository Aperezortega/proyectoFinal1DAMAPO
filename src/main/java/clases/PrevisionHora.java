package clases;

import java.time.LocalTime;

/**
 * Clase que representa una previsión por hora.
 */
public class PrevisionHora {
    private LocalTime hora;
    private int visitas;

    /**
     * Constructor de PrevisionHora.
     *
     * @param hora     La hora de la previsión.
     * @param visitas  El número de visitas en esa hora.
     */
    public PrevisionHora(LocalTime hora, int visitas) {
        this.hora = hora;
        this.visitas = visitas;
    }

    /**
     * Obtiene la hora de la previsión.
     *
     * @return La hora de la previsión.
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Establece la hora de la previsión.
     *
     * @param hora La hora de la previsión.
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * Obtiene el número de visitas en esa hora.
     *
     * @return El número de visitas en esa hora.
     */
    public int getVisitas() {
        return visitas;
    }

    /**
     * Establece el número de visitas en esa hora.
     *
     * @param visitas El número de visitas en esa hora.
     */
    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    @Override
    public String toString() {
        return "Prevision " + hora + ": " + visitas;
    }
}
