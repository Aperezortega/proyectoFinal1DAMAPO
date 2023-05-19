package main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import clases.Planificador;
import clases.PrevisionVisitas;
import clases.RequerimientosEmpleados;

public class Main {

    public static void main(String[] args) {
	PrevisionVisitas p=null;
	try {
	      p= new PrevisionVisitas("prevision.csv", LocalDate.now());
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
	
	
	
	
	
	
	
	
    }

}
