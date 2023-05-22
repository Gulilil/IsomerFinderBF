import java.util.Scanner;

public class Isomer {
    private static int isomerCount;

    public static void countIncrement(){
        isomerCount++;
    }

    public static int getIsomerCount(){
        return isomerCount;
    }

    public static void recursiveLoop(Map m, int r, int c, int maxRow, int maxCol){
        if (r == 0 && c == 0){
            for (int k = 0; k < 4; k++){
                m.setIntToMap(k, r, c);

                if (m.isAnIsomer(maxCol+1)){
                    m.displayMap();
                    countIncrement();
                }
            }
        }
        else {
            // Rekurens
            for (int itr = -1; itr < 4; itr++){
                m.setIntToMap(itr, r, c);
                if (c > 0){
                    recursiveLoop(m, r, c-1,maxRow,  maxCol);
                } else {
                    recursiveLoop(m, r-1, maxCol, maxRow, maxCol);
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

        Map m = new Map(row, col);

        for(int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                recursiveLoop(m, i, j, row-1, col-1);
                m.resetMap();
            }
        }    
        System.out.println("===============================");
        System.out.println("===============================");
        System.out.println("     "+getIsomerCount()+" Isomers found");
        System.out.println("===============================");
        System.out.println("===============================");
    }
}