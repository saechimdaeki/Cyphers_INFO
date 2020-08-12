package nexon.cyphers.app.todayCyphers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.airbnb.lottie.L;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import nexon.cyphers.app.R;
import nexon.cyphers.app.adapter.todayCypherAdpater;
import nexon.cyphers.app.adapter.todaytitleAdpater;
import nexon.cyphers.app.databinding.ActivityTodaycyphersBinding;
import nexon.cyphers.app.model.todayCypherModel.TodayCypherModel;
import nexon.cyphers.app.model.todayCypherModel.TodaytextModel;

public class TodayCyphersActivity extends AppCompatActivity {
    ActivityTodaycyphersBinding binding;
    todaytitleAdpater newsadpater,todayadapter,updateadapter;
    todayCypherAdpater todayCypherAdpater,magazineAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_todaycyphers);
        binding.setTodaydata(this);
       newsadpater=new todaytitleAdpater();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.todaynewsRecycle.setLayoutManager(linearLayoutManager);
        binding.todaynewsRecycle.setAdapter(newsadpater);
       todayadapter=new todaytitleAdpater();
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this);
       binding.todaycyphersRecycle.setLayoutManager(linearLayoutManager2);
       binding.todaycyphersRecycle.setAdapter(todayadapter);
        updateadapter=new todaytitleAdpater();
        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(this);
        binding.todayupdaterecycle.setLayoutManager(linearLayoutManager3);
        binding.todayupdaterecycle.setAdapter(updateadapter);

        todayCypherAdpater=new todayCypherAdpater();
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(this);
        binding.todayeventrecycler.setLayoutManager(linearLayoutManager4);
        binding.todayeventrecycler.setAdapter(todayCypherAdpater);

        magazineAdpater=new todayCypherAdpater();
        LinearLayoutManager linearLayoutManager5=new LinearLayoutManager(this);
        binding.todaymagazineRecycle.setLayoutManager(linearLayoutManager5);
        binding.todaymagazineRecycle.setAdapter(magazineAdpater);
        getData();
    }
    private void getData(){
        /* 그냥 기능마다 따로따로의 private class로 나눔. */
        cyphers cypherstask=new cyphers();
        cypherstask.execute();
        todycyphers todycypherstask=new todycyphers();
        todycypherstask.execute();
        todayupdate todayupdate=new todayupdate();
        todayupdate.execute();
        event event=new event();
        event.execute();
        magazine magazine=new magazine();
        magazine.execute();
    }
    private class cyphers extends AsyncTask<Void , Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> clickUrl=new ArrayList<>();
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("http://cyphers.nexon.com/cyphers/article/notice").get();
                final Elements title = doc.select("p.txt2 a");
                final Elements click=doc.select("p.txt2 a");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(Element element: title) {
                            listTitle.add(element.text());
                            
                        }
                        for(Element element:click){
                            clickUrl.add(element.attr("href"));

                        }
                        for (int i = 0; i < 5 ; i++) {
                            TodaytextModel data=new TodaytextModel();
                            if(listTitle.get(i)==null)
                                data.setTitle("현재 인터넷이 원활하지않습니다");
                            else
                            data.setTitle(listTitle.get(i));
                            data.setUrl(clickUrl.get(i));
                            newsadpater.addItem(data);
                        }
                        newsadpater.notifyDataSetChanged();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    private class todycyphers extends AsyncTask<Void , Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> clickUrl=new ArrayList<>();
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("http://cyphers.nexon.com/cyphers/article/today2").get();
                final Elements title = doc.select("div.today_box ul.t_list li p a");
                final Elements click=doc.select("div.today_box ul.t_list li p a");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(Element element: title) {
                            listTitle.add(element.text());
                            Log.e("오싸",element.text());
                        }
                        for(Element element:click){
                            clickUrl.add(element.attr("href"));
                            Log.e("오싸2",element.text());
                        }
                        for (int i = 0; i < 4 ; i++) {
                            TodaytextModel data=new TodaytextModel();
                            data.setTitle(listTitle.get(i));
                            data.setUrl(clickUrl.get(i));
                            todayadapter.addItem(data);
                        }
                        todayadapter.notifyDataSetChanged();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    private class todayupdate extends AsyncTask<Void , Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> clickUrl=new ArrayList<>();
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("http://cyphers.nexon.com/cyphers/article/update").get();
                final Elements title = doc.select("div.content_board p.txt");
                final Elements click=doc.select("td.btn a");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(Element element: title) {
                            listTitle.add(element.text());
                        }
                        for(Element element:click){
                            clickUrl.add(element.attr("href"));
                        }
                        for (int i = 0; i < 4 ; i++) {
                            TodaytextModel data=new TodaytextModel();
                            data.setTitle(listTitle.get(i));
                            data.setUrl(clickUrl.get(i));
                            updateadapter.addItem(data);
                        }
                        updateadapter.notifyDataSetChanged();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    private class event extends AsyncTask<Void , Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> clickUrl=new ArrayList<>();
        ArrayList<String> imgsrc=new ArrayList<>();
        ArrayList<String> datelist=new ArrayList<>();
        ArrayList<String> contentlist=new ArrayList<>();

        ArrayList<String> listTitle2 = new ArrayList<>();
        ArrayList<String> clickUrl2=new ArrayList<>();
        ArrayList<String> imgsrc2=new ArrayList<>();
        ArrayList<String> datelist2=new ArrayList<>();
        ArrayList<String> contentlist2=new ArrayList<>();
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("http://cyphers.nexon.com/cyphers/article/event/search/status/running/1").get();
                Document doc2 = Jsoup.connect("http://cyphers.nexon.com/cyphers/article/event/search/status/running/2").get();
                final Elements title = doc.select("li.con p.txt");
                final Elements click=doc.select("div.board_event li.thum a");
                final Elements date=doc.select("p.txt2");
                final Elements content=doc.select("p.txt3");
                final Elements img=doc.select("div.board_event li.thum img");
                final Elements title2 = doc2.select("li.con p.txt");
                final Elements click2=doc2.select("div.board_event li.thum a");
                final Elements date2=doc2.select("p.txt2");
                final Elements content2=doc2.select("p.txt3");
                final Elements img2=doc2.select("div.board_event li.thum img");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(Element element: title) {
                            listTitle.add(element.text());
                        }
                        for(Element element:click){
                            clickUrl.add(element.attr("href"));
                        }
                        for(Element element: img)
                        {
                            imgsrc.add(element.attr("src"));
                        }
                        for(Element element: date)
                            datelist.add(element.text());
                        for(Element element:content)
                            contentlist.add(element.text());
                        for(Element element: title2) {
                            listTitle2.add(element.text());
                        }
                        for(Element element:click2){
                            clickUrl2.add(element.attr("href"));
                        }
                        for(Element element: img2)
                        {
                            imgsrc2.add(element.attr("src"));
                        }
                        for(Element element: date2)
                            datelist2.add(element.text());
                        for(Element element:content2)
                            contentlist2.add(element.text());

                        for (int i = 0; i < listTitle.size() ; i++) {
                            TodayCypherModel data=new TodayCypherModel();
                            data.setTitle(listTitle.get(i));
                            data.setClickUrl(clickUrl.get(i));
                            data.setContent(contentlist.get(i));
                            data.setImgurl(imgsrc.get(i));
                            data.setDate(datelist.get(i));
                            todayCypherAdpater.addItem(data);
                        }
                        for(int i=0;i<listTitle2.size(); i++)
                        {
                            TodayCypherModel data=new TodayCypherModel();
                            data.setTitle(listTitle2.get(i));
                            data.setClickUrl(clickUrl2.get(i));
                            data.setContent(contentlist2.get(i));
                            data.setImgurl(imgsrc2.get(i));
                            data.setDate(datelist2.get(i));
                            todayCypherAdpater.addItem(data);
                        }
                        todayCypherAdpater.notifyDataSetChanged();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    private class magazine extends AsyncTask<Void , Void, Void> {
        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<String> clickUrl=new ArrayList<>();
        ArrayList<String> imgsrc=new ArrayList<>();
        ArrayList<String> datelist=new ArrayList<>();
        ArrayList<String> contentlist=new ArrayList<>();
        int cnt=1;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("http://cyphers.nexon.com/cyphers/article/magazine").get();
                final Elements title = doc.select("p.txt");
                final Elements click=doc.select("td.thum a");
                final Elements date=doc.select("p.date");
                final Elements content=doc.select("p.con a");
                final Elements img=doc.select("td.thum a img");

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(Element element: title) {
                            if(cnt%2!=0)
                            {
                                listTitle.add(element.text());
                                cnt++;
                            }else
                                cnt++;

                        }
                        for(Element element:click){
                            clickUrl.add(element.attr("href"));
                        }
                        for(Element element: img)
                        {
                            imgsrc.add(element.attr("src"));
                        }
                        for(Element element: date)
                            datelist.add(element.text());
                        for(Element element:content)
                            contentlist.add(element.text());
                        for (int i = 0; i < 5 ; i++) {
                            TodayCypherModel data=new TodayCypherModel();
                            data.setTitle(listTitle.get(i));
                            data.setClickUrl(clickUrl.get(i));
                            data.setContent(contentlist.get(i));
                            data.setImgurl(imgsrc.get(i));
                            data.setDate(datelist.get(i));
                            magazineAdpater.addItem(data);
                        }
                        magazineAdpater.notifyDataSetChanged();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
