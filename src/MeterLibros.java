import java.io.File;
import java.io.IOException;

/**
 * Clase para medir cuánto tarda el diccionario con libros
 * @author adsw
 * @since 2017
 */
public class MeterLibros {

    /**
     * Ruta del directorio de los libros
     */
    private static final String DIR = "c:/adsw/libros"; // Cambiar /home/cif/adsw/libros
    /**
     * Nombre del fichero de prueba del libro El Lazarillo de Tormes
     */
    private static final String LAZARILLO = "Lazarillo.txt";
    /**
     * Nombre del fichero de prueba del libro Huckleberry Finn
     */
    private static final String HUCKLEBERRY = "Huckleberry.txt";
    /**
     * Nombre del fichero de prueba del libro Don Quijote de la Mancha
     */
    private static final String DON_QUIJOTE = "DonQuijote.txt";

    private static final String DRACULA = "Dracula.txt";

    private static final String LOSMISERABLES = "LosMiserables.txt";


    /**
     * Realizamos pruebas de medida de tiempos
     * @param args No usa argumentos
     * @throws IOException Si no encuentra un fichero
     */
    public static void main(String [] args) throws IOException{
        medir(LAZARILLO);
        medir(DRACULA);
        medir(HUCKLEBERRY);
        medir(DRACULA,LAZARILLO);
        medir(DON_QUIJOTE);
        medir(LOSMISERABLES);
        medir(LAZARILLO, HUCKLEBERRY, DON_QUIJOTE);
    }

    /**
     * Mide tiempos
     * @param libros se pasan varios nombres de ficheros de libros
     * @throws IOException si no encuentra el fichero
     */
    private static void medir(String... libros) throws IOException{
        WordCounter wc = new WordCounter();
        long t0 = System.currentTimeMillis();
        for (String libro : libros) {
            File f = new File(DIR,libro);
            wc.load(f);
        }
        wc.getTop(3);
        long t2 = System.currentTimeMillis();
        System.out.printf("%d %d%n", wc.size(), t2 - t0);
    }
}




