import java.io.Serializable;
import java.util.List;

public class TypeForAnimals implements Serializable {
    private String type;
    private List<Object> foodList;

    public TypeForAnimals(String type, List<Object> foodList) {
        this.type = type;
        this.foodList = foodList;
    }

    public String getTypeName() {
        return type;
    }

    public void setTypeName(String type) {
        this.type = type;
    }

    public List<Object> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Object> foodList) {
        this.foodList = foodList;
    }

    @Override
    public String toString() {
        return "TypeForAnimals{" +
                "type='" + type + '\'' +
                ", foodList="  +
                '}';
    }
}
