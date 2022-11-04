package e3;

import java.util.ArrayList;
import java.util.List;

public class Lists implements NetworkManager{

    List<String> users = new ArrayList<>();
    List<List<TopicOfInterest>> TemasInteres = new ArrayList<>();
    

    public void addUser(String user, List<TopicOfInterest> topicsOfInterest){
        int i;

        if(users.size() != 0){
            for (i = 0; i < users.size() && !users.get(i).equals(user); i++) ;

            users.add(i, user);
            TemasInteres.add(i, topicsOfInterest);
        }else
            users.add(user);
            TemasInteres.add(topicsOfInterest);
    }

    public void removeUser(String user){
        int i, j;

        for(i = 0; i < users.size() && !users.get(i).equals(user); i++);

        if(users.get(i).equals(user)){
            users.remove(i);
        }
        TemasInteres.remove(i);
    }

    public void addInterest(String user, TopicOfInterest topicOfInterest){
        int i, j, aux = 0;
        
        for(i = 0; i < users.size() && !users.get(i).equals(user); i++);
        if(users.get(i).equals(user)){
             aux = i;
        }
        if(TemasInteres.get(aux).size() == 0) {
            List<TopicOfInterest> listaAux = new ArrayList<>();
            listaAux.add(topicOfInterest);
            TemasInteres.add(i, listaAux);
        }else{
            for(j = 0; j < TemasInteres.get(aux).size(); j++);
            TemasInteres.get(aux).add(j, topicOfInterest);
        }
    }

    public void removeInterest(String user, TopicOfInterest topicOfInterest){
        int i, j, aux = 0;

        for(i = 0; i < users.size() && !users.get(i).equals(user); i++);
        if(users.get(i).equals(user)){
            aux = i;
        }
        for(j = 0; j < TemasInteres.get(aux).size() && !(TemasInteres.get(aux).get(j) == topicOfInterest); j++);
        if(TemasInteres.get(aux).get(j) == topicOfInterest){
            TemasInteres.get(aux).remove(j);
        }
    }

    public List<String> getUsers(){
        return this.users;
    }

    public String getUser(int index){
        return users.get(index);
    }

    public List<TopicOfInterest> getInterests(){
        return TopicOfInterest.getIntereses();
    }

    public List<TopicOfInterest> getInterestsUser(String user){
        int i, j;

        for(i = 0; i < users.size() && !users.get(i).equals(user); i++);

        return TemasInteres.get(i);
    }
}
