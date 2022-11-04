package e1;

public class Residente extends IntegrantesHogwarts {
    private final CasaAsociada casaAsociada;

    private enum CasaAsociada {Gryffindor, Hufflepuf, Ravenclaw, Slytherin}

    private CasaAsociada StringToCat(String casaAsoc) {
        return switch (casaAsoc) {
            case "Gryffindor", "gryffindor" -> CasaAsociada.Gryffindor;
            case "Hufflepuf", "hufflepuf" -> CasaAsociada.Hufflepuf;
            case "Ravenclaw", "ravenclaw" -> CasaAsociada.Ravenclaw;
            case "Slytherin", "slytherin" -> CasaAsociada.Slytherin;
            default -> throw new IllegalArgumentException("La casa asociada introducida no es valida");
        };
    }

    private String CatToString(CasaAsociada casaAsoc) {
        return switch (casaAsoc) {
            case Gryffindor -> "Gryffindor";
            case Hufflepuf -> "Hufflepuf";
            case Ravenclaw -> "Ravenclaw";
            case Slytherin -> "Slytherin";
        };
    }

    public Residente(String name, String surname, int age, String cat, String casaAsoc) {
        super(name, surname, age, cat); //llama al constructor de la clase de la que hereda
        this.casaAsociada = StringToCat(casaAsoc);
    }

    @Override
    public String additionalInfo() {
        return (" de " + CatToString(casaAsociada));
    }

    @Override
    public float getRecompensa() {
        float recompensaBase = super.getRecompensa();
        if (casaAsociada == CasaAsociada.Slytherin)
            return recompensaBase * 2;
        else
            return recompensaBase;
    }
}
