package nexon.cyphers.app.Toosin.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lid.lib.LabelButtonView;

import nexon.cyphers.app.R;
import nexon.cyphers.app.Toosin.ToosinActivity;

public class Toosin_main extends Fragment {
    private View view;
    LabelButtonView button1;//파
    LabelButtonView buutton2;//격
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.toosin_main, container, false);
            button1=view.findViewById(R.id.labelbutton1);
            buutton2=view.findViewById(R.id.labelbutton2);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ToosinActivity)getActivity()).setFrag(1);
                }
            });
            buutton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ToosinActivity)getActivity()).setFrag(0);
                }
            });
        return view;
    }
}
