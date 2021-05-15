package com.example.crs.searchtext;
import java.io.File;
import java.io.FilenameFilter;

public class MyFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return name.matches(".*txt");
    }
}
