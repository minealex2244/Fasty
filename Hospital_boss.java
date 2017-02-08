import greenfoot.*;

public class Hospital_boss extends Actor
{
    int time=0,ok=1;
    public void act() 
    {
        time++;
        if (! getWorld().getObjects(Ambulance.class).isEmpty()) //If we got the ambulance added to world...
        {
            Actor ambulance = (Actor)getWorld().getObjects(Ambulance.class).get(0);
            setLocation(getX(),ambulance.getY()); //...we set the location of boss here and it'll follow ambulance Y axis
        }
        if(ok==1){
            getWorld().addObject(new Hospital(),775,getY()); //The boss will start shooting with hospitals
            ok=0;
        }
        if (! getWorld().getObjects(Hospital.class).isEmpty())
        {
            Actor hospital = (Actor)getWorld().getObjects(Hospital.class).get(0);
            if(time%120==0)
                getWorld().addObject(new Hospital(),775,getY());
            hospital.setLocation(hospital.getX()-5,hospital.getY()); //Moving the hospital to get the shooting effect
        }
        else
        {
            getWorld().addObject(new Hospital(),775,getY());
        }
    }    
}
