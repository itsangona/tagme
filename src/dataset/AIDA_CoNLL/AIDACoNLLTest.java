package dataset.AIDA_CoNLL;

import java.io.*;
import java.util.*;

import dataset.file.ReadFile;
import dataset.file.WriteFile;


import it.acubelab.tagme.AnnotatedText;
import it.acubelab.tagme.Annotation;
import it.acubelab.tagme.Disambiguator;
import it.acubelab.tagme.RelatednessMeasure;
import it.acubelab.tagme.RhoMeasure;
import it.acubelab.tagme.Segmentation;
import it.acubelab.tagme.TagmeParser;
import it.acubelab.tagme.config.TagmeConfig;
import it.acubelab.tagme.preprocessing.TopicSearcher;


public class AIDACoNLLTest {

    public static void main(String[] args) throws IOException {
        File tsv = new File("/media/cifs_dragon/uqwzha10/dataset/AIDA-YAGO2-dataset.tsv");  // tSV文件路径
        //File tsv = new File("H:\\DATASET\\aida-yago2-dataset\\aida-yago2-dataset\\AIDA-YAGO2-dataset.tsv");  // tSV文件路径
        ReadFile rd = new ReadFile();
        WriteFile wt = new WriteFile();
        List<String> text = rd.getToken(tsv);
        List<String> output = new ArrayList<>();
        //System.out.println(text);
        List<String> testB = new ArrayList<>();
        for (int i = 273308; i < text.size(); i++) {
            testB.add(text.get(i));
        }
        //System.out.println(testB.get(0));
        //System.out.println(testB.size());
        String test = testB.toString();
        //System.out.println("line:" + text.size());
        TagmeConfig.init();

        String lang = "en";

        AnnotatedText ann_text = new AnnotatedText(test);

        RelatednessMeasure rel = RelatednessMeasure.create(lang);

        TagmeParser parser = new TagmeParser(lang, true);
        Disambiguator disamb = new Disambiguator(lang);
        Segmentation segmentation = new Segmentation();
        RhoMeasure rho = new RhoMeasure();

        parser.parse(ann_text);
        segmentation.segment(ann_text);
        disamb.disambiguate(ann_text, rel);
        rho.calc(ann_text, rel);

        List<Annotation> annots = ann_text.getAnnotations();
        TopicSearcher searcher = new TopicSearcher(lang);
        int annotatedEntity = 0;
        String tmp = "";
        for (Annotation a : annots) {
            if (a.isDisambiguated() && a.getRho() >= 0.1) {
                annotatedEntity++;
                //System.out.println("wid: " + a.getTopic() + "\nspot: "
                //        + ann_text.getOriginalText(a) + "\nrho: " + a.getRho());
                //System.out.println("Wikipedia Page: "
                //        + searcher.getTitle(a.getTopic()));
                tmp += "wid: " + a.getTopic() + "\r\nspot: " + ann_text.getOriginalText(a) + "\r\nrho: " + a.getRho()
                        + "\r\nWikiPage: " + searcher.getTitle(a.getTopic()) + "\r\n======================\r\n\r\n";
                //output.add(tmp);
                //System.out.println(tmp);
            }
        }
        tmp += "\nThe number of annotated entity is: " + annotatedEntity;
        wt.writeToTxt(tmp,"AIDAoutput.txt");
        System.out.println("The number of annotated entity is: " + annotatedEntity);
    }
}
