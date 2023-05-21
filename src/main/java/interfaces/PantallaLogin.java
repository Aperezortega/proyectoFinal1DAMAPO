package interfaces;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import java.awt.Color;

public class PantallaLogin extends JPanel {
    private Ventana ventana; // esto es para tener una referencia facil para cambiar de ventana
    private JTextField txtPassword;
    private JTextField txtIdEmpleado;
    private Image backgroundImage;
	public PantallaLogin(Ventana v) throws IOException {
	    this.ventana=v;
		setLayout(null);
		this.setPreferredSize(new Dimension(400, 400));
		txtPassword = new JTextField();
		txtPassword.setToolTipText("ID Empleado");
		txtPassword.setBounds(154, 225, 86, 20);
		add(txtPassword);
		txtPassword.setColumns(10);
		txtIdEmpleado = new JTextField();
		txtIdEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
		txtIdEmpleado.setToolTipText("");
		txtIdEmpleado.setBounds(154, 180, 86, 20);
		add(txtIdEmpleado);
		txtIdEmpleado.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(154, 267, 89, 23);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(154, 211, 86, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID Empleado");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(154, 166, 86, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("EasyPlanner");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel_2.setBounds(102, 66, 177, 51);
		add(lblNewLabel_2);
	
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
	}

