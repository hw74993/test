package com.saic.baiduremote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.saic.baiduremote.BaiduDataModel.Content;

public class App {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        CSVUtil util = new CSVUtil();
        String testURL0 = "http://api.map.baidu.com/?qt=rgc&x=12947477.19&y=4846763.79&dis_poi=100&poi_num=10&ie=utf-8&oue=1&fromproduct=jsapi&res=api&callback=BMap._rd._cbk78406&ak=E4805d16520de693a3fe707cdc962045";

        String file = "/Users/haowang/Documents/BaiduTest.csv";

        ArrayList op = util.readFile(file);

        System.out.println(op);

        Content content = new BaiduDataModel().new Content();

        Iterator it1 = op.iterator();
        while (it1.hasNext()) {
            // System.out.println(new String(it1.next()));
            ArrayList ll = CSVUtil.fromCSVLinetoArray(it1.next().toString());
            // System.out.println(ll);
            String testURL = "http://api.map.baidu.com/?qt=rgc&x="
                    + new Double(Double.parseDouble((String) ll.get(1)) * 100000).toString() + "&y="
                    + new Double(Double.parseDouble((String) ll.get(0)) * 100000).toString()
                    + "&dis_poi=100&poi_num=10&ie=utf-8&oue=1&fromproduct=jsapi&res=api&callback=BMap._rd._cbk98703&ak=SCV6z94qGUWg8ukXbXGZfZnpNj8KLfT8";
            System.out.println("testURL: " + testURL0);

            Document doc;
            try {

                doc = Jsoup.parseBodyFragment(
                        Jsoup.connect(testURL0).ignoreContentType(true).get().toString());

                String all = doc.body().toString().replaceAll("<body>", "")
                        .replaceAll("</body>", "").trim();

                String json = all.substring(all.indexOf("(") + 1, all.indexOf(")"));
                // .replaceAll("(","0");

                // .replaceAll(")", "");

                // doc = Jsoup.connect(testURL).get();
                System.out.println(json);
                // org.jsoup.select.Elements els = doc.select("#house-lst li");
                // JSON.parse("/**/BMap._rd._cbk98703 && BMap._rd._cbk98703( {\"content\":{})");

                content = BaiduUtil.parseJson(json);
                System.out.println(content.getAddress_detail().getCity());
                // System.out.println(content.getResult().getX());

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // System.out.println(url + i);
            // System.out.println(doc);

            try {
                Thread.currentThread();
                Thread.sleep((long) (1000 * Math.random() * 2 + 3));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
