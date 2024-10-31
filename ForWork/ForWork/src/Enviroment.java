public class Enviroment {
    private double temperature;
    private double humidity;
    private double waterAvailability;

    public Enviroment(double temperature, double humidity, double waterAvailability) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.waterAvailability = waterAvailability;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWaterAvailability() {
        return waterAvailability;
    }

    public void setWaterAvailability(double waterAvailability) {
        this.waterAvailability = waterAvailability;
    }
}
