package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class PanelListarPersonas extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable tablePersonas;
    private DefaultTableModel tableModel;
    private JButton btnRefrescar;

    public PanelListarPersonas() {
        setLayout(null);
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("DNI");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido");

        tablePersonas = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePersonas);
        scrollPane.setBounds(50, 50, 300, 150); 
        add(scrollPane);
     
        btnRefrescar = new JButton("Refrescar");
        btnRefrescar.setBounds(50, 210, 100, 30);
        add(btnRefrescar);
    }
   
    public void actualizarTabla(Object[][] data) {
        tableModel.setRowCount(0); 
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
    
    public void setBtnRefrescarActionListener(ActionListener listener) {
        btnRefrescar.addActionListener(listener);
    }

    public JButton getBtnRefrescar() {
        return btnRefrescar;
    }

}
