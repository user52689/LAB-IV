package Ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;


public class Archivo {
	private String ruta;
	
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getRuta() {
		return ruta;
	}
	
    public TreeSet<Persona> leer() {
       
        TreeSet<Persona> personas = new TreeSet<Persona>();

        try (BufferedReader buffer = new BufferedReader(new FileReader(getRuta()))) {
            String linea = buffer.readLine();

            while (linea != null) {
                String[] partes = linea.trim().split("-");

                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String apellido = partes[1].trim();
                    String dni = partes[2].trim();

                    try {
                        if (Persona.VerificarDniInvalido(dni)) {
                            Persona persona = new Persona(nombre, apellido, dni);
                            personas.add(persona);
                        }
                    } catch (DniInvalido e) {
                        System.out.println("DNI inválido (" + dni + "): " + e.getMessage());
                    }
                } else {
                    System.out.println("Línea malformada: " + linea);
                }

                linea = buffer.readLine();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error al leer archivo.");
        }

        return personas;  
      }

      public void escribirResultado(TreeSet<Persona> personas) {
          try {
              FileWriter entrada = new FileWriter("Resultado.txt", false);
              BufferedWriter miBuffer = new BufferedWriter(entrada);

              for (Persona persona : personas) {
                  String linea = persona.toString();
                  miBuffer.write(linea);
                  miBuffer.newLine();
              }

              miBuffer.close();
              entrada.close();
              System.out.println("Archivo Resultado.txt creado correctamente.");

          } catch (IOException e) {
              e.printStackTrace();
              System.out.println("Error al escribir en Resultado.txt");
          }
      }
  }
