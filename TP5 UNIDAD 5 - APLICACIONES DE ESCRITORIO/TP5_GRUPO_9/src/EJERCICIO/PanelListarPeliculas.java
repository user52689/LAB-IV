package EJERCICIO;

import javax.swing.JPanel;
import javax.swing.JList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;

public class PanelListarPeliculas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<Peliculas> listPeliculas;

	public PanelListarPeliculas() {
		setLayout(null);
        setBounds(0, 0, 707, 460);
        
        JLabel lblPeliculas = new JLabel("Peliculas");
        lblPeliculas.setBounds(84, 159, 62, 14);
        add(lblPeliculas);
        
        // Copiamos los elementos del modelo a una lista común
        ArrayList<Peliculas> peliculasOrdenadas = new ArrayList<>();
        for (int i = 0; i < Programa.listaPeliculas.size(); i++) {
            peliculasOrdenadas.add(Programa.listaPeliculas.get(i));
        }

        // Ordenamos alfabéticamente por título
        Collections.sort(peliculasOrdenadas, new Comparator<Peliculas>() {
            public int compare(Peliculas p1, Peliculas p2) {
                return p1.getNombre().compareToIgnoreCase(p2.getNombre());
            }
        });

        // Mostramos la lista ordenada en el JList
        listPeliculas = new JList<>(peliculasOrdenadas.toArray(new Peliculas[0]));
        listPeliculas.setBounds(156, 11, 468, 346);
        add(listPeliculas);
	}
}
