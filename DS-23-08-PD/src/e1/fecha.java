package diseño;

import java.util.ArrayList;

public class fecha extends ElementosBilletes {

    public fecha(String cadena){
        this.valor = cadena;
        this.resultado = "";
        procesar();
    }

    public String getValor() {
        return this.valor;
    }

    public String getResultado() {
        return this.resultado;
    }

    public String comprobar_linea(ArrayList<String> disponibles, int index){
        return disponibles.get(index);
    }

    public void procesar(){

        String aux = valor.replace(" ", "");
        String[] aux1;
        String[] aux2;

        if(!valor.equals("")) {
            if (aux.contains("AND")) {
                System.out.println("Error: datos incorrectos");
            } else {
                aux1 = aux.split("OR");
                for (int i = 0; i < aux1.length; i++) {
                    if (i != 0) {
                        this.resultado = this.resultado.concat(" OR ");
                    }
                    aux2 = aux1[i].split("=");
                    this.resultado = this.resultado.concat(aux2[1]);
                }
            }
        }
    }
}
