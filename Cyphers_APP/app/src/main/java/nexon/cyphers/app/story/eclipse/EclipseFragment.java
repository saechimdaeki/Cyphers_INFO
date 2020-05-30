package nexon.cyphers.app.story.eclipse;

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

import nexon.cyphers.app.databinding.EclipseFragmentBinding;


public class EclipseFragment extends Fragment {
    EclipseFragmentBinding binding;
    public  String crawlingmelee;

    public EclipseFragment(String crawlingmelee) {
        this.crawlingmelee = crawlingmelee;
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=EclipseFragmentBinding.inflate(inflater,container,false);
        binding.setEclipsefragment(this);
        getdata();

        return binding.getRoot();
    }
    private void getdata(){
        crawlingEclipse crawling=new crawlingEclipse();
        crawling.execute();
    }

    private class crawlingEclipse extends AsyncTask<Void , Void, Void> {
        ArrayList<String> listrp=new ArrayList<>();
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
                        binding.textcrawling.append(listrp.get(0));
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}

