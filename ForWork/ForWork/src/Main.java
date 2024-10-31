import java.util.*;
import java.util.stream.Collectors;

public class Main {
    //исправить
    private static final Scanner scanner = new Scanner(System.in);
    private static final String regForNumbers = ".*\\d.*";
    private static final String regForSymbols = ".*[^a-zA-Z0-9].*";
    //private static final String regForList = "([a-zA-Zа-яА-Я0-9]+)(, [a-zA-Zа-яА-Я0-9]+)*";

    private static final Map<Integer, PlantType> plantTypeMap = Map.of(
            1, PlantType.GRASS,
            2, PlantType.FLOWER,
            3, PlantType.TREE,
            4, PlantType.BUSH
    );

    public static void main(String[] args) {
        //addDataAboutAnimals(new Ecosystem());
        addDataAboutPlants(new Ecosystem());
    }

    private static void addDataAboutPlants(Ecosystem ecosystem) {
        String species;
        int intForCategory;
        PlantType plantType;
        int quantity;
        while (true) {
            try {
                System.out.println("Enter the species of plants:");
                species = scanner.nextLine();
                if (species.matches(regForNumbers) || species.matches(regForSymbols)) {
                    throw new RuntimeException();
                }
                break;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }

        while (true) {
            try {
                System.out.println("Well done! How much animals of this species do you want to add?");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 1) {
                    throw new RuntimeException();
                }
                break;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }

        while (true) {
            try {
                System.out.println("Enter the category of plants:" +
                        "Example:1 2 3\n" +
                        "1)Grass\n" +
                        "2)Flower\n" +
                        "3)Tree\n" +
                        "4)Bush");
                intForCategory= Integer.parseInt(scanner.nextLine());
                if (intForCategory<1 || intForCategory > 4) {
                    throw new RuntimeException();
                }
                 plantType = plantTypeMap.get(intForCategory);
                break;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }

        addPlant(species, quantity, plantType, ecosystem);
    }

    private static void addDataAboutAnimals(Ecosystem ecosystem) {
        String species;
        int type;
        int quantity;
        while (true) {
            try {
                System.out.println("Enter the species of animal:");
                species = scanner.nextLine();
                if (species.matches(regForNumbers) || species.matches(regForSymbols)) {
                    throw new RuntimeException();
                }
                break;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }

        while (true) {
            try {
                System.out.println("Well done! How much animals of this species do you want to add?");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 1) {
                    throw new RuntimeException();
                }
                break;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
        while (true) {
            try {
                System.out.println("Choose(enter number 1 or 2):\n1)Your animal is HERBIVORE\n2)Your animal is CARNIVORE");
                type = Integer.parseInt(scanner.nextLine());
                if (type > 2 || type < 1) {
                    throw new RuntimeException();
                }
                break;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }


        List<PlantType> planTypesForAnimal = new ArrayList<>();
        switch (type) {
            case 1 -> {


                while (true) {
                    try {
                        System.out.println("Choose(enter number 1 or 2):\n1)Enter plant categories");

                        while (true) {
                            try {
                                System.out.println("Select a plant category. If you want more than one, enter them separated by a space." +
                                        "Example:1 2 3\n" +
                                        "1)Grass\n" +
                                        "2)Flower\n" +
                                        "3)Tree\n" +
                                        "4)Bush");
                                String categories = scanner.nextLine();
                                if (!categories.matches("^(?:[1-4](?:\\s+[1-4])*)+$")) {
                                    throw new RuntimeException();
                                }
                                List<String> strings = new ArrayList<>(Arrays.asList(categories.split(" ")));
                                List<Integer> categoriesIntegerList = strings.stream().map(Integer::parseInt).toList();
                                Set<Integer> categoriesSet = new HashSet<>(categoriesIntegerList);
                                plantTypeMap.forEach((key, value) -> {
                                    if (categoriesSet.contains(key)) {
                                        planTypesForAnimal.add(value);
                                    }
                                });
                                addHerbivore(species, quantity, planTypesForAnimal, ecosystem);
                                break;
                            } catch (RuntimeException e) {
                                System.out.println("Incorrect value!\n");
                            }
                        }

                        break;
                    } catch (RuntimeException e) {
                        System.out.println("Incorrect value!\n");
                    }
                }
            }

            case 2 -> {
                String food;
                List<String> specialsToEat = new ArrayList<>();
                System.out.println("Read further carefully. You must write a list of animals that are included in your animal's diet. Write \"end\" when you done ");
                while (true) {
                    try {
                        food = scanner.nextLine();
                        if (food.matches(regForNumbers) || food.matches(regForSymbols)) {
                            throw new RuntimeException();
                        }
                        if (food.equals("end")) {
                            break;
                        }
                        specialsToEat.add(food);
                    } catch (RuntimeException e) {
                        System.out.println("Incorrect value!\n");
                    }

                }
                addCarnivore(species, quantity, specialsToEat, ecosystem);
        }
     }
    }


    private static void addCarnivore(String species, int quantity, List<String> listOfFood, Ecosystem ecosystem){
        for (int i = 0; i < quantity; i++) {
            Animal animal = new Animal(species);
            animal.setAnimalType(AnimalType.CARNIVORE);
            animal.setListOfAnimals(listOfFood);
            //еда и здоровье
            ecosystem.addSpecies(animal);
            System.out.println(ecosystem.getAllSpecies());
        }
    }

    private static void addHerbivore(String species, int quantity, List<PlantType> plantTypes, Ecosystem ecosystem){
        for (int i = 0; i < quantity; i++) {
            Animal animal = new Animal(species);
            animal.setAnimalType(AnimalType.HERBIVORE);
            animal.setListOfPlans(plantTypes);
            //еда и здоровье
            ecosystem.addSpecies(animal);
            System.out.println(ecosystem.getAllSpecies());
        }
    }

    private static void addPlant(String species, int quantity, PlantType plantTypes, Ecosystem ecosystem){
        for (int i = 0; i < quantity; i++) {
            Plant plant = new Plant(species, plantTypes);
            //еда и здоровье
            ecosystem.addSpecies(plant);
            System.out.println(ecosystem.getAllSpecies());
        }
    }

    private static void updateDataAnimal(Ecosystem ecosystem){
        System.out.println("Choose id animal to update:");
        Set<Animal> animals = (Set<Animal>) ecosystem.getAllSpecies().stream()
                .filter(x -> x instanceof Animal)
                .map(x -> (Animal) x)
                .collect(Collectors.toSet());

        Map<Integer, Animal> mapWithAnimals = new HashMap<>();
        int i =0;

        for (Animal animal : animals) {
            mapWithAnimals.put(i++, animal);
        }

        mapWithAnimals.forEach((key, value) -> {
            System.out.println(key + ". " + value.getType());
        });
        int choice;
        while (true) {
            try {
                System.out.println("Your choice:");
                choice = Integer.parseInt(scanner.nextLine());
                if (!mapWithAnimals.containsKey(choice)) {
                    throw new RuntimeException();
                }
                break;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }

        Animal chooseAnimal = mapWithAnimals.get(choice);
        menuToUpdateAnimals(chooseAnimal);
    }

    private static Animal menuToUpdateAnimals(Animal animal){
        Map<Integer, >
        System.out.println(animal.toString());
        System.out.println();
    }
}