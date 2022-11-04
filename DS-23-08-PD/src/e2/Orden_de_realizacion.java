package e2;

import java.util.ArrayList;

public abstract class Orden_de_realizacion{
    private final ArrayList<Tarea> orden_de_realizacion;
    private ArrayList<Tarea> tareas_pendientes;
    protected Orden_de_realizacion() {
        orden_de_realizacion = new ArrayList<>();
        tareas_pendientes    = new ArrayList<>();
    }

    public ArrayList<Tarea> realizar_tareas(Gestor_Tareas gestor){
        tareas_pendientes = gestor.copiarLista(gestor.getListaTareas());
        while(!tareas_pendientes.isEmpty()){
            Tarea tarea = escoger_candidato(tareas_pendientes);
            if(tarea != null){
                orden_de_realizacion.add(tarea);
            }
            tareas_pendientes.remove(tarea);
        }
        return getOrden_de_realizacion();
    }

    public ArrayList<Tarea> getOrden_de_realizacion(){
        return orden_de_realizacion;
    }

    public abstract Tarea escoger_candidato(ArrayList<Tarea> lista_tareas);
}
