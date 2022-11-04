package e1;

public class StringCount {


    public static int countWords(String text){
        if(text != null) {
            int count = 0, n = 0, cnt = 0, cnt1 = 0;
            String[] aux = text.split(" ", 0), aux2, aux3 = new String[20];

            for(String j : aux){
                if(!j.equals("")){ //JAVA guarda el espacio despues del split como una cadena vacía
                    count++;
                }
            }
            return count;

        }else
            return 0;
    }

    public static int countChar(String text, char c) {
        if (text != null) {
            int count = 0;
            String[] aux = text.split(" ");

            for (String s : aux) {
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == c) {
                        count++;
                    }
                }
            }
            return count;
        }else
            return 0;
    }

    public static int countCharIgnoringCase(String text, char c){
        if(text != null) {
            int count = 0;
            char auxiliar = Character.toLowerCase(c);
            String[] aux = text.toLowerCase().split(" ");

            for (String s : aux) {
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == auxiliar) {
                        count++;
                    }
                }
            }
            return count;
        }else
            return 0;
    }

    public static boolean Is_special(char s){
        return s != '?' && s != '@' && s != '#' && s != '$' && s != '.' && s != ',';
    }
    public static boolean Is_digit(char s){
        return s != '0' && s != '1' && s != '2' && s != '3' && s != '4' && s != '5' && s != '6' && s != '7' &&
                s != '8' && s != '9';
    }

    public static boolean isPasswordSafe(String password){
        int upper = 0, lower = 0, digit = 0, special = 0, aux = 0;

        if(password != null && password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) != '\'' && Is_special(password.charAt(i)) && password.charAt(i) != ' '
                        && password.charAt(i) == password.toLowerCase().charAt(i)) {
                    lower++;
                }
                if (password.charAt(i) != '\'' && Is_special(password.charAt(i)) && password.charAt(i) != ' '
                        && password.charAt(i) == password.toUpperCase().charAt(i)) {
                    upper++;
                }
                if(!Is_digit(password.charAt(i))){
                    digit++;
                }

                if (!Is_special(password.charAt(i))) {
                    special++;
                }
            }
            return (lower > 0 && upper > 0 && digit > 0 && special > 0);
        }else
            return false;
    }
}
