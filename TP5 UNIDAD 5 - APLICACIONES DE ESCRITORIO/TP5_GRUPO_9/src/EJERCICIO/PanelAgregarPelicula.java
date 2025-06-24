package EJERCICIO;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PanelAgregarPelicula extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField txtNombre;
    private JComboBox<String> comboGeneros;
    private JLabel lblIdValor;

    public PanelAgregarPelicula() {
        setLayout(null);
        setBounds(0, 0, 707, 460);

        JLabel lblId = new JLabel("ID:");
        lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblId.setBounds(74, 84, 46, 14);
        add(lblId);

        lblIdValor = new JLabel(String.valueOf(Peliculas.getContador() + 1));
        lblIdValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblIdValor.setBounds(150, 84, 100, 14);
        add(lblIdValor);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(74, 124, 62, 17);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 122, 200, 20);
        txtNombre.setColumns(10);
        add(txtNombre);

        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGenero.setBounds(74, 169, 62, 17);
        add(lblGenero);

        comboGeneros = new JComboBox<>();
        comboGeneros.setBounds(150, 167, 200, 20);
        for (String genero : Géneros.getListaGeneros()) {
            comboGeneros.addItem(genero);
        }
        add(comboGeneros);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(150, 210, 100, 25);
        btnAceptar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String generoSeleccionado = (String) comboGeneros.getSelectedItem();

            if (nombre.isEmpty() || generoSeleccionado.equals("Seleccione un género")) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Géneros genero = new Géneros(generoSeleccionado);
            Peliculas nuevaPelicula = new Peliculas(nombre, genero);
            Programa.listaPeliculas.addElement(nuevaPelicula);

            JOptionPane.showMessageDialog(this, "Película agregada correctamente.");
            txtNombre.setText("");
            comboGeneros.setSelectedIndex(0);
            lblIdValor.setText(String.valueOf(Peliculas.getContador() + 1));
        });
        add(btnAceptar);
    }
}