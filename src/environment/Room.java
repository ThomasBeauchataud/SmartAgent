package environment;

public class Room {

    private boolean dust;
    private boolean jewel;

    public boolean hasDust() {
        return dust;
    }

    public void putDust() {
        this.dust = true;
    }

    public boolean hasJewel() {
        return jewel;
    }

    public void putJewel() {
        this.jewel = true;
    }

    public void removeDust() {
        this.dust = false;
    }

    public void removeJewel() {
        this.jewel = false;
    }

}
