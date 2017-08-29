package com.saic.lianjia;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Beijing {

    private static final String CSV_SEPARATOR = ",";
    static String               sdate         = new SimpleDateFormat("yyyy-MM-dd")
            .format(new Date());
    static String               opFile        = new String(
            "/Users/haowang/Documents/Data/LianjiaData_Beijing_" + sdate + ".csv").trim();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // FileWriter fw;
        try {
            // fw = new FileWriter("./out", true);

            // actshowMap_list
            // PrintWriter pw = new PrintWriter(fw);
            String url = "http://bj.lianjia.com/xiaoqu/pg";
            int maxct = 200;

            // int count = 0;

            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(opFile), "UTF-8"));
            for (int i = 1; i < maxct; i++) {
                Document doc = Jsoup.connect(url + i).get();
                // System.out.println(url + i);
                // System.out.println(doc);

                org.jsoup.select.Elements els = doc.select(".img");
                System.out.println("get doc: " + doc.id());
                // System.out.println("els:" + els);
                for (Element el : els) {

                    // for (String u : el.attr("href")) {}
                    Document innerdoc = Jsoup.connect(el.attr("href")).get();
                    System.out.println("searching sub page: " + el.attr("href"));
                    StringBuffer oneLine = new StringBuffer();

                    oneLine.append(
                            innerdoc.select(".xiaoquInfoItem").select("span").attr("mendian"));
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(innerdoc.select(".detailTitle").text());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(innerdoc.select(".xiaoquUnitPrice").text());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(sdate);
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append("Beijing");

                    bw.write(oneLine.toString());
                    bw.newLine();

                    Thread.currentThread();
                    Thread.sleep(1000);

                }

                int perc = i * 100 / maxct;
                System.out.println("" + perc + "% done");
            }
            bw.flush();
            bw.close();

            System.out.println("ALL" + " done");
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}