package interfazGrafica;

import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.Icon;

public class IniciarSecion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSecion frame = new IniciarSecion();
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
	public IniciarSecion() {
		setTitle("INICIAR SECION");
		this.setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mail");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 108, 126, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(0, 138, 126, 30);
		contentPane.add(lblContrasea);
		
		textField = new JTextField();
		textField.setBounds(129, 149, 257, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(129, 108, 257, 30);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setIcon(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(152, 200, 150, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Registrarse registro = new Registrarse();
				ocultarVentana();
				registro.setVisible(true);
				
				
			}
		});
		btnNewButton_1.setBounds(374, 247, 108, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("No tenes cuenta?");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(368, 232, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(396, 41, 150, 157);
		try {
			URL url = new URL("https://www.shutterstock.com/image-vector/vector-logo-game-pad-store-260nw-1437140663.jpg");
			Image imagen = ImageIO.read(url);
			ImageIcon icono = new ImageIcon(imagen.getScaledInstance(lblNewLabel_2.getWidth(),lblNewLabel_2.getHeight(), Image.SCALE_DEFAULT));
			lblNewLabel_2.setIcon(icono);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		
		//lblNewLabel_2.setBounds(387, 55, 160, 124);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(312, 196, 49, 39);
		try {
			URL url = new URL("https://media.istockphoto.com/id/1094780808/vector/approval-symbol-check-mark-in-a-circle-drawn-by-hand-vector-green-sign-ok-approval-or.jpg?s=612x612&w=0&k=20&c=0mlB50r769kHmLkVcq_HpdNFGdHIA_Cu_tPqN4IKZbc=");
			Image imagen = ImageIO.read(url);
			ImageIcon icono = new ImageIcon(imagen.getScaledInstance(lblNewLabel_3.getWidth(),lblNewLabel_3.getHeight(), Image.SCALE_DEFAULT));
			lblNewLabel_3.setIcon(icono);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        }
		contentPane.add(lblNewLabel_3);
		
		JLabel imagen_3 = new JLabel("");
		imagen_3.setBounds(492, 243, 29, 27);
		
		try {
			URL url = new URL("https://images.emojiterra.com/google/android-oreo/512px/1f609.png");
			Image imagen = ImageIO.read(url);
			ImageIcon icono = new ImageIcon(imagen.getScaledInstance(imagen_3.getWidth(),imagen_3.getHeight(), Image.SCALE_DEFAULT));
			imagen_3.setIcon(icono);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		contentPane.add(imagen_3);
	}

	public void ocultarVentana()
	{
		this.dispose();
	}
}
