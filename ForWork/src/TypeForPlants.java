import java.io.Serializable;

public class TypeForPlants implements Serializable {

    private String type;
    private PlantType plantType;

    public TypeForPlants(String type, PlantType plantType) {
        this.type = type;
        this.plantType = plantType;
    }

    public String getTypeName() {
        return type;
    }

    public void setTypeName(String type) {
        this.type = type;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }
}
