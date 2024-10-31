import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Carnivore extends Animal{




    public Carnivore(TypeForAnimals type, int levelOfHealth) {
        super(type);
        this.levelOfHealth = levelOfHealth;
        this.startLevelOfHealth=levelOfHealth;
    }

    public Carnivore(TypeForAnimals type) {
        super(type);
    }

    @Override
    public String eat(List<LivingBeings> livingBeings) {

        if(isHunterLucky()){

            Iterator<LivingBeings> iterator = livingBeings.iterator();

            while (iterator.hasNext()) {
                Object objectAnimal = iterator.next();

                if (objectAnimal instanceof Animal) {
                    Animal animal = (Animal) objectAnimal;

                    for (Object o : type.getFoodList()) {
                        if (o instanceof String && animal.getType().getTypeName().equals(o)) {
                            iterator.remove();
                            isTryEat = true;
                            levelOfHealth = Math.min(startLevelOfHealth,levelOfHealth+1);
                            return "Hunter luck. Health: " + levelOfHealth + ". " + type.getTypeName() + " ate " + animal.getType().getTypeName() + "\n";
                        }
                    }
                }
            }


        }

        levelOfHealth-= 1;
        isTryEat = true;
        return "Hunter is not luck. " + type.getTypeName() + " didn't eat today. "+ "Health: " +levelOfHealth + "\n";

    }

    public boolean isHunterLucky(){
        int number = random.nextInt(100) + 1;
        return number < 70;
    }


}
