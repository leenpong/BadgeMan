package com.badgeman;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetFilePathThread implements Runnable {
    
    public String pathString;
    public List<String> listpath;
    public GetFilePathThread(String path,List<String> list) {
        this.pathString=path;
        this.listpath=list;
    }
    public static StringBuffer buffer=new StringBuffer();
    public void run() {

        File file=new File("/sdcard/");//new File(pathString);
        if (file.isDirectory()) {
            File fs[]=file.listFiles();
            if(fs!=null) {
                System.out.println("目录里面包含的所有文件个数--->"+fs.length);
                if (fs.length>0) {
                    for(int i=0;i<fs.length;i++) {
                        if (fs[i].isFile()) {
                            listpath.add(fs[i].getAbsolutePath());
                            System.out.println("文件的绝对路径---->"+fs[i].getAbsolutePath());
                        } else if (fs[i].isDirectory()) {
                            System.out.println("目录的绝对路径---->"+fs[i].getAbsolutePath()+"/");
                            new Thread(new GetFilePathThread(fs[i].getAbsolutePath()+"/", listpath)).start();
                         //    getFilePath(fs[i].getAbsolutePath(),listpath);
                             
                        }
                    }
                }
            }
        }
        
        for(int i=0;i<listpath.size();i++) {
            if (listpath.get(i).endsWith(".jpg")) {
                buffer.append(listpath.get(i).toString()+"n");
            }
        }
    }
    
    
}
