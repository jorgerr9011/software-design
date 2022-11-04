package e4;

public class Calculator {

    String operaciones;
    String valores;

    public Calculator(){
        this.operaciones = "";
        this.valores = "";
    }

    public void cleanOperations () {
        this.operaciones = "";
        this.valores = "";
    }

    public  void  addOperation(String  operation , float ...  values) {
        int i;
        if(operation.equals("+") || operation.equals("-") || operation.equals("/") || operation.equals("*")){
            if(operaciones.equals("") && values.length == 2) {
                operaciones = operaciones.concat(operation);
                for(float s : values){
                    valores = valores.concat(""+s+",");
                }
                valores = valores.concat(" ");
            }else {

                operaciones = operaciones.concat(" " + operation);
                for(i = 0; i < 1; i++){
                    valores = valores.concat("" + values[i] + " ");
                }
            }
        }else{
            cleanOperations();
            throw new IllegalArgumentException(operation);
        }
    }

    public  float  executeOperations () {
        String[] j = this.operaciones.split(" ");
        String[] i = this.valores.split(" ");
        int b, a;
        float resultado = 0;

        for(a = 0; a < j.length; a++){
            for(b = 0; b < i.length; b++){
                String[] h = i[b].split(",");
                if(b == 0){
                    String aux = h[0], aux1 = h[1];
                    float g = Float.parseFloat(aux);
                    float k = Float.parseFloat(aux1);
                    if(k == 0 && j[a].equals("/")) {
                        cleanOperations();
                        throw new ArithmeticException();
                    }else {
                        switch (j[a]) {
                            case "+" -> resultado = g + k;
                            case "*" -> resultado = g * k;
                            case "-" -> resultado = g - k;
                            case "/" -> resultado = g / k;
                        }
                    }

                    a++;
                }else {
                    float y = Float.parseFloat(i[b]);
                    if(y == 0 && j[a].equals("/")) {
                        cleanOperations();
                        throw new ArithmeticException();
                    }else {
                        switch (j[a]) {
                            case "+" -> resultado = resultado + y;
                            case "*" -> resultado = resultado * y;
                            case "-" -> resultado = resultado - y;
                            case "/" -> resultado = resultado / y;
                        }
                    }
                    a++;
                }
            }
        }
        cleanOperations();
        return resultado;
    }

    @Override
    public  String  toString () {
        int t, u, aux = 0;

        String cadena = "[STATE:";
        String[] j = this.operaciones.split(" ");
        String[] i = this.valores.split(" ");
        if(!operaciones.equals("") && !valores.equals("")) {
            for (t = 0; t < j.length; t++) {
                cadena = cadena.concat("[" + j[t] + "]");
                for (u = 0; u < i.length; u++) {
                    String[] h = i[u].split(",");

                    if (t == 0 && aux == 0) {
                        cadena = cadena.concat(h[0] + "_");
                        cadena = cadena.concat(h[1]);
                        aux++;
                    } else if (t > 0 && u != 0) {
                        if (aux == u) {
                            cadena = cadena.concat(h[0]);
                            aux++;
                            break;
                        }
                    }
                }
            }
        }
        cadena = cadena.concat("]");
        return cadena;
    }
}

