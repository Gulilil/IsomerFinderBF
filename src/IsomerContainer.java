import java.util.*;

public class IsomerContainer {
    private List<Map> container;

    public IsomerContainer(){
        container = new ArrayList<>();
    }

    public void addMap(Map m){
        Map temp = new Map(m.getRow(), m.getCol());
        temp.copyFrom(m);
        container.add(temp);
    }

    public boolean isMapAlreadyInContainer(Map m){
        for (Map map : container){
            if (map.isSameMap(m)){
                return true;
            }
        }
        return false;
    }

    public void displayAllMap(){
        for (Map m: container){
            m.displayMap();
        }
    }

    
}
