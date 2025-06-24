package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PanelAgregarPersonas extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDni;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblDni;
    private JButton btnAceptar;

    public PanelAgregarPersonas() {
        setLayout(null);

        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(94, 39, 89, 26);
        add(lblNombre);

        lblApellido = new JLabel("Apellido");
        lblApellido.setBounds(94, 76, 110, 26);
        add(lblApellido);

        lblDni = new JLabel("DNI");
        lblDni.setBounds(94, 113, 110, 26);
        add(lblDni);

        txtNombre = new JTextField();
        txtNombre.setBounds(193, 42, 142, 20);
        add(txtNombre);
        txtNombre.setColumns(10);

        txtApellido = new JTextField();
        txtApellido.setColumns(10);
        txtApellido.setBounds(193, 79, 142, 20);
        add(txtApellido);

        txtDni = new JTextField();
        txtDni.setColumns(10);
        txtDni.setBounds(193, 116, 142, 20);
        add(txtDni);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(94, 150, 110, 23);
        add(btnAceptar);

        txtNombre.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                    e.consume(); 
                }
            }
        });

        txtApellido.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                    e.consume();
                }
            }
        });

        txtDni.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JTextField getTxtDni() {
        return txtDni;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDni.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}