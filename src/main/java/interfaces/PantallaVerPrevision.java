package interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import utils.ExcelReader;

import javax.swing.RowFilter;

public class PantallaVerPrevision extends Pantalla {
    private Ventana ventana;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnCargarArchivo;
    private JButton btnCargarBD;

    public PantallaVerPrevision(Ventana v) {
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

     // Crear el botón "Filtrar"
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(450, 480, 100, 40);
        add(btnFiltrar);

        // Declarar y asignar la variable sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(modeloTabla);

        // Agregar acción al botón "Filtrar"
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Ingrese el valor a filtrar:");
                if (input != null && !input.isEmpty()) {
                    RowFilter<TableModel, Integer> rowFilter = RowFilter.regexFilter(input, 0, 1);
                    sorter.setRowFilter(rowFilter);
                } else {
                    sorter.setRowFilter(null);
                }
            }
        });


        // Crear el botón "Cargar en base de datos"
        btnCargarBD = new JButton("Cargar en base de datos");
        btnCargarBD.setBounds(250, 480, 200, 40);
        add(btnCargarBD);

        // Agregar acción al botón "Cargar en base de datos"
        btnCargarBD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes escribir la lógica para cargar los datos en la base de datos
                // Por ejemplo, puedes obtener los datos de la tabla y realizar las operaciones necesarias
            }
        });

        // Agregar ordenamiento y filtrado a las cabeceras de la tabla
     // Agregar ordenamiento y filtrado a las cabeceras de la tabla
        TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(tabla.getModel());

        // Obtener el TableColumnModel para acceder a la columna "Previsión"
        TableColumnModel columnModel = tabla.getColumnModel();
        TableColumn previsionColumn = columnModel.getColumn(2);

        // Crear un Comparator personalizado para la columna "Previsión"
        Comparator<String> previsionComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // Parsear los valores de previsión como números para compararlos
                int prevision1 = Integer.parseInt(o1);
                int prevision2 = Integer.parseInt(o2);

                return Integer.compare(prevision1, prevision2);
            }
        };

        // Establecer el Comparator personalizado en el TableRowSorter para la columna "Previsión"
        sorter1.setComparator(2, previsionComparator);

        tabla.setRowSorter(sorter1);

        tabla.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = tabla.columnAtPoint(e.getPoint());
                sorter1.toggleSortOrder(columnIndex);
            }
        });

        // Asignar el sorter1 a la tabla
        tabla.setRowSorter(sorter1);


    // Clase interna para renderizar las cabeceras de tabla como clickeables
    class ClickableHeaderRenderer extends DefaultTableCellRenderer {
        public ClickableHeaderRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
            setForeground(Color.BLUE);
            setFont(getFont().deriveFont(Font.BOLD));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            setText(value.toString());
            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (isEnabled()) {
                int x = getWidth() - 12;
                int y = getHeight() / 2 - 3;
                g.setColor(Color.BLACK);
                g.drawLine(x, y, x + 5, y);
                g.drawLine(x + 5, y, x + 2, y + 3);
                g.drawLine(x + 2, y + 3, x, y);
            		}	
        	}
    	}
    }
}
