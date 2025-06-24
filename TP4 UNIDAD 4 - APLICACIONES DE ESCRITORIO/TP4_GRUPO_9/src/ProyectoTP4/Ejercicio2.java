package ProyectoTP4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class Ejercicio2 extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtNota1;
    private JTextField txtNota2;
    private JTextField txtNota3;
    private JTextField txtPromedio;
    private JTextField txtCondicion;

    public Ejercicio2() {
        setTitle("Ejercicio 2");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(725, 450, 391, 313);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        setContentPane(panel);
        
        JPanel panelNotasEstudiante = new JPanel();
        panelNotasEstudiante.setBounds(27, 11, 191, 146);
        panelNotasEstudiante.setBorder(BorderFactory.createTitledBorder("Notas de Estudiante"));
        panelNotasEstudiante.setLayout(null);
        panel.add(panelNotasEstudiante);
        
        
        JLabel lblNota1 = new JLabel("Nota 1:");
        lblNota1.setBounds(22, 26, 46, 14);
        panelNotasEstudiante.add(lblNota1);
        
        txtNota1 = new JTextField();
        txtNota1.setBounds(78, 23, 86, 20);
        txtNota1.setColumns(10);
        panelNotasEstudiante.add(txtNota1);
        
        
        JLabel lblNota2 = new JLabel("Nota 2:");
        lblNota2.setBounds(22, 51, 46, 14);
        panelNotasEstudiante.add(lblNota2);
        
        txtNota2 = new JTextField();
        txtNota2.setBounds(78, 48, 86, 20);
        txtNota2.setColumns(10);
        panelNotasEstudiante.add(txtNota2);
        
        
        JLabel lblNota3 = new JLabel("Nota 3:");
        lblNota3.setBounds(22, 76, 46, 14);
        panelNotasEstudiante.add(lblNota3);
        
        txtNota3 = new JTextField();
        txtNota3.setBounds(78, 73, 86, 20);
        txtNota3.setColumns(10);
        panelNotasEstudiante.add(txtNota3);
        
        JLabel lblTPS = new JLabel("TPS");
        lblTPS.setBounds(22, 101, 46, 14);
        panelNotasEstudiante.add(lblTPS);
        
        JComboBox<String> cboxTPS = new JComboBox();
        cboxTPS.setModel(new DefaultComboBoxModel(new String[] {"Aprobado", "Desaprobado"}));
        cboxTPS.setBounds(78, 97, 86, 22);
        panelNotasEstudiante.add(cboxTPS);
        
        JPanel panelResultado = new JPanel();
        panelResultado.setBounds(27, 168, 191, 100);
        panelResultado.setBorder(BorderFactory.createTitledBorder("Notas de Estudiante"));
        panelResultado.setLayout(null);
        panel.add(panelResultado);

        JLabel lblPromedio = new JLabel("Promedio:");
        lblPromedio.setBounds(22, 28, 70, 14);
        panelResultado.add(lblPromedio);

        txtPromedio = new JTextField();
        txtPromedio.setBounds(90, 27, 86, 20);
        txtPromedio.setColumns(10);
        panelResultado.add(txtPromedio);
        
        JLabel lblCondicion = new JLabel("Condición:");
        lblCondicion.setBounds(22, 53, 70, 14);
        panelResultado.add(lblCondicion);

        txtCondicion = new JTextField();
        txtCondicion.setBounds(90, 53, 86, 20);
        txtCondicion.setColumns(10);
        panelResultado.add(txtCondicion);
        
        JButton btnCalcular = new JButton("CALCULAR");
        btnCalcular.setBounds(223, 40, 120, 40);
        panel.add(btnCalcular);
        
        EventoCalcular calcular = new EventoCalcular(txtNota1,txtNota2,txtNota3,txtPromedio,txtCondicion,cboxTPS);
        btnCalcular.addActionListener(calcular);
        
        JButton btnNuevo = new JButton("NUEVO");
        btnNuevo.setBounds(223, 90, 120, 40);
        panel.add(btnNuevo);
        
        EventoNuevo nuevo = new EventoNuevo(txtNota1,txtNota2,txtNota3,txtPromedio,txtCondicion,cboxTPS);
        btnNuevo.addActionListener(nuevo);
        
        JButton btnSalir = new JButton("SALIR");
        btnSalir.setBounds(223, 141, 120, 40);
        panel.add(btnSalir);
    }
    
    class EventoNuevo implements ActionListener{
    	private JTextField txtNota1;
        private JTextField txtNota2;
        private JTextField txtNota3;
        private JComboBox<String> cboxTPS;
        private JTextField txtPromedio;
        private JTextField txtCondicion;
        
        public EventoNuevo(JTextField txtNota1, JTextField txtNota2, JTextField txtNota3,
                JTextField txtPromedio, JTextField txtCondicion, JComboBox<String> cboxTPS) {
        	this.txtNota1 = txtNota1;
    		this.txtNota2 = txtNota2;
    		this.txtNota3 = txtNota3;
    		this.txtPromedio = txtPromedio;
    		this.txtCondicion = txtCondicion;
    		this.cboxTPS = cboxTPS;
        }
        
        public void actionPerformed(ActionEvent e) {
        	this.txtNota1.setText("");
    		this.txtNota2.setText("");
    		this.txtNota3.setText("");
    		this.txtPromedio.setText("");
    		this.txtCondicion.setText("");
    		this.cboxTPS.setSelectedIndex(0);;
        }
        
    }
    
    class EventoCalcular implements ActionListener {
    	private JTextField txtNota1;
        private JTextField txtNota2;
        private JTextField txtNota3;
        private JComboBox<String> cboxTPS;
        private JTextField txtPromedio;
        private JTextField txtCondicion;
        
    	
        public EventoCalcular(JTextField txtNota1, JTextField txtNota2, JTextField txtNota3,
                JTextField txtPromedio, JTextField txtCondicion, JComboBox<String> cboxTPS) {
        		this.txtNota1 = txtNota1;
        		this.txtNota2 = txtNota2;
        		this.txtNota3 = txtNota3;
        		this.txtPromedio = txtPromedio;
        		this.txtCondicion = txtCondicion;
        		this.cboxTPS = cboxTPS;
        }
        
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		if(!(txtNota1.getText().isBlank() && txtNota2.getText().isBlank() && txtNota3.getText().isBlank())) {
    			try {
    				float nota1 = Float.parseFloat(txtNota1.getText());
    				float nota2 = Float.parseFloat(txtNota2.getText());
    				float nota3 = Float.parseFloat(txtNota3.getText());
    				String tps = (String) cboxTPS.getSelectedItem();
    				if(nota1<=10 && nota1>=1 && nota2<=10 && nota2>=1 && nota3<=10 && nota3>=1) {
    					txtPromedio.setText(String.valueOf((nota1+nota2+nota3)/3));    	
    					txtCondicion.setText(obtenerCondicion(nota1, nota2, nota3, tps));
    				}
    				else {
    					System.out.println("Los números ingresados deben estar entre el 1 y el 10");
    				}
    			}catch(NumberFormatException e1){
    				System.out.println("Solo debe ingresar números");
    			}   			
    		}
    		else {
    			System.out.println("Todos los campos deben estar llenos");
    		}
    	    
    	    
		}
    	
    	private String obtenerCondicion(float n1, float n2, float n3, String tps) {
            if (tps.equals("Desaprobado") || n1 < 6 || n2 < 6 || n3 < 6) {
                return "libre";
            } else if (n1 >= 8 && n2 >= 8 && n3 >= 8) {
                return "promociona";
            } else {
                return "regular";
            }
        }
    }
}