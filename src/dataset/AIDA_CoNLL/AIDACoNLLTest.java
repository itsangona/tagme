package dataset.AIDA_CoNLL;

import java.io.*;

import dataset.readfile.ReadFile;

public class AIDACoNLLTest {

    public static void main(String[] args) {
        File tsv = new File("H:\\DATASET\\aida-yago2-dataset\\aida-yago2-dataset\\AIDA-YAGO2-dataset.tsv");  // tSV文件路径
        ReadFile rd = new ReadFile();
        rd.readAll(tsv);
        /*BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(tsv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        try {
            List<String> allString = new ArrayList<>();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("tsv表格中所有行数："+allString.size());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        */
    }
}
