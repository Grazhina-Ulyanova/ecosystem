import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Herbivore extends Animal{
    public Herbivore(TypeForAnimals type) {
        super(type);
    }

    public Herbivore(TypeForAnimals type, int levelOfHealth) {
        super(type);
        this.levelOfHealth = levelOfHealth;
        this.startLevelOfHealth = levelOfHealth;
    }

    @Override
    public String eat(List<LivingBeings> livingBeings) {


        Iterator<LivingBeings> iterator = livingBeings.iterator();
        while (iterator.hasNext()) {
            Object objectPlant = iterator.next();

            if (objectPlant instanceof Plant) {
                Plant plant = (Plant) objectPlant;

                for (Object o : type.getFoodList()) {
                    if (o instanceof PlantType && plant.getTypeForPlants().getPlantType().equals(o)) {
                        iterator.remove();
                        isTryEat = true;
                        levelOfHealth = Math.min(startLevelOfHealth,levelOfHealth+1);
                        return "Hunter luck. Health: " + levelOfHealth + ". " + type.getTypeName() + " ate " + plant.getTypeForPlants().getTypeName() + "\n";
                    }
                }
            }
        }
        levelOfHealth-=1;
        isTryEat = true;
        return type.getTypeName() + " didn't eat today. "+ "Health: " +levelOfHealth + "\n";

    }




}
