import java.io.IOException;

public class Tank {
    private  float mX;
    private  float mY;

    public Tank(float x, float y) throws IOException {
        mX = x;
        mY = y;
    }
    public float getX(){
        return mX;
    }
    public float getY(){
        return mY;
    }
    public void setX(float x){mX=x; }
    public void setY(float y){mY=y; }
    protected void updatePositiveX()
    {
        setX((getX()+10));
    }
    protected void updateNegativeX()
    {
        setX(getX()-10);
    }
    protected void updatePositiveY()
    {
        setY(getY()+10);
    }
    protected void updateNegativeY()
    {
        setY(getY()-10);
    }
}
