package com.example.lenovo.tuozhuaidome;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private TextView viewById;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank, container, false);
        viewById = inflate.findViewById(R.id.qw);
        return inflate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
//        Bundle arguments = getArguments();
//        String name = arguments.getString("name");
//        if(!name.equals("")){
//            viewById.setText(name);
//        }
        super.onCreate(savedInstanceState);
    }
}
