import java.util.List;

public class Animal extends Species{

    @Override
    public String toString() {
        return "Animal{" +
                "animalType=" + animalType +
                ", levelOfFood=" + levelOfFood +
                ", listOfAnimals=" + listOfAnimals +
                ", listOfPlans=" + listOfPlans +
                ", type=" + type +
                '}';
    }

    private AnimalType animalType;
    private int levelOfFood;

    private List<String> listOfAnimals;//для хищников
    private List<PlantType> listOfPlans;//для травоядных

    public List<String> getListOfAnimals() {
        return listOfAnimals;
    }

    public void setListOfAnimals(List<String> listOfAnimals) {
        this.listOfAnimals = listOfAnimals;
    }

    public Animal(String nameOfType) {
        super(nameOfType);
    }

    public List<PlantType> getListOfPlans() {
        return listOfPlans;
    }

    public void setListOfPlans(List<PlantType> listOfPlans) {
        this.listOfPlans = listOfPlans;
    }

    public Animal(AnimalType animalType, String nameOfType) {
        super(nameOfType);
        this.animalType = animalType;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public int getLevelOfFood() {
        return levelOfFood;
    }

    public void setLevelOfFood(int levelOfFood) {
        this.levelOfFood = levelOfFood;
    }

    public void eat(){

    }

}



