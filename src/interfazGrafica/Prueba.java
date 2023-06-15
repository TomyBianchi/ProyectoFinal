package interfazGrafica;
import java.lang.Exception;
import Genericas.G_Usuario;
import Clases.Usuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.AWTException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.JPanel;

public class Prueba {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtUsuario;
	private JTextField txtContrasea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba window = new Prueba();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Prueba() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 675, 316);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(new Color(245, 117, 245));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Segoe UI Historic", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				Usuario a = new Usuario();  ///supongamos que creo q el usuario o me lo traigo de un arch
				
				    String g = String.valueOf(passwordField.getPassword());  //con esto obtengo el largo de su contraseña
					if(g.length()<3)
					{
						JOptionPane.showMessageDialog(null, "la contraseña es muy corta como tu pito"); //muestro un mensaje si su contraseña es muy corta //faltan mas comprobaciones 
					}else 
					{
						
						JOptionPane.showMessageDialog(null, textField.getText()+ ", bienvenid@!"); //se tiene q mejorar pero por ahora no hay como
						
						frame.dispose(); //cierro el frame1
						
						TiendaPrincipalPrueba prueba = new TiendaPrincipalPrueba(textField.getText()); //abro el frame2 con la tienda y le paso el nombre de usuario (con el nombre de usuario reviso el doc con usuario y creo su tienda)
						prueba.setVisible(true); //hago visible el segundo frame
						
						//JOptionPane.showMessageDialog(null,"¡BIENVENIDO "+ textField.getText() +" A LA MEJOR TIENDA DE PEREFICOS GAMER!");  //prueba de cartelito
					}
					
			
				
				//JOptionPane.showMessageDialog(null, "puto el que lee");
				
			}
		});
		btnNewButton.setBounds(202, 183, 104, 37);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(141, 84, 249, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 123, 249, 26);
		frame.getContentPane().add(passwordField);
		
		txtUsuario = new JTextField();
		txtUsuario.setForeground(Color.BLACK);
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtUsuario.setEnabled(false);
		txtUsuario.setBackground(Color.WHITE);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setEditable(false);
		txtUsuario.setText("USUARIO");
		txtUsuario.setBounds(29, 88, 102, 20);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasea = new JTextField();
		txtContrasea.setForeground(Color.BLACK);
		txtContrasea.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtContrasea.setEnabled(false);
		txtContrasea.setText("CONTRASEÑA");
		txtContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		txtContrasea.setEditable(false);
		txtContrasea.setColumns(10);
		txtContrasea.setBackground(Color.WHITE);
		txtContrasea.setBounds(29, 126, 102, 20);
		frame.getContentPane().add(txtContrasea);
		
		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegistracionPrueba prueba2 = new RegistracionPrueba();
				prueba2.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(316, 227, 104, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
