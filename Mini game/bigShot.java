public class bigShot extends Bullet{
    private int mDamage = 20;
    private int mRange = 60;
    public bigShot() {
    }

    @Override
    public int getRange() {
        return mRange;
    }
    @Override
    public int getDamage() {
        return mDamage;
    }
    public String toString(){
        return  "bigShot, D: " + getDamage() + "," + "R: " + getRange();
    }

}
