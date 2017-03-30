package com.example.lemgo.mymenu;

import java.io.File;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class FileUntil {
    private File f  = null;
    public FileUntil(File file){
        f  = file;
    }

    public File getF() {
        return f;
    }

    @Override
    public String toString() {
        if (getF()==null){
            return " ";
        }
        return String.format("[%s]%s",getF().isDirectory()?"Dir":"file",getF().getName());
    }
}
