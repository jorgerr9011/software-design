package e2;

import java.util.Comparator;

public class YearOfConstruction implements Comparator<AnuncioApartamento> {
    @Override
    public int compare(AnuncioApartamento a1, AnuncioApartamento a2){
        return a1.getAnhoConstruccion() - a2.getAnhoConstruccion();
    }
}
