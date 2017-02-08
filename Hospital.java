import greenfoot.*;

public class Hospital extends Actor
{
    public void act() 
    {
        if(isAtEdge()) //Used in boss level
        {
            getWorld().removeObject(this);
        }
    }   
    public void addedToWorld(World w)
    {
        //Preventing from spawning on top of another object except for another building
        while(isTouching(Boy.class) || isTouching(Ambulance.class))
        {
            int x=Greenfoot.getRandomNumber(getWorld().getWidth()-10);
            int y=Greenfoot.getRandomNumber(getWorld().getHeight()-10);
            setLocation(x, y);
        }
    }
}
