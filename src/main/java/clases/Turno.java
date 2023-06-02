package clases;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import enums.Funcion;
import enums.TipoTurno;
import utils.DAO;

public class Turno {

    private String idTurno;
    private LocalDate fechaTurno;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Empleado empleado;
    private Funcion funcion;
    private TipoTurno tipoTurno;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private boolean validado;

    /**
     * Constructor de Turno que carga los datos desde la base de datos utilizando el ID del turno.
     *
     * @param idTurno El ID del turno.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public Turno(String idTurno) throws SQLException {
        ArrayList<String> consulta = DAO.selectAndPrint("select * from turnos where id_turno ='" + idTurno + "'");
        this.idTurno = consulta.get(1);
        this.fechaTurno = LocalDate.parse(consulta.get(2));
        this.horaInicio = LocalTime.parse(consulta.get(3));
        this.horaFin = LocalTime.parse(consulta.get(4));
        String empleadoId = consulta.get(5);
        if (empleadoId != null && !empleadoId.equalsIgnoreCase("null")) {
            this.empleado = new Empleado(empleadoId);
        }
        this.empleado = new Empleado(consulta.get(5));
        this.funcion = Funcion.valueOf(consulta.get(6));
        if (consulta.get(7) != null) {
            this.checkIn = LocalTime.parse(consulta.get(7));
        }
        if (consulta.get(8) != null) {
            this.checkOut = LocalTime.parse(consulta.get(8));
        }

        if (horaInicio.getHour() >= 10 && horaInicio.getHour() < 14) {
            this.tipoTurno = TipoTurno.MAÑANA;
        } else {
            this.tipoTurno = TipoTurno.TARDE;
        }
    }

    /**
     * Constructor de Turno con todos los parámetros.
     *
     * @param idTurno     El ID del turno.
     * @param fechaTurno  La fecha del turno.
     * @param horaInicio  La hora de inicio del turno.
     * @param horaFin     La hora de finalización del turno.
     * @param empleado    El empleado asignado al turno.
     * @param funcion     La función del turno.
     */
    public Turno(String idTurno, LocalDate fechaTurno, LocalTime horaInicio, LocalTime horaFin, Empleado empleado,
                 Funcion funcion) {
        this.idTurno = idTurno;
        this.fechaTurno = fechaTurno;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.empleado = empleado;
        this.funcion = funcion;
        if (horaInicio.getHour() >= 10 && horaInicio.getHour() < 14) {
            this.tipoTurno = TipoTurno.MAÑANA;
        } else {
            this.tipoTurno = TipoTurno.TARDE;
        }
        try {
            DAO.insert("INSERT INTO turnos (id_turno, fecha_turno, hora_inicio, hora_fin, id_empleado, funcion) " +
                    "VALUES ('" + idTurno + "', '" + fechaTurno + "', '" + horaInicio + "', '" + horaFin + "', '" +
                    empleado.getIdEmpleado() + "', '" + funcion + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor de Turno sin empleado asignado.
     *
     * @param idTurno     El ID del turno.
     * @param fechaTurno  La fecha del turno.
     * @param horaInicio  La hora de inicio del turno.
     * @param horaFin     La hora de finalización del turno.
     * @param funcion     La función del turno.
     */
    public Turno(String idTurno, LocalDate fechaTurno, LocalTime horaInicio, LocalTime horaFin,
                 Funcion funcion) {
        this.idTurno = idTurno;
        this.fechaTurno = fechaTurno;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.funcion = funcion;
        if (horaInicio.getHour() >= 10 && horaInicio.getHour() < 14) {
            this.tipoTurno = TipoTurno.MAÑANA;
        } else {
            this.tipoTurno = TipoTurno.TARDE;
        }
        try {
            DAO.insert("INSERT INTO turnos (id_turno, fecha_turno, hora_inicio, hora_fin, funcion) " +
                    "VALUES ('" + idTurno + "', '" + fechaTurno + "', '" + horaInicio + "', '" + horaFin + "', '" +
                    funcion + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el ID del turno.
     *
     * @return El ID del turno.
     */
    public String getIdTurno() {
        return idTurno;
    }

    /**
     * Establece el ID del turno.
     *
     * @param idTurno El ID del turno.
     */
    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    /**
     * Obtiene la fecha del turno.
     *
     * @return La fecha del turno.
     */
    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    /**
     * Establece la fecha del turno.
     *
     * @param fechaTurno La fecha del turno.
     */
    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    /**
     * Obtiene la hora de inicio del turno.
     *
     * @return La hora de inicio del turno.
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Establece la hora de inicio del turno.
     *
     * @param horaInicio La hora de inicio del turno.
     */
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Obtiene la hora de finalización del turno.
     *
     * @return La hora de finalización del turno.
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     * Establece la hora de finalización del turno.
     *
     * @param horaFin La hora de finalización del turno.
     */
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * Obtiene el empleado asignado al turno.
     *
     * @return El empleado asignado al turno.
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Establece el empleado asignado al turno.
     *
     * @param empleado El empleado asignado al turno.
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * Obtiene la función del turno.
     *
     * @return La función del turno.
     */
    public Funcion getFuncion() {
        return funcion;
    }

    /**
     * Establece la función del turno.
     *
     * @param funcion La función del turno.
     */
    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    /**
     * Obtiene el tipo de turno (MAÑANA o TARDE).
     *
     * @return El tipo de turno.
     */
    public TipoTurno getTipoTurno() {
        return tipoTurno;
    }

    /**
     * Establece el tipo de turno (MAÑANA o TARDE).
     *
     * @param tipoTurno El tipo de turno.
     */
    public void setTipoTurno(TipoTurno tipoTurno) {
        this.tipoTurno = tipoTurno;
    }

    /**
     * Obtiene la hora de check-in del turno.
     *
     * @return La hora de check-in del turno.
     */
    public LocalTime getCheckIn() {
        return checkIn;
    }

    /**
     * Establece la hora de check-in del turno.
     *
     * @param checkIn La hora de check-in del turno.
     */
    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * Obtiene la hora de check-out del turno.
     *
     * @return La hora de check-out del turno.
     */
    public LocalTime getCheckOut() {
        return checkOut;
    }

    /**
     * Establece la hora de check-out del turno.
     *
     * @param checkOut La hora de check-out del turno.
     */
    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    /**
     * Calcula las horas trabajadas durante el turno.
     *
     * @param checkIn  La hora de check-in del turno.
     * @param checkOut La hora de check-out del turno.
     * @return El número de horas trabajadas.
     */
    public long getHorasTrabajadas(LocalTime checkIn, LocalTime checkOut) {
        if (checkIn == null || checkOut == null) {
            // Decide cómo manejar esta situación. Podrías devolver cero,
            // lanzar una excepción, registrar un mensaje de error, etc.
            return 0;
        }

        Duration duration = Duration.between(checkIn, checkOut);
        long horasTrabajadas = duration.toHours();
        return horasTrabajadas;
    }

    @Override
    public String toString() {
        String idTurnoFormateado = String.format("%-22s", idTurno);

        if (this.empleado == null) {
            return idTurnoFormateado + "," + horaInicio + "-" + horaFin;
        } else {
            return idTurnoFormateado + empleado.getNombre() + "-" + horaInicio + "-" + horaFin;
        }
    }
}

