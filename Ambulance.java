import greenfoot.*; 
import java.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class Ambulance extends Actor 
{
    int secret=0,saved_people=0,people=0,level=0,level2,time_power=0,time_power_used=0,lives=0,new_chance=0,won=0,ok=1,ok2=1,boss_life=3;
    private int time = 900; //Default is 900 for 15 seconds, but for debug porposes change to 1200 for 20 seconds
    String username;
    public void act()
    {
        if (time>0 && won==0 && boss_life>0)
        {
            if(level!=20)
            {
                time--;
                getWorld().showText(Integer.toString(time/60),50,30); //Showing the time left
            }
            else
                getWorld().showText("Boss: " + boss_life, 700,100);
            if(time == 0){
                getWorld().showText("You lost! Press 'Reset' to try again.",400,300); //Centered text
                Greenfoot.playSound("level_fail.mp3");
            }
            if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")){
                setLocation(getX()-10, getY());
                setRotation(0);
            }
            if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")){
                setLocation(getX()+10, getY());
                setRotation(180);
            }
            if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")){
                setLocation(getX(), getY()-10);
                setRotation(90);
            }
            if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")){
                setLocation(getX(), getY()+10);
                setRotation(270);
            }
            if(Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("w"))
            {
                setLocation(getX()-1/2, getY());
                setLocation(getX(), getY()-1/2);
                setRotation(45);
            }
            if(Greenfoot.isKeyDown("left")&&Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("a")&&Greenfoot.isKeyDown("s"))
            {
                setLocation(getX()-1/2, getY());
                setLocation(getX(), getY()+1/2);
                setRotation(-45);
            }
            if(Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("w"))
            {
                setLocation(getX()+1/2, getY());
                setLocation(getX(), getY()-1/2);
                setRotation(180-45);
            }
            if(Greenfoot.isKeyDown("right")&&Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("d")&&Greenfoot.isKeyDown("s"))
            {
                setLocation(getX()+1/2, getY());
                setLocation(getX(), getY()+1/2);
                setRotation(45-180);
            }
            if(Greenfoot.isKeyDown("p"))
                Greenfoot.stop();
            if(Greenfoot.isKeyDown("q")) //Time power key
            {
                time_power_used=1;
                Greenfoot.playSound("power_used.mp3");
            }
            if(Greenfoot.isKeyDown("e")) //New chance
            {
                new_chance=1;
                Greenfoot.playSound("power_used.mp3");
            }
            if(Greenfoot.isKeyDown("l") && secret==1) //The secret key ;)
            {
                if(ok==1){
                    saved_people++;
                    ok=0;
                }
            }
            if(isTouching(Hospital.class))
            {
                if(lives==0)
                {
                    setRotation(0);
                    setLocation(400,300);
                    setImage(new GreenfootImage("You crashed in the building! Press 'Reset' and try again.", 25, Color.RED, Color.BLACK));
                    Greenfoot.playSound("level_fail.mp3");
                    time=0;
                }
                if(lives==1){
                    removeTouching(Hospital.class);
                    lives=0;
                }
            }
            if(isTouching(Boy.class)){
                removeTouching(Boy.class);
                score();
            }
            if(isTouching(Hospital_boss.class))
            {
                boss_life--;
                setLocation(150,300);
                if(boss_life==0){
                    getWorld().showText("Boss: " + boss_life, 700,100);
                    boss_defeat();
                    Greenfoot.stop();
                }
            }
        }
        else if(lives==0)
                Greenfoot.stop();
        else
            Greenfoot.stop();
    }
    private void boss_defeat() {
        setRotation(0);
        setLocation(400,300);
        setImage(new GreenfootImage("You won the game! You can play it in continue if you want :)", 25, Color.GREEN, Color.BLACK));
        Greenfoot.playSound("level_success.mp3");
        File res_file = new File("res.txt");
        try{
            FileInputStream in = new FileInputStream(res_file);
            Properties prop = new Properties();
            prop.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(res_file);
            level++;
            String level_string = Integer.toString(level);
            prop.setProperty("level", level_string);
            prop.store(out, null);
            out.close();
        }
        catch(IOException ioe2){
            System.out.println("Error: Can't find 'res.txt'!");
        }
    }
    private void score() {
        saved_people++;
        if(level!=20)
            getWorld().showText("Level/saved/total " + level + "/" + saved_people + "/" + people,125,10);
        //...Just a delimiter
        if(level==11 && saved_people==1) //I'm a notification :)
        {
            System.out.println("*******************************************************************************");
            System.out.println("*I need to stop you! You're good!");
            System.out.println("*******************************************************************************");
            Greenfoot.stop();
        }
        if(level==12 && saved_people==1) //Me too :D
        {
            System.out.println("*******************************************************************************");
            System.out.println("*I came back...to annoy you, muahahaha >:)");
            System.out.println("*******************************************************************************");
            Greenfoot.stop();
        }
        if(level==13 && saved_people==9) //Don't forget me guys ;)
        {
            System.out.println("*******************************************************************************");
            System.out.println("*I'm out! Your level is big.");
            System.out.println("*******************************************************************************");
            Greenfoot.stop();
        }
        if(level==14 && saved_people==1) //I'm the compiler :) /*Actually not.*/
        {
            System.out.println("*******************************************************************************");
            System.out.println("*Oh sorry! I remeber that I have level 3897256342658263. Good luck with that ;)");
            System.out.println("*******************************************************************************");
            Greenfoot.stop();
        }
        if(level==14 && saved_people==5) //OK guys calm down, I'm the OS >:)
        {
            System.out.println("*******************************************************************************");
            System.out.println("*You know... I can make this game harder. But I won't.");
            System.out.println("*******************************************************************************");
            Greenfoot.stop();
        }
        if(level==19 && saved_people==5) //Shut up, I'm the energy B-)
        {
            if(ok2==1){
                System.out.println("*******************************************************************************");
                System.out.println("*This game is really hard so you will fail. :) Only a secret key can help you.");
                System.out.println("*******************************************************************************");
                saved_people--;
                secret=1;
                ok2=0;
                Greenfoot.stop();
            }
        }
        //...and another one
        File res_file = new File("res.txt");
        try{
            FileInputStream in = new FileInputStream(res_file);
            Properties prop = new Properties();
            prop.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(res_file);
            if(new_chance==1)
            {
                lives = Integer.parseInt(prop.getProperty("lives"));
                if(lives==1){
                    new_chance=0;
                    prop.setProperty("lives", "0");
                }
            }
            if(time_power_used==1){
                time_power = Integer.parseInt(prop.getProperty("time_power"));
                time_power_used=0;
                prop.setProperty("time_power", "0");
                time+=time_power;
                time_power=0;
            }
            level = Integer.parseInt(prop.getProperty("level"));
            people = Integer.parseInt(prop.getProperty("people")); //Getting the value of people_string from res.txt (original value from bg.java)
            if(saved_people>=people){
                setRotation(0);
                setLocation(400,300);
                setImage(new GreenfootImage("You won! Press 'Reset' for next level.", 25, Color.GREEN, Color.BLACK));
                Greenfoot.playSound("level_success.mp3");
                level++;
                String level_string = Integer.toString(level);
                prop.setProperty("level", level_string);
                if(level%5==0 && level!=0)
                {
                    time_power=Greenfoot.getRandomNumber(120)+60;
                    getWorld().showText("You got a bonus of " + time_power/60 + " seconds.",400,350); //Below center
                    String time_power_string = Integer.toString(time_power);
                    prop.setProperty("time_power", time_power_string);
                }
                if(level%5==0 && level>=10)
                {
                    getWorld().showText("You got a new bonus chance.",400,375); //Below center
                    prop.setProperty("lives", "1");
                }
                //The BEGINNING of notifications
                if(level==1)
                {
                    System.out.println("*******************************************************************************");
                    System.out.println("*Good! You did it! I come back at level 10. Close me now.");
                    System.out.println("*******************************************************************************");
                    Greenfoot.stop();
                }
                if(level==5)
                {
                    System.out.println("*******************************************************************************");
                    System.out.println("*Level 5! Wow! You got a gift :). Look at the game text (below 'You won!').");
                    System.out.println("*You get that bonus from 5 to 5 levels. Use it with 'Q' and wisely!");
                    System.out.println("*******************************************************************************");
                    Greenfoot.stop();
                }
                if(level==10)
                {
                    System.out.println("*******************************************************************************");
                    System.out.println("*This is really hard for everyone (except me :D).");
                    System.out.println("*I'll give you a new chance from 10 to 10 levels. Use it wisely with 'E'.");
                    System.out.println("*******************************************************************************");
                    Greenfoot.stop();
                }
                //The END of notifications
                won=1;
            }
            prop.store(out, null);
            out.close();
        }
        catch(IOException ioe2){
            System.out.println("Error: Can't find 'res.txt'!");
        }
    }
    public void addedToWorld(World w)
    {
        File res_file = new File("res.txt");
        try{ //Reading res.txt
            FileInputStream in = new FileInputStream(res_file);
            Properties prop = new Properties();
            prop.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(res_file);
            try{
                level = Integer.parseInt(prop.getProperty("level"));
                people = Integer.parseInt(prop.getProperty("people")); //Getting the value of people_string from res.txt (original value from bg.java)
                username = prop.getProperty("username");
                if(username.equals("Player"))
                {
                    username = Greenfoot.ask("Enter your name:");
                    prop.setProperty("username", username);
                }
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("Please delete \"res.txt\".");
            }
            prop.store(out, null);
            out.close();
        }
        catch(IOException ioe2){
            System.out.println("Warning: Can't find 'res.txt'! Another one will be created.");
        }
        if(level==20) //Preparing for boss level
        {
            setLocation(150,300);
            getWorld().addObject(new Hospital_boss(),800,300);
            getWorld().showText("Boss: " + boss_life, 700,100);
        }
        else
            getWorld().showText("Level/saved " + level + "/" + saved_people,125,10);
    }
}
