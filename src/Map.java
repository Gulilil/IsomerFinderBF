
public class Map {
    private int[][] m;

    public Map(int row, int col){
        this.m = new int[row][col];
        for (int i = 0 ; i < row; i++){
            for (int j = 0; j < col; j++){
                m[i][j] = -1;
            }
        }
    }
    
    public int getRow(){
        return m.length;
    }

    public int getCol(){
        return m[0].length;
    }

    public void setIntToMap(int n, int r, int c){
        m[r][c] = n;
    }

    public int getIntFromMap(int r, int c){
        return m[r][c];
    }

    public void displayMap(){
        for (int i = 0; i < getRow(); i++){
            for (int j = 0; j < getCol(); j++){
                if (j == 0){
                    System.out.print("[ ");
                }

                int x = getIntFromMap(i, j);
                String c = " ";
                if (x != -1){
                    c = String.valueOf(x);
                }

                if (j == getCol()-1){
                    System.out.println(c+" ]");
                }else {
                    System.out.print(c+", ");
                }
            }
        }
        System.out.println();
    }

    public void resetMap(){
        for (int i = 0 ; i < getRow(); i++){
            for (int j = 0; j < getCol(); j++){
                m[i][j] = -1;
            }
        }
    }

    public boolean isValidCarbon(int i, int j){
        int count = 4;
        if (i != 0 && getIntFromMap(i-1, j) != -1){
            count--;
        }
        if (i != getRow()-1 && getIntFromMap(i+1, j) != -1){
            count--;
        }
        if (j != 0 && getIntFromMap(i, j-1) != -1){
            count--;
        }
        if (j != getCol()-1 && getIntFromMap(i, j+1) != -1){
            count--;
        }
        return (count == getIntFromMap(i, j));
    }

    public boolean isAnIsomer(int n){
        int carbonCount = 0;
        int hydrogenCount = 0;
        for(int i = 0; i < getRow(); i++){
            for(int j = 0; j < getCol(); j++){
                if (m[i][j] != -1){
                    if (isValidCarbon(i, j)){
                        carbonCount++;
                        hydrogenCount += m[i][j];
                    }
                    else {
                        return false;
                    }
                    
                }
            }
        }
        return (carbonCount == n && hydrogenCount == (2*n+2));
    }

    public void copyFrom(Map map){
        for (int i = 0; i < getRow(); i++){
            for(int j = 0; j < getCol(); j++){
                m[i][j] = map.getIntFromMap(i, j);
            }
        }
    }

    public boolean isSameMap(Map map){
        for (int i = 0; i < getRow(); i++){
            for(int j = 0; j < getCol(); j++){
                if (m[i][j] != map.getIntFromMap(i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTileConnectedFromStart(int r, int c, int startR, int startC){
        if (r == startR && c == startC+1){
            return true;
        }
        if (c != 0 && m[r][c-1] != -1){
            isTileConnectedFromStart(r, c-1, startR, startC);
        }
        if (r != 0 && m[r-1][c] != -1){
            isTileConnectedFromStart(r-1, c, startR, startC);
        }
        if (c != getCol()-1 && m[r][c+1] != -1){
            isTileConnectedFromStart(r, c+1, startR, startC);
        }
        if (r != getRow()-1 && m[r+1][c] != -1){
            isTileConnectedFromStart(r+1, c, startR, startC);
        }
        return false;

    }
}
