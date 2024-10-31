import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Plant extends LivingBeings{
    private TypeForPlants typeForPlants;
    private int startLevelOfHealth;
    private int quantityOfWater;

    public int getQuantityOfWater() {
        return quantityOfWater;
    }

    public void setQuantityOfWater(int quantityOfWater) {
        this.quantityOfWater = quantityOfWater;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return levelOfHealth == plant.levelOfHealth && quantityOfWater == plant.quantityOfWater && Objects.equals(typeForPlants, plant.typeForPlants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeForPlants, levelOfHealth, quantityOfWater);
    }

    @Override
    public String toString() {
        return "Plant{" +
                "typeForPlants=" + typeForPlants.getTypeName() +
                ", categoryForPlants=" + typeForPlants.getPlantType() +
                ", levelOfHealth=" + levelOfHealth +
                ", quantityOfWater=" + quantityOfWater +
                '}';
    }


    public Plant(TypeForPlants type) {
        this.typeForPlants=type;
    }

    public Plant(TypeForPlants type, int levelOfHealth) {
        this.typeForPlants=type;
        this.levelOfHealth=levelOfHealth;
        this.startLevelOfHealth=levelOfHealth;
    }

    public Plant(TypeForPlants type, int levelOfHealth, int quantityOfWater) {
        this.typeForPlants=type;
        this.levelOfHealth=levelOfHealth;
        this.startLevelOfHealth=levelOfHealth;
        this.quantityOfWater = quantityOfWater;
    }


    public int getLevelOfHealth() {
        return levelOfHealth;
    }

    public void setLevelOfHealth(int levelOfHealth) {
        this.levelOfHealth = levelOfHealth;
    }

    public int getStartLevelOfHealth() {
        return startLevelOfHealth;
    }

    public void setStartLevelOfHealth(int startLevelOfHealth) {
        this.startLevelOfHealth = startLevelOfHealth;
    }

    public TypeForPlants getTypeForPlants() {
        return typeForPlants;
    }

    public void setTypeForPlants(TypeForPlants typeForPlants) {
        this.typeForPlants = typeForPlants;
    }


    public String drink(Enviroment enviroment){
        if(enviroment.getWaterAvailability()>quantityOfWater){
            enviroment.setWaterAvailability(enviroment.getWaterAvailability() - quantityOfWater);
            isTryEat=true;
            levelOfHealth = Math.min(startLevelOfHealth, levelOfHealth+1);
            return typeForPlants.getTypeName() + " drink enough. It's health didn't change"  + "\n";
        }
        else {
            levelOfHealth -= enviroment.getWaterAvailability()>=0? 1: 2;
            enviroment.setWaterAvailability(Math.max(0,enviroment.getWaterAvailability() - quantityOfWater));
            isTryEat=true;
            return typeForPlants.getTypeName() + " didn't drink enough. Health: " + levelOfHealth + "\n";
        }
    }

    public Optional<String> reproduction(List<LivingBeings> livingBeings, Enviroment enviroment, List<LivingBeings> listToAddAfter){
        Optional<String> optionalS = Optional.empty();
        if(levelOfHealth>(startLevelOfHealth*0.8)){
            int amount = calculateAmountPlants(enviroment);
            for (int i = 0; i < amount; i++) {
                Plant plant = new Plant(typeForPlants, startLevelOfHealth);
                listToAddAfter.add(plant);
                isTryToReproduce = true;
            }

            optionalS = Optional.of("We have a new addition! " + amount +" new "+ typeForPlants.getTypeName() + "\n");
        }

        isTryToReproduce = true;

        return optionalS;
    }

    private int calculateAmountPlants(Enviroment enviroment){
        if(enviroment.getHumidity()<=40){
            return (enviroment.getTemperature()>=25) ? 0:1;
        }
        if(enviroment.getHumidity()>=80){
            return (enviroment.getTemperature()<=20) ? 4:3;
        }
        return 2;
    }

}
