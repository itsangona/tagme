package dataset.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import java.util.Arrays;


public class ReadFile {
    public List<String> readAll(File filename) {//read all lines
        BufferedReader br;
        String line = "";
        String everyLine = "";
        List<String> allString = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(filename));

            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("file中所有行数：" + allString.size());
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allString;
    }

    public void readFirstLine(File filename) {
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                System.out.println(line.split("\t")[0]);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getToken(File filename) {
        String text = "";
        String line = "";
        String firstLine = "";
        BufferedReader br;
        int k = 0;
        List<String> allString = new ArrayList<>();
        String everyLine = "";
        try {
            br = new BufferedReader(new FileReader(filename));

            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {

                everyLine = line.split("\t")[0];

                // System.out.println(everyLine);
                //System.out.println("tokn"+k);
                allString.add(everyLine);

            }
            //System.out.println("llllllll"+allString.get(10));
            /*for (int i = 0; i < allString.size(); i++) {
                text += allString.get(i).toString();
            }*/
            // text = allString.toString();

            //System.out.println("token：" + text);
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allString;
    }

    public List<String> readMultiFiles(String path) {
        File file = new File(path);
        //File[] tempList = file.listFiles();
        File[] files = file.listFiles();
        List<String> allString = new ArrayList<>();

        try {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    InputStreamReader reader = new InputStreamReader(new FileInputStream(files[i]));
                    BufferedReader br = new BufferedReader(reader);
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        allString.add(line);
                        //System.out.println(files[i].getName() + ": " + allString);
                    }
                    br.close();
                }
            }
            //System.out.println(allString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allString;
    }
}
