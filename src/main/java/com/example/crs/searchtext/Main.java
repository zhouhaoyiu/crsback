package com.example.crs.searchtext;
 



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 *    �㷨�ܽ᣺
 *
 *    һ������Ϊ ��1  ��������Ϊ��2
 *
 *    ����ı���Դ�ļ����໥���ڴ������ظ����ʣ���1 �ͷ�2 �������������㷨֮���ʱ����죬���������ֳ�ѭ�����������졣
 *    �����β�����ĳ�ļ����������ҵ��ʣ���ô��1�ͷ�2������
 *
 *    ��2�������������������ԣ�
 *    1. ����ı���Դ�ļ��м����������������ظ�����
 *    2. ��Ҫɾ����file��inmaplist���������ĵ���
 *
 *    ���������ֳ�ʱ����췽����
 *
 *    ĳ���ļ��еĺ���һ�������������ļ��ж��еģ��ھ�����β����Ժ�file���Ӧ�ļ���ֻ���� һ������ ��ô����ɾ��ֻ��Ҫ����һ�� ��ȱ������е�������Ч�Ľ�ʡʱ��
 *
 *
 */
public class Main {
    // ����1
    private static List<Words> mainList=new ArrayList<>();
    // ס��2
    private static Map<String,List> mainMap = new HashMap<>();
    //�õ��������ļ���Դ
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

//���ĵ�ʱ��Ϊ��0.117



    // ����һ�δ��� ȥ�ظ� ��Сдת�� ȥ���� �����ļ�list
    public static List<Files> initDatas() throws IOException {
//
//        Scanner scanner=new Scanner(System.in);
//        String dir=scanner.nextLine();
        String dir="D:/testSet/";
        // ����дһ��try catch �����ָ�����
        String [] readFromFileslist=new File(dir).list(new MyFileFilter());

        List<Files> filesList=new ArrayList<>();
        for (String filename:readFromFileslist){
            StringBuffer buffer = new StringBuffer();
            BufferedReader bf= new BufferedReader(new FileReader(dir+filename));
            String s = null;
            while((s = bf.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
                buffer.append(s.trim());
            }
            filesList.add(new Files(filename,buffer.toString()));
        }

        return filesList;
    }
    //ִ�в��Ҳ���
    public static List<String> findWord(String word){
        try {
            files=initDatas();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * ��Ҫʵ���Ƿ��ѯ���Ĺ���
         */

        int flag=0;
        for(Words w:mainList){
            if (word.equals(w.toString())){
                return w.files;
            }
        }
        Words findedWord=new Words(word);
        //���������ļ�
        for(Files file: files){
            // �����ļ��е�ÿ������
            flag=0;
            for(String str:file.getStringArray()){
                if (str.equals(findedWord.toString())){
                    //flag��ʾ���ҵ�����
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
    //ͨ����2�ķ�ʽɾ�� ���ص���ѭ������
    public static int deleteFile(String filename){
        // ��Ҫ����ɾ������Դ
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
        //��ʼ����mainMap�е����� ����mainmap���Ƴ�filename������ļ�  ע�����������
        List<Words> inmapList=mainMap.get(filename);
        if (inmapList==null) return 0;
        mainMap.remove(filename);
        count=remove(inmapList,filename);
        long end=System.currentTimeMillis();

        return count;
    }


    //��ȫѭ����1 ����ɾ��
    @Deprecated
    public static int deleteFile(String filename,int code) {
        // ��Ҫ����ɾ������Դ
        int flag = 0, wordflg =0;
        int count=0;
        long start=System.currentTimeMillis();
        // ˯��10*10 millis ��Ȼ�޷���׽������ʱ��
        try {
            for (int i = 0; i <10 ; i++) {
                Thread.sleep(10);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // ��ɾ���ļ����е��ļ��������´β��ҳ��ָ��ļ�
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

    // ����������ҵ�������

    public static void coutDatas(List<String> list){

        for(String s:list){

        }


    }

    // ���ݴ����list ����ɾ������ ������ɾ�����������ڴ����list����
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

