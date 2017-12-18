package mypackage;

import java.io.*;

import java.util.ArrayList;


public class ChallengesArray {

    private ArrayList<Challenge> array;

    public ChallengesArray(){
        Challenge c1 = new Challenge(10,0,0,false);
        Challenge c2 = new Challenge(0, 5, 0,false);
        Challenge c3 = new Challenge(0, 0, 10,false);
        Challenge c4 = new Challenge(200,0,0,false);
        Challenge c5 = new Challenge(0,  70, 0,false);
        Challenge c6 = new Challenge(0, 0, 30,false);
        Challenge c7 = new Challenge(150,0,0,false);
        Challenge c8 = new Challenge(0, 3000, 0,false);
        Challenge c9 = new Challenge(0, 0, 1000,false);
        Challenge c10 = new Challenge(0, 0, 0,true);

        array = new ArrayList<>(10);
        array.add(c1);
        array.add(c2);
        array.add(c3);
        array.add(c4);
        array.add(c5);
        array.add(c6);
        array.add(c7);
        array.add(c8);
        array.add(c9);
        array.add(c10);
    }

    public boolean check(){
        for (Challenge c : array){
            if(!c.getIs_done()){
                return false;
            }
        }
        return true;
    }

    public Challenge get_current(){
        for (Challenge c : array){
            if(!c.getIs_done()){
                return c;
            }
        }
        return null;
    }

    public void read_from_file(File f){
        BufferedReader reader = null;
        int i = 0;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    if(line.equals("true")){
                        array.get(i).setIs_done(true);
                    } else {
                        array.get(i).setIs_done(false);
                    }
                    i++;
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write_to_file(File f) {
        PrintWriter fos = null;
        try {
            fos = new PrintWriter(new FileWriter(f));
            String str = null;
            for(Challenge c : array) {
                str = c.getIs_done() + "";
                fos.println(str);
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}