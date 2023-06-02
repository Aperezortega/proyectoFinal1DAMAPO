package interfaces;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Empleado;
import clases.Turno;
import enums.Funcion;
import exceptions.ContraseñaInvalidaExcepcion;
import exceptions.UsuarioNoExisteExcepcion;
import utils.DAO;
import utils.Session;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class PantallaLogin extends Pantalla {
    private JPasswordField txtPassword;
    private JTextField txtEmailEmpleado;
    private Image backgroundImage;
	public PantallaLogin(Ventana v) throws IOException {
	   super(v);
		setLayout(null);
		this.setPreferredSize(new Dimension(800, 600));
		txtPassword = new JPasswordField();
		txtPassword.setToolTipText("ID Empleado");
		txtPassword.setBounds(248, 295, 100, 20);
		add(txtPassword);
		txtPassword.setColumns(10);
		txtEmailEmpleado = new JTextField();
		txtEmailEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmailEmpleado.setToolTipText("");
		txtEmailEmpleado.setBounds(248, 250, 100, 20);
		add(txtEmailEmpleado);
		txtEmailEmpleado.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    char[] password = txtPassword.getPassword();
			    String passwordAString = new String(password);
			    
			    
			    try {
				Empleado empleadoActual= new Empleado(txtEmailEmpleado.getText(),passwordAString) ;
				Session.getInstance().setEmpleadoActual(empleadoActual);
				if(empleadoActual.tieneFuncionBoolean(Funcion.SUPERVISOR)) {
				    
				
				 try {
					ventana.cambiarAPantalla(PantallaMenu.class);
					Session.getInstance().setPlantilla(DAO.SelectEmpleados());
					
				    } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				    }
				}else {
				    JOptionPane.showMessageDialog(null, "Acceso no autorizado", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
				}
			    } catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
			    }catch(UsuarioNoExisteExcepcion e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
			    }catch(ContraseñaInvalidaExcepcion e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
			    	}
			    
			    }
			   
			}
		);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(248, 371, 100, 23);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(248, 281, 86, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(248, 236, 86, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("EasyPlanner");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_2.setBounds(213, 136, 177, 51);
		add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Check in");
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        char[] password = txtPassword.getPassword();
		        String passwordAString = new String(password);
		        Empleado empleadoActual = null;
		        try {
		            empleadoActual = new Empleado(txtEmailEmpleado.getText(), passwordAString);
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        } catch (UsuarioNoExisteExcepcion e1) {
		            e1.printStackTrace();
		            JOptionPane.showMessageDialog(null, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (ContraseñaInvalidaExcepcion e1) {
		            e1.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		        /*
		        comprueba que hay turno
		        si hay inserta
		        si no crea un turno e inserta
		        */
		        LocalDate fechaHoy = LocalDate.now();
		        String query = "SELECT id_turno FROM turnos WHERE fecha_turno = '"
		                + fechaHoy.toString()
		                + "' AND id_empleado = '"
		                + empleadoActual.getIdEmpleado()
		                + "'";

		        try {
		            ArrayList<String> resultados = DAO.select(query);

		            if (!resultados.isEmpty()) {
		                String idTurno = resultados.get(0);
		                LocalTime checkInActual = LocalTime.now();
		                query = "UPDATE turnos SET check_in = '" + checkInActual.toString() + "' WHERE id_turno = '" + idTurno + "'";
		                DAO.insert(query);

		                System.out.println("Insert realizado");
		            } else {
		                LocalTime checkInActual = LocalTime.now();
		                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		                Turno nuevoTurno = new Turno("checkin" + empleadoActual.getIdEmpleado() + checkInActual.format(formatter).toString(), fechaHoy, empleadoActual, checkInActual);

		                System.out.println("Nuevo turno creado e insert realizado");
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnNewButton_1.setBounds(138, 371, 100, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Check out");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    char[] password = txtPassword.getPassword();
			        String passwordAString = new String(password);
			        Empleado empleadoActual = null;
			        try {
			            empleadoActual = new Empleado(txtEmailEmpleado.getText(), passwordAString);
			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        } catch (UsuarioNoExisteExcepcion e1) {
			            e1.printStackTrace();
			            JOptionPane.showMessageDialog(null, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
			        } catch (ContraseñaInvalidaExcepcion e1) {
			            e1.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			        /*
			        comprueba que hay turno
			        si hay inserta
			        si no crea un turno e inserta
			        */
			        LocalDate fechaHoy = LocalDate.now();
			        String query = "SELECT id_turno FROM turnos WHERE fecha_turno = '"
			                + fechaHoy.toString()
			                + "' AND id_empleado = '"
			                + empleadoActual.getIdEmpleado()
			                + "'";

			        try {
			            ArrayList<String> resultados = DAO.select(query);

			            if (!resultados.isEmpty()) {
			                String idTurno = resultados.get(0);
			                LocalTime checkInActual = LocalTime.now();
			                query = "UPDATE turnos SET check_out = '" + checkInActual.toString() + "' WHERE id_turno = '" + idTurno + "'";
			                DAO.insert(query);

			                System.out.println("Insert realizado");
			            } else {
			                LocalTime checkInActual = LocalTime.now();
			                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			                Turno nuevoTurno = new Turno("checkout" + empleadoActual.getIdEmpleado() + checkInActual.format(formatter).toString(), fechaHoy, empleadoActual, checkInActual);

			                System.out.println("Nuevo turno creado e insert realizado");
			            }
			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        }
			    }
			});   
			    
		
		btnNewButton_2.setBounds(358, 371, 100, 23);
		add(btnNewButton_2);
		
		
	
		// Load the background image
	       
	          
	            backgroundImage = ImageIO.read(new File("fondo.png"));
	        
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        // Draw the background image
	        if(backgroundImage != null) {
	            Image scaledImage = backgroundImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
	            g.drawImage(scaledImage, 0, 0, this);
	        }
	    }

	    /**
	     * @return the txtPassword
	     */
	    public JTextField getTxtPassword() {
	        return txtPassword;
	    }

	    /**
	     * @param txtPassword the txtPassword to set
	     */
	    public void setTxtPassword(JPasswordField txtPassword) {
	        this.txtPassword = txtPassword;
	    }

	    /**
	     * @return the txtIdEmpleado
	     */
	    public JTextField getTxtEmailEmpleado() {
	        return txtEmailEmpleado;
	    }

	    /**
	     * @param txtIdEmpleado the txtIdEmpleado to set
	     */
	    public void setTxtIdEmpleado(JTextField txtIdEmpleado) {
	        this.txtEmailEmpleado = txtIdEmpleado;
	    }
	}

