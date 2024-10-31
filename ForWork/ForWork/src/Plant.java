public class Plant extends Species{
    private PlantType plantType;
    private int levelOfHealth;
    private int levelOfWater;


    public Plant(String nameOfType, PlantType plantType) {
        super(nameOfType);
        this.plantType = plantType;
    }

    public int getLevelOfHealth() {
        return levelOfHealth;
    }

    public void setLevelOfHealth(int levelOfHealth) {
        this.levelOfHealth = levelOfHealth;
    }

    public int getLevelOfWater() {
        return levelOfWater;
    }

    public void setLevelOfWater(int levelOfWater) {
        this.levelOfWater = levelOfWater;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }

//    public void eat(int water){
//
//    }
}
