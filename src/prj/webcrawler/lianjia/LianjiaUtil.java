/**
 * 
 */
package prj.webcrawler.lianjia;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.alibaba.fastjson.JSON;
import com.saic.lianjia.util.LianjiaDataModel;
import com.saic.lianjia.util.LianjiaDataModel.LJData;

/**
 * @author tansheng-7
 *
 */
public class LianjiaUtil {

	private static final String CSV_SEPARATOR = ",";

	private String getFullURL(double lat, double lon, double inc) {
		return "http://soa.dooioo.com/api/v4/online/house/ershoufang/listMapResult?"
				+ "access_token=7poanTTBCymmgE0FOn1oKp&client=pc&cityCode=sh&type=village&" + "minLatitude="
				+ new Double(lat).toString() + "&maxLatitude=" + new Double(lat + inc).toString() + "&minLongitude="
				+ new Double(lon).toString() + "&maxLongitude=" + new Double(lon + inc).toString() + "&siteType=quyu";
	}

	public String getWebpage(String url) {

		// String url = LJurl;

		Document doc;
		Element body;
		try {

			// System.out.println(Jsoup.connect(LJurl).ignoreContentType(true).get());
			// String testurl =
			// "http://soa.dooioo.com/api/v4/online/house/ershoufang/listMapResult?access_token=7poanTTBCymmgE0FOn1oKp&client=pc&cityCode=sh&type=village&minLatitude=31.271665&maxLatitude=31.27322&minLongitude=121.464157&maxLongitude=121.472403&siteType=quyu";
			doc = Jsoup.parseBodyFragment(Jsoup.connect(url).ignoreContentType(true).get().toString());
			body = doc.body();
			// System.out.println(doc.select("body").toString().replaceAll("<body>",
			// "").replaceAll("</body>", ""));
			// doc = Jsoup.connect(LJurl).get();

			// Elements ele = doc.getElementsByAttribute("ele");
			// System.out.println(doc.toString());

			// System.out.println(doc.select("div
			// id=\"house-map\"").get(0).text().split("\\s")[1]);

			// String area = doc.select("tr.tdc").get(2).text();
			return body.toString().replaceAll("<body>", "").replaceAll("</body>", "");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public List<LJData> parseJson(String jsonstr) {

		List<LJData> list = null;

		if (jsonstr.length() != 0) {
			LianjiaDataModel jsdata = JSON.parseObject(jsonstr, LianjiaDataModel.class);
			/*
			 * for (LJData row : jsdata.getDataList())
			 * System.out.println(row.getShowName() + ": " +
			 * row.getDealAvgPrice());
			 */
			// System.out.println(System.currentTimeMillis());
			list = jsdata.getDataList();
			// System.out.println("list.size():" + list.size());
			/*
			 * List<JsonModel> jsonObjList = JSON.parseArray(jsonstr,
			 * JsonModel.class);
			 * 
			 * for (JsonModel jso : jsonObjList) { JsonModel mtimeModel = new
			 * JsonModel(); String VideoID = "mtime" + jso.getVideoID(); String
			 * MovieID = "mtime" + jso.getMovieID(); String ShortTitle =
			 * jso.getShortTitle(); String url = "http://video.mtime.com/" +
			 * jso.getVideoID() + "/?mid" + jso.getMovieID();
			 * mtimeModel.setPrmovieId(VideoID); mtimeModel.setUrl(url);
			 * mtimeModel.setMovieID(MovieID);
			 * mtimeModel.setShortTitle(ShortTitle); LJ.add(mtimeModel);
			 * logger.info("VideoID:  " + VideoID + "  MovieID:" + MovieID +
			 * "  ShortTitle:" + ShortTitle + "  url:" + url); }
			 */
		}
		return list;
	}

	public List<LJData> search2000(double minLat, double maxLat, double minLon, double maxLon, double inc) {
		List<LJData> lst = new ArrayList<LJData>();

		for (double lat = minLat; lat < maxLat; lat += inc) {
			for (double lon = minLon; lon < maxLon; lon += inc) {

				String fullurl = getFullURL(lat, lon, inc);

				// System.out.println("list");
				List<LJData> lstadd = parseJson(getWebpage(fullurl));
				// System.out.println(lstadd);
				if (lstadd.size() != 0 && lstadd.size() < 2000) {// System.out.println("Requesting:
																	// " +
					// fullurl);
					System.out.println(lstadd.size());

					lst.addAll(lstadd);
				} else if (lstadd.size() == 2000) {

					lst.addAll(search2000(minLat, maxLat, minLon, maxLon, inc / 2));

				}
			}
		}

		return lst;

	}

	private void writeToCSV(String sdate, String opFile, List<LJData> list) {
		try {

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(opFile), "UTF-8"));

			for (LJData product : list) {
				// {"currentType":"village","dataId":"5011000005196","dealAvgPrice":0,
				// "latitude":31.272366,"longitude":121.466974,"saleAvgPrice":40868,
				// "saleTotal":8,"showName":"Ê±´úöÎ¶¼","sort":0,"type":"village"},

				StringBuffer oneLine = new StringBuffer();
				oneLine.append(product.getCurrentType());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(product.getDataId());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(product.getDealAvgPrice());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(product.getLatitude());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(product.getLongitude());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(product.getSaleAvgPrice());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(product.getSaleTotal());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(product.getShowName());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(product.getSort());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(product.getType());
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(sdate);

				bw.write(oneLine.toString());
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (UnsupportedEncodingException e) {
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
}
