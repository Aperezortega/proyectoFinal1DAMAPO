package main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import clases.Empleado;
import clases.GrupoEmpleados;
import clases.Planificador;
import clases.PrevisionFecha;
import clases.RequerimientosEmpleados;
import enums.Funcion;
import enums.GruposEmpleados;

public class Main {

    public static void main(String[] args) {
	PrevisionFecha p=null;
	ArrayList<Empleado> plantilla =new ArrayList<Empleado>();
	try {
	      p= new PrevisionFecha("prevision.csv", LocalDate.now());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	ArrayList<RequerimientosEmpleados> requerimientos= new  ArrayList<RequerimientosEmpleados>();
	
	try {
	   requerimientos =  Planificador.generarRequerimientos("requisitos.csv");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	Planificador pl=null;
	try {
	    pl = new Planificador(p);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	System.out.println(RequerimientosEmpleados.imprimirRequerimientos(requerimientos));
	System.out.println(p);
	System.out.println(pl);
	System.out.println(pl.imprimirListaTurnos());
	
	
	 Empleado empleado1 = new Empleado("EMP1", "Empleado 1", 0.5f, Funcion.CAJA, Funcion.ATTPUBLICO);
	 plantilla.add(empleado1);
	 Empleado empleado2 = new Empleado("EMP2", "Empleado 2", 0.4f, Funcion.ATTPUBLICO);
	 plantilla.add(empleado2);
	 Empleado empleado3 = new Empleado("EMP3", "Empleado 3", 0.6f, Funcion.CAJA);
	 plantilla.add(empleado3);
	 Empleado empleado4 = new Empleado("EMP4", "Empleado 4", 0.6f, Funcion.CAJA);
	 plantilla.add(empleado4);
	 Empleado empleado5 = new Empleado("EMP5", "Empleado 5", 0.3f, Funcion.ATTPUBLICO);
	 plantilla.add(empleado5);
	 Empleado empleado6 = new Empleado("EMP6", "Empleado 6", 0.5f, Funcion.CAJA, Funcion.ATTPUBLICO);
	 plantilla.add(empleado6);
	 Empleado empleado7 = new Empleado("EMP7", "Empleado 7", 0.7f, Funcion.CAJA);
	 plantilla.add(empleado7);
	 Empleado empleado8 = new Empleado("EMP8", "Empleado 8", 0.4f, Funcion.ALMACEN);
	 plantilla.add(empleado8);
	 Empleado empleado9 = new Empleado("EMP9", "Empleado 9", 0.6f, Funcion.CAJA);
	 plantilla.add(empleado9);
	 Empleado empleado10 = new Empleado("EMP10", "Empleado 10", 0.5f, Funcion.ATTPUBLICO);
	 plantilla.add(empleado10);
	 Empleado empleado11 = new Empleado("EMP11", "Empleado 11", 0.4f, Funcion.ALMACEN);
	 plantilla.add(empleado11);
	 Empleado empleado12 = new Empleado("EMP12", "Empleado 12", 0.6f, Funcion.CAJA, Funcion.ALMACEN);
	 plantilla.add(empleado12);
	 Empleado empleado13 = new Empleado("EMP13", "Empleado 13", 0.7f, Funcion.CAJA, Funcion.ATTPUBLICO);
	 plantilla.add(empleado13);
	 Empleado empleado14 = new Empleado("EMP14", "Empleado 14", 0.5f, Funcion.CAJA);
	 plantilla.add(empleado14);
	 Empleado empleado15 = new Empleado("EMP15", "Empleado 15", 0.4f, Funcion.ALMACEN);
	 plantilla.add(empleado15);
	 Empleado empleado16 = new Empleado("EMP16", "Empleado 16", 0.6f, Funcion.CAJA, Funcion.ALMACEN);
	 plantilla.add(empleado16);
	 Empleado empleado17 = new Empleado("EMP17", "Empleado 17", 0.6f, Funcion.CAJA);
	 plantilla.add(empleado17);
	 Empleado empleado18 = new Empleado("EMP18", "Empleado 18", 0.3f, Funcion.ATTPUBLICO);
	 plantilla.add(empleado18);
	 Empleado empleado19 = new Empleado("EMP19", "Empleado 19", 0.5f, Funcion.CAJA, Funcion.ATTPUBLICO);
	 plantilla.add(empleado19);
	 Empleado empleado20 = new Empleado("EMP20", "Empleado 20", 0.7f, Funcion.CAJA);
	 plantilla.add(empleado20);
	
	ArrayList<Empleado> arrayA = new ArrayList<Empleado>();
	ArrayList<Empleado> arrayB = new ArrayList<Empleado>();
	ArrayList<Empleado> arrayC = new ArrayList<Empleado>();
	GrupoEmpleados A = new GrupoEmpleados(GruposEmpleados.GRUPO_A);
	GrupoEmpleados B = new GrupoEmpleados(GruposEmpleados.GRUPO_B);
	GrupoEmpleados C = new GrupoEmpleados(GruposEmpleados.GRUPO_C);
	A.addEmpleado(empleado1);
	A.addEmpleado(empleado2);
	A.addEmpleado(empleado3);
	A.addEmpleado(empleado4);
	A.addEmpleado(empleado5);
	A.addEmpleado(empleado6);
	B.addEmpleado(empleado7);
	B.addEmpleado(empleado8);
	B.addEmpleado(empleado9);
	B.addEmpleado(empleado10);
	B.addEmpleado(empleado11);
	B.addEmpleado(empleado12);
	C.addEmpleado(empleado13);
	C.addEmpleado(empleado14);
	C.addEmpleado(empleado15);
	C.addEmpleado(empleado16);
	C.addEmpleado(empleado17);
	C.addEmpleado(empleado18);
	C.addEmpleado(empleado19);
	C.addEmpleado(empleado20);
	System.out.println(pl.verEmpleadosDisponibles("2023-05-20Caja1", plantilla).toString());
    }

}
