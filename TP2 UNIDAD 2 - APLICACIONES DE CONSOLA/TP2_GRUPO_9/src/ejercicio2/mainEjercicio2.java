package ejercicio2;

public class mainEjercicio2 {

    public static void main(String[] args) {

        // OBJETOS 
        ProductoFresco fresco = new ProductoFresco(
                "2025-04-20","LF1","2025-04-01","Argentina"
        );

        ProductoRefrigerado refrigerado = new ProductoRefrigerado(
                "2025-05-15","LR2","COD-111"
        );

        ProductoCongelado congelado = new ProductoCongelado(
                "2025-05-12","LC3","-20°C"
        );

        // MOSTRAR
        System.out.println(fresco.toString());
        System.out.println(refrigerado.toString());
        System.out.println(congelado.toString());
    }
}