package nexon.cyphers.app.story.bluemoon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import nexon.cyphers.app.databinding.BluemoonMainBinding;


public class BlueMoonMainFragment extends Fragment {
    BluemoonMainBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= BluemoonMainBinding.inflate(inflater,container,false);
        binding.setMainbluemoon(this);
        Glide.with(getActivity()).load("http://static.cyphers.co.kr/img/story/img_bluemoon_home.png").into(binding.bluemoonMain);
        return binding.getRoot();
    }
}
