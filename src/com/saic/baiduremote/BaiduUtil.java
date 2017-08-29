/**
 * 
 */
package com.saic.baiduremote;

import com.alibaba.fastjson.JSON;
import com.saic.baiduremote.BaiduDataModel.Content;

/**
 * @author haowang
 */
public class BaiduUtil {

    public static Content parseJson(String jsonstr) {

        BaiduDataModel jsdata = JSON.parseObject(jsonstr, BaiduDataModel.class);

        return jsdata.getContent();

    }

}
