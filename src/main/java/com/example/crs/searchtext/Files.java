package com.example.crs.searchtext;
import java.util.List;

public class Files {
    public String filename;
    public String str;
    public List<Words> words;
    public Files(String filename,String str){
        str = str.replaceAll("[\\pP¡®¡¯¡°¡±]", "");
        str=str.toLowerCase();
        this.str=str;
        this.filename=filename;
    }
    public String [] getStringArray(){
        str = str.replaceAll("[\\pP¡®¡¯¡°¡±]", "");
        return str.split(" ");
    }

    @Override
    public String toString() {
        return  filename;
    }
}

