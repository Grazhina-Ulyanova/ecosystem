import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EcoManager {

    private Ecosystem ecosystem;
    private void save(){
        try {
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream(ecosystem.getFileName()));
            objectOutputStream.writeObject(ecosystem);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
