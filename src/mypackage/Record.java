package mypackage;

import java.io.Serializable;

public class Record implements Serializable{

    private int score;
    private String username;

    public Record(){}

    public Record(int score, String username){
        this.score = score;
        this.username = username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public int getScore(){
        return score;
    }

    public String getUsername() {
        return username;
    }

    public String toString(){
        return username + " " + score;
    }

}