package org.read;

import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 张弘毅
 * @version 1.0
 * @date 2022/3/22 上午11:44
 */
public class ReadJavaDoc {
    public static void main(String[] args) {
        //创建一个新的临时文件夹，并把需要复制的文件放入其中，有重复的先删除
        String dir = args[0];
        ReadJavaDoc readJavaDoc = new ReadJavaDoc();
        String targetDir = readJavaDoc.mkdir(dir);
        if(!StringUtil.isBlank(targetDir)){
            readJavaDoc.Clone(dir,targetDir);
            //读取javadoc
//            Document html = Jsoup.parse(, "UTF-8");
            //翻译
            //替换
        }else{
            System.out.println("文件夹已存在，需要手动删除临时文件");
        }
    }

    /**
     * 创建临时文件
     * @param dir
     * @return
     */
    public String mkdir(String dir){
        String osName = System.getProperty("os.name");
        String tempDir,lastDir;
        int lastIndex = 0;
        if (osName.equalsIgnoreCase("Windows")){
            lastIndex = dir.lastIndexOf("\\");
            if(lastIndex==(dir.length()-2)){
                lastIndex = dir.lastIndexOf("\\",dir.length()-3);
            }
            lastDir = "\\temp"+dir.substring(lastIndex);
        }else{
            lastIndex = dir.lastIndexOf("/");
            if(lastIndex==(dir.length()-1)){
                lastIndex = dir.lastIndexOf("/",dir.length()-2);
            }
            lastDir = "/temp"+dir.substring(lastIndex);
        }
        tempDir = dir.substring(0,lastIndex);
        File file = new File(tempDir+lastDir);
        file.mkdir();
        return tempDir+lastDir;
    }

    /**
     * 遍历文件夹并复制
     * @param sourseUrl
     * @param targetUrl
     */
    public void Clone(String sourseUrl,String targetUrl){
        //获取目录下所有文件
        File f = new File(sourseUrl);
        File[] allf = f.listFiles();

        //遍历所有文件
        for(File fi:allf) {
            try {
                targetUrl = targetUrl+fi.getCanonicalPath().substring(fi.getCanonicalPath().lastIndexOf("/"));

                //创建目录或文件
                if(fi.isDirectory()) {
                    Createflies(targetUrl);
                }else {
                    fileInputOutput(fi.getAbsolutePath(),targetUrl);
                }

                //递归调用
                if(fi.isDirectory()) {
                    Clone(fi.getAbsolutePath(),targetUrl);
                }

            }catch (Exception e) {
                System.out.println("error");
            }
        }
    }

    /**
     * 复制文件
     * @param sourse
     * @param target
     */
    public void fileInputOutput(String sourse,String target) {
        try {
            File s = new File(sourse);
            File t = new File(target);

            FileInputStream fin = new FileInputStream(s);
            FileOutputStream fout = new FileOutputStream(t);

            byte[] a = new byte[1024*1024*4];
            int b = -1;

            //边读边写
            while((b = fin.read(a))!=-1) {
                fout.write(a,0,b);
            }

            fout.close();
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建目录
     * @param name
     * @return
     */
    public boolean Createflies(String name) {
        boolean flag=false;
        File file=new File(name);
        //创建目录
        if(file.mkdir() == true){
            System.out.println("文件夹创建成功！");
            flag=true;
        }else {
            System.out.println("文件夹创建失败！");
            flag=false;

        }

        return flag;
    }
}
