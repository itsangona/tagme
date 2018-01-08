package dataset.readfile;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;


public class ReadFile {
    public void readAll(File filename) {//read all lines
        BufferedReader br;
        String line = "";
        String everyLine = "";
        try {
            br = new BufferedReader(new FileReader(filename));
            List<String> allString = new ArrayList<>();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("tsv表格中所有行数：" + allString.size());
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String getToken(File filename) {
        String text = "";
        String line = "";
        String firstLine = "";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filename));
            List<String> allString = new ArrayList<>();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                firstLine = line.split("\t")[0];
                //System.out.println(firstLine);
                allString.add(firstLine);
            }
            text = allString.toString();
            //System.out.println("token：" + text);
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
