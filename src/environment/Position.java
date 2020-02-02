package environment;

@SuppressWarnings("WeakerAccess")
public class Position {

    private int x;
    private int y;

    public int getX() { return x; }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equalsTo(Position position) {
        return (x == position.getX() && y == position.getY());
    }
}
