/**
 * Contenedor para asociar un cnt a una palabra
 * @author adsw
 * @since 2017
 */
public class Registro implements Comparable<Registro> {

    private final String clave;
    private int cnt;


    /**
     * Constructor. El cnt se inicia a 1.
     * @param clave palabra
     */
    Registro(String clave){
        this.clave = clave;
        this.cnt = 1;
    }

    /**
     * Getter
     * @return clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * Getter
     * @return cnt
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * Incrementa el cnt
     */
    public void inc() {
        cnt++;
    }

    /**
     * Para comparar registros
     * @param o Objeto con el que nos comparamos
     * @return un entero negativo, cero o positivo si este objeto es menor, igual o
     * mayor que el objeto especificado
     */
    public int compareTo(Registro o) {
        return this.cnt - o.cnt;
    }

    /**
     * Método para imprimir objetos registro
     * @return Cadena con el objeto con formato [clave -> contador]
     */
    public String toString() {
        return String.format("[%s -> %d]", clave, cnt);
    }
}
