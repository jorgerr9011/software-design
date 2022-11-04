package e3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Melody {

    public enum Notes{
        DO(1),
        RE(3),
        MI(5),
        FA(6),
        SOL(8),
        LA(10),
        SI(12);

        private final int numAsociado;

        Notes(int numero){
            this.numAsociado = numero;
        }
        public int getNumAsociado(){
            return numAsociado;
        }
    }

    public enum Accidentals{
        NATURAL(1),
        FLAT(2),
        SHARP(3);

        private final int numAsociado;

        Accidentals(int numero){
            this.numAsociado = numero;
        }

        public int getNumAsociado() {
            return numAsociado;
        }
    }

    final List<String> lista;
    /**
     * Creates an empty Melody instance .
     */
    public Melody () {
        this.lista = new ArrayList<>();
    }

    private String whichNote(Notes note){
        return switch (note) {
            case DO -> "DO";
            case RE -> "RE";
            case MI -> "MI";
            case FA -> "FA";
            case SOL -> "SOL";
            case LA -> "LA";
            case SI -> "SI";
        };
    }
    private Notes whichNote(String s){
        return switch (s){
            case "DO" -> Notes.DO;
            case "RE" -> Notes.RE;
            case "MI" -> Notes.MI;
            case "FA" -> Notes.FA;
            case "SOL" -> Notes.SOL;
            case "LA" -> Notes.LA;
            case "SI" -> Notes.SI;
            default -> throw new IllegalArgumentException();
        };
    }
    private String whichAccidental(Accidentals accidental){
        return switch (accidental) {
            case FLAT -> "b";
            case SHARP -> "#";
            case NATURAL -> "";
        };
    }
    private Accidentals whichAccidental(String s){
        return switch (s) {
            case "b" -> Accidentals.FLAT;
            case "#" -> Accidentals.SHARP;
            case "" -> Accidentals.NATURAL;
            default -> throw new IllegalArgumentException();
        };
    }
    private String getMelodyPart (String s, int parte){
        String[] partes;
        String accidental;
        if(s.split("#").length > 1){
            partes = s.split("#");
            accidental = "#";
        }
        else if(s.split("b").length > 1){
            partes = s.split("b");
            accidental = "b";
        }
        else{
            partes = s.split(Pattern.quote("("));
            accidental = "";
        }
        if(parte == 1)
            return partes[0];
        else if(parte ==2)
            return accidental;
        else
            throw new IllegalArgumentException();
    }
    private float getMelodyPart (String s){
        StringBuilder tiempo = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if((c > 47 && c < 58) || c == 46)
                tiempo.append(c);
        }
        return Float.parseFloat(tiempo.toString());
    }
    private boolean isValidPos (int index){
        return (index >= 0) && (index < this.size());
    }
    /**
     * Add a note at the end of this melody .
     * @param note The note to add
     * @param accidental The accidental of the note
     * @param time The duration of the note in milliseconds
     * @throws IllegalArgumentException if the note , the accidental
     * or the time are not valid values .
     */
    public void addNote (Notes note , Accidentals accidental , float time ) {
        if((note == null) ||(accidental == null)){
            throw new IllegalArgumentException();
        }
        else if((note.getNumAsociado() < 1) || (note.getNumAsociado() > 12) || (accidental.getNumAsociado() < 1)
                || (accidental.getNumAsociado() > 3) || (time <= 0))
            throw new IllegalArgumentException();
        String nota = whichNote(note) + whichAccidental(accidental) + "(" + time + ")";
        lista.add(nota);
    }
    /**
     * Returns the note on the given position
     * @param index The position of the note to get .
     * @return The note on index .
     * @throws IllegalArgumentException if the index is not a valid position .
     */
    public Notes getNote ( int index ) {
        if(isValidPos(index)){
            String nota = lista.get(index);
            return whichNote(getMelodyPart(nota, 1));
        }
        else throw new IllegalArgumentException();
    }
    /**
     * Returns the accidental of the note on the given position
     * @param index The position of the accidental to get .
     * @return The accidental on index .
     * @throws IllegalArgumentException if the index is not a valid position .
     */
    public Accidentals getAccidental (int index ) {
        if(isValidPos(index)){
            String accidental = lista.get(index);
            return whichAccidental(getMelodyPart(accidental, 2));
        }
        else throw new IllegalArgumentException();
    }
    /**
     * Returns the duration of the note on the given position
     * @param index The position of the time to get .
     * @return The time on index .
     * @throws IllegalArgumentException if the index is not a valid position .
     */
    public float getTime ( int index ) {
        if(isValidPos(index)){
            String time = lista.get(index);
            return getMelodyPart(time);
        }
        else throw new IllegalArgumentException();
    }
    /**
     * Returns the number of notes in this melody .
     * @return The number of notes in this melody .
     */
    public int size () {
        return lista.size();
    }
    /**
     * Returns the duration of this melody .
     * @return The duration of this melody in milliseconds .
     */
    public float getDuration () {
        float duration = 0;
        for(int i = 0; i < this.size(); i++){
            duration += this.getTime(i);
        }
        return duration;
    }
    /**
     * Performs the equality tests of the current melody with another melody
     * passed as a parameter . Two melodies are equal if they represent the same
     * music fragment regardless of the name of its notes .
     * @param o The melody to be compared with the current melody
     * @return true if the melodies are equals , false otherwise .
     */
    @Override
    public boolean equals ( Object o) {
        if(this == o)
            return true;
        if(o == null)
            return false;
        if(this.getClass() != o.getClass())
            return false;
        Melody melody = (Melody) o;
        if((melody.size() != this.size()) || (melody.getDuration() != this.getDuration()))
            return false;
        for(int i = 0; i < melody.size(); i++){
            if(melody.getTime(i) != this.getTime(i))
                return false;
            Notes note1 = melody.getNote(i);
            Notes note2 = this.getNote(i);
            Accidentals a1 = melody.getAccidental(i);
            Accidentals a2 = this.getAccidental(i);
            if(note1 != note2){
                if(!CompareNotes.equalNotes(note1, note2, a1, a2))
                    return false;
            }
            else
                if(a1 != a2)
                    return false;
        }
        return true;
    }
    /**
     * Returns an integer that is a hash code representation of the melody .
     * Two melodies m1 , m2 that are equals (m1. equals (m2) == true ) must
     * have the same hash code .
     * @return The hash code of this melody .
     */
    @Override
    public int hashCode () {
        int result = 0, accidental, nota;

        for(int i = 0; i < this.size(); i++){
            nota = this.getNote(i).getNumAsociado();
            accidental = this.getAccidental(i).getNumAsociado();
            if(accidental == 2)
                if(nota == 1)
                    nota = 12;
                else
                    nota -= 1;
            else if(accidental == 3)
                if(nota == 12)
                    nota = 1;
                else
                    nota += 1;
            else if(accidental != 1)
                throw new IllegalArgumentException();
            result = 31 * result + nota;
        }

        return result;
    }
    /**
     * The string representation of this melody .
     * @return The String representantion of this melody .
     */
    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.size(); i++){
            if(i != this.size() - 1)
                sb.append(lista.get(i)).append(" ");
            else
                sb.append(lista.get(i));
        }
        return sb.toString();
    }
}
