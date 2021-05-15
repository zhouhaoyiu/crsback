package com.example.crs.searchtext;
import java.util.ArrayList;
import java.util.List;

public class Words {
    private String word;
    public List<String> files;
    public Words(String word){
        this.word=word;
        files=new ArrayList<>();
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return  word;
    }
}

