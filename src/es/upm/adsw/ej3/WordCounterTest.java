package es.upm.adsw.ej3;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jcala on 05/04/2017.
 */
public class WordCounterTest {

    private WordCounter wc;
    private String masMenosUsados;
    private String repetidos;
    private String vacioTexto;
    private String estandard;
    private String noAscii;
    private String simbolos;
    private String dash;
    private File vacioFile;

    private static final String DIR = "c:/adsw/libros";

    @Before
    public void setUp(){
        wc = new WordCounter();
        repetidos = "Hola Hola Hola Hola Hola Hola Hola Hola Hola Hola Hola Adios"; //11 Holas
        vacioTexto = "";
        estandard = "Dulce y lejana voz por mí vertida.\n" +
                "Dulce y lejana voz por mí gustada.\n" +
                "Lejana y dulce voz amortecida. \n";
        noAscii = "mí vida, mí realidad, mí sueño"; //mi con tilde , no ascii
        simbolos = "¿??=?¿¿?=)(Hola=|@Adios";
        dash = "hola-mundo";
        vacioFile = new File(DIR,"vacio.txt");
        masMenosUsados = "Hola Hola adios Hola esto esto Hola Hola esto esto pero pero";
    }

    @Test
    public void masMenosUsadosTest(){
        wc.load(masMenosUsados);
        assertEquals("[hola -> 5]",wc.getTop(1).get(0).toString());
        assertEquals("[adios -> 1]",wc.getTop(-1).get(0).toString());
        assertEquals("[pero -> 2]",wc.getTop(-2).get(1).toString());
        assertEquals("[esto -> 4]",wc.getTop(-3).get(2).toString());
        assertEquals("[hola -> 5]",wc.getTop(-4).get(3).toString());
        assertEquals("[adios -> 1]",wc.getTop(4).get(3).toString());
        wc.reset();
    }
    @Test
    public void repetidosTest(){
        wc.load(repetidos);
        assertEquals(2,wc.size());
        assertEquals(11,wc.getTop(1).get(0).getCnt());
        assertEquals(1,wc.getTop(2).get(1).getCnt());
        wc.reset();
    }

    @Test
    public void vacioTest(){
        wc.load(vacioTexto);
        assertEquals(0,wc.size());
        assertTrue(wc.getTop(20).isEmpty());
        wc.reset();
    }

    @Test
    public void vacioTestFile(){
        try {
            wc.load(vacioFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(0,wc.size());
        wc.reset();
    }

    @Test
    public void estandardTest(){
        wc.load(estandard);
        List<Registro> miLista = wc.getTop(4);
        //Dulce, lejana, y , voz son las palabras que mas se repiten
        assertEquals("[lejana -> 3]",miLista.get(0).toString());
        assertEquals("[dulce -> 3]",miLista.get(1).toString());
        assertEquals("[y -> 3]",miLista.get(2).toString());
        assertEquals("[voz -> 3]",miLista.get(3).toString());
        assertEquals(0,wc.countBelow(0));
        assertEquals(3,wc.countBelow(1));
        wc.reset();
    }

    @Test
    public void noAsciiTest(){
        wc.load(noAscii);
        assertEquals(3,wc.getTop(1).get(0).getCnt());
        wc.reset();
    }

    @Test
    public void simbolosTest(){
        wc.load(simbolos);
        assertEquals(2,wc.size());
        wc.reset();
    }

    @Test
    public void dashTest(){
        wc.load(dash);
        assertEquals(2,wc.size());
        wc.reset();
    }



}