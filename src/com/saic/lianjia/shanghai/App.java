package com.saic.lianjia.shanghai;

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

public class App {

    private static final String CSV_SEPARATOR = ",";
    static String               sdate         = new SimpleDateFormat("yyyy-MM-dd")
            .format(new Date());
    static String               opFile        = new String(
            "/Users/haowang/Documents/Data/LianjiaData_Shanghai_" + sdate + ".csv").trim();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // FileWriter fw;
        try {
            // fw = new FileWriter("./out", true);

            // actshowMap_list
            // PrintWriter pw = new PrintWriter(fw);
            String url = "http://sh.lianjia.com/xiaoqu/d";
            int maxct = 2000;

            // int count = 0;

            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(opFile), "UTF-8"));
            for (int i = 1; i < maxct; i++) {
                Document doc;
                doc = Jsoup.connect(url + i).get();
                // System.out.println(url + i);
                // System.out.println(doc);
                org.jsoup.select.Elements els = doc.select("#house-lst li");
                for (Element el : els) {

                    /*
                     * System.out.println(el.select(".info-panel h2").text() +
                     * "|"
                     * + el.select(".actshowMap_list").attr("xiaoqu")
                     * + el.select(".num").text() + (count++));
                     * pw.println(el.select(".info-panel h2").text() + "|"
                     * + el.select(".actshowMap_list").attr("xiaoqu")
                     * + el.select(".num").text());
                     */
                    StringBuffer oneLine = new StringBuffer();
                    oneLine.append(el.select(".actshowMap_list").attr("xiaoqu").replaceAll(" ", "")
                            .replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\'", ""));
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(el.select(".num").text().replaceAll(" ", ","));
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(sdate);
                    bw.write(oneLine.toString());
                    bw.newLine();

                    /*
                     * oneLine.append(product.getDataId());
                     * oneLine.append(CSV_SEPARATOR);
                     * oneLine.append(product.getDealAvgPrice());
                     * oneLine.append(CSV_SEPARATOR);
                     * oneLine.append(product.getLatitude());
                     * oneLine.append(CSV_SEPARATOR);
                     * oneLine.append(product.getLongitude());
                     * oneLine.append(CSV_SEPARATOR);
                     * oneLine.append(product.getSaleAvgPrice());
                     * oneLine.append(CSV_SEPARATOR);
                     * oneLine.append(product.getSaleTotal());
                     * oneLine.append(CSV_SEPARATOR);
                     * oneLine.append(product.getShowName());
                     * oneLine.append(CSV_SEPARATOR);
                     * oneLine.append(product.getSort());
                     * oneLine.append(CSV_SEPARATOR);
                     * oneLine.append(product.getType());
                     * oneLine.append(CSV_SEPARATOR);
                     * oneLine.append(sdate);
                     * bw.write(oneLine.toString());
                     * bw.newLine();
                     */

                    // pw.flush();
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
        }

    }
}