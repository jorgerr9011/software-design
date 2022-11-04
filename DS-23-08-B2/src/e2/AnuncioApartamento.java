package e2;

import java.util.ArrayList;

public class AnuncioApartamento implements Comparable<AnuncioApartamento>{
    private final int numeroReferencia;
    private final int habitaciones;
    private final int metrosCuadrados;
    private final int baths;
    private final int anhoConstruccion;
    private final int codigoPostal;
    private final String localizacion;
    private final int precioBase;
    private final ArrayList<Integer> precioGarajes;
    private final int precioTotal;

    public AnuncioApartamento(int numeroReferencia, int habitaciones, int metrosCuadrados, int baths,
                              int anhoConstruccion, int codigoPostal, String localizacion, int precioBase,
                              String informacionAdicional){ //el formato de la info adicional es: garajes num precio num
                                                            // garaje1 num precio num
                                                            // garaje2 num precio num...
        if((habitaciones == 0) || (metrosCuadrados == 0) || (baths == 0) || (anhoConstruccion < 1600) || (localizacion == null)
            || (precioBase == 0))
            throw new IllegalArgumentException("Error al meter la informacion");

        int precioAux = 0;

        this.numeroReferencia = numeroReferencia;
        this.habitaciones = habitaciones;
        this.metrosCuadrados = metrosCuadrados;
        this.baths = baths;
        this.anhoConstruccion = anhoConstruccion;
        this.codigoPostal = codigoPostal;
        this.localizacion = localizacion;
        this.precioBase = precioBase;
        if(informacionAdicional != null){
            this.precioGarajes = new ArrayList<>();
            String partes[] = informacionAdicional.split(" ");
            int numGarajes  = Integer.parseInt(partes[1]);
            for(int i = 0, b = 0; i < numGarajes; i ++, b += 4){
                this.precioGarajes.add(Integer.parseInt(partes[b+3]));
                precioAux        += precioGarajes.get(i);
            }
        }
        else
            this.precioGarajes = null;
        precioTotal               = precioAux + this.precioBase;

    }
    @Override
    public int compareTo(AnuncioApartamento apartamento) {
        return this.getNumeroReferencia() - apartamento.getNumeroReferencia();
    }

    public int getNumeroReferencia(){
        return numeroReferencia;
    }
    public int getHabitaciones(){
        return habitaciones;
    }
    public int getMetrosCuadrados(){
        return metrosCuadrados;
    }
    public int getAnhoConstruccion(){
        return anhoConstruccion;
    }
    public int getBaths(){
        return baths;
    }
    public int getCodigoPostal(){
        return codigoPostal;
    }
    public String getLocalizacion(){
        return localizacion;
    }
    public int getPrecioGaraje(int i) {
        return precioGarajes.get(i);
    }
    public ArrayList<Integer> getPrecioGarajes() {
        return precioGarajes;
    }
    public int getPrecioBase(){
        return precioBase;
    }
    public int getPrecioTotal(){
        return precioTotal;
    }
    @Override
    public boolean equals ( Object o) {
        if(this == o)
            return true;
        if(o == null)
            return false;
        if(this.getClass() != o.getClass())
            return false;
        AnuncioApartamento apartamento = (AnuncioApartamento) o;
        return (this.getHabitaciones() == apartamento.getHabitaciones()) && (this.getMetrosCuadrados() == apartamento.getMetrosCuadrados())
                && (this.getAnhoConstruccion() == apartamento.getAnhoConstruccion()) && (this.getBaths() == apartamento.getBaths())
                && (this.getCodigoPostal() == apartamento.getCodigoPostal()) && this.getLocalizacion().equals(apartamento.getLocalizacion())
                && (this.getPrecioGarajes() == apartamento.getPrecioGarajes()) && this.getPrecioBase() == apartamento.getPrecioBase()
                && (this.getPrecioTotal() == apartamento.getPrecioTotal());
    }
    @Override
    public int hashCode () {
        int result = 0;
        result = 31 * result + this.getHabitaciones();
        result = 31 * result + this.getMetrosCuadrados();
        result = 31 * result + this.getAnhoConstruccion();
        result = 31 * result + this.getBaths();
        result = 31 * result + this.getCodigoPostal();
        String[] partes = this.getLocalizacion().split(" ");
        result = 31 * result + Integer.parseInt(partes[0]);
        if(this.getPrecioGarajes() != null){
            for(int i : this.getPrecioGarajes())
                result = 31 * result + this.getPrecioGaraje(i);
        }
        result = 31 * result + this.getPrecioBase();
        result = 31 * result + this.getPrecioTotal();

        return result;
    }

}
