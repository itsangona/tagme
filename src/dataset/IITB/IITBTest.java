package dataset.IITB;


import java.io.*;
import java.util.List;

import dataset.file.ReadFile;
import dataset.file.WriteFile;
import it.acubelab.tagme.*;
import it.acubelab.tagme.config.TagmeConfig;
import it.acubelab.tagme.preprocessing.TopicSearcher;


public class IITBTest {
    public static void main(String[] args) throws IOException {
        String path = "./dataset/CSAW_crawledDocs.tar/CSAW_crawledDocs/crawledDocs"; //on Service
        //String path = "h:/DATASET/CSAW_crawledDocs.tar/CSAW_crawledDocs/crawledDocs/";
        ReadFile rd = new ReadFile();
        List<String> text = rd.readMultiFiles(path);

        String test = text.toString();
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

        WriteFile wt = new WriteFile();
        int annotatedEntity = 0;
        String tmp = "";
        for (Annotation a : annots) {
            if (a.isDisambiguated() && a.getRho() >= 0.1) {
                annotatedEntity++;
                tmp += "wid: " + a.getTopic() + "\r\nspot: " + ann_text.getOriginalText(a) + "\r\nrho: " + a.getRho()
                        + "\r\nWikiPage: " + searcher.getTitle(a.getTopic()) + "\r\n======================\r\n\r\n";
            }
        }
        tmp += "\nThe number of annotated entity is: " + annotatedEntity;
        wt.writeToTxt(tmp, "IITBoutput.txt");
        System.out.println("The number of annotated entity is: " + annotatedEntity);
    }
}
