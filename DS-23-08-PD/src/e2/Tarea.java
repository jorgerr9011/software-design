package e2;

import java.util.ArrayList;

public class Tarea {
    private final char nombreTarea;
    private ArrayList<Character> dependen_de_tarea;
    private ArrayList<Character> depende_de_tarea;
    private int nivel;

    public Tarea(char nombre, char nombre_dependencia, boolean depende_de){
        if((nombre < 65) || (nombre_dependencia < 65) || (nombre > 90) || (nombre_dependencia > 90))
            throw new IllegalArgumentException();
        dependen_de_tarea = new ArrayList<>();
        depende_de_tarea  = new ArrayList<>();
        nombreTarea       = nombre;
        nivel             = 0;
        if(depende_de) {
            depende_de_tarea.add(nombre_dependencia);
            nivel++;
        }else
            dependen_de_tarea.add(nombre_dependencia);
    }
    public Tarea(char nombre){
        if((nombre < 65) || (nombre > 90))
            throw new IllegalArgumentException();
        dependen_de_tarea = new ArrayList<>();
        depende_de_tarea  = new ArrayList<>();
        nombreTarea = nombre;
    }

    public void addDependencia(char dependencia, boolean depende_de){
        if(depende_de){
            for(Character character:depende_de_tarea){
                if(character == dependencia)
                    return;
            }
            if(getDependencias(true).isEmpty())
                nivel++;
            depende_de_tarea.add(dependencia);
        }
        else{
            for(Character character:dependen_de_tarea){
                if(character == dependencia)
                    return;
            }
            dependen_de_tarea.add(dependencia);
        }
    }

    public void eliminarDependencia(char dependencia){
        int i = 0;
        ArrayList<Character> copia_depende_de_tarea = depende_de_tarea;
        ArrayList<Character> copia_dependen_de_tarea = dependen_de_tarea;
        for(char character:depende_de_tarea){
            if(character == dependencia){
                copia_depende_de_tarea.remove(i);
                break;
            }
            i++;
        }
        i = 0;
        for(char character:dependen_de_tarea){
            if(character == dependencia){
                copia_dependen_de_tarea.remove(i);
                break;
            }
            i++;
        }
        setDepende_de_tarea(copia_depende_de_tarea);
        setDependen_de_tarea(copia_dependen_de_tarea);

    }

    public void setDependen_de_tarea(ArrayList<Character> nuevaLista){
        dependen_de_tarea = nuevaLista;
    }

    public void setDepende_de_tarea(ArrayList<Character> nuevaLista){
        depende_de_tarea = nuevaLista;
    }

    public int getNumDependencias(boolean depende_de){
        if(depende_de)
            return depende_de_tarea.size();
        else
            return dependen_de_tarea.size();
    }

    public char getDependencia(int i, boolean depende_de){
        if(depende_de)
            return depende_de_tarea.get(i);
        else
            return dependen_de_tarea.get(i);
    }

    public int getDependencia(char c, boolean depende_de){
        int i = 0;
        if(depende_de){
            for(Character character:depende_de_tarea){
                if(character == c)
                    return i;
                i++;
            }
        }else{
            for(Character character:depende_de_tarea){
                if(character == c)
                    return i;
                i++;
            }
        }
        return -1;
    }

    public ArrayList<Character> getDependencias(boolean depende_de){
        if(depende_de)
            return depende_de_tarea;
        else
            return dependen_de_tarea;
    }
    public boolean tieneDependencia(boolean depende_de){
        if(depende_de) {
            return !depende_de_tarea.isEmpty();
        }
        else
            return  !dependen_de_tarea.isEmpty();
    }

    public int getNivel(){
        return nivel;
    }

    public char getNombreTarea(){
        return nombreTarea;
    }

    public void setNivel(int nuevoNivel){
        this.nivel = nuevoNivel;
    }

    @Override
    public boolean equals ( Object o) {
        if(this == o)
            return true;
        if(o == null)
            return false;
        if(this.getClass() != o.getClass())
            return false;
        Tarea tarea = (Tarea) o;
        boolean mismasDependencias = false;
        boolean dependen_de_mismas = false;
        boolean depende_de_mismas  = false;
        if((this.dependen_de_tarea.size() != tarea.dependen_de_tarea.size()) &&
                (this.depende_de_tarea.size() != tarea.depende_de_tarea.size()))
            return false;
        if(!depende_de_tarea.isEmpty()){
            int i = 0;
            for(Character character : this.depende_de_tarea){
                for(Character character2 : tarea.depende_de_tarea){
                    if(character == character2)
                        i++;
                }
            }
            if(i == this.depende_de_tarea.size()-1)
                depende_de_mismas = true;
        }else
            depende_de_mismas = true;
        if(!dependen_de_tarea.isEmpty()){
            int a = 0;
            for(Character character : this.dependen_de_tarea){
                for(Character character2 : tarea.dependen_de_tarea){
                    if(character == character2)
                        a++;
                }
            }
            if(a == this.dependen_de_tarea.size()-1)
                dependen_de_mismas = true;
        }else
            dependen_de_mismas = true;
        if(depende_de_mismas && dependen_de_mismas)
            mismasDependencias = true;
        return ((tarea.getNombreTarea() == this.getNombreTarea()) && (mismasDependencias));
    }

    @Override
    public int hashCode () {
        int result = 0;

        result = 31 * result + this.getNombreTarea();
        for (Character character : dependen_de_tarea) {
            result = 31 * result + character;
        }
        for (Character character : depende_de_tarea) {
            result = 31 * result + character;
        }

        return result;
    }
}
