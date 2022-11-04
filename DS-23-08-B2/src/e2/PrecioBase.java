package e2;

import java.util.Comparator;

public class PrecioBase implements Comparator<AnuncioApartamento> {

    @Override
    public int compare(AnuncioApartamento a1, AnuncioApartamento a2){
        return a1.getPrecioBase() - a2.getPrecioBase();
    }
}