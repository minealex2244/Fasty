import greenfoot.*; 
import java.io.*;
import java.util.*;

/**
 * @Pop Alexandru Radu & @Petric Ovidiu Vasiliu
 * @3.0.0
 */
public class bg extends World
{
    public bg()throws Exception
    {    
        super(900, 650, 1); 
        addObject(new Ambulance(), Greenfoot.getRandomNumber(getWidth()-10), Greenfoot.getRandomNumber(getHeight()-10));
        int player_level;
        File res_file = new File("res.txt"); //it can be changed to another file name
        try{
            FileInputStream in = new FileInputStream(res_file);
        }
        //search for res.txt, if not found we create a default one
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
        //load the available res.txt
        FileInputStream in = new FileInputStream(res_file);
        Properties prop = new Properties();
        prop.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream(res_file);
        String username = prop.getProperty("username");
        showText(username,100,220);
        player_level=Integer.parseInt(prop.getProperty("level"));
        int people=10+player_level;
        if(people>30)
        {
            people=30;
        }
        String people_string=Integer.toString(people);
        for(int i=0;i<people;i++)
            addObject(new Boy(), Greenfoot.getRandomNumber(getWidth()-10), Greenfoot.getRandomNumber(getHeight()-10));
        prop.setProperty("people", people_string); //optimization to get this value between classes (bg.java and Ambulance.java)
        if(player_level>=5){
            for(int i=0;i<3;i++){
                int obstacle_x=Greenfoot.getRandomNumber(getWidth()-10);
                int obstacle_y=Greenfoot.getRandomNumber(getHeight()-10);
                addObject(new Hospital(), obstacle_x, obstacle_y);
            }
        }
        if(player_level>=10){
            for(int i=0;i<2;i++){
                int obstacle_x=Greenfoot.getRandomNumber(getWidth()-10);
                int obstacle_y=Greenfoot.getRandomNumber(getHeight()-10);
                addObject(new Hospital(), obstacle_x, obstacle_y);
            }
        }
        if(player_level==20)
        {
            removeObjects(getObjects(Hospital.class));
            removeObjects(getObjects(Boy.class));
        }
        try{
            prop.setProperty("username", username); //comment this if it doesn't compile (NullPointerException) in case of deleted res.txt (location is where README.TXT is) then re-enable after a successful compilation
        }
        catch(NullPointerException npe){
            username="Player";
            prop.setProperty("username", username); //this too
        }
        prop.store(out, null);
        out.close();
       }
}