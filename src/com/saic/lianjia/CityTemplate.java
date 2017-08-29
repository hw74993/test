package com.saic.lianjia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.saic.lianjia.util.ProxyUtil;

public class CityTemplate {
    
    private static final String CSV_SEPARATOR = ",";
    static String               sdate         = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    
    public static Boolean makeDirs(String filePath) {
        String folderName = filePath;
        if (folderName == null || folderName.isEmpty()) {
            return false;
        }
        
        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }
    
    public static void RunCity(String city, String url, int delay) {
        // TODO Auto-generated method stub
        // FileWriter fw;
        makeDirs(new String("/Users/haowang/Documents/Data_" + sdate));
        String opFile = new String(
                "/Users/haowang/Documents/Data_" + sdate + "/LianjiaData_" + city + "_" + sdate + ".csv").trim();
        
        // fw = new FileWriter("./out", true);
        
        // actshowMap_list
        // PrintWriter pw = new PrintWriter(fw);
        // String url = "http://nj.lianjia.com/xiaoqu/pg";
        int maxct = 2000;
        
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(opFile), "UTF-8"));
            // int count = 0;
            for (int i = 1; i < maxct; i++) {
                try {
                    String pxy = ProxyUtil.getRandomProxy();
                    // Proxy prxy = new Proxy("HTTP", "paygo.crawlera.com:8010");
                    // ProxyUtil.getRandomProxy();
                    System.out.println("getting doc");
                    Document doc = Jsoup.connect(url + i)
                            // .proxy("paygo.crawlera.com", 8010)
                            .userAgent(
                                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
                            .header("CRAWLERA_ENABLED", "True").header("CRAWLERA_USER", "<API key>")
                            .header("CRAWLERA_APIKEY", "1234").timeout(10000).get();
                    // System.out.println(url + i);
                    // System.out.println(doc);
                    org.jsoup.select.Elements els = doc.select(".img");
                    System.out.println("get doc: " + doc.baseUri());
                    // select(".img");
                    // System.out.println("get doc: " + doc.id());
                    // System.out.println("els:" + els);
                    
                    // org.jsoup.select.Elements els1 = doc.select("title");
                    //
                    // System.out.println(els1.text());
                    
                    for (Element el : els) {
                        
                        try {
                            // for (String u : el.attr("href")) {}
                            Document innerdoc = Jsoup.connect(el.select(".img").attr("href")).timeout(2000).get();
                            System.out.println("searching sub page: " + el.attr("href"));
                            StringBuffer oneLine = new StringBuffer();
                            
                            /*
                             * System.out.println(
                             * innerdoc.select(".xiaoquInfoItem").select("span").attr("mendian"));
                             * oneLine.append(
                             * innerdoc.select(".xiaoquInfoItem").select("span").attr("mendian"));
                             * oneLine.append(CSV_SEPARATOR);
                             * System.out.println(innerdoc.select(".detailTitle").text());
                             * oneLine.append(innerdoc.select(".detailTitle").text());
                             * oneLine.append(CSV_SEPARATOR);
                             * System.out.println(innerdoc.select(".xiaoquUnitPrice").text());
                             * oneLine.append(innerdoc.select(".xiaoquUnitPrice").text());
                             * oneLine.append(CSV_SEPARATOR);
                             * oneLine.append(sdate);
                             * oneLine.append(CSV_SEPARATOR);
                             * oneLine.append(city);
                             */
                            String line = "" + city + CSV_SEPARATOR + sdate + CSV_SEPARATOR
                                    + innerdoc.select(".detailTitle").text() + CSV_SEPARATOR
                                    + innerdoc.select(".xiaoquUnitPrice").text() + CSV_SEPARATOR
                                    + innerdoc.select(".xiaoquInfoItem").select("span").attr("mendian");
                            System.out.println(line);
                            oneLine.append(line);
                            
                            bw.write(oneLine.toString());
                            bw.newLine();
                            bw.flush();
                            
                            Thread.currentThread();
                            Thread.sleep((long) (1000 * Math.random() * delay + 3000));
                        } catch (Exception e) {
                            System.out.print("Inner Exception: ");
                            e.printStackTrace();
                        }
                    }
                    int perc = i * 100 / maxct;
                    System.out.println("" + perc + "% done");
                    
                    bw.flush();
                    bw.close();
                    
                } catch (Exception e) {
                    System.out.print("Outter Exception: ");
                    e.printStackTrace();
                }
                
            }
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println("ALL" + " done");
        
    }
}