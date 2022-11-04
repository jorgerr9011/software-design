package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ColegioTest {

    private Colegio hogwarts;

    @BeforeEach
    void setUp(){
        hogwarts = new Colegio();

        Residente residente1 = new Residente("Don", "Cabrero", 17, "Estudiante", "slytherin");
        Residente residente2 = new Residente("Venancio", "Fernandez", 176, "fantasma", "ravenclaw");
        Residente residente3 = new Residente("Hermione", "Granger", 14, "estudiante", "Gryffindor");
        Residente residente4 = new Residente("Nameless", "King", 256, "Fantasma", "slytherin");
        Residente residente5 = new Residente("Nameless", "King", 256, "Fantasma", "hufflepuf");

        Personal personal1   = new Personal("Severus", "Snape", 57, "Docente", "Defensa");
        Personal personal2   = new Personal("Pontiff", "Sulyvahn", 69, "guardabosques", "nocturnidad");
        Personal personal3   = new Personal("Dragonslayer", "Armor", 420, "Conserje", null);
        Personal personal4   = new Personal("Minerva", "McGonagall", 72, "docente", "transformaciones");
        Personal personal5   = new Personal("M.", "Rajoy", 48, "docente", "historia");
        Personal personal6   = new Personal("Pedro", "Sanchez", 48, "docente", "pociones");
        Personal personal7   = new Personal("Lewis", "hamilton", 37, "docente", "Herbologia");

        residente2.destruccionHorrocruxes();
        residente3.destruccionHorrocruxes();
        residente3.destruccionHorrocruxes();
        residente3.destruccionHorrocruxes();
        personal1.destruccionHorrocruxes(4);
        personal2.destruccionHorrocruxes(10);
        personal3.destruccionHorrocruxes(8);
        personal4.destruccionHorrocruxes(1);
        personal5.destruccionHorrocruxes(3);

        hogwarts.addIntegrante(residente1);
        hogwarts.addIntegrante(residente2);
        hogwarts.addIntegrante(residente3);
        hogwarts.addIntegrante(residente4);
        hogwarts.addIntegrante(residente5);
        hogwarts.addIntegrante(personal1);
        hogwarts.addIntegrante(personal2);
        hogwarts.addIntegrante(personal3);
        hogwarts.addIntegrante(personal4);
        hogwarts.addIntegrante(personal5);
        hogwarts.addIntegrante(personal6);
        hogwarts.addIntegrante(personal7);

    }

    @Test
    void testColegio(){
        assertEquals(hogwarts.getIntegrante(0).getStringCategoria(), "Estudiante");
        assertNotEquals(hogwarts.getIntegrante(1).getStringCategoria(), "Estudiante");
        assertEquals(hogwarts.getIntegrante(1).getCategoria(), hogwarts.getIntegrante(3).getCategoria());

        System.out.println(hogwarts.imprimirRecompensa());
        System.out.println(hogwarts.imprimirSalarios());
    }

    @Test
    void testErroresAlMeterNuevosIntegrantes(){

        assertThrows(IllegalArgumentException.class, () -> new Residente("UwU", "AwA", 12, "adsadasdasdas", "ravenclaw"));
        assertThrows(IllegalArgumentException.class, () -> new Residente("UwU", "AwA", 12, null, "ravenclaw"));
        assertThrows(IllegalArgumentException.class, () -> new Residente("UwU", "AwA", 1, "estudiante", "ravenclaw"));
        assertThrows(IllegalArgumentException.class, () -> new Residente(null, "AwA", 13, null, "ravenclaw"));
        assertThrows(IllegalArgumentException.class, () -> new Residente("ewe", "AwA", 16, "estudiante", "asdasdasda"));
        assertThrows(IllegalArgumentException.class, () -> new Personal("ewe", "AwA", 53, "docente", "asdasdasda"));
        assertThrows(IllegalArgumentException.class, () -> new Personal("ewe", "AwA", 53, "guardabosques", "asdasdasda"));

        assertThrows(UnsupportedOperationException.class, () -> hogwarts.getIntegrante(0).calcularSalario());

        hogwarts.getIntegrante(0).destruccionHorrocruxes(3);
        assertEquals(hogwarts.getIntegrante(0).getHorrocrux(), 3);

        hogwarts.getIntegrante(0).destruccionHorrocruxes();
        assertEquals(hogwarts.getIntegrante(0).getHorrocrux(), 4);

        assertThrows(IllegalArgumentException.class, () -> hogwarts.getIntegrante(0).destruccionHorrocruxes(1));

    }
}
