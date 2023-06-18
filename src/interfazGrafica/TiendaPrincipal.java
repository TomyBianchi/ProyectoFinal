//package interfazGrafica;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.SwingConstants;
//
//import Clases.GestionTienda;
//import Clases.Usuario;
//
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import javax.swing.JPanel;
//import java.awt.Color;
//import javax.swing.JScrollBar;
//
//public class TiendaPrincipal {
//
//	private JFrame frame;
//	private Usuario usuario;
//	private String s;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//
//		GestionTienda MercadoLibre = new GestionTienda();
//
//
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TiendaPrincipal window = new TiendaPrincipal();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public TiendaPrincipal() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.setSize(1280, 720);
//		frame.setBounds(100, 100, 1280, 720);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLocationRelativeTo(null);
//		frame.getContentPane().setLayout(null);
//
//		JLabel lblNewLabel = new JLabel("tienda COnstruyendose");
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setBounds(203, 151, 189, 20);
//
//
//		frame.getContentPane().add(lblNewLabel);
//
//
//				JButton btnNewButton = new JButton("inciar secion");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//
//				IniciarSecion secion = new IniciarSecion();
//				secion.setVisible(true);
//				btnNewButton.setVisible(false);
//				ocultarVentana();
//
//			}
//		});
//		btnNewButton.setBounds(27, 40, 105, 23);
//		frame.getContentPane().add(btnNewButton);
//
//
//
//
//	}
//	public void setUser(Usuario user) //este es nuestro nuevo main sige roto
//	{
//
//		JPanel panel = new JPanel();
//		panel.setOpaque(true);
//		panel.setBounds(0, 0, 1280, 720);
//		panel.setLayout(null);
//		frame.getContentPane().add(panel);
//		frame.getLayeredPane().setComponentZOrder(panel, 0);
//
//
//		JPanel panelPublicaciones = new JPanel();
//		panelPublicaciones.setBounds(150, 0, 1180, 720);
//		panelPublicaciones.setOpaque(true);
//		panelPublicaciones.setBackground(Color.CYAN);
//		panelPublicaciones.setLayout(null);
//		frame.getContentPane().add(panelPublicaciones);
//		frame.getLayeredPane().setComponentZOrder(panelPublicaciones, 0);
//
//
//
//		this.usuario = user;
//		JLabel nomUsuario = new JLabel();
//		nomUsuario.setBounds(27, 11, 145, 20);
//		panel.add(nomUsuario);
//		nomUsuario.setText("Bienvenido "+user.getNombre()+"!");
//
//
//		JScrollBar scrollBar = new JScrollBar();
//		scrollBar.setBounds(1247, 0, 17, 681);
//		panelPublicaciones.add(scrollBar);
//		frame.getLayeredPane().setComponentZOrder(scrollBar, 0);
//
//
//		JButton btnNewButton = new JButton("crear publicacion");
//	btnNewButton.addActionListener(new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//
//			//algoritmo para crear nueva publi
//			JOptionPane.showMessageDialog(null, "todavia no existe la funcion pero pronto lo hara");
//
//		}
//	});
//
//	btnNewButton.setBounds(27, 40, 105, 50);
//	panel.add(btnNewButton);
//
//
//	    JLabel lblNewLabel = new JLabel("tienda COnstruyendose");
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setBounds(203, 151, 189, 20);
//		frame.getContentPane().add(lblNewLabel);
//
//
//	}
//
//	//metodos aux
//	public void ocultarVentana()
//	{
//		frame.dispose();
//	}
//	public void getVisible() {
//		// TODO Auto-generated method stub
//		frame.setVisible(true);
//
//	}
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//}
//
