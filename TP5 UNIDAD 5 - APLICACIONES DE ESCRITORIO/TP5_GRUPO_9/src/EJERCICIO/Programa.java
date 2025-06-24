package EJERCICIO;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.DefaultListModel;


public class Programa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	public static DefaultListModel<Peliculas> listaPeliculas = new DefaultListModel<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Programa frame = new Programa();
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
	public Programa() {
		setTitle("Programa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 460);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPeliculas = new JMenu("Peliculas");
		menuBar.add(mnPeliculas);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Agregar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				PanelAgregarPelicula panelAgregarPelicula = new PanelAgregarPelicula();
				panel.add(panelAgregarPelicula);
				panel.repaint();
				panel.revalidate();
			}
		});
		mnPeliculas.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_Listar = new JMenuItem("Listar");
		mntmNewMenuItem_Listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				PanelListarPeliculas panelListar = new PanelListarPeliculas();
				panel.add(panelListar);
				panel.repaint();
				panel.revalidate();
			}
		});
		mnPeliculas.add(mntmNewMenuItem_Listar);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
