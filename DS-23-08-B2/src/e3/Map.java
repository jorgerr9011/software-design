package e3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Map implements NetworkManager{

    LinkedHashMap<String, List<TopicOfInterest>> c = new LinkedHashMap<>();

    public void addUser(String user, List<TopicOfInterest> topicsOfInterest){
        c.putIfAbsent(user, topicsOfInterest);
    }

    public void removeUser(String user){
        c.remove(user, c.get(user));
    }

    public void addInterest(String user, TopicOfInterest topicOfInterest){
        List<TopicOfInterest> aux;
        aux = c.get(user);
        aux.add(topicOfInterest);

        c.put(user, aux);
    }

    public void removeInterest(String user, TopicOfInterest topicOfInterest){
        List<TopicOfInterest> aux;
        aux = c.get(user);
        aux.remove(topicOfInterest);

        c.put(user, aux);
    }

    public List<String> getUsers(){
        return new ArrayList<>(c.keySet());
    }

    public List<TopicOfInterest> getInterests(){
        return TopicOfInterest.getIntereses();
    }

    public List<TopicOfInterest> getInterestsUser(String user){
        return c.get(user);
    }

    public String getUser(int index){
        int i = 0;
        String aux = null;

        for(String user : c.keySet()){
            if(index == i){
                aux = user;
            }
            i++;
        }
        return aux;
    }
}
