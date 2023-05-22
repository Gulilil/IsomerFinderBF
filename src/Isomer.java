import java.util.Scanner;

public class Isomer {
    private static int isomerCount;
    private static int iterationCount;

    public static void isomerCountIncrement(){
        isomerCount++;
    }

    public static int getIsomerCount(){
        return isomerCount;
    }

    public static void iterationCountIncrement(){
        iterationCount++;
    }
    
    public static int getIterationCount(){
        return iterationCount;
    }

    public static void recursiveLoop(IsomerContainer ic, Map m, int r, int c, int maxRow, int maxCol){
        // System.out.println(r+" "+c);
        if (r == 0 && c == 0){
            // Special Case for Methane
            if (maxCol == 0 && maxRow == 0){
                m.setIntToMap(4, maxRow, maxCol);
            }
            // Handle case for Small n
            if (r == maxRow/2){
                m.setIntToMap(3, r, c);
            }
            // Check if it is an Isomer
            if (m.isAnIsomer(maxCol+1)){
                // Prevent double output
                if (!ic.isMapAlreadyInContainer(m)){
                    ic.addMap(m);
                    m.displayMap();
                    isomerCountIncrement();
                }
            }
            iterationCount++;
        }
        // Rekurens
        else {
            System.out.println(r+" "+ c);
            m.displayMap();

            if (c > 0){
                if (m.isTileConnectedFromStart(r, c, maxRow/2, 0)){
                    for (int itr = -1; itr < 4; itr++){
                        m.setIntToMap(itr, r, c);
                        recursiveLoop(ic, m, r, c-1,maxRow,  maxCol);
                    }
                }
            }
            else {
                if (r == maxRow/2){
                    m.setIntToMap(3, r, c);
                } else {
                    m.setIntToMap(-1, r, c);
                }
                if (m.isTileConnectedFromStart(r, c, maxRow/2, 0)){
                    recursiveLoop(ic, m, r-1, maxCol, maxRow, maxCol);
                }
            }
            
        }
    }

    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);

        // Get User Input
        System.out.print("Insert the amount of carbon atoms in the Alkane Compound: ");
        int n = scanner.nextInt();
        int col = n;
        int row = n/2 + 1;
        scanner.close();

        System.out.println("===============================");
        System.out.println("===============================");
        System.out.println(" The Alkane Compound is C"+n+"H"+(2*n+2));
        System.out.println("===============================");
        System.out.println("===============================");

        // Setup
        Map m = new Map(row, col);
        IsomerContainer ic = new IsomerContainer();

        // Start the timer
        long startTime = System.nanoTime();

        // Looping
        for(int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                recursiveLoop(ic, m, i, j, row-1, col-1);
                m.resetMap();
            }
        }    

        // Stop the timer
        long stopTime = System.nanoTime();

        // Display Result
        // ic.displayAllMap();

        System.out.println("===============================");
        System.out.println("===============================");
        System.out.println("     "+getIsomerCount()+" Isomers found");
        System.out.println("===============================");
        System.out.println("===============================");
        System.out.println("Execution Time: " + (stopTime-startTime)/1000000 + "ms");
        System.out.println("Iteration Count: " + getIterationCount());
    }
}