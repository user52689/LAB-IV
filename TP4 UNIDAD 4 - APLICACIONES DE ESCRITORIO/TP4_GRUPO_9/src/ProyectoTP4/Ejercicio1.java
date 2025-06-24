package ProyectoTP4;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Ejercicio1 extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtFechaNac;
    private JLabel lblResultado;

    public Ejercicio1() {
        setTitle("Ejercicio 1");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 450, 400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);

              //Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 20, 80, 25);
        panel.add(lblNombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(120, 20, 200, 25);
        panel.add(txtNombre);
        
             //Apellido
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(30, 60, 80, 25);
        panel.add(lblApellido);

        JTextField txtApellido = new JTextField();
        txtApellido.setBounds(120, 60, 200, 25);
        panel.add(txtApellido);
        
             //Telefono
        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setBounds(30, 100, 80, 25);
        panel.add(lblTelefono);

        JTextField txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 100, 200, 25);
        panel.add(txtTelefono);
             
             //Fec Nac.
        JLabel lblFechaNac = new JLabel("Fecha Nac:");
        lblFechaNac.setBounds(30, 140, 80, 25);
        panel.add(lblFechaNac);

        JTextField txtFechaNac = new JTextField();
        txtFechaNac.setBounds(120, 140, 200, 25);
        panel.add(txtFechaNac);
        
        JLabel lblResultado = new JLabel("");
        lblResultado.setBounds(30, 220, 340, 25);
        panel.add(lblResultado);
        
        JButton btnMostrar = new JButton("Mostrar");
        btnMostrar.setBounds(231, 187, 89, 23);
        panel.add(btnMostrar);

        EventoMostrar eventoMostrar = new EventoMostrar();
        eventoMostrar.setTxtNombre(txtNombre);
        eventoMostrar.setTxtApellido(txtApellido);
        eventoMostrar.setTxtTelefono(txtTelefono);
        eventoMostrar.setTxtFechaNac(txtFechaNac);
        eventoMostrar.setLblResultado(lblResultado);
        
        btnMostrar.addActionListener(eventoMostrar);
        
        
    }
}

//Clase para la funcionalidad
class EventoMostrar implements ActionListener {
	 private JTextField txtNombre;
	 private JTextField txtApellido;
	 private JTextField txtTelefono;
	 private JTextField txtFechaNac;
	 private JLabel lblResultado;
 
	 public EventoMostrar() {
	 }
	 
	 public void setTxtNombre(JTextField txt){
		    this.txtNombre = txt;
		}
		public void setTxtApellido(JTextField txt){
		    this.txtApellido = txt;
		}
		public void setTxtTelefono(JTextField txt){
		    this.txtTelefono = txt;
		}
		public void setTxtFechaNac(JTextField txt){
		    this.txtFechaNac = txt;
		}
		public void setLblResultado(JLabel lbl){
		    this.lblResultado = lbl;
		}

		
		@Override
		public void actionPerformed(ActionEvent e) {
		    String nombre = txtNombre.getText();
		    String apellido = txtApellido.getText();
		    String telefono = txtTelefono.getText();
		    String fechaNac = txtFechaNac.getText();
		    
		    resetearColores();
		    
		    boolean campoVacio = verificarCamposCompletos(nombre, apellido, telefono, fechaNac);
		    
		    if (!campoVacio) {
		        String resultado = "Los datos ingresados fueron: " + 
		                nombre + " " + 
		                apellido + ", Tel: " + 
		                telefono + ", Fecha Nac.: " + 
		                fechaNac;
		        
		        lblResultado.setText(resultado);
		        limpiarCampos();
		    }
		}
		
		
		
		

		private void resetearColores() {
		    txtNombre.setBackground(Color.white);
		    txtApellido.setBackground(Color.white);
		    txtTelefono.setBackground(Color.white);
		    txtFechaNac.setBackground(Color.white);
		}

		private boolean verificarCamposCompletos(String nombre, String apellido, String telefono, String fechaNac) {
		    boolean campoVacio = false;
		    
		    if (nombre.trim().isEmpty()) {
		        txtNombre.setBackground(Color.red);
		        campoVacio = true;
		    }
		    
		    if (apellido.trim().isEmpty()) {
		        txtApellido.setBackground(Color.red);
		        campoVacio = true;
		    }
		    
		    if (telefono.trim().isEmpty()) {
		        txtTelefono.setBackground(Color.red);
		        campoVacio = true;
		    }
		    
		    if (fechaNac.trim().isEmpty()) {
		        txtFechaNac.setBackground(Color.red);
		        campoVacio = true;
		    }
		    
		    return campoVacio;
		}

		private void limpiarCampos() {
		    txtNombre.setText("");
		    txtApellido.setText("");
		    txtTelefono.setText("");
		    txtFechaNac.setText("");
		}

}