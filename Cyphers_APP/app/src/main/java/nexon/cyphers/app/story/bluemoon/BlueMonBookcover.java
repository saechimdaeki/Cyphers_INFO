package nexon.cyphers.app.story.bluemoon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import nexon.cyphers.app.R;
import nexon.cyphers.app.databinding.BluemoonbookCoverBinding;

public class BlueMonBookcover extends Fragment {
    BluemoonbookCoverBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= BluemoonbookCoverBinding.inflate(inflater,container,false);
        binding.setBluecover(this);
        Glide.with(getActivity()).load(R.drawable.bluemoonbookcover).into(binding.bluemoonCover);
        return binding.getRoot();
    }
}
