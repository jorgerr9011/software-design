package e3;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NetworkTest {

    Lists list = new Lists();
    Map map = new Map();
    Network networkL = new Network(list);
    Network networkM = new Network(map);

    @BeforeEach
    void setUp(){

        List<TopicOfInterest> f = new ArrayList<>();
        List<TopicOfInterest> e = new ArrayList<>();
        List<TopicOfInterest> j = new ArrayList<>();
        List<TopicOfInterest> g = new ArrayList<>();


        e.add(TopicOfInterest.DEPORTES);
        j.add(TopicOfInterest.EVENTOS);
        f.add(TopicOfInterest.DEPORTES);
        f.add(TopicOfInterest.INFORMACION);
        g.add(TopicOfInterest.NOTICIAS);

        networkL.darAlta("manolo", f);
        networkL.darAlta("pepe", g);
        networkL.darAlta("josefina", j);
        networkL.darAlta("jose", e);

        networkM.darAlta("manolo", f);
        networkM.darAlta("pepe", g);
        networkM.darAlta("josefina", j);
        networkM.darAlta("jose", e);
    }

    @Test
    void testModificarConListas(){
        List<TopicOfInterest> n = new ArrayList<>();
        n.add(TopicOfInterest.NOTICIAS);
        n.add(TopicOfInterest.DEPORTES);

        List<TopicOfInterest> l = new ArrayList<>();
        l.add(TopicOfInterest.EVENTOS);
        l.add(TopicOfInterest.INFORMACION);

        List<TopicOfInterest> p = new ArrayList<>();
        p.add(TopicOfInterest.DEPORTES);
        p.add(TopicOfInterest.NOTICIAS);


        assertEquals(networkL.modificar_intereses("añadir", "pepe", TopicOfInterest.DEPORTES), n);
        assertNotEquals(networkL.modificar_intereses("añadir", "manolo", TopicOfInterest.NOTICIAS), n);
        assertEquals(networkL.modificar_intereses("añadir", "josefina", TopicOfInterest.INFORMACION), l);

        assertEquals(networkL.modificar_intereses("eliminar", "manolo", TopicOfInterest.INFORMACION), p);

        p.remove(TopicOfInterest.DEPORTES);
        assertEquals(networkL.modificar_intereses("eliminar", "manolo", TopicOfInterest.DEPORTES), p);

        p.remove(TopicOfInterest.NOTICIAS);
        assertEquals(networkL.modificar_intereses("eliminar", "manolo", TopicOfInterest.NOTICIAS), p);

        p.add(TopicOfInterest.INFORMACION);
        assertEquals(networkL.modificar_intereses("añadir", "manolo", TopicOfInterest.INFORMACION), p);
    }

    @Test
    void testModificarConMap(){
        LinkedHashMap<String, List<TopicOfInterest>> c = new LinkedHashMap<>();

        List<TopicOfInterest> n = new ArrayList<>();
        n.add(TopicOfInterest.NOTICIAS);
        n.add(TopicOfInterest.DEPORTES);

        List<TopicOfInterest> l = new ArrayList<>();
        l.add(TopicOfInterest.EVENTOS);
        l.add(TopicOfInterest.INFORMACION);

        List<TopicOfInterest> p = new ArrayList<>();
        p.add(TopicOfInterest.DEPORTES);
        p.add(TopicOfInterest.NOTICIAS);


        assertEquals(networkM.modificar_intereses("añadir", "pepe", TopicOfInterest.DEPORTES), n);
        assertNotEquals(networkM.modificar_intereses("añadir", "manolo", TopicOfInterest.NOTICIAS), n);
        assertEquals(networkM.modificar_intereses("añadir", "josefina", TopicOfInterest.INFORMACION), l);

        assertEquals(networkM.modificar_intereses("eliminar", "manolo", TopicOfInterest.INFORMACION), p);

        p.remove(TopicOfInterest.DEPORTES);
        assertEquals(networkM.modificar_intereses("eliminar", "manolo", TopicOfInterest.DEPORTES), p);

        p.remove(TopicOfInterest.NOTICIAS);
        assertEquals(networkM.modificar_intereses("eliminar", "manolo", TopicOfInterest.NOTICIAS), p);
    }

    @Test
    void testUsuariosInteresesIgualesL(){
        List<String> n = new ArrayList<>();
        n.add("manolo");
        n.add("jose");

        List<String> l = new ArrayList<>();
        l.add("manolo");

        List<String> p = new ArrayList<>();

        assertEquals(networkL.UsuariosInteresesIguales(TopicOfInterest.DEPORTES), n);
        assertNotEquals(networkL.UsuariosInteresesIguales(TopicOfInterest.NOTICIAS), n);
        assertEquals(networkL.UsuariosInteresesIguales(TopicOfInterest.INFORMACION), l);
    }

    @Test
    void testUsuariosInteresesIgualesM(){
        List<String> n = new ArrayList<>();
        n.add("manolo");
        n.add("jose");

        List<String> l = new ArrayList<>();
        l.add("manolo");


        List<String> p = new ArrayList<>();


        assertEquals(networkM.UsuariosInteresesIguales(TopicOfInterest.DEPORTES), n);
        assertNotEquals(networkM.UsuariosInteresesIguales(TopicOfInterest.NOTICIAS), n);
        assertEquals(networkM.UsuariosInteresesIguales(TopicOfInterest.INFORMACION), l);
    }

    @Test
    void testComun2Users(){
        List<TopicOfInterest> n = new ArrayList<>();
        n.add(TopicOfInterest.EVENTOS);


        List<TopicOfInterest> l = new ArrayList<>();
        l.add(TopicOfInterest.DEPORTES);


        networkM.modificar_intereses("añadir", "manolo", TopicOfInterest.EVENTOS);
        assertEquals(networkM.Comun2Users("manolo", "josefina"), n);
        assertNotEquals(networkM.Comun2Users("manolo", "pepe"), n);

        networkM.modificar_intereses("añadir", "josefina", TopicOfInterest.DEPORTES);
        networkM.modificar_intereses("añadir", "pepe", TopicOfInterest.DEPORTES);
        assertEquals(networkM.Comun2Users("josefina", "pepe"), l);
    }

    @Test
    void testTodosIntereses(){
        List<TopicOfInterest> a = new ArrayList<>();
        a.add(TopicOfInterest.DEPORTES);
        a.add(TopicOfInterest.NOTICIAS);
        a.add(TopicOfInterest.EVENTOS);
        a.add(TopicOfInterest.INFORMACION);

        assertEquals(networkM.TodosIntereses(), a);
        assertEquals(networkL.TodosIntereses(), a);
    }

    @Test
    void testModificacionesListas(){

        assertEquals(networkM.metodo.getUser(2), "josefina");
        assertEquals(networkL.metodo.getUser(2), "josefina");

        networkM.darBaja("josefina");
        networkL.darBaja("josefina");

        assertEquals(networkM.metodo.getUser(2), "jose");
        assertEquals(networkL.metodo.getUser(2), "jose");
    }

    @Test
    void testMostrarLista(){
        networkM.darBaja("jose");
        networkL.darBaja("jose");
        networkM.darBaja("manolo");
        networkL.darBaja("manolo");

        String comprobacion = "pepe [NOTICIAS]\njosefina [EVENTOS]\n";

        //System.out.println(networkM.MostrarListas());
        assertEquals(networkL.MostrarListas(), comprobacion);

        assertEquals(networkM.MostrarListas(), comprobacion);
    }
}
