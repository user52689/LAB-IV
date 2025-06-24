package ProyectoTP4;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setTitle("TP 4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 150, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		JButton btnEjercicio1 = new JButton("Ejercicio 1");
		btnEjercicio1.setBounds(172, 72, 120, 23);
		panelPrincipal.add(btnEjercicio1);
		btnEjercicio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ejercicio1 ventana = new Ejercicio1();
				ventana.setVisible(true);
			}
		});

		JButton btnEjercicio2 = new JButton("Ejercicio 2");
		btnEjercicio2.setBounds(172, 125, 120, 23);
		panelPrincipal.add(btnEjercicio2);
		btnEjercicio2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ejercicio2 ventana = new Ejercicio2();
				ventana.setVisible(true);
			}
		});

		JButton btnEjercicio3 = new JButton("Ejercicio 3");
		btnEjercicio3.setBounds(172, 182, 120, 23);
		panelPrincipal.add(btnEjercicio3);
		btnEjercicio3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ejercicio3 ventana = new Ejercicio3();
				ventana.setVisible(true);
			}
		});

		JLabel grupo = new JLabel("GRUPO N°: 9");
		grupo.setBounds(62, 36, 80, 14);
		panelPrincipal.add(grupo);
	}
}
