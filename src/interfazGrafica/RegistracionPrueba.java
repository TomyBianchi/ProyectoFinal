package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistracionPrueba extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtMail;
	private JTextField textPhone;
	private JTextField textContra;
	private JTextField textRContra;
	private JTextField txtDNI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistracionPrueba frame = new RegistracionPrueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistracionPrueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(317, 64, 103, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(317, 95, 103, 20);
		contentPane.add(txtApellido);
		
		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(317, 126, 103, 20);
		contentPane.add(txtMail);
		
		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(317, 157, 103, 20);
		contentPane.add(textPhone);
		
		textContra = new JTextField();
		textContra.setColumns(10);
		textContra.setBounds(317, 188, 103, 20);
		contentPane.add(textContra);
		
		textRContra = new JTextField();
		textRContra.setColumns(10);
		textRContra.setBounds(317, 219, 103, 20);
		contentPane.add(textRContra);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(261, 67, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(261, 98, 46, 14);
		contentPane.add(lblApellido);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(261, 129, 46, 14);
		contentPane.add(lblMail);
		
		JLabel lblNumeroTelefono = new JLabel("Numero telefono");
		lblNumeroTelefono.setBounds(204, 160, 103, 14);
		contentPane.add(lblNumeroTelefono);
		
		JLabel lblContrasea = new JLabel("contraseña");
		lblContrasea.setBounds(231, 191, 86, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(261, 253, 56, 14);
		contentPane.add(lblDni);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(317, 249, 103, 20);
		contentPane.add(txtDNI);
		
		JLabel lblRepetirContrasea = new JLabel("Repetir contraseña");
		lblRepetirContrasea.setBounds(192, 222, 115, 14);
		contentPane.add(lblRepetirContrasea);
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(txtNombre.getText().length()<=0 && txtApellido.getText().length()<=0 && txtApellido.getText().length()<=0 && txtMail.getText().length()<=0 && textPhone.getText().length()<=0 &&  textContra.getText().length()<=0 &&  textRContra.getText().length()<=0) {
					JOptionPane.showMessageDialog(null,"faltan completar campos");// tengo q aprender a usar un try catch aca aunque podria dejarlo asi
				}else
					{
					if(!textContra.getText().equals(textRContra.getText()))
					{
						JOptionPane.showMessageDialog(null,"las contrasñas no coinciden");
					}else
					{
						JOptionPane.showMessageDialog(null,"usuario creado con exito");   //luego se tiene q mandar los datos al archivo o a algun crear usuario 
					}
					
					}
				
				
			}
		});
		btnNewButton.setBounds(314, 281, 89, 23);
		contentPane.add(btnNewButton);
	}
}
