package feng.jiyun.com.jsoupdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String url = "http://news.ipanda.com/2017/08/25/ARTICdDX6JZzG5cFaGwmo29W170825.shtml";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc ;
                try {
                    //从一个URL加载一个Document对象。
                    doc = Jsoup.connect(url).get();
                   /* Elements elements = doc.select("div.col_w920");
                    Element element=elements.get(0);
                    List<Node> nodes = element.childNodes().get(5).childNodes();
                    for (int i = 0; i < nodes.size(); i++) {
                        if (nodes.get(i).nodeName().equals("p")) {
                            String ssk  = nodes.get(i).childNode(0).outerHtml();
                            Log.e("TAG","=========="+ssk);
                        }

                    }*/
                        //选择一个节点
                    Elements elements = doc.select("div.cnt_bd");
                    for(Element element : elements){

                        Elements title = elements.select("div.cnt_bd");
                        Log.e("TAG","标题-------"+title.get(0).select("h1").text());

                        Elements time = element.select("span.info");
                        Log.e("TAG","时间-------"+time.get(0).text());

                        Elements pic = elements.select("div.cnt_bd");
                        Log.e("TAG", "图片-------" + pic.get(0).select("p").select("img").attr("src"));
                        Log.e("TAG", "内容-------" + pic.get(0).select("p").text());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
