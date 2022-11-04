package e1;

import java.util.ArrayList;

public class Colegio {
    private final ArrayList<IntegrantesHogwarts> listaIntegrantes;

    public Colegio(){
        this.listaIntegrantes = new ArrayList<>();
    }

    public void addIntegrante(IntegrantesHogwarts nuevoIntegrante){
        listaIntegrantes.add(nuevoIntegrante);
    }
    //for the sake of testing
    public IntegrantesHogwarts getIntegrante(int pos){
        return listaIntegrantes.get(pos);
    }

    public String imprimirRecompensa(){
        float recompensa = 0;
        StringBuilder output = new StringBuilder();
        for(IntegrantesHogwarts integrante : listaIntegrantes){
            output.append(integrante.getNombre()).append(" ").append(integrante.getApellido()).append("(").
                    append(integrante.getStringCategoria()).append(integrante.additionalInfo()).append(",").
                    append(integrante.getHorrocrux()).append(" horrocruxes): ").append(integrante.getRecompensa()).
                    append(" galeones\n");
            recompensa = recompensa + integrante.getRecompensa();
        }
        output.append("La recompensa total del Colegio Hogwarts es de ").append(recompensa).append(" galeones\n");
        return output.toString();
    }
    public String imprimirSalarios(){
        int salarioTotal = 0;
        StringBuilder output = new StringBuilder();
        for(IntegrantesHogwarts integrante : listaIntegrantes){
            String cat = integrante.getStringCategoria();
            if(cat.equals("Docente") || cat.equals("Conserje") || cat.equals("Guardabosques")){
                output.append(integrante.getNombre()).append(" ").append(integrante.getApellido()).append("(").
                        append(cat).append(integrante.additionalInfo()).append("): ").append(integrante.calcularSalario()).
                        append(" galeones\n");
                salarioTotal = salarioTotal + integrante.calcularSalario();
            }
        }
        output.append("La recompensa total del Colegio Hogwarts es de ").append(salarioTotal).append(" galeones\n");
        return output.toString();
    }
}
