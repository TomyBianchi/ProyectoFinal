package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Clases.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TiendaPrincipal {

	private JFrame frame;
	private Usuario usuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TiendaPrincipal window = new TiendaPrincipal();
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
	public TiendaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("tienda COnstruyendose");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(203, 151, 189, 20);
		frame.getContentPane().add(lblNewLabel);
		
	
				JButton btnNewButton = new JButton("inciar secion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				IniciarSecion secion = new IniciarSecion();
				secion.setVisible(true);
				btnNewButton.setVisible(false);
				
				
			}
		});
		btnNewButton.setBounds(27, 11, 105, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("ayuda porfavo");
		lblNewLabel_1.setBounds(50, 15, 221, 14);
		frame.getContentPane().add(lblNewLabel_1);
	
	
		
		
	}
	//metodos aux
	public void ocultarVentana()
	{
		frame.dispose();
	}
	public void getVisible() {
		// TODO Auto-generated method stub
		frame.setVisible(true);
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

