import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class EcoManager {

    private Ecosystem ecosystem;

    private String file;
    Random random;

    {
        random = new Random();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void save(){
        try {
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(ecosystem);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(String name) {
        file = name;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            ecosystem = (Ecosystem) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("file");
        }
    }

    public void simulate(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("simulation_log.txt"))) {
            writer.write("Running the simulation...\n\n");
            writer.write("The first parameters:\n");
            writer.write(returnDayParameters() +"\n\n");
            for (int i = 0; i < 3; i++) {
                writer.write("Day" + (i+1) + "\n");
                ecosystem.checkDead();
                if(!isEcosystemLive()){
                    writer.write("Your ecosystem is dead...Try again.\n\n");
                    break;
                }
                writer.write("The begging of the day:\n\n");

                ecosystem.getEnviroment().updateParameters();
                ecosystem.getLivingBeings().forEach(x -> x.isTryEat=false);
                writer.write(returnDayParameters() + "\n");



                writer.write("\nAnimals try to eat. Plants start to drink:" + ecosystem.getEnviroment().getWaterAvailability() + "\n\n");



                for (int j = 0; j < ecosystem.getLivingBeings().size(); j++) {
                    writer.write(ecosystem.ecosystemEat());
                }

                writer.write("\nWater availability after plants drinking:" + ecosystem.getEnviroment().getWaterAvailability() + "\n\n");

                if((i+1)%3==0){
                    int amount = ecosystem.getLivingBeings().size();
                    for (int j = 0; j < amount; j++) {
                        writer.write(ecosystem.ecosystemReproduction());
                    }
                    ecosystem.getLivingBeings().addAll(ecosystem.getListToAddAfter());
                    ecosystem.getListToAddAfter().clear();
                    ecosystem.getLivingBeings().forEach(x -> x.isTryToReproduce = false);
                }

                writer.write("\nEvents of the day:\n");
                if(isItRain()) {
                    ecosystem.getEnviroment().addWaterBecauseOfRain(howMuchWaterOfTheRain());
                    writer.write("The rain has started...");
                    writer.write("\nWater availability became:" + ecosystem.getEnviroment().getWaterAvailability() + "\n\n");

                }

                if(isItChangeEnviroment()) {
                    changeEnviroment();
                    writer.write("The climate has changed...");
                    writer.write(returnDayParameters()+"\n");

                }
                writer.write("\n\n");


            }



        } catch (IOException e) {
        e.printStackTrace();
        }


    }

    public boolean isEcosystemLive(){
        return !ecosystem.getLivingBeings().isEmpty();
    }

    public Ecosystem getEcosystem() {
        return ecosystem;
    }

    public void setEcosystem(Ecosystem ecosystem) {
        this.ecosystem = ecosystem;
    }

    public String returnDayParameters(){

        return "Temperature: " + ecosystem.getEnviroment().getTemperature() + "\nHumidity: " + ecosystem.getEnviroment().getHumidity()  + "\n" +
                "Water availability:" + ecosystem.getEnviroment().getWaterAvailability() + "\n";
    }

    public boolean isItRain(){
        return (random.nextInt(99)+1)<30;
    }

    public boolean isItChangeEnviroment(){
        return (random.nextInt(99)+1)<20;
    }

    public void changeEnviroment(){
        int changeTemperature = random.nextInt(20)-10;
        int changeHumidity = random.nextInt(20) -10;
        ecosystem.getEnviroment().changeParameters(changeTemperature, changeHumidity);
    }

    public int howMuchWaterOfTheRain(){
        return (random.nextInt(50)+50);
    }
}
