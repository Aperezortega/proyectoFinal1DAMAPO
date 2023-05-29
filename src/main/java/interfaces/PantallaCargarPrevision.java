package interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import clases.PrevisionFecha;
import utils.ExcelReader;

public class PantallaCargarPrevision extends Pantalla {
    private Ventana ventana;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnCargarArchivo;
    private JButton btnCargarBD;
    private String path ="";
    public PantallaCargarPrevision(Ventana v) {
        super(v);
        this.setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        // Crear la tabla y el modelo de tabla
        modeloTabla = new DefaultTableModel();
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(50, 50, 700, 400);
        add(scrollPane);

        // Agregar columnas a la tabla
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Hora");
        modeloTabla.addColumn("Previsión");

        // Crear el botón "Cargar archivo"
        btnCargarArchivo = new JButton("Cargar archivo");
        btnCargarArchivo.setBounds(50, 480, 150, 40);
        add(btnCargarArchivo);

        // Agregar acción al botón "Cargar archivo"
        btnCargarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccionar archivo");
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos Excel", "xlsx"));
          

                int selection = fileChooser.showOpenDialog(null);
                if (selection == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String filePath = file.getAbsolutePath();
                    path=filePath;
                    try {
                        ExcelReader excelReader = new ExcelReader();
                        excelReader.readExcelFile(filePath, modeloTabla);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al leer el archivo Excel", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Crear el botón "Cargar en base de datos"
        btnCargarBD = new JButton("Cargar en base de datos");
        btnCargarBD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    try {
                        ExcelReader excelReader = new ExcelReader();
                        excelReader.readExcelFile(path);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al leer el archivo Excel", "Error", JOptionPane.ERROR_MESSAGE);
                    }  
        	    
        	}
        });
        btnCargarBD.setBounds(250, 480, 200, 40);
        add(btnCargarBD);

        // Agregar acción al botón "Cargar en base de datos"
       

    }
}
