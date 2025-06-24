package presentacion.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private PanelAgregarPersonas panelAgregar;
    private PanelModificarPersonas panelModificar;
    private PanelEliminarPersonas panelEliminar;
    private PanelListarPersonas panelListar;


    /**
     * Create the frame.
     */
    public VentanaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 441, 331);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        contentPane.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuPersona = new JMenu("Persona");

        JMenuItem itemAgregar = new JMenuItem("Agregar");
        JMenuItem itemModificar = new JMenuItem("Modificar");
        JMenuItem itemEliminar = new JMenuItem("Eliminar");
        JMenuItem itemListar = new JMenuItem("Listar");

        menuPersona.add(itemAgregar);
        menuPersona.add(itemModificar);
        menuPersona.add(itemEliminar);
        menuPersona.add(itemListar);

        menuBar.add(menuPersona);
        setJMenuBar(menuBar);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        contentPane.add(cardPanel, BorderLayout.CENTER);

        panelAgregar = new PanelAgregarPersonas();
        panelEliminar = new PanelEliminarPersonas();
        panelListar = new PanelListarPersonas();
        panelModificar = new PanelModificarPersonas();

        cardPanel.add(panelAgregar, "Agregar");
        cardPanel.add(panelEliminar, "Eliminar");
        cardPanel.add(panelListar, "Listar");
        cardPanel.add(panelModificar, "Modificar");

        new Controlador(panelAgregar, panelEliminar, panelListar, panelModificar);

        itemAgregar.addActionListener(e -> mostrarPanel("Agregar"));
        itemModificar.addActionListener(e -> mostrarPanel("Modificar"));
        itemEliminar.addActionListener(e -> mostrarPanel("Eliminar"));
        itemListar.addActionListener(e -> mostrarPanel("Listar"));
    }

    private void mostrarPanel(String panelName) {
        switch (panelName) {
            case "Agregar":
                cardLayout.show(cardPanel, "Agregar");
                break;
            case "Modificar":
                cardLayout.show(cardPanel, "Modificar");
                break;

            case "Eliminar":
                cardLayout.show(cardPanel, "Eliminar");
                break;
            case "Listar":
                cardLayout.show(cardPanel, "Listar");
                break;

        }
    }
}
