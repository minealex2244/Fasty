import greenfoot.*;

public class Boy extends Actor
{
    public void act() 
    {
        //TODO
    }  
    public void addedToWorld()
    {
        /*int y = getY();
        while(y<25)
        {
            y=Greenfoot.getRandomNumber(getWorld().getHeight()-10);
            setLocation(getX(),y);
        }*/
        while(isTouching(Statusbar.class))
        {
            int x=Greenfoot.getRandomNumber(getWorld().getWidth()-10);
            int y=Greenfoot.getRandomNumber(getWorld().getHeight()-10);
            setLocation(x, y);
        }
    }
}
