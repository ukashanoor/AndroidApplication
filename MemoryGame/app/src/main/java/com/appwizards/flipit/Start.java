package com.appwizards.flipit;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wajahatkarim3.easyflipview.EasyFlipView;

public class Start extends Fragment {

    public Start() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_start, container, false);
        EasyFlipView flipView = rootView.findViewById(R.id.tile_line2);

        flipView.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView easyFlipView, EasyFlipView.FlipState newCurrentSide) {
                easyFlipView.setFlipEnabled(false);

                rootView.findViewById(R.id.easy).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = getActivity();
                        EditText editText = rootView.findViewById(R.id.tvEnterName);
                        String name = editText.getText().toString();
                        Log.i("START", name);
                        fragmentTrasaction(new EasyLevel(name));
                    }
                });
                rootView.findViewById(R.id.hard).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentTrasaction(new HardLevel());
                    }
                });
            }
        });

        rootView.findViewById(R.id.leaderboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTrasaction(new Leaderboard());
            }
        });
        return rootView;
    }

    private void fragmentTrasaction(Fragment f){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.layoutFragment, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
