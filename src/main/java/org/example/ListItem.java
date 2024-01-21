package org.example;

public class ListItem {
    public String fileName;
    public boolean fileEnd;

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileEnd(boolean isHasFileEnd){
        this.fileEnd = isHasFileEnd;
    }

    public boolean getFileEnd(){
        return fileEnd;
    }

    public ListItem (){
        fileName = "";
        fileEnd = false;
    }

    public ListItem (String fileName, boolean fileEnd){
        this.fileName = fileName;
        this.fileEnd = fileEnd;
    }
}
