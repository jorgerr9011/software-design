package e3;

public class CompareNotes {
    public static boolean equalNotes(Melody.Notes n1, Melody.Notes n2, Melody.Accidentals a1, Melody.Accidentals a2){

        if(n1.getNumAsociado() == n2.getNumAsociado() - 2)
            return a1.getNumAsociado() == 3 && a2.getNumAsociado() == 2;

        else if(n1.getNumAsociado() == n2.getNumAsociado() - 1){
            if((a1.getNumAsociado() == 3) && (a2.getNumAsociado() == 1))
                return true;
            else return (a1.getNumAsociado() == 1) && (a2.getNumAsociado() == 2);
        }

        else if((n1.getNumAsociado() == 1) && (n2.getNumAsociado() == 12)){
            if((a1.getNumAsociado() == 2) && (a2.getNumAsociado() == 1))
                return true;
            else return (a1.getNumAsociado() == 1) && (a2.getNumAsociado() == 3);
        }

        else if(n2.getNumAsociado() == n1.getNumAsociado() - 2)
            return a2.getNumAsociado() == 3 && a1.getNumAsociado() == 2;

        else if(n2.getNumAsociado() == n1.getNumAsociado() - 1){
            if((a2.getNumAsociado() == 3) && (a1.getNumAsociado() == 1))
                return true;
            else return (a2.getNumAsociado() == 1) && (a1.getNumAsociado() == 2);
        }

        else if((n2.getNumAsociado() == 1) && (n1.getNumAsociado() == 12)){
            if((a2.getNumAsociado() == 2) && (a1.getNumAsociado() == 1))
                return true;
            else return (a2.getNumAsociado() == 1) && (a1.getNumAsociado() == 3);
        }

        else
            return false;
    }
}
