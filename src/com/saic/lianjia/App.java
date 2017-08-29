package com.saic.lianjia;

public class App {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int maxDelay = 2;
        // lng,lat,name,price,sold,date,city
        Shanghai.RunShanghai();
        CityTemplate.RunCity("Beijing", "http:bj.lianjia.com/xiaoqu/pg", maxDelay);
        CityTemplate.RunCity("Chengdu", "http:cd.lianjia.com/xiaoqu/pg", maxDelay);
        CityTemplate.RunCity("Chongqing", "http:cq.lianjia.com/xiaoqu/pg", maxDelay);
        // CityTemplate.RunCity("Changsha", "http:cs.lianjia.com/xiaoqu/pg", maxDelay);
        // CityTemplate.RunCity("Dalian", "http:dl.lianjia.com/xiaoqu/pg", maxDelay);
        // CityTemplate.RunCity("Dongguan", "http:dg.lianjia.com/xiaoqu/pg", maxDelay);
        // CityTemplate.RunCity("Foshan", "http:fs.lianjia.com/xiaoqu/pg", maxDelay);
        CityTemplate.RunCity("Guangzhou", "http:gz.lianjia.com/xiaoqu/pg", maxDelay);
        CityTemplate.RunCity("Hangzhou", "http:hz.lianjia.com/xiaoqu/pg", maxDelay);
        // CityTemplate.RunCity("Haikou", "http:hk.lianjia.com/xiaoqu/pg", maxDelay);
        // CityTemplate.RunCity("Hefei", "http:hf.lianjia.com/xiaoqu/pg", maxDelay);
        // CityTemplate.RunCity("Jinan", "http:jn.lianjia.com/xiaoqu/pg", maxDelay);
        CityTemplate.RunCity("Nanjing", "http:nj.lianjia.com/xiaoqu/pg", maxDelay);
        CityTemplate.RunCity("Qingdao", "http:qd.lianjia.com/xiaoqu/pg", maxDelay);

        CityTemplate.RunCity("Shenzhen", "http:sz.lianjia.com/xiaoqu/pg", maxDelay);
        CityTemplate.RunCity("Suzhou", "http:su.lianjia.com/xiaoqu/pg", maxDelay);
        // CityTemplate.RunCity("Shijiazhuang", "http:sjz.lianjia.com/xiaoqu/pg", maxDelay);
        // CityTemplate.RunCity("Shenyang", "http:sy.lianjia.com/xiaoqu/pg", maxDelay);
        CityTemplate.RunCity("Tianjin", "http:tj.lianjia.com/xiaoqu/pg", maxDelay);
        CityTemplate.RunCity("Wuhan", "http:wh.lianjia.com/xiaoqu/pg", maxDelay);

    }

}
