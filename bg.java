import greenfoot.*; 
import java.io.*;
import java.util.*;

/**
 * @Pop Alexandru Radu & @Petric Ovidiu Vasiliu
 * @3.1.0
 */
public class bg extends World
{
    public bg()throws Exception
    {    
        super(900, 600, 1); 
        int y = Greenfoot.getRandomNumber(getHeight()+50);
        if(y<50)
            y=200;
        addObject(new Ambulance(), Greenfoot.getRandomNumber(getWidth()-10), y);
        addObject(new Statusbar(), getWidth()/2, 25/2);
        int player_level;
        File res_file = new File("res.txt"); //It can be changed to another file name
        try{
            FileInputStream in = new FileInputStream(res_file);
        }
        //Search for res.txt. If not found we create a default one
        catch(IOException ioe){
            res_file.createNewFile();
            FileInputStream in = new FileInputStream(res_file);
            Properties prop = new Properties();
            prop.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(res_file);
            prop.setProperty("username", "Player");
            prop.setProperty("level", "0");
            prop.setProperty("time_power", "0");
            prop.setProperty("lives", "0");
            prop.store(out, null);
            out.close();
        }
        //Load the available res.txt data
        FileInputStream in = new FileInputStream(res_file);
        Properties prop = new Properties();
        prop.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream(res_file);
        String username = prop.getProperty("username");
        showText(username,400,10);
        player_level=Integer.parseInt(prop.getProperty("level"));
        int people=10+player_level; //We'll slowly increase the difficulty -> 10 minimum people plus level
        if(people>30)
        {
            people=30; //Trying to collect 9.000.000 people will result in an OutOfMemoryException :)
        }
        String people_string=Integer.toString(people);
        for(int i=0;i<people;i++){
            y = Greenfoot.getRandomNumber(getHeight()-10);
            if(y<50)
                y=100;
            addObject(new Boy(), Greenfoot.getRandomNumber(getWidth()-10), y);
        }
        prop.setProperty("people", people_string); //This property will let Ambulance.java know how many people we need to save
        if(player_level>=5) //Adding 3 buildings
        {
            for(int i=0;i<3;i++){
                int obstacle_x=Greenfoot.getRandomNumber(getWidth()-10);
                int obstacle_y=Greenfoot.getRandomNumber(getHeight()-10);
                addObject(new Hospital(), obstacle_x, obstacle_y);
            }
        }
        if(player_level>=10) //Adding 2 more buildings than normal
        {
            for(int i=0;i<2;i++){
                int obstacle_x=Greenfoot.getRandomNumber(getWidth()-10);
                int obstacle_y=Greenfoot.getRandomNumber(getHeight()-10);
                addObject(new Hospital(), obstacle_x, obstacle_y);
            }
        }
        if(player_level==20) //This is the boss level
        {
            removeObjects(getObjects(Hospital.class));
            removeObjects(getObjects(Boy.class));
        }
        try //This will help us if our res.txt file gets deleted or corrupted
        {
            prop.setProperty("username", username); 
        }
        catch(NullPointerException npe){
            username="Player";
            prop.setProperty("username", username);
        }
        prop.store(out, null);
        out.close();
       }
}
