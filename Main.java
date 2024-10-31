import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Main {
    //исправить
    private static final Scanner scanner = new Scanner(System.in);
    private static final String regForNumbers = ".*\\d.*";
    private static final String regForSymbols = ".*[^a-zA-Z0-9].*";

    private static final Map<Integer, PlantType> plantTypeMap = Map.of(
            1, PlantType.GRASS,
            2, PlantType.FLOWER,
            3, PlantType.TREE,
            4, PlantType.BUSH
    );

    public static void main(String[] args) {

//        List<LivingBeings> livingBeings = new ArrayList<>();
//        TypeForAnimals type = new TypeForAnimals("волк", List.of("заяц"));
//        TypeForAnimals type1 = new TypeForAnimals("заяц", List.of(PlantType.GRASS));
//
//        TypeForPlants typeForPlants = new TypeForPlants("трава", PlantType.GRASS);
//
//        livingBeings.add(new Carnivore(type, 10));
//        livingBeings.add(new Carnivore(type, 10));
//        livingBeings.add(new Herbivore(type1, 10));
//        livingBeings.add(new Herbivore(type1, 10));
//
//
//        livingBeings.add(new Plant(typeForPlants, 10, 5));
//        livingBeings.add(new Plant(typeForPlants, 10, 5));


        //Ecosystem ecosystem = createNewEcosystem();
//        ecosystem.setLivingBeings(livingBeings);
//
//        EcoManager ecoManager = new EcoManager();
//        ecoManager.setEcosystem(ecosystem);
//        ecosystem.getLivingBeings().forEach(System.out::println);
//        System.out.println();
//        ecoManager.simulate();

        start();
    }

    private static void start(){
        int choice;
        while (true) {
            try {
                System.out.println("Choose what do you want to do:");
                System.out.println("1.Create new ecosystem\n" +
                        "2.Continue the old ecosystem\n" +
                        "3.Exit\n");
                choice= Integer.parseInt(scanner.nextLine());
                if (choice<1 || choice > 4) {
                    throw new RuntimeException();
                }

                switch (choice){
                    case 1 -> {EcoManager ecoManager = createNewEcosystem();
                        menuForEcosystem(ecoManager);
                    }
                    case 2 -> {
                        EcoManager ecoManager = continueOld();
                        menuForEcosystem(ecoManager);
                    }
                    case 3 -> {
                        System.exit(0);
                    }
                }
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }

    private static EcoManager continueOld() {
        Map<Integer, String> stringMapFiles = getFiles();
        stringMapFiles.forEach((key, value) -> {
            System.out.println(key + ". " + value);
        });
        String name;
        int choice;
        EcoManager ecoManager = new EcoManager();
        while (true) {
            try {
                System.out.println("Enter:");
                String line = scanner.nextLine();
                if(line.equals("Back")){
                    break;
                }
                choice = Integer.parseInt(line);
                if (choice < 0 || choice > stringMapFiles.size()) {
                    throw new RuntimeException();
                }
                name = stringMapFiles.get(choice);
                ecoManager.load(name);
                break;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
        return ecoManager;
    }

    private static Map<Integer, String> getFiles(){
        File directory = new File("."); // Укажите нужную директорию
        Map<Integer, String> listOfFile = new HashMap<>();
        int i = 0;

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt") && !file.getName().equals("simulation_log.txt")) {
                    listOfFile.put(i++, file.getName());
                }
            }
        } else {
            System.out.println("Не удалось получить список файлов.");
        }
        return listOfFile;
    }

//    private static Ecosystem createNewEcosystem(){
//        Ecosystem ecosystem = new Ecosystem();
//        ecosystem.setName(enterName());
//        Enviroment enviroment = new Enviroment();
//        System.out.println("The temperature");
//        enviroment.setTemperature(enterParametersForEnviroment());
//
//        System.out.println("The humidity");
//        enviroment.setHumidity(enterParametersForEnviroment());
//
//        System.out.println("The waterAvailability");
//        enviroment.setWaterAvailability(enterParametersForEnviroment());
//        //исправить
//        System.out.println("Your ecosystem is created");
//        ecosystem.setEnviroment(enviroment);
//
//        return ecosystem;
//    }

    private static EcoManager createNewEcosystem(){
        Ecosystem ecosystem = new Ecosystem();
        ecosystem.setName(enterName());
        Enviroment enviroment = new Enviroment();
        System.out.println("The temperature");
        enviroment.setTemperature(enterParametersForEnviroment());

        System.out.println("The humidity");
        enviroment.setHumidity(enterParametersForEnviroment());

        System.out.println("The waterAvailability");
        enviroment.setWaterAvailability(enterParametersForEnviroment());

        System.out.println("Your ecosystem is created\n");
        ecosystem.setEnviroment(enviroment);

        EcoManager ecoManager = new EcoManager();
        ecoManager.setFile(ecosystem.getName()+".txt");
        ecoManager.setEcosystem(ecosystem);
        ecoManager.save();
        return ecoManager;
    }

    private static int enterParametersForEnviroment(){
        int parameter;
        while (true) {
            try {
                System.out.println("Enter:");
                parameter = Integer.parseInt(scanner.nextLine());
                if (parameter < 1) {
                    throw new RuntimeException();
                }
                return parameter;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }

    private static String enterName(){
        String name;
        while (true) {
            try {
                System.out.println("Enter ecosystem name");
                name = scanner.nextLine();
                if (name.matches(regForNumbers) || name.matches(regForSymbols)) {
                    throw new RuntimeException();
                }
                return name;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }

    private static void menuForEcosystem(EcoManager ecoManager){
        int choice;
        while (true) {
            try {
                System.out.println("Choose what do you want to do:");
                System.out.println("1.Add animals or plants\n" +
                        "2.Update animals or plants\n" +
                        "3.Delete animals or plants\n" +
                        "4.Show all Living Beings\n" +
                        "5.Ecosystem launch\n" +
                        "6.Predict population\n" +
                        "7.Back\n");
                choice= Integer.parseInt(scanner.nextLine());
                if (choice<1 || choice > 7) {
                    throw new RuntimeException();
                }
                if (choice ==7){
                    break;
                }
                switch (choice){
                    case 1 -> {addMenu(ecoManager.getEcosystem());
                    ecoManager.save();}
                    case 2 -> {updateMenu(ecoManager.getEcosystem());
                        ecoManager.save();}
                    case 3 -> {delete(ecoManager.getEcosystem());
                        ecoManager.save();}
                    case 4 -> {showAllSpecies(ecoManager.getEcosystem());}
                    case 5 -> {
                        ecoManager.simulate();
                        System.out.println("Running the simulation...");
                        System.out.println("Check the simulation_log.txt");
                        System.out.println();
                    }
                    case 6 -> {
                        System.out.println(ecoManager.getEcosystem().predictPopulationChanges());
                    }

                }
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }

    private static void addMenu(Ecosystem ecosystem){
        int choice;
        while (true) {
            try {
                System.out.println("Choose what do you want to do:");
                System.out.println("1.Add animals\n" +
                        "2.Add plants\n" +
                        "3.Done\n");
                choice= Integer.parseInt(scanner.nextLine());
                if (choice<1 || choice > 3) {
                    throw new RuntimeException();
                }
                if (choice==3) {
                    break;
                }
                switch (choice){
                    case 1 -> {addDataAboutAnimals(ecosystem);}
                    case 2 -> {addDataAboutPlants(ecosystem);}

                }
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }



    private static void updateMenu(Ecosystem ecosystem){
        int choice;
        while (true) {
            try {
                System.out.println("Choose what do you want to do:");
                System.out.println("1.Update animals\n" +
                        "2.Update plants\n" +
                        "3.Done\n");
                choice= Integer.parseInt(scanner.nextLine());
                if (choice<1 || choice > 3) {
                    throw new RuntimeException();
                }
                if (choice==3) {
                    break;
                }
                switch (choice){
                    case 1 -> {updateDataAnimal(ecosystem);}
                    case 2 -> {updateDataPlants(ecosystem);}

                }
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }


    private static void addDataAboutPlants(Ecosystem ecosystem) {

        String species = enterSpecies();
        int quantity = enterQuantity();
        PlantType plantType = enterPlantType();
        int levelOfHealth = enterHealth();
        int quantityOfWater = enterQuantityOfWater();
        addPlant(species, quantity, plantType, levelOfHealth, quantityOfWater, ecosystem);
    }

    private static PlantType enterPlantType(){
        int intForCategory;
        PlantType plantType;
        while (true) {
            try {
                System.out.println("Enter the category of plants:\n" +
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

        return plantType;
    }

    private static void addDataAboutAnimals(Ecosystem ecosystem) {
        int type;

        String species = enterSpecies();
        int quantity = enterQuantity();
        int levelOfHealth = enterHealth();
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



        switch (type) {
            case 1 -> {
                List<PlantType> planTypesForAnimal = getHerbivoreList();
                addHerbivore(species, quantity, planTypesForAnimal, levelOfHealth, ecosystem);
            }

            case 2 -> {
                List<String> specialsToEat = getCarnivoreList();
                addCarnivore(species, quantity, specialsToEat, levelOfHealth, ecosystem);
        }
     }
    }


    private static void addCarnivore(String species, int quantity, List<String> listOfFood, int levelOfHealth, Ecosystem ecosystem){
        TypeForAnimals type = new TypeForAnimals(species, new ArrayList<>(listOfFood));
        for (int i = 0; i < quantity; i++) {
            Carnivore carnivore = new Carnivore(type);
            carnivore.setLevelOfHealth(levelOfHealth);
            carnivore.setStartLevelOfHealth(levelOfHealth);

            ecosystem.add(carnivore);

        }
        //System.out.println(ecosystem.getAnimalList());
    }

    private static void addHerbivore(String species, int quantity, List<PlantType> plantTypes, int levelOfHealth, Ecosystem ecosystem){
        TypeForAnimals type = new TypeForAnimals(species, new ArrayList<>(plantTypes));

        for (int i = 0; i < quantity; i++) {
            Herbivore herbivore = new Herbivore(type);
            herbivore.setLevelOfHealth(levelOfHealth);
            herbivore.setStartLevelOfHealth(levelOfHealth);

            ecosystem.add(herbivore);

        }
        //System.out.println(ecosystem.getAnimalList());
    }

    private static void addPlant(String species, int quantity, PlantType plantTypes, int levelOfHealth, int quantityOfWater, Ecosystem ecosystem){
        TypeForPlants type = new TypeForPlants(species, plantTypes);

        for (int i = 0; i < quantity; i++) {
            Plant plant = new Plant(type);
            plant.setLevelOfHealth(levelOfHealth);
            plant.setQuantityOfWater(quantityOfWater);
            plant.setStartLevelOfHealth(levelOfHealth);
            ecosystem.add(plant);

        }
        //System.out.println(ecosystem.getPlantList());
    }

    private static void updateDataPlants(Ecosystem ecosystem){
        System.out.println("Choose type plants to update:");
        Set<Plant> plants = ecosystem.getLivingBeings().stream()
                .filter(x -> x instanceof Plant)
                .map(x -> (Plant) x)
                .collect(Collectors.toSet());

        Map<Integer, Plant> mapWithPlants = new HashMap<>();
        int i =1;

        for (Plant plant : plants) {
            mapWithPlants.put(i++, plant);
        }

        mapWithPlants.forEach((key, value) -> {
            System.out.println(key + ". " + value.getTypeForPlants().getTypeName());
        });
        int choice;
        while (true) {
            try {
                System.out.println("Your choice:");
                choice = Integer.parseInt(scanner.nextLine());
                if (!mapWithPlants.containsKey(choice)) {
                    throw new RuntimeException();
                }
                break;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }

        Plant choosePlant = mapWithPlants.get(choice);
        menuToUpdatePlants(choosePlant);
        //ecosystem.getPlantList().forEach(System.out::println);

    }

    private static Plant menuToUpdatePlants(Plant plant){
        Map<Integer, Object> objectMap = new HashMap<>(Map.of(
                1, plant.getTypeForPlants().getTypeName(),
                2, plant.getTypeForPlants().getPlantType()
        ));
        System.out.println(plant);

        int choice;
        while (true) {
            try {
                System.out.println("Choose the parameter that you want to change:\n"+
                        "1.Name of species\n" +
                        "2.Plant type(grass/bush/flower/tree)\n"+
                        "3.End");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice >3) {
                    throw new RuntimeException();
                }
                if(choice ==3){
                    break;
                }
                switch (choice){
                    case 1->{
                        plant.getTypeForPlants().setTypeName(enterSpecies());
                    }
                    case 2->{
                        PlantType plantType= enterPlantType();
                        plant.getTypeForPlants().setPlantType(plantType);
                    }
                }

            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
        return plant;
    }

    private static void updateDataAnimal(Ecosystem ecosystem){
        System.out.println("Choose type animals to update:");
        Set<Animal> animals = ecosystem.getLivingBeings().stream()
                .filter(x -> x instanceof Animal)
                .map(x -> (Animal) x)
                .collect(Collectors.toSet());

        Map<Integer, Animal> mapWithAnimals = new HashMap<>();
        int i =1;

        for (Animal animal : animals) {
            mapWithAnimals.put(i++, animal);
        }

        mapWithAnimals.forEach((key, value) -> {
            System.out.println(key + ". " + value.getType().getTypeName());
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

        //ecosystem.getAnimalList().forEach(System.out::println);
    }

    private static Animal menuToUpdateAnimals(Animal animal){

        System.out.println(animal);

        int choice;
        while (true) {
            try {
                System.out.println("Choose the parameter that you want to change:\n"+
                        "1.Name of species\n" +
                        "2.Animal type(herbivore/carnivore)\n"+
                        "3.End");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice >3) {
                    throw new RuntimeException();
                }
                if(choice ==3){
                    break;
                }
                switch (choice){
                    case 1->{
                        animal.getType().setTypeName(enterSpecies());
                    }
                    case 2->{
                        animal= menuTypeForAnimals(animal);
                    }
                }

            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
        return animal;
    }

    private static Animal menuTypeForAnimals(Animal animal){
        int choice;
        Animal newAnimal = null;
        while (true) {
            try {
                System.out.println("Do you want to change animal type or list of food?\n" +
                        "1.Type\n" +
                        "2.List\n" +
                        "3.Back");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice>3) {
                    throw new RuntimeException();
                }
                if(choice == 3){
                    return animal;
                }
                break;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }

        switch (choice){
            case 1 -> {
                if(animal instanceof Carnivore){
                    Herbivore herbivore = new Herbivore(animal.getType());
                    List<PlantType> listOfFood = getHerbivoreList();
                    herbivore.getType().setFoodList(new ArrayList<>(listOfFood));
                    animal = herbivore;
                }else {
                    Carnivore carnivore = new Carnivore(animal.getType());
                    List<String> listOfFood = getCarnivoreList();
                    carnivore.getType().setFoodList(new ArrayList<>(listOfFood));
                    animal = carnivore;
                }
            }
            case 2 -> {
                if(animal instanceof Carnivore){
                    List<String> listOfFood = getCarnivoreList();
                    Carnivore carnivore = (Carnivore) animal;
                    carnivore.getType().setFoodList(new ArrayList<>(listOfFood));
                    animal = carnivore;
                }else {
                    List<PlantType> listOfFood = getHerbivoreList();
                    Herbivore herbivore = (Herbivore) animal;
                    herbivore.getType().setFoodList(new ArrayList<>(listOfFood));
                    animal = herbivore;
                }
            }
        }
        return animal;
    }

    private static String enterSpecies(){
        String species;
        while (true) {
            try {
                System.out.println("Enter the species:");
                species = scanner.nextLine();
                if (species.matches(regForNumbers) || species.matches(regForSymbols)) {
                    throw new RuntimeException();
                }
                return species;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }

    private static int enterQuantity(){
        int quantity;
        while (true) {
            try {
                System.out.println("Well done! How much this species do you want to add?");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 1) {
                    throw new RuntimeException();
                }
                return quantity;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }

    private static int enterHealth(){
        int level;
        while (true) {
            try {
                System.out.println("Enter level of health");
                level = Integer.parseInt(scanner.nextLine());
                if (level < 1) {
                    throw new RuntimeException();
                }
                return level;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }

    private static int enterQuantityOfWater(){
        int level;
        while (true) {
            try {
                System.out.println("Enter how much water your plants needs");
                level = Integer.parseInt(scanner.nextLine());
                if (level < 1) {
                    throw new RuntimeException();
                }
                return level;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }

    private static List<String> getCarnivoreList(){
        String food;
        List<String> specialsToEat = new ArrayList<>();
        System.out.println("Read further carefully. You must write a list of animals that are included in your animal's diet. " +
                "Write \"end\" when you done ");
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

        return specialsToEat;
    }

    private static List<PlantType> getHerbivoreList(){
        List<PlantType> planTypesForAnimal = new ArrayList<>();
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
                return planTypesForAnimal;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }

    private static void showAllSpecies(Ecosystem ecosystem){
        int i = 0;
        System.out.println("All Living Beings: ");
        System.out.println("\n");
        ecosystem.getLivingBeings().forEach(System.out::println);
        System.out.println("\n");
    }

    private static void delete(Ecosystem ecosystem){
        System.out.println("All animals: ");
        showAllSpecies(ecosystem);
        while (true) {
            try {
                System.out.println("Select id of plants and animals to delete. If you want more than one, enter them separated by a space.\n" +
                        "Example:1 2 3");
                String categories = scanner.nextLine();
//                if (!categories.matches("^(?:[\\d+](?:\\s+[\\d+])*)+$")) {
//                    throw new RuntimeException();
//                }//^(?:[\d+](?:\s+[\d+])*)+$

                List<String> strings = new ArrayList<>(Arrays.asList(categories.split(" ")));
                List<Integer> categoriesIntegerList = strings.stream().map(Integer::parseInt).toList();
                Set<Integer> categoriesSet = new HashSet<>(categoriesIntegerList);
                for (Integer i : categoriesSet) {
                    ecosystem.delete(i);
                }
                break;
            } catch (RuntimeException e) {
                System.out.println("Incorrect value!\n");
            }
        }
    }

}