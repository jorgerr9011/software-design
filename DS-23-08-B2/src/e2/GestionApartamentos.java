package e2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GestionApartamentos {
    private final ArrayList<AnuncioApartamento> lista;
    Comparator<AnuncioApartamento> comparador = null;

    public GestionApartamentos(){
        this.lista = new ArrayList<>();
    }

    public void addApartamento(AnuncioApartamento apartamento){
        for(AnuncioApartamento apartamentoAux : lista){
            if(apartamentoAux.getNumeroReferencia() == apartamento.getNumeroReferencia())
                throw new IllegalArgumentException("Ese apartamento ya esta registrado");
        }
        lista.add(apartamento);
    }
    public AnuncioApartamento getApartamento(int i){
        return lista.get(i);
    }
    public int getNumeroApartamentos(){
        return lista.size();
    }
    public void setComparator(Comparator<AnuncioApartamento> comparator){
        this.comparador = comparator;
    }

    public void ordenarLista(){// va sin args ya que en setComparator ya establecemos el metodo de ordenacion
        if(comparador == null)
            Collections.sort(lista);
        else
            Collections.sort(lista, comparador);
    }
}
