package e2;

import org.junit.jupiter.api.*;
import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class test_gestion_tareas {

    @Test
    void funcionalidades_gestor(){
        Gestor_Tareas gestor = new Gestor_Tareas(new File("./DS-23-08-PD/src/e2/Tareas1.txt"));
        Gestor_Tareas gestor2 = new Gestor_Tareas(new File("./DS-23-08-PD/src/e2/Tareas2.txt"));
        ArrayList<Tarea> listaTareasGestor  = gestor.getListaTareas();

        assertNotEquals(gestor, gestor2);
        assertNotEquals(gestor.getListaTareas(), gestor2.getListaTareas());

        gestor.cambiarTareas(new File("./DS-23-08-PD/src/e2/Tareas2.txt"));

        assertEquals(gestor.getListaTareas().toString(), gestor2.getListaTareas().toString());
        for(int a = 0; a < listaTareasGestor.size(); a++)
            assertEquals(gestor.getTarea(a).toString(), gestor2.getTarea(a).toString());

        Tarea tarea = new Tarea('X', 'C', true);
        gestor.addTarea(tarea);
        assertNotEquals(gestor, gestor2);
        assertNotEquals(gestor.getListaTareas(), gestor2.getListaTareas());

        gestor.removeTarea(gestor.getNumTarea(tarea));
        assertEquals(gestor.getListaTareas().toString(), gestor2.getListaTareas().toString());

        assertThrows(RuntimeException.class, () -> new Gestor_Tareas(new File("./DS-23-08-PD/src/e2/TareasMal.txt")));
        assertThrows(IllegalArgumentException.class, () -> new Tarea('Ñ'));
    }

    @Test
    void prueba_realizaciones(){
        Gestor_Tareas gestor = new Gestor_Tareas(new File("./DS-23-08-PD/src/e2/Tareas1.txt"));
        Gestor_Tareas gestor2 = new Gestor_Tareas(new File("./DS-23-08-PD/src/e2/Tareas1.txt"));
        String resolucion_fuerte    = "C - A - B - D - G - F - E - H - J";
        String resolucion_debil     = "C - A - B - D - E - F - G - H - J";
        String resolucion_orden     = "C - G - A - F - H - B - D - E - J";
        Dependencia_fuerte dependencia_fuerte = new Dependencia_fuerte();
        Dependencia_debil dependencia_debil   = new Dependencia_debil();
        Orden_jerarquico orden_jerarquico     = new Orden_jerarquico();

        gestor.setOrden(dependencia_fuerte);
        gestor2.setOrden(dependencia_debil);
        gestor.gestionarTareas();
        gestor2.gestionarTareas();

        assertEquals(resolucion_fuerte, gestor.string_tareas_realizadas());
        assertEquals(resolucion_debil, gestor2.string_tareas_realizadas());
        assertNotEquals(gestor.string_tareas_realizadas(), gestor2.string_tareas_realizadas());

        gestor.setOrden(orden_jerarquico);
        gestor.gestionarTareas();
        assertEquals(resolucion_orden, gestor.string_tareas_realizadas());
    }
}
