package presentacion.vista;

import javax.swing.*;
import entidad.Persona;

public class PanelModificarPersonas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDNI;
	private JList<Persona> listPersonas; 
	private JButton btnModificar;

	public PanelModificarPersonas() {
		setLayout(null);
		
		JLabel lblModificar = new JLabel("Seleccione la persona que desea modificar:");
		lblModificar.setBounds(10, 11, 312, 14);
		add(lblModificar);
		
		// USAR EL ATRIBUTO, NO VARIABLE LOCAL
		listPersonas = new JList<>();
		listPersonas.setBounds(10, 36, 377, 153);
		add(listPersonas);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 200, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(106, 200, 86, 20);
		add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDNI = new JTextField();
		txtDNI.setEditable(false);
		txtDNI.setBounds(202, 200, 86, 20);
		add(txtDNI);
		txtDNI.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(298, 200, 89, 23);
		add(btnModificar);		
		
		listPersonas.addListSelectionListener(e -> {
		    Persona seleccionada = listPersonas.getSelectedValue();
		    if (seleccionada != null) {
		        txtNombre.setText(seleccionada.getNombre());
		        txtApellido.setText(seleccionada.getApellido());
		        txtDNI.setText(seleccionada.getDni());
		    }
		});
	}
	
	public JList<Persona> getListPersonas() {
	    return listPersonas;
	}

	public JTextField getTxtNombre() {
	    return txtNombre;
	}

	public JTextField getTxtApellido() {
	    return txtApellido;
	}

	public JTextField getTxtDNI() {
	    return txtDNI;
	}

	public JButton getBtnModificar() {
	    return btnModificar;
	}
}
