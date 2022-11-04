package e1;

public class IntegrantesHogwarts {
    private final String nombre;
    private final String apellido;
    private final int edad;
    private int horrocrux = 0;
    private final Categoria categoria;

    private enum Categoria{
        Docente(50f),
        Conserje(65f),
        Guardabosques(75f),
        Fantasma(80f),
        Estudiante(90f);

        private final float bono;
        Categoria(float bono){
            this.bono = bono;
        }
        public float getBono(){
            return bono;
        }
    }

    private Categoria StringToCat(String cat){
        return switch (cat) {
            case "Docente", "docente"             -> Categoria.Docente;
            case "Conserje", "conserje"           -> Categoria.Conserje;
            case "Guardabosques", "guardabosques" -> Categoria.Guardabosques;
            case "Fantasma", "fantasma"           -> Categoria.Fantasma;
            case "Estudiante", "estudiante"       -> Categoria.Estudiante;
            default -> throw new IllegalArgumentException("La categoria introducida no es valida");
        };
    }
    private String CatToString(Categoria cat) {
        return switch (cat) {
            case Docente       -> "Docente";
            case Conserje      -> "Conserje";
            case Guardabosques -> "Guardabosques";
            case Fantasma      -> "Fantasma";
            case Estudiante    -> "Estudiante";
        };
    }
    public IntegrantesHogwarts(String name, String surname, int age, String cat){
        if((name == null) || (surname == null) || (age < 11) || (cat == null)) //lo de la edad se basa en que por lo visto los alumnos
                                                              // entraban como muy pronto a los once
            throw new IllegalArgumentException("Los argumentos introducidos no son correctos, vuelva a intentarlo");

        this.nombre    = name;
        this.apellido  = surname;
        this.edad      = age;
        this.categoria = StringToCat(cat);

    }
    public int calcularSalario(){
        throw new UnsupportedOperationException("Solo los docentes tienen salario");
    }
    public String additionalInfo() {
        throw new UnsupportedOperationException();
    }
    //setter en caso de que se avise cada vez que mate a un horrocrux
    public void destruccionHorrocruxes(){
        horrocrux++;
    }
    //setter por si la persona ha matado varios horrocruxes o lleva tiempo sin actualizarse
    public void destruccionHorrocruxes(int horrocrux){
        if(horrocrux < this.horrocrux)
            throw new IllegalArgumentException("Error en los datos introducidos, no se pueden revivir horrocruxes");
        this.horrocrux = horrocrux;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public int getEdad(){
        return edad;
    }
    public int getHorrocrux(){
        return horrocrux;
    }
    public Categoria getCategoria(){
        return categoria;
    }
    public String getStringCategoria(){
        return CatToString(categoria);
    }
    public float getRecompensa(){
        return categoria.getBono()*(float)horrocrux;
    }
}
