public class Skipper extends Bullet {
    private int mDamage = 50;
    private int mRange = 30;
    @Override
    public int getRange() {
        return mRange;
    }

    @Override
    public int getDamage() {
        return mDamage;
    }

    public String toString(){
        return  "Skipper, D: " + getDamage() + "," + "R: " + getRange();
    }
}