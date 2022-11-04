package e3;

import java.util.ArrayList;
import java.util.List;

public class Network {

    private static Object Lists;
    NetworkManager metodo;

    public Network(NetworkManager metodo){
        this.metodo = metodo;
    }

    //Dar de alta y de baja a los usuarios de la red.
    public void darAlta(String user, List<TopicOfInterest> topicsOfInterest){
        metodo.addUser(user, topicsOfInterest);
    }

    //Dar de alta y de baja a los usuarios de la red.
    public void darBaja(String user){
        metodo.removeUser(user);
    }

    //Modificar los temas que interesan a un determinado usuario.
    //PARAMETROS modificar = "añadir" o "eliminar"
    public List<TopicOfInterest> modificar_intereses(String modificar, String user, TopicOfInterest topicOfInterest){
        if(modificar.equals("añadir")){
            metodo.addInterest(user, topicOfInterest);
        }else
            metodo.removeInterest(user, topicOfInterest);

        return ObtenerTemasUsuario(user);
    }

    //Obtener los temas que le interesan a un determinado usuario
    public List<TopicOfInterest> ObtenerTemasUsuario(String user){
        return metodo.getInterestsUser(user);
    }

    //Encontrar a todos los usuarios interesados en un tema especifico.
    public List<String> UsuariosInteresesIguales(TopicOfInterest topicOfInterest){
        int i, j;
        List<String> usuarios;
        List<TopicOfInterest> intereses = new ArrayList<>();
        usuarios = metodo.getUsers();

        List<String> resultado = new ArrayList<>();

        for(i = 0; i < usuarios.size(); i++){
            intereses = metodo.getInterestsUser(usuarios.get(i));
            for(j = 0; j < intereses.size(); j++){
                if(intereses.get(j).equals(topicOfInterest)){
                    resultado.add(usuarios.get(i));
                }
            }
        }
        return resultado;
    }

    //Buscar los temas de interes que tienen en comun dos usuarios.
    public List<TopicOfInterest> Comun2Users(String usuario1, String usuario2){
        int i, j;

        List<TopicOfInterest> user1;
        List<TopicOfInterest> user2;
        user1 = metodo.getInterestsUser(usuario1);
        user2 = metodo.getInterestsUser(usuario2);

        List<TopicOfInterest> aux = new ArrayList<>();

        for(i = 0; i < user1.size(); i++){
            for(j = 0; j < user2.size(); j++){
                if(user1.get(i).equals(user2.get(j))){
                    aux.add(user1.get(i));
                }
            }
        }
        return aux;
    }

    //Obtener la lista de todos los temas que interesan a los usuarios de la red.
    public List<TopicOfInterest> TodosIntereses(){
         return metodo.getInterests();
    }

    //Ver la informacion de todos los usuarios de la red, es decir, sus nombres de
    //usuario junto con su correspondiente lista de temas de interes.
    public String MostrarListas(){
        int i;
        String resultado = "";
        for(i = 0; i < metodo.getUsers().size(); i++){
            resultado = resultado.concat(metodo.getUser(i) + " "+ metodo.getInterestsUser(metodo.getUser(i))+"\n");
        }
        return resultado;
    }
}
