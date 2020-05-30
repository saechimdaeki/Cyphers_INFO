package nexon.cyphers.app.story.supermoon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import nexon.cyphers.app.R;
import nexon.cyphers.app.databinding.SupermoonbookCoverBinding;

public class SuperMoonBookcover extends Fragment {
    SupermoonbookCoverBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= SupermoonbookCoverBinding.inflate(inflater,container,false);
        binding.setSupermooncover(this);
        Glide.with(getActivity()).load(R.drawable.supermoonbookcover).into(binding.supermoonCover);
        return binding.getRoot();
    }
}
