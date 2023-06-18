//package interfazGrafica;
//
//import java.awt.EventQueue;
//
//import java.util.ArrayList;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//import java.io.BufferedWriter;
//import java.io.EOFException;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//
//import Clases.Usuario;
//import Clases.UsuarioNormal;
//import Clases.UsuarioVenta;
//import Enums.E_TipoUsuario;
//import Enums.E_CondFiscal;
//
//import java.awt.Color;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//
//import java.awt.FlowLayout;
//import javax.swing.SwingConstants;
//import java.awt.Font;
//import java.awt.Image;
//import java.awt.Panel;
//import java.io.BufferedReader;
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import java.awt.Label;
//import javax.swing.JTextField;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.List;
//import java.awt.event.ActionEvent;
//import javax.swing.JPasswordField;
//import java.io.ObjectOutputStream;
//
//
//public class Registrarse extends JFrame {
//
//	private JPanel contentPane;
//	private JTextField Usuariotxt;
//	private JTextField mailTxt;
//	private JTextField nombreTxt;
//	private JTextField apellidoTxt;
//	private JTextField numeroTxt;
//	private JTextField DniTxt;
//	private E_TipoUsuario tUsuario;
//	private E_CondFiscal a;
//	private JPasswordField contrasena;
//	private JPasswordField repetirContrasena;
//	private Usuario usuario;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Registrarse frame = new Registrarse();
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public Registrarse() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 924, 589);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//
//		Panel panel = new Panel();
//		panel.setBackground(Color.CYAN);
//		panel.setBounds(23, 10, 283, 530);
//		contentPane.add(panel);
//		panel.setLayout(null);
//
//		JLabel lblNewLabel = new JLabel("TIENDA ");
//		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 45));
//		lblNewLabel.setBounds(10, 11, 205, 98);
//		panel.add(lblNewLabel);
//
//		JLabel lblGamer = new JLabel("GAMER");
//		lblGamer.setFont(new Font("Tahoma", Font.BOLD, 45));
//		lblGamer.setBounds(35, 87, 205, 98);
//		panel.add(lblGamer);
//
//		JLabel lblNewLabel_1 = new JLabel("");
//		lblNewLabel_1.setBounds(10, 162, 230, 172);
//		/*algoritmo para subir imagenes desde google solo hay que reemplazar el nombre en este caso el lblNewLabel_1 **/
//		try {
//			URL url = new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmB_yYU4CYzZ812uRN9sfmtOtaUAnxkamDtg&usqp=CAU");
//			Image imagen = ImageIO.read(url);
//			ImageIcon icono = new ImageIcon(imagen.getScaledInstance(lblNewLabel_1.getWidth(),lblNewLabel_1.getHeight(), Image.SCALE_DEFAULT));
//			lblNewLabel_1.setIcon(icono);
//		} catch (MalformedURLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e) {
//            e.printStackTrace();
//        }
//		panel.add(lblNewLabel_1);
//
//		Usuariotxt = new JTextField();
//		Usuariotxt.setBounds(516, 97, 263, 29);
//		contentPane.add(Usuariotxt);
//		Usuariotxt.setColumns(10);
//
//		mailTxt = new JTextField();
//		mailTxt.setColumns(10);
//		mailTxt.setBounds(516, 137, 263, 29);
//		contentPane.add(mailTxt);
//
//		nombreTxt = new JTextField();
//		nombreTxt.setColumns(10);
//		nombreTxt.setBounds(516, 177, 263, 29);
//		contentPane.add(nombreTxt);
//
//		apellidoTxt = new JTextField();
//		apellidoTxt.setColumns(10);
//		apellidoTxt.setBounds(516, 217, 263, 29);
//		contentPane.add(apellidoTxt);
//
//		numeroTxt = new JTextField();
//		numeroTxt.setColumns(10);
//		numeroTxt.setBounds(516, 257, 263, 29);
//		contentPane.add(numeroTxt);
//
//		DniTxt = new JTextField();
//		DniTxt.setColumns(10);
//		DniTxt.setBounds(516, 297, 263, 29);
//		contentPane.add(DniTxt);
//
//		JLabel usuariolbl = new JLabel("USUARIO");
//		usuariolbl.setBounds(428, 104, 78, 14);
//		contentPane.add(usuariolbl);
//
//		JLabel maillbl = new JLabel("MAIL");
//		maillbl.setBounds(428, 144, 46, 14);
//		contentPane.add(maillbl);
//
//		JLabel nombrelbl = new JLabel("NOMBRE");
//		nombrelbl.setBounds(428, 184, 58, 14);
//		contentPane.add(nombrelbl);
//
//		JLabel apellidolbl = new JLabel("APELLDIO");
//		apellidolbl.setBounds(428, 224, 78, 14);
//		contentPane.add(apellidolbl);
//
//		JLabel numerolbl = new JLabel("NUMERO TEL");
//		numerolbl.setBounds(428, 264, 78, 14);
//		contentPane.add(numerolbl);
//
//		JLabel contrasenalbl = new JLabel("CONTRASEÑA");
//		contrasenalbl.setBounds(404, 344, 82, 14);
//		contentPane.add(contrasenalbl);
//
//		JLabel repetirContraLbl = new JLabel("REPETIR CONTRASEÑA");
//		repetirContraLbl.setHorizontalAlignment(SwingConstants.CENTER);
//		repetirContraLbl.setBounds(361, 384, 145, 14);
//		contentPane.add(repetirContraLbl);
//
//
//		JButton BotonTienda = new JButton("TIENDA");
//		BotonTienda.setBounds(643, 429, 89, 23);
//		contentPane.add(BotonTienda);
//
//
//		JButton BotonUsuario = new JButton("NORMAL");
//		BotonUsuario.setBounds(544, 429, 89, 23);
//		contentPane.add(BotonUsuario);
//
//		JButton btnNewButton = new JButton("Finalizar");
//
//		this.tUsuario = tUsuario.NORMAL;
//		//BOTON USUARIO NORMAL
//		BotonUsuario.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				tUsuario = tUsuario.NORMAL;
//				 JOptionPane.showMessageDialog(null, "sos un usuario de tipo NORMAL ");
//			}
//		});
//		//BOTON USUARIO TIENDA
//	BotonTienda.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				tUsuario = tUsuario.VENTA;
//				 JOptionPane.showMessageDialog(null, "sos un usuario de tipo TIENDA ");
//			}
//		});
//
//
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				if(nombreTxt.getText().length()<=0 && Usuariotxt.getText().length()<=0&& mailTxt.getText().length()<=0&& apellidoTxt.getText().length()<=0&& numeroTxt.getText().length()<=0&& DniTxt.getText().length()<=0)
//				{
//				JOptionPane.showMessageDialog(null, "te faltan Completar Campos");
//				}else
//				{
//					if(!contrasena.getText().equals(repetirContrasena.getText()))
//					{
//						JOptionPane.showMessageDialog(null, "las contraseñas no coinciden");
//					}else
//					{
//						if(tUsuario.compareTo(E_TipoUsuario.NORMAL)==0)
//						{
//							Usuario user = new UsuarioNormal(mailTxt.getText(),contrasena.getText(),nombreTxt.getText(),apellidoTxt.getText(),numeroTxt.getText(),E_TipoUsuario.NORMAL,DniTxt.getText());
//							guardarObjetoEnArchivo(user,"archiPrueba.txt");
//
//						}else
//						{
//							Usuario user = new UsuarioVenta(mailTxt.getText(),contrasena.getText(),nombreTxt.getText(),apellidoTxt.getText(),numeroTxt.getText(),E_TipoUsuario.VENTA,DniTxt.getText(),true,"","",E_CondFiscal.MONOTRIBUTO_A);
//							guardarObjetoEnArchivo(user,"archiPrueba.txt");
//
//						}
//
//						JOptionPane.showMessageDialog(null, "Usuario creado con exito!");
//						IniciarSecion a = new IniciarSecion();
//						a.setVisible(true);
//						ocultarVentana();
//
//					}
//				}
//
//
//			}
//		});
//		btnNewButton.setBounds(596, 481, 110, 39);
//		contentPane.add(btnNewButton);
//
//		JLabel DniLbl = new JLabel("DNI");
//		DniLbl.setHorizontalAlignment(SwingConstants.CENTER);
//		DniLbl.setBounds(372, 304, 145, 14);
//		contentPane.add(DniLbl);
//
//
//
//
//
//		JLabel lblQueTipoDe = new JLabel("QUE TIPO DE USUARIO ERES?");
//		lblQueTipoDe.setHorizontalAlignment(SwingConstants.CENTER);
//		lblQueTipoDe.setBounds(361, 433, 173, 19);
//		contentPane.add(lblQueTipoDe);
//
//		JLabel lblNewLabel_2 = new JLabel("por defecto sos normal");
//		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
//		lblNewLabel_2.setBounds(387, 450, 116, 14);
//		contentPane.add(lblNewLabel_2);
//
//		contrasena = new JPasswordField();
//		contrasena.setBounds(516, 341, 263, 25);
//		contentPane.add(contrasena);
//
//		repetirContrasena = new JPasswordField();
//		repetirContrasena.setBounds(516, 381, 263, 25);
//		contentPane.add(repetirContrasena);
//	}
//
//
//	   public static boolean guardarObjetoEnArchivo(Usuario user, String rutaArchivo) {
//	        try {
//	            FileOutputStream fileOutputStream = new FileOutputStream(rutaArchivo, true);
//	            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//
//	            objectOutputStream.writeObject(user);
//
//	            objectOutputStream.close();
//	            fileOutputStream.close();
//	            return true;
//	        } catch (IOException e) {
//	            System.out.println("Error al guardar el objeto en el archivo: " + e.getMessage());
//	            return false;
//	        }
//	    }
//	public void ocultarVentana()
//	{
//		this.dispose();
//	}
//
//}
