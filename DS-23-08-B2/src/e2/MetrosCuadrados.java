package e2;

import java.util.Comparator;

public class MetrosCuadrados implements Comparator<AnuncioApartamento> {
    @Override
    public int compare(AnuncioApartamento a1, AnuncioApartamento a2) {
        return a1.getMetrosCuadrados() - a2.getMetrosCuadrados();
    }
}