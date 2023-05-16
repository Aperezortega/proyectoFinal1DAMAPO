package main;

import java.io.IOException;
import java.time.LocalDate;

import clases.PrevisionVisitas;

public class Main {

    public static void main(String[] args) {
	PrevisionVisitas p=null;
	try {
	     p = new PrevisionVisitas("prevision.csv", LocalDate.now());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	
	
	System.out.println(p);
	

    }

}
