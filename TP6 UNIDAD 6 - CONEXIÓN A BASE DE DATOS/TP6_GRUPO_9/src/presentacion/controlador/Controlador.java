package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import entidad.Persona;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;
import presentacion.vista.PanelAgregarPersonas;
import presentacion.vista.PanelEliminarPersonas;
import presentacion.vista.PanelListarPersonas;
import presentacion.vista.PanelModificarPersonas;

public class Controlador {
    private PanelAgregarPersonas vistaAgregar;
    private PanelEliminarPersonas vistaEliminar;
    private PanelListarPersonas vistaListar;
    private PanelModificarPersonas vistaModificar;

    private PersonaNegocio negocio;

    public Controlador(PanelAgregarPersonas vistaAgregar, PanelEliminarPersonas vistaEliminar, PanelListarPersonas vistaListar, PanelModificarPersonas vistaModificar) {
        this.vistaAgregar = vistaAgregar;
        this.vistaEliminar = vistaEliminar;
        this.vistaListar = vistaListar;
        this.vistaModificar = vistaModificar;
        this.negocio = new PersonaNegocioImpl();

        this.vistaAgregar.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPersona();
            }
        });

        this.vistaEliminar.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPersona();
            }
        });
        
        this.vistaListar.setBtnRefrescarActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescarTabla();
            }
        });
        this.vistaModificar.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarPersona();
            }
        });

        cargarListaPersonas();
    }

    private void agregarPersona() {
        String nombre = vistaAgregar.getTxtNombre().getText();
        String apellido = vistaAgregar.getTxtApellido().getText();
        String dni = vistaAgregar.getTxtDni().getText();

        Persona p = new Persona(dni, nombre, apellido);

        boolean resultado = negocio.AgregarPersona(p);
        String mensaje = negocio.getMensaje();

        JOptionPane.showMessageDialog(vistaAgregar, mensaje, 
            resultado ? "Exito" : "Atencion", 
            resultado ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);

        if (resultado) {
            vistaAgregar.limpiarCampos();
            cargarListaPersonas();
        }
    }


    private void eliminarPersona() {
        Persona seleccionada = vistaEliminar.getListPersonas().getSelectedValue();
        if (seleccionada != null) {
            int confirm = JOptionPane.showConfirmDialog(vistaEliminar, "¿Seguro que queres eliminar a " + seleccionada + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (negocio.EliminarPersona(seleccionada)) {
                    JOptionPane.showMessageDialog(vistaEliminar, "Persona eliminada con exito");
                    cargarListaPersonas();
                } else {
                    JOptionPane.showMessageDialog(vistaEliminar, "Error al eliminar persona", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(vistaEliminar, "Selecciona una persona para eliminar", "Atencion", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void cargarListaPersonas() {
        var personas = negocio.getAll();

        vistaEliminar.getListModel().clear();
        for (Persona p : personas) {
            vistaEliminar.getListModel().addElement(p);
        }


        DefaultListModel<Persona> modeloModificar = new DefaultListModel<>();
        for (Persona p : personas) {
            modeloModificar.addElement(p);
        }
        vistaModificar.getListPersonas().setModel(modeloModificar);
    }

    private void refrescarTabla() {
        var personas = negocio.getAll();
        Object[][] data = new Object[personas.size()][3];
        for (int i = 0; i < personas.size(); i++) {
            Persona p = personas.get(i);
            data[i][0] = p.getDni();
            data[i][1] = p.getNombre();
            data[i][2] = p.getApellido();
        }
        vistaListar.actualizarTabla(data);
    }
    private void modificarPersona() {
        Persona seleccionada = vistaModificar.getListPersonas().getSelectedValue();
        if (seleccionada != null) {
            String nuevoNombre = vistaModificar.getTxtNombre().getText().trim();
            String nuevoApellido = vistaModificar.getTxtApellido().getText().trim();

            if (!nuevoNombre.isEmpty() && !nuevoApellido.isEmpty()) {
                Persona modificada = new Persona(seleccionada.getDni(), nuevoNombre, nuevoApellido);
                if (negocio.ModificarPersona(modificada)) {
                    JOptionPane.showMessageDialog(vistaModificar, "Persona modificada con exito");
                    cargarListaPersonas(); 
                } else {
                    JOptionPane.showMessageDialog(vistaModificar, "Error al modificar persona", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(vistaModificar, "Nombre y apellido no pueden estar vacios", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(vistaModificar, "Selecciona una persona de la lista", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

}
