package com.cs442.shash5259.sportsmgr;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shash on 09-04-2016.
 */
public class Sportinfo extends Fragment{
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    SharedPreferences sharedpreferences;
    String MyPREFERENCES = "Login_Credentials1";
    Button b1,b2;
    View rootView;
    String u_name=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentManager = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        rootView = inflater.inflate(R.layout.sport_layout, container, false);
        TextView t1 = (TextView)rootView.findViewById(R.id.sport_id);
        ImageView i1 = (ImageView)rootView.findViewById(R.id.sport_image);
        b1 = (Button)rootView.findViewById(R.id.create_team);
        b2 = (Button)rootView.findViewById(R.id.join_team);

        setHasOptionsMenu(true);
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String title= sharedpreferences.getString("sport", null);
        final String u_email = sharedpreferences.getString("email", null);



        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFragmentTransaction.replace(R.id.containerView, new myDialogFragment()).commit();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentTransaction.replace(R.id.containerView, new TeamsListFragment()).commit();
            }
        });
        t1.setText(title);
        ((AppCompatActivity)getActivity()).onBackPressed();

        switch(title)
        {
            case "Baseball":i1.setImageResource(R.drawable.baseball1);
                break;
            case "Cricket":i1.setImageResource(R.drawable.cricket);
                break;
            case "Soccer":i1.setImageResource(R.drawable.soccer);
                break;
            case "Football":i1.setImageResource(R.drawable.football);
                break;
            case "Tabletennis":i1.setImageResource(R.drawable.tt);
                break;
            case "Bowling":i1.setImageResource(R.drawable.bowling);
                break;
            case "Billiards":i1.setImageResource(R.drawable.pool);
                break;
            case "Basketball":i1.setImageResource(R.drawable.basketball);
                break;
            case "Chess":i1.setImageResource(R.drawable.chess);
                break;
            case "Badminton":i1.setImageResource(R.drawable.shuttle);
                break;
            case "Racquetball":i1.setImageResource(R.drawable.racquet);
                break;
            case "Yoga":i1.setImageResource(R.drawable.yoga);
                break;
            case "Pilates":i1.setImageResource(R.drawable.pilates);
                break;
            case "Swimming":i1.setImageResource(R.drawable.swimming);
                break;
            case "Zumba":i1.setImageResource(R.drawable.zumba);
                break;
            case "Kickboxing":i1.setImageResource(R.drawable.kickboxing);
                break;
            case "Cardio":i1.setImageResource(R.drawable.cardio);
                break;
            default:
        }


        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sport_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sport_profile_menu:
                mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
        }return super.onOptionsItemSelected(item);
    }
}



