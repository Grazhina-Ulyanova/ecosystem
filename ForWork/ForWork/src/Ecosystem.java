import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ecosystem implements Serializable {
    //версии?
    private String name;
    private List<Species> allSpecies;
    private List<Event> events;
    private String fileName;
    private Enviroment enviroment;

    {
        allSpecies = new ArrayList<>();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Enviroment getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(Enviroment enviroment) {
        this.enviroment = enviroment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ecosystem() {
    }


    public void setAllSpecies(List<Species> allSpecies) {
        this.allSpecies = allSpecies;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    //crud

    public void addSpecies(Species species){
        allSpecies.add(species);
    }

    public void deleteSpecies(int i){
        allSpecies.remove(i);
    }

    public void updateSpecies(int i, Species newSpecies){
        allSpecies.set(i, newSpecies);
    }

    public List<Species> getAllSpecies() {
        return allSpecies;
    }




}
