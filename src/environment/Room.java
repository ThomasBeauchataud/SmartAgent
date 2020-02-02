package environment;

/**
 * @since 01.02.2020
 * @author Thomas Beauchataud
 * This class represents a Room of a Manor
 */
@SuppressWarnings({"WeakerAccess","BooleanMethodIsAlwaysInverted"})
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

    public void setDust(boolean dust) {
        this.dust = dust;
    }

    public void setJewel(boolean jewel) {
        this.jewel = jewel;
    }

    public boolean equalsTo(Room room) {
        return (room.hasDust() == this.hasDust() && room.hasJewel() == this.hasJewel());
    }

}
