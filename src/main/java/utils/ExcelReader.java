package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import clases.PrevisionFecha;
import clases.PrevisionHora;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class ExcelReader {
    private ArrayList<PrevisionFecha> previsiones;
    
    
    public void readExcelFile(String filePath)throws IOException, SQLException {
	       
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        previsiones = new ArrayList<>();
        int numberOfSheets = workbook.getNumberOfSheets();

        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            System.out.println("Hoja: " + sheet.getSheetName());
            PrevisionFecha previsionFecha = createPrevisionFecha(sheet);
            System.out.println(previsionFecha);
            System.out.println();
        }

        workbook.close();
        file.close(); 
    
}
    
    public void readExcelFile(String filePath,DefaultTableModel modeloTabla)throws IOException {
       
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);
            previsiones = new ArrayList<>();
            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                System.out.println("Hoja: " + sheet.getSheetName());
                readSheetData(sheet, modeloTabla);

            }

            workbook.close();
            file.close(); 
        
    }

    private void readSheetData(Sheet sheet, DefaultTableModel modeloTabla) {
	    DataFormatter dataFormatter = new DataFormatter();
	    int rows = sheet.getPhysicalNumberOfRows();

	    // Obtener la fecha del nombre de la hoja
	    LocalDate fecha = parseFechaFromSheetName(sheet.getSheetName());

	    // Ignorar la primera fila (encabezado)
	    for (int i = 1; i < rows; i++) {
	        Row row = sheet.getRow(i);
	        Cell horaCell = row.getCell(0);
	        Cell previsionCell = row.getCell(1);

	        String hora = dataFormatter.formatCellValue(horaCell);
	        String prevision = dataFormatter.formatCellValue(previsionCell);

	        modeloTabla.addRow(new Object[] { fecha, hora, prevision });
	    }
	}

    
    private PrevisionFecha createPrevisionFecha(Sheet sheet) throws SQLException {
        LocalDate fecha = parseFechaFromSheetName(sheet.getSheetName());
        ArrayList<PrevisionHora> previsionHoras = new ArrayList<>();

        DataFormatter dataFormatter = new DataFormatter();
        int rows = sheet.getPhysicalNumberOfRows();

        // Ignorar la primera fila (encabezado)
        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            Cell horaCell = row.getCell(0);
            Cell previsionCell = row.getCell(1);

            LocalTime hora = parseHoraFromString(dataFormatter.formatCellValue(horaCell));
            int prevision = Integer.parseInt(dataFormatter.formatCellValue(previsionCell));

            PrevisionHora previsionHora = new PrevisionHora(hora, prevision);
            previsionHoras.add(previsionHora);
        }

        return new PrevisionFecha(fecha, previsionHoras);
    }

    private LocalDate parseFechaFromSheetName(String sheetName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyy");
        return LocalDate.parse(sheetName, formatter);
    }

    private LocalTime parseHoraFromString(String horaString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(horaString, formatter);
    }
}
























/*
package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public void readExcelFile(String filePath) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);

            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                System.out.println("Hoja: " + sheet.getSheetName());
                readSheetData(sheet);
                System.out.println();
            }

            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readSheetData(Sheet sheet) {
        DataFormatter dataFormatter = new DataFormatter();
        int rows = sheet.getPhysicalNumberOfRows();

        // Ignorar la primera fila (encabezado)
        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            Cell horaCell = row.getCell(0);
            Cell previsionCell = row.getCell(1);

            String hora = dataFormatter.formatCellValue(horaCell);
            String prevision = dataFormatter.formatCellValue(previsionCell);

            System.out.println("Hora: " + hora + ", Previsión: " + prevision);
        }
    }
}
*/