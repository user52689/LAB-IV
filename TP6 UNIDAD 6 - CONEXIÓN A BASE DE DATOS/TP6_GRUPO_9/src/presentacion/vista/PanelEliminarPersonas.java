package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import entidad.Persona;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;

public class PanelEliminarPersonas extends JPanel {

    private static final long serialVersionUID = 1L;
    private JList<Persona> listPersonas;
	private DefaultListModel<Persona> listModel;
	private JButton btnEliminar;
	
	public PanelEliminarPersonas() {
		
		setLayout(null);
		
		listModel = new DefaultListModel<>();
		
		listPersonas = new JList<Persona>();
		listPersonas.setBounds(96, 31, 251, 147);	
		listModel = new DefaultListModel <Persona>();
		listPersonas.setModel(listModel);
		add(listPersonas);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(173, 189, 105, 18);
		add(btnEliminar);
		
	}
	public JList<Persona> getListPersonas() {
		return listPersonas;
	}
	public void setListPersonas(JList<Persona> listPersonas) {
		this.listPersonas = listPersonas;
	}
	public DefaultListModel<Persona> getListModel() {
		return listModel;
	}
	public void setListModel(DefaultListModel<Persona> listModel) {
		this.listModel = listModel;
	}
	public JButton getBtnEliminar() {
		return btnEliminar;
	}
	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}
}