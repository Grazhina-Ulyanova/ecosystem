import java.io.Serializable;

abstract public class Species implements Serializable {

    protected Type type;


    public Species(String nameOfType) {
        type = new Type(nameOfType);
    }

    public String getType() {
        return type.getType();
    }

    public void setType(String nameOfType) {
        type.setType(nameOfType);
    }
}
