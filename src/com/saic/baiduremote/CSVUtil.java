package com.saic.baiduremote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVUtil {
    public ArrayList readFile(String file) {
        // TODO Auto-generated method stub

        // String testURL =
        // "http://api.map.baidu.com/?qt=rgc&x=13522743.68&y=3650121.8&dis_poi=100&poi_num=10&ie=utf-8&oue=1&fromproduct=jsapi&res=api&callback=BMap._rd._cbk98703&ak=SCV6z94qGUWg8ukXbXGZfZnpNj8KLfT8";

        // String file = "/Users/haowang/Documents/BaiduTest.csv";

        ArrayList op = new ArrayList();
        File inFile = new File(file);// 读取的CSV文件

        String line = null;

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inFile));

            while ((line = reader.readLine()) != null) {

                ArrayList linelist = fromCSVLinetoArray(line);
                // System.out.println(linelist.get(6));
                // System.out.println(linelist.get(7));
                op.add(linelist.get(6) + "," + linelist.get(7));
                // System.out.println(op);
                /*
                 * Iterator it1 = linelist.iterator();
                 * while (it1.hasNext()) {
                 * // System.out.println(it1.next());
                 * it1.next();
                 * }
                 */
                // System.out.println("+++++++++++");

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return op;
    }

    public static ArrayList fromCSVLinetoArray(String source) {
        if (source == null || source.length() == 0) {
            return new ArrayList();
        }
        int currentPosition = 0;
        int maxPosition = source.length();
        int nextComma = 0;
        ArrayList rtnArray = new ArrayList();
        while (currentPosition < maxPosition) {
            nextComma = nextComma(source, currentPosition);
            rtnArray.add(nextToken(source, currentPosition, nextComma));
            currentPosition = nextComma + 1;
            if (currentPosition == maxPosition) {
                rtnArray.add("");
            }
        }
        return rtnArray;
    }

    /**
     * 查询下一个逗号的位置。
     * 
     * @param source
     *            文字列
     * @param st
     *            检索开始位置
     * @return 下一个逗号的位置。
     */
    private static int nextComma(String source, int st) {
        int maxPosition = source.length();
        boolean inquote = false;
        while (st < maxPosition) {
            char ch = source.charAt(st);
            if (!inquote && ch == ',') {
                break;
            } else if ('"' == ch) {
                inquote = !inquote;
            }
            st++;
        }
        return st;
    }

    /**
     * 取得下一个字符串
     */
    private static String nextToken(String source, int st, int nextComma) {
        StringBuffer strb = new StringBuffer();
        int next = st;
        while (next < nextComma) {
            char ch = source.charAt(next++);
            if (ch == '"') {
                if ((st + 1 < next && next < nextComma) && (source.charAt(next) == '"')) {
                    strb.append(ch);
                    next++;
                }
            } else {
                strb.append(ch);
            }
        }
        return strb.toString();
    }

}
