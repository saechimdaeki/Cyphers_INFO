package nexon.cyphers.app.story.supermoon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import nexon.cyphers.app.databinding.SupermoonMainBinding;

public class SuperMoonMainFragment extends Fragment {
    SupermoonMainBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= SupermoonMainBinding.inflate(inflater,container,false);
        binding.setMainsupermoon(this);
        Glide.with(getActivity()).load("http://static.cyphers.co.kr/img/story/img_supermoon1.jpg").into(binding.supermoonMain);
        return binding.getRoot();
    }
}
