package nexon.cyphers.app.story.eclipse;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import nexon.cyphers.app.databinding.EclipseMainBinding;
public class EclipseMainFragment extends Fragment {
    EclipseMainBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= EclipseMainBinding.inflate(inflater,container,false);
        binding.setMaineclipse(this);
        Glide.with(getActivity()).load("http://static.cyphers.co.kr/img/story/story_home.jpg").into(binding.eclipseMain);
        return binding.getRoot();
    }



}
