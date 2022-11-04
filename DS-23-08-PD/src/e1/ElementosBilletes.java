package diseño;

import java.util.ArrayList;

public abstract class ElementosBilletes {

    ArrayList<String> resultado_busq = new ArrayList<>();
    ArrayList<String> billetesDisponibles = new ArrayList<>();

    String valor;
    String resultado;
    String billete;
    String busqueda;

    public abstract void procesar();

    public void mostrar_billete(String formulario){
        System.out.println("*********************");
        System.out.println("Mi formulario:");
        System.out.println("-> "+formulario);
        System.out.println("*********************");
    }

    public void mostrar_totales(){
        System.out.println("*********************");
        System.out.println("Billetes totales:");
        for(int i = 0; i < billetesDisponibles.size(); i++){
            System.out.println("-> "+billetesDisponibles.get(i));
        }
        System.out.println("*********************");

    }

    public void mostrar_disponibles(){
        System.out.println("*********************");
        System.out.println("Billetes disponibles:");
        for(int i = 0; i < resultado_busq.size(); i++){
            System.out.println("-> "+resultado_busq.get(i));
        }
        System.out.println("*********************");
    }

    public abstract String comprobar_linea(ArrayList<String> disponibles, int index);
}
