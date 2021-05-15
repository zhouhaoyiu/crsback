package com.example.crs.searchtext;
 



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 *    算法总结：
 *
 *    一个表方法为 方1  两个表方法为方2
 *
 *    如果文本资源文件中相互存在大量的重复单词，方1 和方2 几乎不能体现算法之间的时间差异，但是能体现出循环的数量差异。
 *    如果多次查找中某文件均包含查找单词，那么方1和方2无区别
 *
 *    方2在如下条件下优势明显：
 *    1. 多个文本资源文件中几乎不或少量存在重复单词
 *    2. 将要删除的file的inmaplist存在少量的单词
 *
 *    绝对能体现出时间差异方法：
 *
 *    某个文件中的含有一个单词是所有文件中独有的，在经过多次查找以后，file表对应文件中只含有 一个单词 那么进行删除只需要遍历一次 相比遍历所有单词能有效的节省时间
 *
 *
 */
public class Main {
    // 主表1
    private static List<Words> mainList=new ArrayList<>();
    // 住表2
    private static Map<String,List> mainMap = new HashMap<>();
    //得到处理后的文件资源
    private static List<Files> files;
    public static void main(String[] args) throws  IOException{

        int a=0;
        Scanner scanner=new Scanner(System.in);
        while(true){

            String str=scanner.nextLine();
            String []code=str.split(" ");
            switch (code[0]){
                case "find":
                    coutDatas(findWord(code[0]));
                    break;
                case "newdelete":

                    break;
                case "delete":

                    break;
                case "help":

                    break;

                default:
                    break;
            }
        }
    }

//消耗的时间为：0.117



    // 数据一次处理 去重复 大小写转换 去符号 返回文件list
    public static List<Files> initDatas() throws IOException {
//
//        Scanner scanner=new Scanner(System.in);
//        String dir=scanner.nextLine();
        String dir="D:/testSet/";
        // 可以写一个try catch 捕获空指针错误
        String [] readFromFileslist=new File(dir).list(new MyFileFilter());

        List<Files> filesList=new ArrayList<>();
        for (String filename:readFromFileslist){
            StringBuffer buffer = new StringBuffer();
            BufferedReader bf= new BufferedReader(new FileReader(dir+filename));
            String s = null;
            while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
                buffer.append(s.trim());
            }
            filesList.add(new Files(filename,buffer.toString()));
        }

        return filesList;
    }
    //执行查找操作
    public static List<String> findWord(String word){
        try {
            files=initDatas();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 需要实现是否查询过的功能
         */

        int flag=0;
        for(Words w:mainList){
            if (word.equals(w.toString())){
                return w.files;
            }
        }
        Words findedWord=new Words(word);
        //遍历所有文件
        for(Files file: files){
            // 遍历文件中的每个单词
            flag=0;
            for(String str:file.getStringArray()){
                if (str.equals(findedWord.toString())){
                    //flag表示查找到单词
                    flag++;
                    findedWord.files.add(file.toString());
                    break;
                }
            }


            if (flag!=0){
                List<Words> inmapList=mainMap.get(file.toString());
                if (inmapList==null)
                    inmapList=new ArrayList<>();
                inmapList.add(findedWord);
                mainMap.put(file.toString(),inmapList);
            }



        }
        if (findedWord.files!=null) mainList.add(findedWord);
        return findedWord.files;
    }
    //通过表2的方式删除 返回的是循环次数
    public static int deleteFile(String filename){
        // 需要首先删除数据源
        int flag=0;
        int count=0;
        long start=System.currentTimeMillis();
        try {
            for (int i = 0; i <10 ; i++) {
                Thread.sleep(10);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        for(Files file:files) {
            if (file.toString().equals(filename)) {
                files.remove(flag);
                break;
            }
            flag++;
        }
        //开始遍历mainMap中的数据 最后从mainmap中移除filename代表的文件  注意存在性问题
        List<Words> inmapList=mainMap.get(filename);
        if (inmapList==null) return 0;
        mainMap.remove(filename);
        count=remove(inmapList,filename);
        long end=System.currentTimeMillis();

        return count;
    }


    //完全循环表1 进行删除
    @Deprecated
    public static int deleteFile(String filename,int code) {
        // 需要首先删除数据源
        int flag = 0, wordflg =0;
        int count=0;
        long start=System.currentTimeMillis();
        // 睡眠10*10 millis 不然无法捕捉到运行时间
        try {
            for (int i = 0; i <10 ; i++) {
                Thread.sleep(10);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // 先删除文件表中的文件，避免下次查找出现该文件
        for (Files file : files) {
            if (file.toString().equals(filename)) {
                files.remove(flag);
                break;
            }
            flag++;
        }
        count=remove(mainList,filename);
        long end=System.currentTimeMillis();

        return count;
    }

    // 用于输出查找到的数据

    public static void coutDatas(List<String> list){

        for(String s:list){

        }


    }

    // 根据传入的list 进行删除操作 ，两个删除方法体现在传入的list差异
    private static int remove(List<Words> list,String filename){
        int count=0,fileflg=0;
        for (Words word: list) {
            for (String str : word.files) {
                if (str.equals(filename)) {
                    word.files.remove(fileflg);
                    break;
                }
                fileflg++;
                count++;
            }
            fileflg=0;
        }
        return count;

    }
}

