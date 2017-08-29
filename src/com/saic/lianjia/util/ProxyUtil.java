package com.saic.lianjia.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;

public class ProxyUtil {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /* Use as: .proxy(pxy.split(":")[0], new Integer(pxy.split(":")[1])) */
    public static String getRandomProxy() {
        /*
         * System.setProperty("http.maxRedirects", "50");
         * System.getProperties().setProperty("proxySet", "true");
         * // Set proxy
         * String ip = "59.78.160.244";
         * System.getProperties().setProperty("http.proxyHost", ip);
         * System.getProperties().setProperty("http.proxyPort", "8080");
         */
        // return "59.78.160.244:8080";
        return "59.78.160.244:8080";

    }

    public static List    ipList   = new ArrayList();
    public static boolean gameOver = false;

    public static void App() {
        long fetchIpSeconds = 5;
        int threadNum = 10;
        int testTime = 3;
        String order = "这里换成你的订单号，百度全网代理IP获取";
        System.out.println(">>>>>>>>>>>>>>全网代理动态IP测试开始<<<<<<<<<<<<<<");
        System.out.println("***************");
        System.out.println("接口返回IP为国内各地区，每次最多返回10个");
        System.out.println("提取IP间隔 " + fetchIpSeconds + " 秒 ");
        System.out.println("开启爬虫线程 " + threadNum);
        System.out.println("爬虫目标网址  http://1212.ip138.com/ic.asp");
        System.out.println("测试次数 3 ");
        System.out.println("***************\n");
        TestDynamicIp tester = new TestDynamicIp();
        new Thread(tester.new GetIP(fetchIpSeconds * 1000, testTime, order)).start();
        for (int i = 0; i < threadNum; i++) {
            tester.new Ip138Tester(100).start();
        }
        while (!gameOver) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(">>>>>>>>>>>>>>全网代理动态IP测试结束<<<<<<<<<<<<<<");
        System.exit(0);
    }

    // 抓取IP138，检测IP
    public class Ip138Tester extends Thread {
        @Override
        public void run() {
            while (!gameOver) {
                webParseHtml("http://1212.ip138.com/ic.asp");
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        long sleepMs = 200;

        public Ip138Tester(long sleepMs) {
            this.sleepMs = sleepMs;
        }

        public String webParseHtml(String parentUrl) {
            String html = "";
            WebClient client = new WebClient();
            try {
                client.getOptions().setThrowExceptionOnFailingStatusCode(false);
                client.getOptions().setJavaScriptEnabled(false);
                client.getOptions().setCssEnabled(false);
                client.getOptions().setThrowExceptionOnScriptError(false);
                client.getOptions().setTimeout(10000); // 10s超时
                client.getOptions().setAppletEnabled(true);
                client.getOptions().setGeolocationEnabled(true);
                client.getOptions().setRedirectEnabled(true);

                String ipport = getAProxy();
                if (ipport != null) {
                    ProxyConfig proxyConfig = new ProxyConfig(ipport.split(":")[0],
                            Integer.parseInt(ipport.split(":")[1]));
                    client.getOptions().setProxyConfig(proxyConfig);
                } else {
                    System.out.print(".");
                    return "";
                }

                HtmlPage page = client.getPage(parentUrl);
                html = page.asXml();

                if (html.length() > 0) {
                    html = Jsoup.parse(html).select("center").first().text();
                }

                System.out.println(getName() + " 使用代理 " + ipport + "请求IP138返回：" + html);

            } catch (Exception e) {
                return webParseHtml(parentUrl);
            } finally {
                client.close();
            }
            return html;
        }

        private String getAProxy() {
            if (ipList.size() > 0) {
                String ip = ipList.get((int) (Math.random() * ipList.size()));
                return ip;
            }
            return null;
        }
    }

    // 定时获取动态IP
    public class GetIP implements Runnable {
        long   sleepMs = 1000;
        int    maxTime = 3;
        String order   = "";

        public GetIP(long sleepMs, int maxTime, String order) {
            this.sleepMs = sleepMs;
            this.maxTime = maxTime;
            this.order = order;
        }

        @Override
        public void run() {
            long getIpTime = 0;
            int time = 1;
            while (!gameOver) {
                if (time >= 4) {
                    gameOver = true;
                    break;
                }
                try {
                    java.net.URL url = new java.net.URL(
                            "http://dynamic.goubanjia.com/dynamic/get/" + order + ".html?ttl");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(3000);
                    connection = (HttpURLConnection) url.openConnection();

                    InputStream raw = connection.getInputStream();
                    InputStream in = new BufferedInputStream(raw);
                    byte[] data = new byte[in.available()];
                    int bytesRead = 0;
                    int offset = 0;
                    while (offset < data.length) {
                        bytesRead = in.read(data, offset, data.length - offset);
                        if (bytesRead == -1) {
                            break;
                        }
                        offset += bytesRead;
                    }
                    in.close();
                    raw.close();
                    String[] res = new String(data, "UTF-8").split("\n");
                    List ipList = new ArrayList();
                    for (String ip : res) {
                        try {
                            String[] parts = ip.split(",");
                            if (Integer.parseInt(parts[1]) > 0) {
                                ipList.add(parts[0]);
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (ipList.size() > 0) {
                        TestDynamicIp.ipList = ipList;
                        System.out.println("第" + ++getIpTime + "次获取动态IP " + ipList.size() + " 个");
                        time += 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(">>>>>>>>>>>>>>获取IP出错");
                }
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
