package e1;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class test {

    @Test
    void TestListas() {
        ArrayList<String> array1 = new ArrayList<>();
        List<String> e = new ArrayList<>();
        List<String> j = new ArrayList<>();
        List<String> g = new ArrayList<>();

        array1.add(0, "Coruña AND Ourense AND 15 AND 02/12/21");

        origen o = new origen("origen=Coruña");
        destino d = new destino("destino=Ourense");
        precio p = new precio("=15");
        fecha f = new fecha("fecha=02/12/21");
        Billete proc = new Billete(o, d, p, f, array1);

        Assertions.assertEquals(proc.comprobar_linea(proc.getResultado_busq(), 0), array1.get(0));

        fecha fe = new fecha("fecha=10/12/21");
        array1.add(1, "Coruña AND Ourense AND 15 AND 10/12/21");
        Billete proc1 = new Billete(o, d, p, fe, array1);

        Assertions.assertEquals(proc1.comprobar_linea(proc1.getResultado_busq(), 0), array1.get(1));

        array1.add(2, "Coruña AND Santiago AND 15 AND 10/12/21");
        proc1.buscar_billetes(array1);

        System.out.println();
        Assertions.assertNotEquals(proc1.comprobar_linea(proc1.getResultado_busq(), 1), array1.get(2));

        array1.add(0, "Coruña AND Ourense AND 15 AND 10/12/21");
        array1.add(1, "Santiago AND Ourense AND 15 AND 10/12/21");
        array1.add(2, "Lugo AND Ourense AND 15 AND 10/12/21");
        origen or = new origen("origen=Coruña OR origen=Santiago");
        Billete proc2 = new Billete(or, d, p, fe, array1);

        Assertions.assertEquals(proc2.comprobar_linea(proc2.getResultado_busq(), 0), array1.get(0));
        Assertions.assertEquals(proc2.comprobar_linea(proc2.getResultado_busq(), 1), array1.get(1));

        origen or1 = new origen("origen=Coruña OR origen=Santiago OR origen=Lugo");
        Billete proc3 = new Billete(or1, d, p, fe, array1);

        Assertions.assertEquals(proc3.comprobar_linea(proc3.getResultado_busq(), 2), array1.get(2));

        array1.add(3, "Lugo AND Ourense AND 15 AND 11/12/21");
        fecha fe1 = new fecha("fecha=10/12/21 OR fecha=11/12/21");
        Billete proc4 = new Billete(or1, d, p, fe1, array1);

        Assertions.assertEquals(proc4.comprobar_linea(proc4.getResultado_busq(), 3), array1.get(3));

    }
}
