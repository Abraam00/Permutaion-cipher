import java.io.FileNotFoundException;

public class Edibles
{
    private double mCalories;
    private String mItem;
    public Edibles(String item, double calories) throws FileNotFoundException {
        mCalories = calories;
        mItem = item;
    }
    public String getName(){

        return mItem;
    }
    public double getCalories(){

        return mCalories;
    }

}
