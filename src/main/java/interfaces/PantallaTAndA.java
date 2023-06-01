package interfaces;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.github.lgooddatepicker.components.DatePicker;

import clases.Empleado;
import clases.Turno;
import utils.DAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaTAndA extends Pantalla {
    private DatePicker inicioDatePicker;
    private DatePicker finDatePicker;
    private Ventana ventana;
    private ArrayList<Empleado> plantilla;
    private JTable tabla;
    private JTable tablaRegistro;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    private JComboBox<Empleado> empleadosComboBox;

    public PantallaTAndA(Ventana v) {
        super(v);
        this.setPreferredSize(new Dimension(1500, 1000));
        setLayout(null);
        try {
            plantilla = DAO.SelectEmpleados();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        inicioDatePicker = new DatePicker();
        inicioDatePicker.setBounds(54, 61, 173, 35);
        inicioDatePicker.getComponentToggleCalendarButton().setBounds(141, 5, 26, 25);
        inicioDatePicker.getComponentDateTextField().setBounds(0, 5, 147, 25);
        add(inicioDatePicker);
        inicioDatePicker.setLayout(null);

        finDatePicker = new DatePicker();
        finDatePicker.getComponentToggleCalendarButton().setBounds(131, 5, 26, 25);
        finDatePicker.getComponentDateTextField().setBounds(0, 5, 137, 25);
        finDatePicker.setBounds(54, 131, 167, 35);
        add(finDatePicker);
        finDatePicker.setLayout(null);

        JLabel lblNewLabel = new JLabel("Fecha inicio");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(54, 48, 167, 14);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Fecha fin");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(44, 104, 173, 14);
        add(lblNewLabel_1);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(288, 140, 1098, 500);
        add(scrollPane);

        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1 || column == 2 || column == 5) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return String.class;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        return Integer.class;
                    case 10:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };

        modeloTabla.setColumnIdentifiers(new Object[]{
                "Fecha", "Hora de inicio", "Hora de fin",
                "Check in", "Check out", "Horas trabajadas",
                "horas base", "horas complementarias",
                "horas vacaciones", "horas ausencia", "Validado"
        });

        tabla = new JTable(modeloTabla);
        scrollPane.setViewportView(tabla);

        empleadosComboBox = new JComboBox<>();
        empleadosComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualizarTabla();
            }
        });
        empleadosComboBox.setBounds(538, 48, 167, 35);
        empleadosComboBox.addItem(null);
        add(empleadosComboBox);
        for (Empleado empleado : plantilla) {
            empleadosComboBox.addItem(empleado);
        }
        empleadosComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Empleado) {
                    Empleado empleado = (Empleado) value;
                    setText(empleado.getNombre() + " " + empleado.getApellidos());
                } else {
                    setText("");
                }
                return this;
            }
        });

        JLabel lblNewLabel_2 = new JLabel("Empleados");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(538, 22, 167, 14);
        add(lblNewLabel_2);

        JButton leftArrow = new JButton("<=");
        leftArrow.setBounds(474, 48, 54, 35);
        add(leftArrow);
        leftArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = empleadosComboBox.getSelectedIndex();
                if (selectedIndex > 0) {
                    empleadosComboBox.setSelectedIndex(selectedIndex - 1);
                }
            }
        });

        JButton rightArrow = new JButton("=>");
        rightArrow.setBounds(715, 48, 54, 35);
        add(rightArrow);
        rightArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = empleadosComboBox.getSelectedIndex();
                if (selectedIndex < empleadosComboBox.getItemCount() - 1) {
                    empleadosComboBox.setSelectedIndex(selectedIndex + 1);
                }
            }
        });

        JScrollPane scrollPaneRegistroHoras = new JScrollPane();
        scrollPaneRegistroHoras.setBounds(288, 700, 1098, 200);
        add(scrollPaneRegistroHoras);

        DefaultTableModel modeloTablaRegistroHoras = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    default:
                        return String.class;
                }
            }
        };

        modeloTablaRegistroHoras.setColumnIdentifiers(new Object[]{
                "Empleado", "Fecha", "Horas trabajadas",
                "Horas base", "Horas complementarias",
                "Horas vacaciones", "Horas ausencia"
        });

        tablaRegistro = new JTable(modeloTablaRegistroHoras);
        scrollPaneRegistroHoras.setViewportView(tablaRegistro);
        TableColumn empleadoColumn = tablaRegistro.getColumnModel().getColumn(0);
        empleadoColumn.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Empleado) {
                    Empleado empleado = (Empleado) value;
                    value = empleado.getNombre() + " " + empleado.getApellidos();
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });

        JButton btnNewButton = new JButton("Validar filas ");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    if ((boolean) modeloTabla.getValueAt(i, 10)) {
                        modeloTablaRegistroHoras.addRow(new Object[]{
                                empleadosComboBox.getSelectedItem(),
                                modeloTabla.getValueAt(i, 0),
                                modeloTabla.getValueAt(i, 5),
                                modeloTabla.getValueAt(i, 6),
                                modeloTabla.getValueAt(i, 7),
                                modeloTabla.getValueAt(i, 8),
                                modeloTabla.getValueAt(i, 9)
                        });
                    }
                }
                modeloTabla.setRowCount(0);
                
            }
        });
        btnNewButton.setBounds(54, 191, 167, 23);
        add(btnNewButton);

        inicioDatePicker.addDateChangeListener(e -> actualizarTabla());
        finDatePicker.addDateChangeListener(e -> actualizarTabla());
        empleadosComboBox.addActionListener(e -> actualizarTabla());
    }

    

    private void actualizarTabla() {
        LocalDate fechaInicio = inicioDatePicker.getDate();
        LocalDate fechaFin = finDatePicker.getDate();
        Empleado empleado = (Empleado) empleadosComboBox.getSelectedItem();

        if (fechaInicio != null && fechaFin != null && empleado != null) {
            modeloTabla.setRowCount(0);

            try {
                ArrayList<Turno> turnos = DAO.selectTurnosDe(fechaInicio, fechaFin, empleado);

                for (Turno turno : turnos) {
                    modeloTabla.addRow(new Object[]{
                            turno.getFechaTurno(), turno.getHoraInicio(), turno.getHoraFin(),
                            turno.getCheckIn(), turno.getCheckOut(), turno.getHorasTrabajadas(turno.getCheckIn(), turno.getCheckOut()),
                            "", "","", "", false
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}