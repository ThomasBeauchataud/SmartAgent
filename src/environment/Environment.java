package environment;

public interface Environment {

    void updateView();

    boolean equalsTo(Environment environment);

    Environment copy();

}
