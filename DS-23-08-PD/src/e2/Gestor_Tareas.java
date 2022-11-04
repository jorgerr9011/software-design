package e2;

import java.io.*;
import java.util.ArrayList;

public class Gestor_Tareas {
    private Orden_de_realizacion orden;
    private ArrayList<Tarea> listaTareas;
    private File ficheroConTareas;
    private ArrayList<Tarea> lista_realizacion;

    public Gestor_Tareas(File fichero){
        listaTareas = new ArrayList<>();
        ficheroConTareas = fichero;
        obtenerListaTareas();
    }

    private void obtenerListaTareas(){
            if (ficheroConTareas.exists()){
            String tareas = fichero_a_String();
            String[] todas_las_tareas = tareas.split("\\r?\\n");
            for (String tarea_con_dependencias : todas_las_tareas) {
                String[] partes = tarea_con_dependencias.split(" ");
                if ((partes.length > 3) || (partes[0].length() > 1) || (partes[1].length() > 2) || (partes[2].length() > 1))
                    throw new IllegalArgumentException();
                crear_tarea_o_dependencia(partes[0].charAt(0), partes[2].charAt(0));
            }
        }
        else {
            throw new IllegalArgumentException("ese fichero no existe");
        }
        ordenarTareas();
        distribuirNiveles();
    }

    private void crear_tarea_o_dependencia(char nombreTarea, char nombreDependen){
        if(listaTareas.isEmpty()){
            Tarea nueva_tarea = new Tarea(nombreTarea, nombreDependen, false);
            nueva_tarea.addDependencia(nombreDependen, false);
            addTarea(nueva_tarea);
        }
        if(existeTarea(nombreTarea)){
            Tarea tarea = getTarea(nombreTarea); //ignoro el posible null porque entonces existe tarea seria false
            tarea.addDependencia(nombreDependen, false);
        }else{
            Tarea tarea = new Tarea(nombreTarea, nombreDependen, false);
            addTarea(tarea);
        }
        if(existeTarea(nombreDependen)){
            Tarea tarea = getTarea(nombreDependen);
            tarea.addDependencia(nombreTarea, true);
        }else{
            Tarea tarea = new Tarea(nombreDependen, nombreTarea, true);
            addTarea(tarea);
        }
    }

    private boolean existeTarea(char nombreTarea){
        for(Tarea tarea:getListaTareas()){
            if(tarea.getNombreTarea() == nombreTarea)
                return true;
        }
        return false;
    }

    private void distribuirNiveles(){
        int nivel = 0;
        for(Tarea tarea:listaTareas){
            if(!tarea.tieneDependencia(true)){
                for(int i = 0; i < tarea.getNumDependencias(false); i++){
                    Tarea tareaAux = getTarea(tarea.getDependencia(i, false));
                    tareaAux.setNivel(1);
                }
            }
        }
        boolean sigue = true;
        while(sigue){
            sigue = false;
            for(Tarea tarea:listaTareas){
                if(tarea.tieneDependencia(true)){
                    nivel = 100;
                    for(int i = 0; i < tarea.getNumDependencias(true); i++){
                        Tarea tareaAux = getTarea(tarea.getDependencia(i, true));
                        if(tareaAux.getNivel() < nivel){
                            nivel = tareaAux.getNivel();
                        }

                    }
                    if((tarea.tieneDependencia(true)) && (tarea.getNivel() == 0))
                        sigue = true;
                    tarea.setNivel(nivel + 1);
                }
            }
        }
    }


    public void gestionarTareas(){
        ordenarTareas();
        this.lista_realizacion = orden.realizar_tareas(this);
    }

    public ArrayList<Tarea> copiarLista(ArrayList<Tarea> lista_tareas){
        ArrayList<Tarea> tareas = new ArrayList<>();
        for(Tarea tareaAux:lista_tareas){
            Tarea tarea = new Tarea(tareaAux.getNombreTarea());
            for(Character character:tareaAux.getDependencias(true)){
                tarea.addDependencia(character, true);
            }
            for(Character character:tareaAux.getDependencias(false)){
                tarea.addDependencia(character, false);
            }
            tarea.setNivel(tareaAux.getNivel());
            tareas.add(tarea);
        }
        return tareas;
    }

    private void ordenarTareas(){
        ArrayList<Tarea> tareas_ordenadas = new ArrayList<>();
        ArrayList<Tarea> copia_lista_tareas = listaTareas;
        for(Tarea tarea:copia_lista_tareas){
            Tarea tarea_a_ordenar = obtener_tareas_iniciales(tarea, copia_lista_tareas);
            tareas_ordenadas.add(tarea_a_ordenar);
        }
        listaTareas = tareas_ordenadas;
    }

    private Tarea obtener_tareas_iniciales(Tarea tarea, ArrayList<Tarea> listaTareasAux){
        Tarea tarea_a_ordenar = tarea;
        if(!tarea_a_ordenar.tieneDependencia(true)) {
            if (tarea.getNumDependencias(true) > 0) {
                for (Tarea tarea2 : listaTareasAux){
                    if (tarea2.getNombreTarea() == tarea.getDependencia(0, false))
                        if(tarea2.getNombreTarea() < tarea_a_ordenar.getNombreTarea())
                            tarea_a_ordenar = tarea2;
                }
            }
        }
        return tarea_a_ordenar;
    }

    private String fichero_a_String(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(ficheroConTareas));
            try{
                StringBuilder sb = new StringBuilder();
                String linea     = br.readLine();
                while(linea != null){
                    sb.append(linea);
                    sb.append(System.lineSeparator());
                    linea = br.readLine();
                }
                return sb.toString();
            } catch (IOException e) {
                throw new RuntimeException();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch(FileNotFoundException exception){
            throw new RuntimeException(exception);
        }
    }

    public void cambiarTareas(File nuevasTareas){
        listaTareas.clear();
        ficheroConTareas = nuevasTareas;
        obtenerListaTareas();
    }

    public void addTarea(Tarea tarea){
        for(Tarea tareaAux:listaTareas){
            if(tarea == tareaAux)
                return;
        }
        listaTareas.add(tarea);
    }

    public void setOrden(Orden_de_realizacion ordenElegido) {
        this.orden = ordenElegido;
    }

    public Orden_de_realizacion getOrden() {
        return orden;
    }

    public Tarea getTarea(int i){
        return listaTareas.get(i);
    }
    public Tarea getTarea(char nombre){
        for(Tarea tarea:getListaTareas()){
            if(tarea.getNombreTarea() == nombre)
                return tarea;
        }
        return null;
    }

    public void removeTarea(int i){
        Tarea tarea = getTarea(i);
        if(tarea.getNumDependencias(true) > 0){
            for(int a = 0; a < tarea.getNumDependencias(true); a++){
                for(Tarea tareaAux:listaTareas)
                    if(tareaAux.getNombreTarea() == tarea.getDependencia(a, true)){
                        tareaAux.eliminarDependencia(tarea.getNombreTarea());
                    }
            }
        }
        if(tarea.getNumDependencias(false) > 0){
            for(int a = 0; a < tarea.getNumDependencias(false); a++){
                for(Tarea tareaAux:listaTareas)
                    if(tareaAux.getNombreTarea() == tarea.getDependencia(a, false)){
                        tareaAux.eliminarDependencia(tarea.getNombreTarea());
                    }
            }
        }
        ordenarTareas();
        listaTareas.remove(i);
    }

    public ArrayList<Tarea> getListaTareas() {
        return listaTareas;
    }

    public ArrayList<Tarea> getLista_realizacion(){
        return lista_realizacion;
    }

    public int getNumTarea(Tarea tarea){
        int i = 0;
        for(Tarea tareaAux:listaTareas){
            if(tareaAux.equals(tarea))
                return i;
            i++;
        }
        return -1;
    }

    public String string_tareas_realizadas(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(Tarea tarea:getLista_realizacion()){
            sb.append(tarea.getNombreTarea());
            if(i != getLista_realizacion().size()-1)
                sb.append(" - ");
            i++;
        }

        return sb.toString();
    }
}
