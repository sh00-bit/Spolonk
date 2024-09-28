package kr.co.softsoldesk.servlet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/fetchDataServlet")
public class FetchDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // 뉴스 페이지 URL
            String url = "https://sports.news.naver.com/index";
            Document doc = Jsoup.connect(url).get();

            // 뉴스 항목 추출
            Elements newsElements = doc.select(".today_list .today_item");

            if (!newsElements.isEmpty()) {
                Element firstNewsElement = newsElements.first();
                Element linkElement = firstNewsElement.select("a.link_today").first();
                String newsPageUrl = linkElement != null ? linkElement.absUrl("href") : "#";

                // 뉴스 페이지에서 이미지 추출
                Document newsDoc = Jsoup.connect(newsPageUrl).get();
                Elements imgElements = newsDoc.select("img[src~=(?i)\\.(png|jpe?g|gif)]"); // 이미지 파일만 선택

                // 이미지 URL 리스트 준비
                JSONArray imageUrls = new JSONArray();
                for (Element imgElement : imgElements) {
                    String imageUrl = imgElement.attr("src");
                    imageUrl = URLDecoder.decode(imageUrl, StandardCharsets.UTF_8.name());
                    if (imageUrl.startsWith("//")) {
                        imageUrl = "https:" + imageUrl; // 프로토콜이 누락된 경우 보완
                    }
                    imageUrls.put(imageUrl);
                }

                // 제목과 링크 추출
                String title = linkElement != null ? linkElement.attr("title") : "제목을 찾을 수 없습니다";
                String link = linkElement != null ? linkElement.absUrl("href") : "#";

                // JSON 형식으로 클라이언트에 전송
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("title", title);
                jsonResponse.put("link", link);
                jsonResponse.put("images", imageUrls);

                // JSON을 예쁘게 출력
                String jsonOutput = jsonResponse.toString(2);
                out.println(jsonOutput);
            } else {
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("error", "뉴스 항목을 찾을 수 없습니다.");
                out.println(jsonResponse.toString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("error", "오류 발생");
            out.println(jsonResponse.toString(2));
        } finally {
            out.close();
        }
    }
}
