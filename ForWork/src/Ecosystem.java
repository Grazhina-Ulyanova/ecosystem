import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ecosystem implements Serializable{
    private String name;
    private List<LivingBeings> livingBeings;
    private List<LivingBeings> listToAddAfter;

    private Enviroment enviroment;


    {
        listToAddAfter = new ArrayList<>();
        livingBeings = new ArrayList<>();
    }



    public Enviroment getEnviroment() {
        return enviroment;
    }

    public List<LivingBeings> getListToAddAfter() {
        return listToAddAfter;
    }

    public void setListToAddAfter(List<LivingBeings> listToAddAfter) {
        this.listToAddAfter = listToAddAfter;
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


    public void add(LivingBeings livingBeing){
        livingBeings.add(livingBeing);
    }

    public void delete(int i){
        livingBeings.remove(i);
    }



    public String ecosystemEat(){
        String s = "";
        Optional<LivingBeings> optionalLivingBeings = livingBeings.stream().filter(livingBeings1 -> !livingBeings1.isTryEat).findAny();
        if(optionalLivingBeings.isPresent()){
            LivingBeings livingBeing = optionalLivingBeings.get();
            if(livingBeing instanceof Animal){
                s = ((Animal) livingBeing).eat(livingBeings);
            }else {
                s = ((Plant) livingBeing).drink(enviroment);
            }
        }


        return s;
    }

    public String ecosystemReproduction(){
        Optional<String> optional = Optional.empty();
        Optional<LivingBeings> optionalLivingBeings = livingBeings.stream().filter(livingBeings1 -> !livingBeings1.isTryToReproduce).findAny();
        if(optionalLivingBeings.isPresent()){
            LivingBeings livingBeing = optionalLivingBeings.get();
            if(livingBeing instanceof Animal){
                optional = ((Animal) livingBeing).reproduction(livingBeings, listToAddAfter);
            }else {
                optional = ((Plant) livingBeing).reproduction(livingBeings, enviroment, listToAddAfter);
            }
        }
        System.out.println(optional.orElse("noo"));
        return optional.orElse("\n");
    }

    public List<LivingBeings> getLivingBeings() {
        return livingBeings;
    }

    public void setLivingBeings(List<LivingBeings> livingBeings) {
        this.livingBeings = livingBeings;
    }

    public void checkDead(){
        livingBeings.removeIf(x -> x.levelOfHealth <= 0);
    }

    // Метод для предсказания изменений в популяциях видов
    public String predictPopulationChanges() {
        StringBuilder prediction = new StringBuilder("Population predictions:\n");

        // Проходим по каждому живому существу в экосистеме
        Map<String, Integer> speciesHealthSummary = new HashMap<>();

        for (LivingBeings being : livingBeings) {
            String typeName = "";

            // Проверка на типы живых существ
            if (being instanceof Animal) {
                typeName = ((Animal) being).getType().getTypeName();
            } else if (being instanceof Plant) {
                typeName = ((Plant) being).getTypeForPlants().getTypeName();
            }

            // Считаем средний уровень здоровья для каждого вида
            speciesHealthSummary.merge(typeName, being.levelOfHealth, Integer::sum);
        }

        // Определяем прогнозы по видам
        for (Map.Entry<String, Integer> entry : speciesHealthSummary.entrySet()) {
            String typeName = entry.getKey();
            int totalHealth = entry.getValue();
            int averageHealth = totalHealth / (int) livingBeings.stream().filter(lb -> {
                if (lb instanceof Animal) {
                    return ((Animal) lb).getType().getTypeName().equals(typeName);
                } else if (lb instanceof Plant) {
                    return ((Plant) lb).getTypeForPlants().getTypeName().equals(typeName);
                }
                return false;
            }).count();

            // Прогноз на основе параметров среды и уровня здоровья
            if (enviroment.getTemperature() < 10 || averageHealth < 3) {
                prediction.append(typeName).append(": Population likely to decrease.\n");
            } else if (enviroment.getWaterAvailability() >= 50 && averageHealth > 5) {
                prediction.append(typeName).append(": Population likely to increase.\n");
            } else {
                prediction.append(typeName).append(": Population likely to remain stable.\n");
            }
        }

        return prediction.toString();
    }
}
