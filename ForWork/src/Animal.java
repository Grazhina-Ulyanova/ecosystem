import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Animal extends LivingBeings{
    protected int startLevelOfHealth;

    protected TypeForAnimals type;

    protected final Random random = new Random();


    @Override
    public String toString() {
        return "Animal{" +
                "level Of Food=" + levelOfHealth +
                ", type=" + type.getTypeName() +
                ", List of food=" + type.getFoodList() +
                '}';
    }

    public Animal(TypeForAnimals type) {
        this.type = type;
    }

    public TypeForAnimals getType() {
        return type;
    }

    public void setType(TypeForAnimals type) {
        this.type = type;
    }

    public int getLevelOfHealth() {
        return levelOfHealth;
    }

    public void setLevelOfHealth(int levelOfHealth) {
        this.levelOfHealth = levelOfHealth;
    }

    abstract public String eat(List<LivingBeings> livingBeings);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return levelOfHealth == animal.levelOfHealth && Objects.equals(type, animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelOfHealth, type);
    }

    public int getStartLevelOfHealth() {
        return startLevelOfHealth;
    }

    public void setStartLevelOfHealth(int startLevelOfHealth) {
        this.startLevelOfHealth = startLevelOfHealth;
    }

    public Optional<String> reproduction(List<LivingBeings> livingBeings, List<LivingBeings> listToAddAfter){
        Optional<String> optionalS = Optional.empty();


        if(levelOfHealth>(startLevelOfHealth*0.6)){
            for (LivingBeings livingBeing : livingBeings) {

                System.out.println(livingBeing instanceof Animal);
                if(livingBeings instanceof Animal  && isOtherAnimalHealthyAndHaveRightType((Animal) livingBeing)){
                    int amount = amountOfProgeny();

                    addReproduction(amount, listToAddAfter);
                    optionalS = Optional.of("We have a new addition! " + amount +" new "+ type.getTypeName() + "\n");
                    isTryToReproduce = true;
                }
            }
        }
        isTryToReproduce = true;

        return optionalS;
    }

    private boolean isOtherAnimalHealthyAndHaveRightType(Animal animal){
        return type.getTypeName().equals(animal.type.getTypeName()) && animal.levelOfHealth>(startLevelOfHealth/0.7);
    }


    private int amountOfProgeny(){
        return random.nextInt(5);
    }

    private void addReproduction(int amount, List<LivingBeings> livingBeings){
        for (int i = 0; i < amount; i++) {
            Animal animal = this instanceof Carnivore? new Carnivore(type, startLevelOfHealth): new Herbivore(type, startLevelOfHealth);
            livingBeings.add(animal);
        }
    }


}



