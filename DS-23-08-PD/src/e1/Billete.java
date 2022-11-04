package diseño;

import java.util.ArrayList;

public class Billete extends ElementosBilletes {

    origen beginning;
    destino destiny;
    precio price;
    fecha date;

    public Billete(origen beginning, destino destiny, precio price, fecha date, ArrayList<String> disponibles){
        this.beginning = beginning;
        this.destiny = destiny;
        this.price = price;
        this.date = date;
        billete = "";
        this.procesar();
        this.buscar_billetes(disponibles);
    }

    public String getBillete(){
        return billete;
    }

    public void setBillete(origen beginning, destino destiny, precio price, fecha date, ArrayList<String> disponibles){
        this.beginning = beginning;
        this.destiny = destiny;
        this.price = price;
        this.date = date;
        billete = "";
        this.procesar();
        this.buscar_billetes(disponibles);
    }

    public String comprobar_linea(ArrayList<String> disponibles, int index){
        return disponibles.get(index);
    }

    public ArrayList<String> getResultado_busq() {
        return resultado_busq;
    }

    public void procesar() {

        this.billete = this.billete.concat(beginning.getResultado());

        if(!billete.equals("")){
            billete = billete.concat(" AND ");
        }
        billete = billete.concat(destiny.getResultado());

        if(!billete.equals("")){
            billete = billete.concat(" AND ");
        }
        billete = billete.concat(price.getResultado());

        if(!billete.equals("")){
            billete = billete.concat(" AND ");
        }
        billete = billete.concat(date.getResultado());
    }

    public void buscar_billetes(ArrayList<String> disponibles){

        String billeteDis;
        String[] aux = billete.replace(" ", "").split("AND");
        String[] Disp;
        String comparador;
        boolean t = false;

        String[] orig = new String[1];
        String[] dest = new String[1];
        String[] prec = new String[1];
        String[] fech = new String[1];
        if(!aux[0].equals("")) {
            orig = aux[0].split("OR");
        }else
            orig[0] = "";
        if(!aux[1].equals("")) {
            dest = aux[1].split("OR");
        }else
            dest[0] = "";
        if(!aux[2].equals("")) {
            prec = aux[2].split("OR");
        }else
            prec[0] = "";
        if(!aux[3].equals("")) {
            fech = aux[3].split("OR");
        }else
            fech[0] = "";

        for(int i = 0; i < disponibles.size(); i++){
            billeteDis = disponibles.get(i).replace(" ", "");
            Disp = billeteDis.split("AND");
            for(int a = 0; a < orig.length; a++) {
                for (int b = 0; b < dest.length; b++) {
                    for (int c = 0; c < prec.length; c++) {
                        if(!prec[c].equals("")) {
                            if(prec[c].startsWith(">=")) {
                                comparador = prec[c].replace(">=", "");
                                t = (Integer.parseInt(Disp[2])) >= (Integer.parseInt(comparador));
                            }else if(prec[c].startsWith("<=")) {
                                comparador = prec[c].replace("<=", "");
                                t = (Integer.parseInt(Disp[2])) <= (Integer.parseInt(comparador));
                            }else if(prec[c].startsWith("<")) {
                                comparador = prec[c].replace("<", "");
                                t = (Integer.parseInt(Disp[2])) < (Integer.parseInt(comparador));
                            }else if(prec[c].startsWith(">")) {
                                comparador = prec[c].replace(">", "");
                                t = (Integer.parseInt(Disp[2])) > (Integer.parseInt(comparador));
                            }else if(prec[c].startsWith("=")) {
                                comparador = prec[c].replace("=", "");
                                t = (Integer.parseInt(Disp[2])) == (Integer.parseInt(comparador));
                            }
                        }else
                            t = true;

                        for (int d = 0; d < fech.length; d++) {
                            if ((Disp[0].equals(orig[a]) || orig[a].equals("")) && (Disp[1].equals(dest[b]) ||
                                    dest[b].equals("")) && t && (Disp[3].equals(fech[d]) || fech[d].equals(""))) {
                                resultado_busq.add(disponibles.get(i));
                            }
                        }
                    }
                }
            }
        }
    }
}
