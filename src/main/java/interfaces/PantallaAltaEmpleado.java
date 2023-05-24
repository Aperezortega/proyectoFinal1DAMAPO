package interfaces;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import clases.Empleado;
import enums.Funcion;
import enums.GruposEmpleados;
import utils.DAO;

import javax.swing.UIManager;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class PantallaAltaEmpleado extends JPanel {

    
    private Ventana ventana;
    private JTextField txtHola;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField textField_4;
    private Map<JRadioButton,GruposEmpleados> mapaEnumRadio = new HashMap<>();
    
    
    /**
     * @wbp.parser.constructor
     */
    public PantallaAltaEmpleado (Ventana v){
	
	this.setPreferredSize(new Dimension(800, 600));
	setLayout(null);
	
	JMenuBar menuBar = new JMenuBar();
	menuBar.setBounds(0, 0, 800, 22);
	add(menuBar);
	
	JMenu mnNewMenu = new JMenu("Empleados");
	menuBar.add(mnNewMenu);
	
	JMenuItem mntmNewMenuItem = new JMenuItem("Alta Empleado");
	mntmNewMenuItem.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaAltaEmpleado.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu.add(mntmNewMenuItem);
	
	JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ver Empleados");
	mntmNewMenuItem_1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaVerEmpleados.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});

	mnNewMenu.add(mntmNewMenuItem_1);
	
	JMenu mnNewMenu_1 = new JMenu("Planning");
	menuBar.add(mnNewMenu_1);
	
	JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cargar Prevision");
	mntmNewMenuItem_2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaCargarPrevision.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_1.add(mntmNewMenuItem_2);
	
	JMenuItem mntmNewMenuItem_3 = new JMenuItem("Requisitos");
	mntmNewMenuItem_3.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaRequisitosEmpleado.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_1.add(mntmNewMenuItem_3);
	
	JMenuItem mntmNewMenuItem_4 = new JMenuItem("Turnos");
	mntmNewMenuItem_4.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaTurnos.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_1.add(mntmNewMenuItem_4);
	
	JMenu mnNewMenu_2 = new JMenu("T&A");
	menuBar.add(mnNewMenu_2);
	
	JMenuItem mntmNewMenuItem_5 = new JMenuItem("Abrir T&A");
	mntmNewMenuItem_5.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaTAndA.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_2.add(mntmNewMenuItem_5);
	
	JMenu mnNewMenu_3 = new JMenu("Informes");
	menuBar.add(mnNewMenu_3);
	JMenuItem mntmNewMenuItem_6 = new JMenuItem("Generar Informe");
	mntmNewMenuItem_6.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            v.cambiarAPantalla(PantallaInformes.class);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	});
	mnNewMenu_3.add(mntmNewMenuItem_6);
	//FIN DEL MENU
	// __________________________________________________________________________________________________________  	
	
	
	
	JLabel lblNewLabel = new JLabel("Altas Empleado");
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setBounds(10, 33, 780, 22);
	add(lblNewLabel);
	
	txtHola = new JTextField();
	txtHola.setBounds(332, 112, 216, 20);
	add(txtHola);
	txtHola.setColumns(10);
	
	
	textField_1 = new JTextField();
	textField_1.setBounds(332, 143, 216, 20);
	add(textField_1);
	textField_1.setColumns(10);
	
	
	textField_2 = new JTextField();
	textField_2.setBounds(332, 174, 216, 20);
	add(textField_2);
	textField_2.setColumns(10);
	
	
	textField_3 = new JTextField();
	textField_3.setBounds(332, 205, 216, 20);
	add(textField_3);
	textField_3.setColumns(10);
	
	
	passwordField = new JPasswordField();
	passwordField.setBounds(332, 236, 216, 20);
	add(passwordField);
	
	
	textField_4 = new JTextField();
	textField_4.setColumns(10);
	textField_4.setBounds(332, 358, 102, 20);
	add(textField_4);
	
	
	JLabel lblNewLabel_1 = new JLabel("ID Empleado:");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
	lblNewLabel_1.setBorder(UIManager.getBorder("TextField.border"));
	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1.setBounds(188, 112, 155, 20);
	add(lblNewLabel_1);
	
	passwordField_1 = new JPasswordField();
	passwordField_1.setBounds(332, 267, 216, 20);
	add(passwordField_1);
	
	
	JLabel lblNewLabel_1_1 = new JLabel("Apellidos");
	lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
	lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1_1.setBorder(UIManager.getBorder("TextField.border"));
	lblNewLabel_1_1.setBounds(188, 143, 155, 20);
	add(lblNewLabel_1_1);
	
	JLabel lblNewLabel_1_2 = new JLabel("Nombre");
	lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
	lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1_2.setBorder(UIManager.getBorder("TextField.border"));
	lblNewLabel_1_2.setBounds(188, 174, 155, 20);
	add(lblNewLabel_1_2);
	
	JLabel lblNewLabel_1_3 = new JLabel("Email");
	lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
	lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1_3.setBorder(UIManager.getBorder("TextField.border"));
	lblNewLabel_1_3.setBounds(188, 205, 155, 20);
	add(lblNewLabel_1_3);
	
	JLabel lblNewLabel_1_4 = new JLabel("Contraseña");
	lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
	lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1_4.setBorder(UIManager.getBorder("TextField.border"));
	lblNewLabel_1_4.setBounds(188, 236, 155, 20);
	add(lblNewLabel_1_4);
	
	JLabel lblNewLabel_1_5 = new JLabel("Confirmar Contraseña");
	lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
	lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1_5.setBorder(UIManager.getBorder("TextField.border"));
	lblNewLabel_1_5.setBounds(188, 267, 155, 20);
	add(lblNewLabel_1_5);
	
	JRadioButton rdbtnGrupoA = new JRadioButton(GruposEmpleados.GRUPO_A.name());
	rdbtnGrupoA.setFont(new Font("Tahoma", Font.PLAIN, 8));
	buttonGroup.add(rdbtnGrupoA);
	rdbtnGrupoA.setBounds(349, 294, 65, 23);
	add(rdbtnGrupoA);
	
	JRadioButton rdbtnGrupoB = new JRadioButton(GruposEmpleados.GRUPO_B.name());
	rdbtnGrupoB.setFont(new Font("Tahoma", Font.PLAIN, 8));
	rdbtnGrupoB.setBounds(416, 294, 65, 23);
	add(rdbtnGrupoB);
	
	JRadioButton rdbtnGrupoC = new JRadioButton(GruposEmpleados.GRUPO_C.name());
	rdbtnGrupoC.setFont(new Font("Tahoma", Font.PLAIN, 8));
	rdbtnGrupoC.setBounds(483, 294, 65, 23);
	add(rdbtnGrupoC);
	
	
	/** Este mapa se usa para asociar el boton de radio con el grupo de empleados. Al hacer el constructor de empleado se usara. 
	 * 
	 */
	mapaEnumRadio.put(rdbtnGrupoA, GruposEmpleados.GRUPO_A);
	mapaEnumRadio.put(rdbtnGrupoA, GruposEmpleados.GRUPO_B);
	mapaEnumRadio.put(rdbtnGrupoA, GruposEmpleados.GRUPO_C);

	JToggleButton tglbtnNewToggleButton = new JToggleButton("Caja");
	tglbtnNewToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
	tglbtnNewToggleButton.setBounds(370, 324, 81, 23);
	add(tglbtnNewToggleButton);
	
	JToggleButton tglbtnAlmacen = new JToggleButton("Almacen");
	tglbtnAlmacen.setFont(new Font("Tahoma", Font.PLAIN, 10));
	tglbtnAlmacen.setBounds(279, 324, 81, 23);
	add(tglbtnAlmacen);
	
	JToggleButton tglbtnAttpublico = new JToggleButton("AttPublico");
	tglbtnAttpublico.setFont(new Font("Tahoma", Font.PLAIN, 10));
	tglbtnAttpublico.setBounds(188, 324, 81, 23);
	add(tglbtnAttpublico);
	
	JToggleButton tglbtnSupervisor = new JToggleButton("Supervisor");
	tglbtnSupervisor.setFont(new Font("Tahoma", Font.PLAIN, 10));
	tglbtnSupervisor.setBounds(461, 324, 87, 23);
	add(tglbtnSupervisor);
	
	JLabel lblNewLabel_1_5_1 = new JLabel("Grupo de Trabajo");
	lblNewLabel_1_5_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
	lblNewLabel_1_5_1.setBorder(UIManager.getBorder("TextField.border"));
	lblNewLabel_1_5_1.setBounds(188, 297, 147, 20);
	add(lblNewLabel_1_5_1);
	
	
	JLabel lblNewLabel_1_6 = new JLabel("Horas Semanales");
	lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
	lblNewLabel_1_6.setBorder(UIManager.getBorder("TextField.border"));
	lblNewLabel_1_6.setBounds(188, 358, 155, 20);
	add(lblNewLabel_1_6);
	
	
	JLabel lblNewLabel_1_6_1 = new JLabel("Coeficiente: ");
	lblNewLabel_1_6_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1_6_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
	lblNewLabel_1_6_1.setBorder(UIManager.getBorder("TextField.border"));
	lblNewLabel_1_6_1.setBounds(416, 358, 132, 20);
	add(lblNewLabel_1_6_1);
	
	JButton btnNewButton = new JButton("DAR DE ALTA");
	btnNewButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    String idEmpleado = txtHola.getText();
		    String nombre = textField_1.getText();
		    String apellidos = textField_2.getText();
		    String email = textField_3.getText();
		    char[] password = passwordField.getPassword();
		    char[] confirmaPassword = passwordField_1.getPassword();
		    
		    List<Funcion> funcionesList = new ArrayList<Funcion>();
	                if (tglbtnNewToggleButton.isSelected()) {
	                    funcionesList.add(Funcion.CAJA);
	                }
	                if (tglbtnAlmacen.isSelected()) {
	                    funcionesList.add(Funcion.ALMACEN);
	                }
	                if (tglbtnAttpublico.isSelected()) {
	                    funcionesList.add(Funcion.ATTPUBLICO);
	                }
	                if (tglbtnSupervisor.isSelected()) {
	                    funcionesList.add(Funcion.SUPERVISOR);
	                }

	                // Convertir la lista a un array.
	                Funcion[] funciones = funcionesList.toArray(new Funcion[0]);
	                
		    if(Arrays.equals(password, confirmaPassword)) {
			String passwordAString = new String(password);
			try {
				Empleado nuevoEmpleado = new Empleado(idEmpleado,apellidos,nombre,email,passwordAString, obtenerGrupo(),obtenerCoeficienteParcialidad(), funciones);
			    } catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			    }
		    }else {
			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
			
		    }
		    
		}
	});
	btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnNewButton.setBounds(188, 389, 360, 23);
	add(btnNewButton);	
   }
    
    
    /**
     * 
     * @param v
     * @param empleado
     */
    public PantallaAltaEmpleado (Ventana v, Empleado empleado){
	/**
	 * @wbp.parser.constructor
	 */
   	this.setPreferredSize(new Dimension(800, 600));
   	setLayout(null);
   	
   	//MENU
   	JMenuBar menuBar = new JMenuBar();
   	menuBar.setBounds(0, 0, 600, 22);
   	add(menuBar);
   	
   	JMenu mnNewMenu = new JMenu("Empleados");
   	menuBar.add(mnNewMenu);
   	
   	JMenuItem mntmNewMenuItem = new JMenuItem("Alta Empleado");
   	mntmNewMenuItem.addActionListener(new ActionListener() {
   	    public void actionPerformed(ActionEvent e) {
   	        try {
   	            v.cambiarAPantalla(PantallaAltaEmpleado.class);
   	        } catch (IOException e1) {
   	            e1.printStackTrace();
   	        }
   	    }
   	});
   	mnNewMenu.add(mntmNewMenuItem);
   	
   	JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ver Empleados");
   	mntmNewMenuItem_1.addActionListener(new ActionListener() {
   	    public void actionPerformed(ActionEvent e) {
   	        try {
   	            v.cambiarAPantalla(PantallaVerEmpleados.class);
   	        } catch (IOException e1) {
   	            e1.printStackTrace();
   	        }
   	    }
   	});

   	mnNewMenu.add(mntmNewMenuItem_1);
   	
   	JMenu mnNewMenu_1 = new JMenu("Planning");
   	menuBar.add(mnNewMenu_1);
   	
   	JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cargar Prevision");
   	mntmNewMenuItem_2.addActionListener(new ActionListener() {
   	    public void actionPerformed(ActionEvent e) {
   	        try {
   	            v.cambiarAPantalla(PantallaCargarPrevision.class);
   	        } catch (IOException e1) {
   	            e1.printStackTrace();
   	        }
   	    }
   	});
   	mnNewMenu_1.add(mntmNewMenuItem_2);
   	
   	JMenuItem mntmNewMenuItem_3 = new JMenuItem("Requisitos");
   	mntmNewMenuItem_3.addActionListener(new ActionListener() {
   	    public void actionPerformed(ActionEvent e) {
   	        try {
   	            v.cambiarAPantalla(PantallaRequisitosEmpleado.class);
   	        } catch (IOException e1) {
   	            e1.printStackTrace();
   	        }
   	    }
   	});
   	mnNewMenu_1.add(mntmNewMenuItem_3);
   	
   	JMenuItem mntmNewMenuItem_4 = new JMenuItem("Turnos");
   	mntmNewMenuItem_4.addActionListener(new ActionListener() {
   	    public void actionPerformed(ActionEvent e) {
   	        try {
   	            v.cambiarAPantalla(PantallaTurnos.class);
   	        } catch (IOException e1) {
   	            e1.printStackTrace();
   	        }
   	    }
   	});
   	mnNewMenu_1.add(mntmNewMenuItem_4);
   	
   	JMenu mnNewMenu_2 = new JMenu("T&A");
   	menuBar.add(mnNewMenu_2);
   	
   	JMenuItem mntmNewMenuItem_5 = new JMenuItem("Abrir T&A");
   	mntmNewMenuItem_5.addActionListener(new ActionListener() {
   	    public void actionPerformed(ActionEvent e) {
   	        try {
   	            v.cambiarAPantalla(PantallaTAndA.class);
   	        } catch (IOException e1) {
   	            e1.printStackTrace();
   	        }
   	    }
   	});
   	mnNewMenu_2.add(mntmNewMenuItem_5);
   	
   	JMenu mnNewMenu_3 = new JMenu("Informes");
   	menuBar.add(mnNewMenu_3);
   	JMenuItem mntmNewMenuItem_6 = new JMenuItem("Generar Informe");
   	mntmNewMenuItem_6.addActionListener(new ActionListener() {
   	    public void actionPerformed(ActionEvent e) {
   	        try {
   	            v.cambiarAPantalla(PantallaInformes.class);
   	        } catch (IOException e1) {
   	            e1.printStackTrace();
   	        }
   	    }
   	});
   	mnNewMenu_3.add(mntmNewMenuItem_6);
   	//FIN DEL MENU
// __________________________________________________________________________________________________________  	
   	
   	
   	
   	JLabel lblNewLabel = new JLabel("Empleado"+ empleado.getApellidos()+" "+empleado.getNombre());
   	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
   	lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
   	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
   	lblNewLabel.setBounds(10, 33, 114, 22);
   	add(lblNewLabel);
   	
   	txtHola = new JTextField();
   	txtHola.setBounds(332, 112, 216, 20);
   	txtHola.setText(empleado.getIdEmpleado());
   	add(txtHola);
   	txtHola.setColumns(10);
   	
   	
   	textField_1 = new JTextField();
   	textField_1.setBounds(332, 143, 216, 20);
   	textField_1.setText(empleado.getApellidos());
   	add(textField_1);
   	textField_1.setColumns(10);
   	
   	
   	textField_2 = new JTextField();
   	textField_2.setBounds(332, 174, 216, 20);
   	textField_2.setText(empleado.getNombre());
   	add(textField_2);
   	textField_2.setColumns(10);
   	
   	
   	textField_3 = new JTextField();
   	textField_3.setBounds(332, 205, 216, 20);
   	textField_3.setText(empleado.getEmail());
   	add(textField_3);
   	textField_3.setColumns(10);
   	
   	
   	passwordField = new JPasswordField();
   	passwordField.setBounds(332, 236, 216, 20);
   	add(passwordField);
   	
   	
   	textField_4 = new JTextField();
   	textField_4.setColumns(10);
   	textField_4.setBounds(332, 358, 102, 20);
   	textField_4.setText(Float.toString(empleado.getHorasBaseContrato()));
   	add(textField_4);
   	
   	
   	JLabel lblNewLabel_1 = new JLabel("ID Empleado:");
   	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	lblNewLabel_1.setBorder(UIManager.getBorder("TextField.border"));
   	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
   	lblNewLabel_1.setBounds(188, 112, 155, 20);
   	add(lblNewLabel_1);
   	
   	passwordField_1 = new JPasswordField();
   	passwordField_1.setBounds(332, 267, 216, 20);
   	add(passwordField_1);
   	
   	
   	JLabel lblNewLabel_1_1 = new JLabel("Apellidos");
   	lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
   	lblNewLabel_1_1.setBorder(UIManager.getBorder("TextField.border"));
   	lblNewLabel_1_1.setBounds(188, 143, 155, 20);
   	add(lblNewLabel_1_1);
   	
   	JLabel lblNewLabel_1_2 = new JLabel("Nombre");
   	lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
   	lblNewLabel_1_2.setBorder(UIManager.getBorder("TextField.border"));
   	lblNewLabel_1_2.setBounds(188, 174, 155, 20);
   	add(lblNewLabel_1_2);
   	
   	JLabel lblNewLabel_1_3 = new JLabel("Email");
   	lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
   	lblNewLabel_1_3.setBorder(UIManager.getBorder("TextField.border"));
   	lblNewLabel_1_3.setBounds(188, 205, 155, 20);
   	add(lblNewLabel_1_3);
   	
   	JLabel lblNewLabel_1_4 = new JLabel("Contraseña");
   	lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
   	lblNewLabel_1_4.setBorder(UIManager.getBorder("TextField.border"));
   	lblNewLabel_1_4.setBounds(188, 236, 155, 20);
   	add(lblNewLabel_1_4);
   	
   	JLabel lblNewLabel_1_5 = new JLabel("Confirmar Contraseña");
   	lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
   	lblNewLabel_1_5.setBorder(UIManager.getBorder("TextField.border"));
   	lblNewLabel_1_5.setBounds(188, 267, 155, 20);
   	add(lblNewLabel_1_5);
   	
   	JCheckBox chckbxNewCheckBox = new JCheckBox("cambiar contraseña al hacer login");
	chckbxNewCheckBox.setBounds(564, 266, 216, 23);
	add(chckbxNewCheckBox);
   	
   	JRadioButton rdbtnGrupoA = new JRadioButton(GruposEmpleados.GRUPO_A.name());
   	rdbtnGrupoA.setSelected(empleado.perteneceAGrupo(GruposEmpleados.GRUPO_A));
   	rdbtnGrupoA.setFont(new Font("Tahoma", Font.PLAIN, 8));
   	buttonGroup.add(rdbtnGrupoA);
   	rdbtnGrupoA.setBounds(349, 294, 65, 23);
   	add(rdbtnGrupoA);
   	
   	JRadioButton rdbtnGrupoB = new JRadioButton(GruposEmpleados.GRUPO_B.name());
   	rdbtnGrupoA.setSelected(empleado.perteneceAGrupo(GruposEmpleados.GRUPO_B));
   	rdbtnGrupoB.setFont(new Font("Tahoma", Font.PLAIN, 8));
   	rdbtnGrupoB.setBounds(416, 294, 65, 23);
   	add(rdbtnGrupoB);
   	
   	JRadioButton rdbtnGrupoC = new JRadioButton(GruposEmpleados.GRUPO_C.name());
   	rdbtnGrupoA.setSelected(empleado.perteneceAGrupo(GruposEmpleados.GRUPO_C));
   	rdbtnGrupoC.setFont(new Font("Tahoma", Font.PLAIN, 8));
   	rdbtnGrupoC.setBounds(483, 294, 65, 23);
   	add(rdbtnGrupoC);
   	
   	
   	/** Este mapa se usa para asociar el boton de radio con el grupo de empleados. Al hacer el constructor de empleado se usara. 
   	 * 
   	 */
   	mapaEnumRadio.put(rdbtnGrupoA, GruposEmpleados.GRUPO_A);
   	mapaEnumRadio.put(rdbtnGrupoA, GruposEmpleados.GRUPO_B);
   	mapaEnumRadio.put(rdbtnGrupoA, GruposEmpleados.GRUPO_C);

   	JToggleButton tglbtnNewToggleButton = new JToggleButton("Caja");
   	tglbtnNewToggleButton.setSelected(empleado.tieneFuncionBoolean(Funcion.CAJA));
   	tglbtnNewToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	tglbtnNewToggleButton.setBounds(370, 324, 81, 23);
   	add(tglbtnNewToggleButton);
   	
   	JToggleButton tglbtnAlmacen = new JToggleButton("Almacen");
   	tglbtnAlmacen.setSelected(empleado.tieneFuncionBoolean(Funcion.ALMACEN));
   	tglbtnAlmacen.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	tglbtnAlmacen.setBounds(279, 324, 81, 23);
   	add(tglbtnAlmacen);
   	
   	JToggleButton tglbtnAttPublico = new JToggleButton("AttPublico");
   	tglbtnAttPublico.setSelected(empleado.tieneFuncionBoolean(Funcion.ATTPUBLICO));
   	tglbtnAttPublico.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	tglbtnAttPublico.setBounds(188, 324, 81, 23);
   	add(tglbtnAttPublico);
   	
   	JToggleButton tglbtnSupervisor = new JToggleButton("Supervisor");
   	tglbtnSupervisor.setSelected(empleado.tieneFuncionBoolean(Funcion.SUPERVISOR));
   	tglbtnSupervisor.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	tglbtnSupervisor.setBounds(461, 324, 87, 23);
   	add(tglbtnSupervisor);
   	
   	JLabel lblNewLabel_1_5_1 = new JLabel("Grupo de Trabajo");
   	lblNewLabel_1_5_1.setHorizontalAlignment(SwingConstants.CENTER);
   	lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	lblNewLabel_1_5_1.setBorder(UIManager.getBorder("TextField.border"));
   	lblNewLabel_1_5_1.setBounds(188, 297, 147, 20);
   	add(lblNewLabel_1_5_1);
   	
   	
   	JLabel lblNewLabel_1_6 = new JLabel("Horas Semanales");
   	lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.CENTER);
   	lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	lblNewLabel_1_6.setBorder(UIManager.getBorder("TextField.border"));
   	lblNewLabel_1_6.setBounds(188, 358, 155, 20);
   	add(lblNewLabel_1_6);
   	
   	
   	JLabel lblNewLabel_1_6_1 = new JLabel("Coeficiente: ");
   	lblNewLabel_1_6_1.setHorizontalAlignment(SwingConstants.CENTER);
   	lblNewLabel_1_6_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
   	lblNewLabel_1_6_1.setBorder(UIManager.getBorder("TextField.border"));
   	lblNewLabel_1_6_1.setBounds(416, 358, 132, 20);
   	add(lblNewLabel_1_6_1);
   	
   	JButton btnNewButton = new JButton("ACTUALIZAR DATOS");
   	btnNewButton.addMouseListener(new MouseAdapter() {
   		@Override
   		public void mouseClicked(MouseEvent e) {
   		    empleado.setIdEmpleado(txtHola.getText());
   		    empleado.setNombre(textField_1.getText());
   		    empleado.setApellidos(textField_2.getText());
   		    empleado.setEmail(textField_3.getText());
   		    char[] password = passwordField.getPassword();
   		    char[] confirmaPassword = passwordField_1.getPassword();
   		    
   		    List<Funcion> funcionesList = new ArrayList<Funcion>();
   	                if (tglbtnNewToggleButton.isSelected()) {
   	                    funcionesList.add(Funcion.CAJA);
   	                }
   	                if (tglbtnAlmacen.isSelected()) {
   	                    funcionesList.add(Funcion.ALMACEN);
   	                }
   	                if (tglbtnAttPublico.isSelected()) {
   	                    funcionesList.add(Funcion.ATTPUBLICO);
   	                }
   	                if (tglbtnSupervisor.isSelected()) {
   	                    funcionesList.add(Funcion.SUPERVISOR);
   	                }

   	                // Convertir la lista a un array.
   	                Funcion[] funciones = funcionesList.toArray(new Funcion[0]);
   	            
   	                
   	            if(chckbxNewCheckBox.isSelected()) {
   	        	 if(Arrays.equals(password, confirmaPassword)) {
   	   			String passwordAString = new String(password);
   	   			empleado.setContraseña(passwordAString);
   	   			try {
				    DAO.insert("update empleados set `ID empleado` = '"+empleado.getIdEmpleado()+"', Apellidos ='"+empleado.getApellidos()+"', Nombre ='"+empleado.getNombre()+"', Email ='"+empleado.getEmail()+
				    	"', Contraseña = '"+empleado.getContraseña()+"', Grupo_Empleados ='"+empleado.getGrupo()+"', Coeficiente_Parcialidad ="+empleado.getCoeficienteParcialidad()+", `Funcion: Caja`="+
				    	empleado.tieneFuncion(Funcion.CAJA)+", `Funcion: Almacen` ="+empleado.tieneFuncion(Funcion.ALMACEN)+", `Funcion: Atencion_Publico` ="+empleado.tieneFuncion(Funcion.ATTPUBLICO)+
				    	", `Funcion: Supervisor` ="+empleado.tieneFuncion(Funcion.SUPERVISOR)+", `Usuario:` ='user', Operacion ='Modificacion' WHERE `ID empleado`= '"+empleado.getIdEmpleado()+"'");
				} catch (SQLException e1) {
				    // TODO Auto-generated catch block
				    e1.printStackTrace();
				}
		
   	   		    }else {
   	   			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
   	   			
   	   		    }
   	            }else {
   	        	try {
			    DAO.insert("update empleados set `ID empleado` = '"+empleado.getIdEmpleado()+"', Apellidos ='"+empleado.getApellidos()+"', Nombre ='"+empleado.getNombre()+"', Email ='"+empleado.getEmail()+
			        	"', Grupo_Empleados ='"+empleado.getGrupo()+"', Coeficiente_Parcialidad ="+empleado.getCoeficienteParcialidad()+", `Funcion: Caja` ="+
			        	empleado.tieneFuncion(Funcion.CAJA)+", `Funcion: Almacen` ="+empleado.tieneFuncion(Funcion.ALMACEN)+", `Funcion: Atencion_Publico` ="+empleado.tieneFuncion(Funcion.ATTPUBLICO)+
			        	", `Funcion: Supervisor` ="+empleado.tieneFuncion(Funcion.SUPERVISOR)+", `Usuario:` ='user', Operacion ='Modificacion' WHERE `ID empleado`= '"+empleado.getIdEmpleado()+"'");
			} catch (SQLException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
   	            }
   		   
   		    
   		}
   	});
   	btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
   	btnNewButton.setBounds(188, 389, 360, 23);
   	add(btnNewButton);
   	
   	
   	
   	
       }
    
    private GruposEmpleados obtenerGrupo() {
	    for (Map.Entry<JRadioButton, GruposEmpleados> entry : mapaEnumRadio.entrySet()) {
	        if (entry.getKey().isSelected()) {
	            return entry.getValue();
	        }
	    }
	    return null;
	}
    private Float obtenerCoeficienteParcialidad() {
	byte horasBase = Byte.parseByte(textField_4.getText());
	Float coeficienteParcialidad = ((horasBase/40f)*100);
	return coeficienteParcialidad;
    }
}
