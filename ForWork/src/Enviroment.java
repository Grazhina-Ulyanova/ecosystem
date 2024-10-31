import java.io.Serializable;

public class Enviroment implements Serializable {
    private int temperature;
    private int humidity;
    private int waterAvailability;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getWaterAvailability() {
        return waterAvailability;
    }

    public void setWaterAvailability(int waterAvailability) {
        this.waterAvailability = waterAvailability;
    }

    public void updateParameters(){
        int adjustment = calculateWaterAdjustment();
        waterAvailability = Math.max(0, waterAvailability+adjustment);
    }

    private int calculateWaterAdjustment(){
        if(humidity<=40){
            return (temperature>=25) ? -20:-10;
        }
        if(humidity>=80){
            return (temperature<=20) ? 20:10;
        }
        return 0;
    }

    public void addWaterBecauseOfRain(int water){
        waterAvailability += water;
    }
    public void changeParameters(int differOfTemperature, int differOfHumidity){
        temperature +=differOfTemperature;
        humidity+=differOfHumidity;

    }
}
