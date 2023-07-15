package com.appwizards.flipit;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class Leaderboard extends Fragment {
    private static String[] dataList = {"Noor", "Folk", "Yogesh"};
    public Leaderboard() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_leaderboard, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.PREF_NAME,0);
        ((TextView) rootView.findViewById(R.id.easylead)).append(""+preferences.getInt(Constants.EASY_HIGH_KEY, (int) (Constants.EASY_TIME/Constants.TIMER_INTERVAL)));
        ((TextView) rootView.findViewById(R.id.hardlead)).append(""+preferences.getInt(Constants.HARD_HIGH_KEY, (int) (Constants.HARD_TIME/Constants.TIMER_INTERVAL)));
        LinearLayout containerLayout = rootView.findViewById(R.id.llLeaderboard);
        Context context = getActivity();
        MyDBHelper dbHelper = new MyDBHelper(context);
        ArrayList<MyDBHelper.MyData> score = dbHelper.getScore();
        for (MyDBHelper.MyData data : score) {
            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            horizontalLayout.setPadding(10, 10, 10, 10);

            TextView textView1 = new TextView(context);
            textView1.setText(data.getName());
            textView1.setPadding(30, 10, 10, 10);
            textView1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.START));

            TextView textView2 = new TextView(context);
            textView2.setText(data.getScore());
            textView2.setPadding(100, 10, 0, 10);
            textView2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.END));

            horizontalLayout.addView(textView1);
            horizontalLayout.addView(textView2);

            containerLayout.addView(horizontalLayout);
        }
        return rootView;
    }

}
