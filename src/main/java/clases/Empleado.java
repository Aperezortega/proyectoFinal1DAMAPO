package clases;

public class Empleado {
    
    private String idEmpleado;
    private String nombre;
    private boolean skillCaja;
    private boolean skillAlmacen;
    private Float horasComplementarias;
    private Float horasVacaciones;
    private Float horasBaseContrato;
   
    /**
     * @param idEmpleado
     * @param nombre
     * @param skillCaja
     * @param skillAlmacen
     * @param horasComplementarias
     * @param horasVacaciones
     * @param horasBaseContrato
     */
    public Empleado(String idEmpleado, String nombre, boolean skillCaja, boolean skillAlmacen,
	    Float horasComplementarias, Float horasVacaciones, Float horasBaseContrato) {
	super();
	this.idEmpleado = idEmpleado;
	this.nombre = nombre;
	this.skillCaja = skillCaja;
	this.skillAlmacen = skillAlmacen;
	this.horasComplementarias = horasComplementarias;
	this.horasVacaciones = horasVacaciones;
	this.horasBaseContrato = horasBaseContrato;
    }
    /**
     * @return the idEmpleado
     */
    public String getIdEmpleado() {
        return idEmpleado;
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
     * @return the skillCaja
     */
    public boolean isSkillCaja() {
        return skillCaja;
    }
    /**
     * @param skillCaja the skillCaja to set
     */
    public void setSkillCaja(boolean skillCaja) {
        this.skillCaja = skillCaja;
    }
    /**
     * @return the skillAlmacen
     */
    public boolean isSkillAlmacen() {
        return skillAlmacen;
    }
    /**
     * @param skillAlmacen the skillAlmacen to set
     */
    public void setSkillAlmacen(boolean skillAlmacen) {
        this.skillAlmacen = skillAlmacen;
    }
    /**
     * @return the horasComplementarias
     */
    public Float getHorasComplementarias() {
        return horasComplementarias;
    }
    /**
     * @param horasComplementarias the horasComplementarias to set
     */
    public void setHorasComplementarias(Float horasComplementarias) {
        this.horasComplementarias = horasComplementarias;
    }
    /**
     * @return the horasVacaciones
     */
    public Float getHorasVacaciones() {
        return horasVacaciones;
    }
    /**
     * @param horasVacaciones the horasVacaciones to set
     */
    public void setHorasVacaciones(Float horasVacaciones) {
        this.horasVacaciones = horasVacaciones;
    }
    /**
     * @return the horasBaseContrato
     */
    public Float getHorasBaseContrato() {
        return horasBaseContrato;
    }
    /**
     * @param horasBaseContrato the horasBaseContrato to set
     */
    public void setHorasBaseContrato(Float horasBaseContrato) {
        this.horasBaseContrato = horasBaseContrato;
    }
    @Override
    public String toString() {
	return "Empleado: "+ idEmpleado + ", nombre=" + nombre + ", Caja: " + skillCaja
		+ ", Almacen: " + skillAlmacen + ", horasComplementarias=" + horasComplementarias
		+ ", horasVacaciones=" + horasVacaciones + ", horasBaseContrato=" + horasBaseContrato + "]";
    }

    
    
    
    
    
    
    
    
    
    
}
