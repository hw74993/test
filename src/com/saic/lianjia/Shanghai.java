package com.saic.lianjia;

import java.io.BufferedWriter;
import java.io.File;
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

public class Shanghai {
    
    private static final String CSV_SEPARATOR = ",";
    static String               sdate         = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    // static String opFile = new String( "/Users/haowang/Documents/Data/LianjiaData_Shanghai_" +
    // sdate + ".csv").trim();
    
    public static Boolean makeDirs(String filePath) {
        String folderName = filePath;
        if (folderName == null || folderName.isEmpty()) {
            return false;
        }
        
        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }
    
    public static void RunShanghai() {
        // TODO Auto-generated method stub
        // FileWriter fw;
        
        // fw = new FileWriter("./out", true);
        
        // actshowMap_list
        // PrintWriter pw = new PrintWriter(fw);
        String url = "http://sh.lianjia.com/xiaoqu/d";
        int maxct = 3000;
        
        makeDirs(new String("D:\\Projects\\0009_Crawler\\OutputData\\prj.webcrawler.lianjia\\Data_" + sdate));
        String opFile = new String("D:\\Projects\\0009_Crawler\\OutputData\\prj.webcrawler.lianjia\\Data_" + sdate
                + "\\LianjiaData_" + "Shanghai" + "_" + sdate + ".csv").trim();
        
        // int count = 0;
        
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(opFile), "UTF-8"));
            
            for (int i = 1; i < maxct; i++) {
                try {
                    
                    Document doc;
                    doc = Jsoup.connect(url + i).get();
                    // System.out.println(url + i);
                    // System.out.println(doc);
                    org.jsoup.select.Elements els = doc.select("#house-lst li");
                    
                    for (Element el : els) {
                        
                        StringBuffer oneLine = new StringBuffer();
                        
                        /*
                         * System.out.println(el.select(".info-panel h2").text() +
                         * "|"
                         * + el.select(".actshowMap_list").attr("xiaoqu")
                         * + el.select(".num").text() + (count++));
                         * pw.println(el.select(".info-panel h2").text() + "|"
                         * + el.select(".actshowMap_list").attr("xiaoqu")
                         * + el.select(".num").text());
                         */
                        
                        oneLine.append(el.select(".actshowMap_list").attr("xiaoqu").replaceAll(" ", "")
                                .replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\'", ""));
                        oneLine.append(CSV_SEPARATOR);
                        oneLine.append(el.select(".num").text().replaceAll(" ", ","));
                        oneLine.append(CSV_SEPARATOR);
                        oneLine.append(sdate);
                        oneLine.append(CSV_SEPARATOR);
                        oneLine.append("Shanghai");
                        
                        System.out.println(oneLine.toString());
                        bw.write(oneLine.toString());
                        
                        bw.newLine();
                        bw.flush();
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
                    
                    Thread.currentThread();
                    Thread.sleep((long) (1000 * Math.random() * 2 + 3000));
                    
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            bw.close();
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("ALL" + " done");
        
    }
}