package e1;

public class Personal extends IntegrantesHogwarts{
    private Asignatura asignatura = null;
    private final int salario;
    private boolean nocturnidad = false;

    private enum Asignatura{Historia, Transformaciones, Defensa, Herbologia, Pociones}

    private Asignatura StringToCat(String cat) {
        return switch (cat) {
            case "Historia", "historia"                 -> Asignatura.Historia;
            case "Transformaciones", "transformaciones" -> Asignatura.Transformaciones;
            case "Defensa", "defensa"                   -> Asignatura.Defensa;
            case "Herbologia", "herbologia"             -> Asignatura.Herbologia;
            case "Pociones", "pociones"                 -> Asignatura.Pociones;
            default -> throw new IllegalArgumentException("La asignatura introducida no es valida");
        };
    }
    private String CatToString(Asignatura asig) {
        return switch (asig) {
            case Historia         -> "Historia";
            case Transformaciones -> "Transformaciones";
            case Defensa          -> "Defensa";
            case Pociones         -> "Pociones";
            case Herbologia       -> "Herbologia";
        };
    }
    private boolean esConserje (String cat){
        return cat.equals("conserje") || cat.equals("Conserje");
    }
    private boolean esGuardabosques (String cat){
        return cat.equals("Guardabosques") || cat.equals("guardabosques");
    }
    public Personal(String name, String surname, int age, String cat, String complemento){
        super(name, surname, age, cat); //llama al constructor de la clase de la que hereda
        if(!esConserje(cat) && !esGuardabosques(cat)){
            if(complemento != null){
                this.asignatura = StringToCat(complemento);
                salario = calcularSalario();
            }
            else throw new IllegalArgumentException("Falta especificar que clase da el docente");
        }
        else{
            if(complemento != null){
                if(complemento.equals("nocturnidad") || complemento.equals("Nocturnidad"))
                    nocturnidad = true;
                else
                    throw new IllegalArgumentException();
            }
            salario = calcularSalario();
        }
    }

    @Override
    public int calcularSalario(){
        int sal = 0;
        if(nocturnidad)
            sal = sal+10;
        if(esConserje(super.getStringCategoria()))
            sal = sal + 150;
        else if(esGuardabosques(super.getStringCategoria()))
            sal = sal + 170;
        else{
            switch(asignatura){
                case Defensa          -> sal = 500;
                case Transformaciones -> sal = 400;
                case Pociones         -> sal = 350;
                case Herbologia       -> sal = 250;
                case Historia         -> sal = 200;
            }
        }
        return sal;
    }

    @Override
    public String additionalInfo(){
        if(!esConserje(super.getStringCategoria()) && !esGuardabosques(super.getStringCategoria()))
            return (" de " + CatToString(asignatura));
        else
            return "";
    }

    @Override
    public float getRecompensa() {
        float recompensaBase = super.getRecompensa();
        if(asignatura == Asignatura.Defensa)
            return recompensaBase*0.75f;
        else
            return recompensaBase;
    }
    public int getSalario(){
        return salario;
    }
}
