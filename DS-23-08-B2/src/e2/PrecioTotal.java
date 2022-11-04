package e2;

import java.util.Comparator;

public class PrecioTotal implements Comparator<AnuncioApartamento> {

    @Override
    public int compare(AnuncioApartamento a1, AnuncioApartamento a2){
        return a1.getPrecioTotal() - a2.getPrecioTotal();
    }
}
