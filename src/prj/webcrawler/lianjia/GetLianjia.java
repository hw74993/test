/**
 * 
 */
package prj.webcrawler.lianjia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.saic.lianjia.util.LianjiaDataModel.LJData;

/**
 * @author tansheng-7
 */
public class GetLianjia {

    /**
     * @param args
     */

    String LJurl        = "http://soa.dooioo.com/api/v1/online/house/ershoufang/listMapResult?"
            + "access_token=7poanTTBCymmgE0FOn1oKp&client=pc&cityCode=sh&type=village&"
            + "minLatitude=0.271665&" + "maxLatitude=51.28222&" + "minLongitude=1.464157&"
            + "maxLongitude=171.472403&" + "siteType=quyu";                                    // +region+%22/%22/;

    double minLatitude  = 29;
    double maxLatitude  = 33;
    double minLongitude = 119;
    double maxLongitude = 124;
    double increment    = 1;

    // Date date = new Date();
    String sdate        = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    String opFile       = new String(
            "D:\\Projects\\0009_Crawler\\Data\\LianjiaData_" + sdate + ".csv").trim();
    // String sdate = "" + date.getYear() + " " + date.getMonth() + " " +
    // date.getDay();

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        LianjiaUtil gl = new LianjiaUtil();
        List<LJData> lst = new ArrayList<LJData>();

        /*
         * for (double lat = gl.minLatitude; lat < gl.maxLatitude; lat +=
         * gl.increment) { for (double lon = gl.minLongitude; lon <
         * gl.maxLongitude; lon += gl.increment) {
         * String fullurl = gl.getFullURL(lat, lon, gl.increment);
         * // System.out.println("list"); List<LJData> lstadd =
         * gl.parseJson(gl.getWebpage(fullurl)); // System.out.println(lstadd);
         * if (lstadd.size() != 0) {// System.out.println("Requesting: " + //
         * fullurl); System.out.println(lstadd.size());
         * lst.addAll(lstadd); } // System.out.println("listAfterAdd" + lat +
         * lon);
         * } }
         */
        // lst = gl.search2000(gl.minLatitude, gl.maxLatitude, gl.minLongitude,
        // gl.maxLongitude, gl.increment);

        // gl.writeToCSV(sdate, opFile, lst);

    }

}
