package prj.webcrawler.lianjia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetPhone {
	public static void main(String[] args) throws IOException {
		BufferedReader br1 = new BufferedReader(new FileReader("D:\\Projects\\0009_Crawler\\user.tsv"));
		PrintWriter pw = new PrintWriter(new FileWriter("D:\\Projects\\0009_Crawler\\p_result.txt"));
		String line = null;
		int count = 1;

		while ((line = br1.readLine()) != null) {
			if (count > 0) {
				String lineArray[] = line.split("\t");
				String realPhone = "n/a";
				if (lineArray.length >= -1) {
					try {
						// String phoneNumber = (lineArray[27]);

						// realPhone = phoneNumber.substring(1);
						// realPhone = realPhone.substring(0, realPhone.length()
						// - 1);

						realPhone = "18321997139";
						// System.out.println(realPhone);
						String url = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=" + realPhone;
						Document doc = Jsoup.connect(url).get();

						String area = doc.select("tr.tdc").get(2).text();// .replaceAll("¿¨ºÅ¹éÊôµØ",
																			// "");
						// System.out.println(doc.select("tr.tdc").get(2).text().replaceAll("¿¨ºÅ¹éÊôµØ",
						// "").replaceAll("\\?", ","));
						System.out.println(doc);
						System.out.println(
								realPhone + "|" + area + " , " + doc.select("tr.tdc").get(3).text().split("\\s")[1]);
						pw.println(realPhone + "|" + area + " , " + doc.select("tr.tdc").get(3).text().split("\\s")[1]);
					} catch (Exception e) {
						System.out.println(e);
						System.out.println("n/a , n/a");
						pw.println(realPhone + "|" + "n/a , n/a");
					}
					// Document doc =
					// Jsoup.connect("http://example.com/").get();
					// String title = doc.title();
				} else {
					System.out.println("n/a , n/a");
					pw.println(realPhone + "|" + "n/a , n/a");
				}
			}
			count++;
			pw.flush();
		}
		System.out.println(count);
	}
}
