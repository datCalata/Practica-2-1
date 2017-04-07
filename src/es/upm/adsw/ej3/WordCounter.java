package es.upm.adsw.ej3;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Analizador de textos
 * @author adsw
 * @since 2017
 */
public class WordCounter {
    private Map<String, Registro> s1;
    private Registro[] s2;

    /**
     * Constructor
     */
    public WordCounter() {
        s1 = new HashMap<>();
        s2 = null;
    }

    /**
     * Reinicia el contador de palabras
     * Vacía el diccionario y anula la tabla de s2 ordenados
     */
    public void reset() {
        s1.clear();
        s2 = null;
    }

    /**
     * Devuelve cuántas palabras hay por debajo de un umbral c.
     * El resultado es un número entre 0 y N, siendo N el número de palabras diferentes en el fichero.
     * @param c umbral
     * @return
     */
    public int countBelow(int c) {
        getDatos();
        int cnt = 0;
        for(int i = 0; i < s2.length;i++){
            if(s2[i].getCnt() <= c){
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * Devuelve las n palabras más usadas (si n es positivo).
     * Devuelve las n palabras menos usadas (si n es negativo).
     * La lista devuelta tiene un tamaño entre 0 y n; es menor que n si no hay suficientes palabras diferentes.
     * @param n número de palabras contando desde el principio o desde el final
     * @return lista de hasta n registros
     */
    public List<Registro> getTop(int n) {
        getDatos();
        int num = Math.min(Math.abs(n),s2.length);
        List<Registro> miLista;
        if(n < 0){
           miLista = getPalabrasMenosUsadas(num);
        }else{
            miLista = getPalabrasMasUsadas(num);
        }
        return miLista;
    }

    private List<Registro> getPalabrasMasUsadas(int n){
        List<Registro> miLista = new ArrayList<>();
        for(int i = s2.length-1; i > (s2.length-1)-n; i--){
            miLista.add(s2[i]);
        }
        return miLista;
    }

    private List<Registro> getPalabrasMenosUsadas(int n){
        List<Registro> miLista = new ArrayList<>();
        for(int i = 0; i < n; i++){
            miLista.add(s2[i]);
        }
        return miLista;
    }

    /**
     * Carga un fichero de texto. Anula la tabla de s2 ordenados
     * @param file Descriptor del fichero
     * @throws IOException si hay problemas con el fichero
     */
    public void load(File file) throws IOException{
        s2 = null;
        Scanner scanner = new Scanner(file, "UTF-8");
        scanner.useDelimiter(("[^\\p{javaLowerCase}\\p{javaUpperCase}]+"));
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            put(word);
        }
        scanner.close();
    }

    /**
     * Mete la palabra en el diccionario si es nueva. Si no es nueva, incrementa el contador
     * @param word
     */
    private void put(String word) {
        Registro dato = s1.get(word);
            if(dato == null){
                s1.put(word,new Registro(word));
            }else{
                dato.inc();
            }
    }

    /**
     * Carga un texto. Anula la tabla de s2 ordenados
     * @param s texto
     */
    public void load(String s) {
        s2 = null;
        Scanner scanner = new Scanner(s);
        scanner.useDelimiter(("[^\\p{javaLowerCase}\\p{javaUpperCase}]+"));
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            put(word);
        }
        scanner.close();
    }


    /**
     * Descarga del diccionario al array de datos
     */
    private void getDatos() {
        if (s2 == null) {
            Collection<Registro> values = s1.values();
            s2 = new Registro[values.size()];
            int at = 0;
            for (Registro r : values) {
                s2[at++] = r;
            }
            Arrays.sort(s2);
        }
    }

    /**
     * Tamaño del diccionario
     * @return número de palabras
     */
    public int size() {
        return s1.size();
    }

    /**
     * Smoke Test
     */
    public static void main(String [] args) {
        String text = "¿Qué es mi barco? Mi tesoro. \n"
                + "¿Qué es mi Dios? La libertad. \n"
                + "¿Mi ley? ¡La fuerza y el viento! \n"
                + "¿Mi única patria? ¡La mar!";
        WordCounter wc = new WordCounter();
        wc.load(text);
        dump("top(5): ", wc.getTop(5));
        dump("top(-5): ", wc.getTop(-5));
        System.out.println("countBelow(2: " + wc.countBelow(2)+" )");
    }

    /**
     * Método para realizar pruebas
     * Muestra por pantalla los parámetros que recibe
     * @param label String que queremos mostrar
     * @param list Lista de Registros para mostrarlos por pantalla
     */
    private static void dump(String label, List<Registro> list) {
        System.out.println(label);
        for (Registro r : list) {
            System.out.printf(" %d: %s%n", r.getCnt(), r.getClave());
        }
        System.out.println();
    }
}
