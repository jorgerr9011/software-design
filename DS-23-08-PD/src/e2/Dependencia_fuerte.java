package e2;

import java.util.ArrayList;

public class Dependencia_fuerte extends Orden_de_realizacion{

    public Tarea escoger_candidato(ArrayList<Tarea> lista_tareas){
        ArrayList<Tarea> tareas_candidato = new ArrayList<>();
        Tarea tareaEscogida = null;
        if(!lista_tareas.isEmpty()){
            for(Tarea tarea:lista_tareas){
                if((tarea.getNumDependencias(true) == 0) || comprobarDependencias(tarea, lista_tareas)){
                    tareas_candidato.add(tarea);
                }
            }
            for(Tarea tarea:tareas_candidato){
                if(tareaEscogida == null){
                    tareaEscogida = tarea;
                }
                if(tarea.getNombreTarea() < tareaEscogida.getNombreTarea())
                    tareaEscogida = tarea;
            }
        }
        return tareaEscogida;
    }

    private boolean comprobarDependencias(Tarea tarea, ArrayList<Tarea> tareas_pendientes){
        if(tarea.getNumDependencias(true) > 0){
            for(Tarea tareaAux:tareas_pendientes){
                for(int i = 0; i < tarea.getNumDependencias(true); i++){
                    if(tareaAux.getNombreTarea() == tarea.getDependencia(i, true))
                        return false;
                }
            }
        }
        return true;
    }
}
