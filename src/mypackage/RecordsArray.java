package mypackage;

import java.io.*;
import java.util.ArrayList;

public class RecordsArray {

    private ArrayList<Record> array;

    public RecordsArray(){
        array = new ArrayList<>();
    }

    public void add(Record record, int index){
        if(size() == 3){
            for(int i = size()-1; i == 1; i--){
                array.set(i, array.get(i-1));
            }
        }
        array.add(index, record);

    }

    public Record get(int i){
        return array.get(i);
    }

    public int size(){
        return array.size();
    }

    public boolean is_empty(){
        if(array.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    public void write_to_file(File f){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            for(Record m : array){
                oos.writeObject(m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read_from_file(File f){
        try {
            Record m = null;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            while((m = (Record)ois.readObject()) != null){
                array.add(m);
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}