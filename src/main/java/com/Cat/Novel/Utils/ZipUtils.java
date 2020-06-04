package com.Cat.Novel.Utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩文件
 * @author Mr.Cat
 * @date 2020-1-16 14:12
 */
public class ZipUtils {

    /**
     * 压缩文件
     * @param path  文件路径
     * @return  压缩是否成功
     * @throws IOException
     */
    public static boolean compressFileToZip(String path) throws IOException {
        boolean bool=false;
        ZipOutputStream zipOut=null;
        File file =new File(path);
        if(file.isDirectory()){
            zipOut=new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(path+".zip")));
            compressFileZip(zipOut,file,"");
        }else{
            zipOut=new ZipOutputStream(new BufferedOutputStream((new FileOutputStream(path.substring(0,path.lastIndexOf("."))+"zip"))));
            zipOFile(zipOut,file);
        }
        zipOut.closeEntry();
        zipOut.close();
        bool=true;
        return  bool;
    }

    /**
     * 递归文件夹
     * @param zipOut
     * @param file
     * @param path
     * @throws IOException
     */
    private static void compressFileZip(ZipOutputStream zipOut, File file, String path) throws IOException {
        //列出所有文件
        File[] listFiles=file.listFiles();
        for(File f:listFiles){
            if(f.isDirectory()){
                if(path.equals("")){
                    compressFileZip(zipOut,f,f.getName());
                }else{
                    compressFileZip(zipOut,f,path+File.separator + f.getName());
                }
            }else{
                zip(zipOut,f,path);
            }
        }
    }

    /**
     * 文件夹中每个文件压缩具体操作
     * @param zipOut
     * @param file
     * @param path
     * @throws IOException
     */
    private static void zip(ZipOutputStream zipOut, File file, String path) throws IOException {
        ZipEntry zEntry=null;
        if(path.equals("")){
            zEntry = new ZipEntry(file.getName());
        }else{
            zEntry=new ZipEntry(path + File.separator + file.getName());
        }
        zipOut.putNextEntry(zEntry);
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
        byte[] by = new byte[10*1024];
        int len =0;
        while((len=bis.read(by))!=-1){
            zipOut.write(by,0,len);
        }
        bis.close();
    }

    /**
     * 单个文件
     * @param zipOut
     * @param file
     * @throws IOException
     */
    private static void zipOFile(ZipOutputStream zipOut, File file) throws IOException {
        ZipEntry zipEntry=new ZipEntry(file.getName());
        zipOut.putNextEntry(zipEntry);
        BufferedInputStream bis =new BufferedInputStream(new FileInputStream(file));
        byte[] by=new byte[10*1024];
        int len =0;
        while( (len =bis.read(by))!=-1){
            zipOut.write(by,0,len);
        }
        bis.close();
    }
}
