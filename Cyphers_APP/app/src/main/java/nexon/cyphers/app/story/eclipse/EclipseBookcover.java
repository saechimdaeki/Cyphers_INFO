package nexon.cyphers.app.story.eclipse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import nexon.cyphers.app.R;
import nexon.cyphers.app.databinding.EclipsebookCoverBinding;

public class EclipseBookcover extends Fragment {
    EclipsebookCoverBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= EclipsebookCoverBinding.inflate(inflater,container,false);
        binding.setEclipsecover(this);
        Glide.with(getActivity()).load(R.drawable.eclipsebookcover).into(binding.eclipseCover);
        return binding.getRoot();
    }



}