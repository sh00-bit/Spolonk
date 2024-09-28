package kr.co.softsoldesk.servlet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/fetchDataServlet3")
public class FetchDataServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Naver 스포츠 뉴스 페이지 URL
            String url = "https://sports.news.naver.com/wfootball/index";
            Document doc = Jsoup.connect(url).get();

            // 뉴스 제목, 링크, 이미지 추출
            List<Element> newsElements = doc.select(".home_news_list li");
            
            if (newsElements.size() > 2) { // 세 번째 뉴스 항목이 존재하는지 확인
                Element thirdNewsElement = newsElements.get(2); // 세 번째 뉴스 항목 선택
                Element linkElement = thirdNewsElement.select("a").first(); // 링크 추출
                Element imgElement = thirdNewsElement.select("img").first(); // 이미지 추출

                String title = linkElement != null ? linkElement.attr("title") : "제목을 찾을 수 없습니다";
                String link = linkElement != null ? linkElement.absUrl("href") : "#";
                String imageUrl = imgElement != null ? imgElement.absUrl("src") : ""; // 이미지 URL 추출
                
                // JSON 형식으로 클라이언트에 전송
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("title", title);
                jsonResponse.put("link", link);
                jsonResponse.put("imageUrl", imageUrl);

                // JSON을 예쁘게 출력
                String jsonOutput = jsonResponse.toString(2);
                out.println(jsonOutput);
            } else {
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("error", "뉴스 항목이 충분하지 않습니다.");
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
