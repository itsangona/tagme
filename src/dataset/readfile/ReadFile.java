package dataset.readfile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public void readAll(File filename){
        BufferedReader br;
        String line = "";
        String everyLine = "";
        try
        {
            br = new BufferedReader(new FileReader(filename));
            List<String> allString = new ArrayList<>();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("tsv表格中所有行数："+allString.size());
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
