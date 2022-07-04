public class Sniper extends Bullet{
    private int mRange = 5;
    private int mDamage = 100;
    @Override
    public int getRange() {
        return mRange;
    }

    @Override
    public int getDamage() {
        return mDamage;
    }
    public String toString(){
        return  "sniper, D: " + getDamage() + "," + "R: " + getRange();
    }
}
