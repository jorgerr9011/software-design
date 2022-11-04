package e2;

public class Slopes {

    private static boolean bajadaLegal (char [][] slopeMap, int right, int down){
        int l, a;
        if((right < 1) || (right >= slopeMap[0].length)
                || (down < 1) || (down >= slopeMap.length))
            return false;
        for(l = 0; l < slopeMap.length; l++){
            if(slopeMap.length != slopeMap[l].length)
                return false;
            for(a = 0; a < slopeMap[0].length; a++){
                if((slopeMap[l][a] != '.') &&(slopeMap[l][a] != '#'))
                    return false;
            }
        }
        return true;
    }
    /**
     * Traverses the slope map making the right and down movements and
     * returns the number of trees found along the way .
     * @param slopeMap A square matrix representing the slope with spaces
     * represented as "." and trees represented as "#".
     * @param right Movement to the right
     * @param down Downward movement
     * @return Number of trees
     * @throws IllegalArgumentException if the matrix is incorrect because :
     * - It is not square .
     * - It has characters other than "." and "#"
     * - right >= number of columns or right < 1
     * - down >= number of rows of the matrix or down < 1
     */
    public static int downTheSlope ( char [][] slopeMap , int right , int down ) {
        int posL = 0, posA = 0, choques = 0, aux;
        boolean keepGoing = true;
        if(!bajadaLegal(slopeMap, right, down))
            throw new IllegalArgumentException();
        while(keepGoing){
            for(aux = 0; aux < right; aux++){
                posA++;
                if(posA >= slopeMap[0].length)
                    posA -= slopeMap[0].length;
                if(slopeMap[posL][posA] == '#')
                    choques++;
            }

            if(posL >= slopeMap.length-1)
                keepGoing = false;

            for(aux = 0; aux < down; aux++){
                posL++;
                if(posL >= slopeMap.length){
                    posL = slopeMap.length-1;
                    if(slopeMap[posL][posA] == '#')
                        return choques;
                }
                if(slopeMap[posL][posA] == '#')
                    choques++;
            }
        }
        return choques;
    }
    /**
     * Traverses the slope map making the right and down movements and
     * returns the number of trees found along the way .
     * Since it " jumps " from the initial position to the final position ,
     * only takes into account the trees on those initial / final positions .
     *
     * Params , return value and thrown expections as in downTheSlope ...
     */
    public static int jumpTheSlope ( char [][] slopeMap , int right , int down ) {
        int posL = 0, posA = 0, choques = 0;
        if(!bajadaLegal(slopeMap, right, down))
            throw new IllegalArgumentException();
        while(posL < slopeMap.length-1){
            posA += right;
            if(posA >= slopeMap[0].length)
                posA -= slopeMap[0].length;

            posL += down;
            if(posL >= slopeMap.length){
              posL = slopeMap.length-1;
              if(slopeMap[posL][posA] == '#')
                  return choques;
            }
            if(slopeMap[posL][posA] == '#')
                choques++;
        }
        return choques;
    }
}
