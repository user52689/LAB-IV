package ProyectoTP4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejercicio3 extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtHoras;

	public Ejercicio3() {
		setTitle("Ejercicio 3");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 250, 450, 298);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		setContentPane(panel);

		JPanel panelSisOpt = new JPanel();
		panelSisOpt.setBounds(15, 20, 400, 50);
		panelSisOpt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelSisOpt.setLayout(null);
		panel.add(panelSisOpt);

		JLabel lblSisOpt = new JLabel("Elije un sistema operativo");
		lblSisOpt.setBounds(10, 13, 160, 20);
		panelSisOpt.add(lblSisOpt);

		JRadioButton rbWindows = new JRadioButton("Windows");
		rbWindows.setBounds(164, 13, 82, 20);
		panelSisOpt.add(rbWindows);

		JRadioButton rbMac = new JRadioButton("Mac");
		rbMac.setBounds(258, 13, 60, 20);
		panelSisOpt.add(rbMac);

		JRadioButton rbLinux = new JRadioButton("Linux");
		rbLinux.setBounds(320, 13, 60, 20);
		panelSisOpt.add(rbLinux);

		ButtonGroup grupoSisOpt = new ButtonGroup();
		grupoSisOpt.add(rbWindows);
		grupoSisOpt.add(rbMac);
		grupoSisOpt.add(rbLinux);

		JPanel panelEspecialidades = new JPanel();
		panelEspecialidades.setLayout(null);
		panelEspecialidades.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelEspecialidades.setBounds(15, 93, 400, 103);
		panel.add(panelEspecialidades);

		JLabel lblElijeUnaEspecialidad = new JLabel("Elije una especialidad");
		lblElijeUnaEspecialidad.setBounds(10, 43, 160, 20);
		panelEspecialidades.add(lblElijeUnaEspecialidad);

		JCheckBox chckbxProgramacion = new JCheckBox("Programación");
		chckbxProgramacion.setBounds(161, 7, 140, 23);
		panelEspecialidades.add(chckbxProgramacion);

		JCheckBox chckbxAdministracion = new JCheckBox("Administración");
		chckbxAdministracion.setBounds(161, 33, 120, 23);
		panelEspecialidades.add(chckbxAdministracion);

		JCheckBox chckbxDisenoGrafico = new JCheckBox("Diseño Gráfico");
		chckbxDisenoGrafico.setBounds(161, 59, 120, 23);
		panelEspecialidades.add(chckbxDisenoGrafico);

		JLabel lblHoras = new JLabel("Cantidad de horas en el computador:");
		lblHoras.setBounds(25, 207, 210, 20);
		panel.add(lblHoras);

		txtHoras = new JTextField();
		txtHoras.setBounds(245, 207, 86, 20);
		panel.add(txtHoras);
		txtHoras.setColumns(10);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(339, 230, 89, 23);
		panel.add(btnAceptar);
		btnAceptar.addActionListener(new EventoAceptar(grupoSisOpt, chckbxProgramacion, chckbxAdministracion, chckbxDisenoGrafico, txtHoras));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Ejercicio3 frame = new Ejercicio3();
			frame.setVisible(true);
		});
	}
}

class EventoAceptar implements ActionListener {
	private ButtonGroup sistema;
	private JCheckBox programacion;
	private JCheckBox administracion;
	private JCheckBox diseñoGrafico;
	private JTextField txtHoras;

	public EventoAceptar(ButtonGroup sistema, JCheckBox programacion, JCheckBox administracion, JCheckBox diseñoGrafico, JTextField txtHoras) {
		this.sistema = sistema;
		this.programacion = programacion;
		this.administracion = administracion;
		this.diseñoGrafico = diseñoGrafico;
		this.txtHoras = txtHoras;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String sistemaSeleccionado = null;
		for (AbstractButton btn : java.util.Collections.list(sistema.getElements())) {
			if (btn.isSelected()) {
				sistemaSeleccionado = btn.getText();
				break;
			}
		}

		if (sistemaSeleccionado == null) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccioná un sistema operativo.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String especialidades = "";
		if (programacion.isSelected()) especialidades += ", " + programacion.getText();
		if (administracion.isSelected()) especialidades += ", " + administracion.getText();
		if (diseñoGrafico.isSelected()) especialidades += ", " + diseñoGrafico.getText();

		if(especialidades == "") {
			especialidades = ", ";
		}
		
		if (!especialidades.isEmpty()) {
			especialidades = especialidades.substring(0, especialidades.length() - 2); 
		} else {
			especialidades = "Ninguna";
		}


		String horas = txtHoras.getText().trim();
		if (horas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, ingresá la cantidad de horas.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (!horas.matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "Solo se permiten números en el campo de horas.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(sistemaSeleccionado != null) {
			JOptionPane.showMessageDialog(null, sistemaSeleccionado + especialidades + ", " + horas + " hs" , "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

	}
}
