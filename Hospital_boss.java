import greenfoot.*;

public class Hospital_boss extends Actor
{
    int time=0,ok=1;
    public void act() 
    {
        time++;
        if (! getWorld().getObjects(Ambulance.class).isEmpty())
        {
            Actor ambulance = (Actor)getWorld().getObjects(Ambulance.class).get(0);
            setLocation(getX(),ambulance.getY());
        }
        if(ok==1){
            getWorld().addObject(new Hospital(),775,getY());
            ok=0;
        }
        if (! getWorld().getObjects(Hospital.class).isEmpty())
        {
            Actor hospital = (Actor)getWorld().getObjects(Hospital.class).get(0);
            if(time%120==0)
                getWorld().addObject(new Hospital(),775,getY());
            hospital.setLocation(hospital.getX()-5,hospital.getY());
        }
        else
        {
            getWorld().addObject(new Hospital(),775,getY());
        }
    }    
}
