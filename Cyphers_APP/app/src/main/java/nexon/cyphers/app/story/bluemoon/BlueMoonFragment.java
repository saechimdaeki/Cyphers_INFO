package nexon.cyphers.app.story.bluemoon;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import nexon.cyphers.app.databinding.BluemoonFragmentBinding;


public class BlueMoonFragment extends Fragment {
    BluemoonFragmentBinding binding;
    public  String crawlingmelee;

    public BlueMoonFragment(String crawlingmelee) {
        this.crawlingmelee = crawlingmelee;
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=BluemoonFragmentBinding.inflate(inflater,container,false);
        binding.setBluemoonfragment(this);
        getdata();

        return binding.getRoot();
    }
    private void getdata(){
        crawlingbluemoon crawling=new crawlingbluemoon();
        crawling.execute();
    }

    private class crawlingbluemoon extends AsyncTask<Void , Void, Void> {
        ArrayList<String> listrp=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        int cnt=0;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(crawlingmelee).get();
                final Elements rp = doc.select("li.bg_con");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(Element element:rp)
                            listrp.add(element.text());
                        for(int i=0; i<listrp.get(0).length(); i++)
                        {
                            sb.append(listrp.get(0).charAt(i));
                            if(listrp.get(0).charAt(i)=='.')
                                cnt++;
                            if(cnt%2==0 && cnt!=0&& sb.length()>=2) {
                                sb.append("\n");
                                cnt=0;
                            }
                        }
                        binding.textcrawling.append(sb);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
