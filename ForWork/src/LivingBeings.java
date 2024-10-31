import java.io.Serializable;

public abstract class LivingBeings implements Serializable {
    protected boolean isTryEat = false;

    protected boolean isTryToReproduce = false;

    protected int levelOfHealth;

}
