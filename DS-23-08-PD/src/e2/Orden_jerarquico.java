package e2;

import java.util.ArrayList;

public class Orden_jerarquico extends Orden_de_realizacion{

    public Tarea escoger_candidato(ArrayList<Tarea> lista_tareas){
        Tarea tareaEscogida = null;
        ArrayList<Tarea> tareas_candidato = new ArrayList<>();
        if(!lista_tareas.isEmpty()){
            for(Tarea tarea:lista_tareas){
                if((tarea.getNumDependencias(true) == 0) || comprobar_dependencias(tarea, lista_tareas)){
                    tareas_candidato.add(tarea);
                }
            }
            for(Tarea tarea:tareas_candidato){
                if(tareaEscogida == null){
                    tareaEscogida = tarea;
                }
                if (tarea.getNombreTarea() < tareaEscogida.getNombreTarea()) {
                    tareaEscogida = tarea;
                    break;
                }
            }
        }
        return tareaEscogida;
    }
    private boolean comprobar_dependencias(Tarea tarea, ArrayList<Tarea> tareas_pendientes){
        for(Tarea tareaAux:tareas_pendientes){
            if(tareaAux.getNivel() < tarea.getNivel())
                return false;
        }
        return true;
    }
}


