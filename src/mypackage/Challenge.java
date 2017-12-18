package mypackage;

public class Challenge {

    private int screen_taps;
    private int tubs_avoided;
    private int score;
    private boolean new_best_score;
    private boolean is_done;

    public Challenge(int screen_taps, int tubs_avoided, int score, boolean new_best_score){
        this.screen_taps = screen_taps;
        this.tubs_avoided = tubs_avoided;
        this.score = score;
        this.new_best_score = new_best_score;
        is_done = false;
    }

    public boolean check(int screen_taps, int tubs_avoided, int score, boolean new_best_score){
        if(this.screen_taps != 0){
            if(screen_taps >= this.screen_taps){
                return true;
            }
        }
        if(this.tubs_avoided != 0){
            if(tubs_avoided >= this.tubs_avoided){
                return true;
            }
        }
        if(this.score != 0){
            if(score >= this.score){
                return true;
            }
        }
        if(this.new_best_score != false){
            if(new_best_score == true){
                return true;
            }
        }
        return false;
    }

    public int getScreen_taps(){
        return screen_taps;
    }

    public int getScore(){
        return score;
    }

    public int getTubs_avoided() {
        return tubs_avoided;
    }

    public boolean getNew_best_score(){
        return new_best_score;
    }


    public void setIs_done(boolean is_done){
        this.is_done = is_done;
    }

    public boolean getIs_done(){
        return is_done;
    }
}