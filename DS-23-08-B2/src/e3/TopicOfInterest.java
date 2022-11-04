package e3;

import java.util.ArrayList;
import java.util.List;

public enum TopicOfInterest {
    DEPORTES("deportes"),
    NOTICIAS("noticias"),
    EVENTOS("eventos"),
    INFORMACION("info");

    public static List<TopicOfInterest> getIntereses(){
        List<TopicOfInterest> a = new ArrayList<>();
        a.add(DEPORTES);
        a.add(NOTICIAS);
        a.add(EVENTOS);
        a.add(INFORMACION);
        return a;
    }

    TopicOfInterest(String valor){
    }
}
