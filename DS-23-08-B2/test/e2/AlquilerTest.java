package e2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AlquilerTest {
    private GestionApartamentos alquilerApartamentos;

    @BeforeEach
    void setUp(){
        alquilerApartamentos = new GestionApartamentos();

        AnuncioApartamento apartamento1 = new AnuncioApartamento(69, 2, 80,
                                                                2, 2001, 4511,
                                                                "1 B", 700, null);

        AnuncioApartamento apartamento2 = new AnuncioApartamento(420, 2, 56,
                                                                 2, 2014, 1,
                                                                "septimo izquierda", 650, "garajes 3 precio 1000 " +
                                                               "garaje 1 precio 250 garaje 2 precio 500 garaje 3 precio 250");

        AnuncioApartamento apartamento3 = new AnuncioApartamento(15, 2, 40,
                                                                    2, 1876, 2001,
                                                                   "3 derecha", 800, null);

        AnuncioApartamento apartamentoIdentico = new AnuncioApartamento(4, 2, 40,
                                                                 2, 1876, 2001,
                                                                 "3 derecha", 800, null);

        AnuncioApartamento apartamento4 = new AnuncioApartamento(17, 2, 71,
                                                                 2, 1789, 27002,
                                                                 "3 D", 500, "garajes 3 precio 1000 " +
                                                                 "garaje 1 precio 250 garaje 2 precio 500 garaje 3 precio 250");
        alquilerApartamentos.addApartamento(apartamento1);
        alquilerApartamentos.addApartamento(apartamento2);
        alquilerApartamentos.addApartamento(apartamento3);
        alquilerApartamentos.addApartamento(apartamento4);
        alquilerApartamentos.addApartamento(apartamentoIdentico);
    }
    @Test
    void testAddApartamentos(){
        assertEquals(alquilerApartamentos.getApartamento(2), alquilerApartamentos.getApartamento(4));
        assertEquals(alquilerApartamentos.getApartamento(2).equals(alquilerApartamentos.getApartamento(4)),
                     alquilerApartamentos.getApartamento(4).equals(alquilerApartamentos.getApartamento(2)));
        assertEquals(alquilerApartamentos.getApartamento(2).hashCode(), alquilerApartamentos.getApartamento(4).hashCode());
        assertNotEquals(alquilerApartamentos.getApartamento(0), alquilerApartamentos.getApartamento(4));
        assertEquals(alquilerApartamentos.getApartamento(0).equals(alquilerApartamentos.getApartamento(4)),
                     alquilerApartamentos.getApartamento(4).equals(alquilerApartamentos.getApartamento(0)));
        //el ultimo assertEquals mira un false, false, por comprobacion esta puesto abajo, aunque hacen lo mismo
        assertFalse(alquilerApartamentos.getApartamento(0).equals(alquilerApartamentos.getApartamento(4)));
        assertNotEquals(alquilerApartamentos.getApartamento(0).hashCode(), alquilerApartamentos.getApartamento(4).hashCode());
        assertNotEquals(alquilerApartamentos.getApartamento(1), alquilerApartamentos.getApartamento(3));

        AnuncioApartamento apartamento = new AnuncioApartamento(420, 2, 40,
                                                                2, 1789, 27002,
                                                               "3 D", 800, "garajes 3 precio 1000 " +
                                                                 "garaje 1 precio 250 garaje 2 precio 500 garaje 3 precio 250");
        assertThrows(IllegalArgumentException.class, () -> alquilerApartamentos.addApartamento(apartamento));

        assertThrows(IllegalArgumentException.class, () -> new AnuncioApartamento(64, 0, 40,
                                                        2, 1984, 2013,
                                                        "1 derecha", 100, null));
        assertThrows(IllegalArgumentException.class, () -> new AnuncioApartamento(61, 2, 0,
                                                            2, 1984, 2013,
                                                            "1 derecha", 100, null));
        assertThrows(IllegalArgumentException.class, () -> new AnuncioApartamento(62, 3, 40,
                                                                0, 1984, 2013,
                                                                "1 derecha", 100, null));
        assertThrows(IllegalArgumentException.class, () -> new AnuncioApartamento(51, 0, 40,
                                                                                2, 0, 2013,
                                                                                "1 derecha", 100, null));
    }
    @Test
    void ordenarLista(){
        PrecioBase precioBase                 = new PrecioBase();
        PrecioTotal precioTotal               = new PrecioTotal();
        MetrosCuadrados metrosCuadrados       = new MetrosCuadrados();
        YearOfConstruction yearOfConstruction = new YearOfConstruction();

        GestionApartamentos alquilerApartamentos2 = new GestionApartamentos();
        for(int i = 0; i < alquilerApartamentos.getNumeroApartamentos(); i++)
            alquilerApartamentos2.addApartamento(alquilerApartamentos.getApartamento(i));

        assertEquals(alquilerApartamentos.getApartamento(0), alquilerApartamentos2.getApartamento(0));
        assertEquals(alquilerApartamentos.getApartamento(1), alquilerApartamentos2.getApartamento(1));
        assertEquals(alquilerApartamentos.getApartamento(2), alquilerApartamentos2.getApartamento(2));
        assertEquals(alquilerApartamentos.getApartamento(3), alquilerApartamentos2.getApartamento(3));


        alquilerApartamentos.ordenarLista();

        assertNotEquals(alquilerApartamentos.getApartamento(0), alquilerApartamentos2.getApartamento(0));
        assertNotEquals(alquilerApartamentos.getApartamento(1), alquilerApartamentos2.getApartamento(1));
        assertNotEquals(alquilerApartamentos.getApartamento(2), alquilerApartamentos2.getApartamento(2));
        assertNotEquals(alquilerApartamentos.getApartamento(3), alquilerApartamentos2.getApartamento(3));

        alquilerApartamentos2.ordenarLista();

        assertEquals(alquilerApartamentos.getApartamento(0), alquilerApartamentos2.getApartamento(0));
        assertEquals(alquilerApartamentos.getApartamento(1), alquilerApartamentos2.getApartamento(1));
        assertEquals(alquilerApartamentos.getApartamento(2), alquilerApartamentos2.getApartamento(2));
        assertEquals(alquilerApartamentos.getApartamento(3), alquilerApartamentos2.getApartamento(3));

        alquilerApartamentos.setComparator(precioBase);
        alquilerApartamentos.ordenarLista();
        alquilerApartamentos2.setComparator(metrosCuadrados);
        alquilerApartamentos2.ordenarLista();

        assertNotEquals(alquilerApartamentos.getApartamento(0), alquilerApartamentos2.getApartamento(0));
        assertNotEquals(alquilerApartamentos.getApartamento(1), alquilerApartamentos2.getApartamento(1));
        assertNotEquals(alquilerApartamentos.getApartamento(2), alquilerApartamentos2.getApartamento(2));
        assertNotEquals(alquilerApartamentos.getApartamento(3), alquilerApartamentos2.getApartamento(3));

        alquilerApartamentos2.setComparator(precioBase);
        alquilerApartamentos2.ordenarLista();

        assertEquals(alquilerApartamentos.getApartamento(0), alquilerApartamentos2.getApartamento(0));
        assertEquals(alquilerApartamentos.getApartamento(1), alquilerApartamentos2.getApartamento(1));
        assertEquals(alquilerApartamentos.getApartamento(2), alquilerApartamentos2.getApartamento(2));
        assertEquals(alquilerApartamentos.getApartamento(3), alquilerApartamentos2.getApartamento(3));

        alquilerApartamentos.setComparator(yearOfConstruction);
        alquilerApartamentos.ordenarLista();

        alquilerApartamentos2.setComparator(precioTotal);
        alquilerApartamentos2.ordenarLista();

        assertNotEquals(alquilerApartamentos.getApartamento(0), alquilerApartamentos2.getApartamento(0));
        assertEquals(alquilerApartamentos.getApartamento(1), alquilerApartamentos2.getApartamento(1));
        //esta puesto como equals porque da la casualidad que con estos dos comparadores sale en la misma fila pese a
        // estar la lista ordenada de forma distinta
        assertEquals(alquilerApartamentos.getApartamento(2), alquilerApartamentos2.getApartamento(2));
        //para lo mismo aqui, aunque la lista en el global (de la posicion 0-3) varia
        assertNotEquals(alquilerApartamentos.getApartamento(3), alquilerApartamentos2.getApartamento(3));


    }
}
