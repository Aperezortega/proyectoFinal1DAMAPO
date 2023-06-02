package clases;

import java.util.ArrayList;

/**
 * Clase que representa los requerimientos de empleados para una cantidad de visitas.
 */
public class RequerimientosEmpleados {

    private int visitas;
    private byte numeroCajeros;
    private byte numeroAlmacen;
    private byte numeroAttPublico;

    /**
     * Constructor de RequerimientosEmpleados.
     *
     * @param visitas         La cantidad de visitas requerida.
     * @param numeroCajeros   El número de cajeros requerido.
     * @param numeroAlmacen   El número de operarios de almacén requerido.
     * @param numeroAttPublico El número de operarios de atención al público requerido.
     */
    public RequerimientosEmpleados(int visitas, byte numeroCajeros, byte numeroAlmacen, byte numeroAttPublico) {
        this.visitas = visitas;
        this.numeroCajeros = numeroCajeros;
        this.numeroAlmacen = numeroAlmacen;
        this.numeroAttPublico = numeroAttPublico;
    }

    /**
     * Imprime los requerimientos de empleados.
     *
     * @param requerimientos La lista de requerimientos de empleados.
     * @return Una cadena de texto con los requerimientos de empleados.
     */
    public static String imprimirRequerimientos(ArrayList<RequerimientosEmpleados> requerimientos) {
        StringBuilder ret = new StringBuilder();
        for (RequerimientosEmpleados req : requerimientos) {
            ret.append(" ").append(req.toString());
        }
        return ret.toString();
    }

    /**
     * Obtiene la cantidad de visitas requerida.
     *
     * @return La cantidad de visitas requerida.
     */
    public int getVisitas() {
        return visitas;
    }

    /**
     * Establece la cantidad de visitas requerida.
     *
     * @param visitas La cantidad de visitas requerida.
     */
    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    /**
     * Obtiene el número de cajeros requerido.
     *
     * @return El número de cajeros requerido.
     */
    public byte getNumeroCajeros() {
        return numeroCajeros;
    }

    /**
     * Establece el número de cajeros requerido.
     *
     * @param numeroCajeros El número de cajeros requerido.
     */
    public void setNumeroCajeros(byte numeroCajeros) {
        this.numeroCajeros = numeroCajeros;
    }

    /**
     * Obtiene el número de operarios de almacén requerido.
     *
     * @return El número de operarios de almacén requerido.
     */
    public byte getNumeroAlmacen() {
        return numeroAlmacen;
    }

    /**
     * Establece el número de operarios de almacén requerido.
     *
     * @param numeroAlmacen El número de operarios de almacén requerido.
     */
    public void setNumeroAlmacen(byte numeroAlmacen) {
        this.numeroAlmacen = numeroAlmacen;
    }

    /**
     * Obtiene el número de operarios de atención al público requerido.
     *
     * @return El número de operarios de atención al público requerido.
     */
    public byte getNumeroAttPublico() {
        return numeroAttPublico;
    }

    /**
     * Establece el número de operarios de atención al público requerido.
     *
     * @param numeroAttPublico El número de operarios de atención al público requerido.
     */
    public void setNumeroAttPublico(byte numeroAttPublico) {
        this.numeroAttPublico = numeroAttPublico;
    }

    @Override
    public String toString() {
        return "RequerimientosEmpleados [visitas=" + visitas + ", numeroCajeros=" + numeroCajeros + ", numeroAlmacen="
                + numeroAlmacen + ", numeroAttPublico=" + numeroAttPublico + "]\n";
    }
}



