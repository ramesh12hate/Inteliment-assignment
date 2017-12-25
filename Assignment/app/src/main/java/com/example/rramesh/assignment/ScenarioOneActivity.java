package com.example.rramesh.assignment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.rramesh.adapters.ItemAdapter;
import com.example.rramesh.adapters.ViewPagerAdapter;
import com.example.rramesh.assignment.databinding.ActivityScenarioOneBinding;
import com.example.rramesh.fragments.FourFragment;
import com.example.rramesh.fragments.OneFragment;
import com.example.rramesh.fragments.ThreeFragment;
import com.example.rramesh.fragments.TwoFragment;
import com.example.rramesh.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


/*
 * By Ramesh Barahate
 * Description : RecyclerView, View Pager, Layout, etc
 */

public class ScenarioOneActivity extends AppCompatActivity implements View.OnClickListener, OnItemClickListener {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ViewPagerAdapter pageAdapter;

    private ActivityScenarioOneBinding mActivityScenarioOneBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityScenarioOneBinding = DataBindingUtil.setContentView(this, R.layout.activity_scenario_one);
        mActivityScenarioOneBinding.myRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mActivityScenarioOneBinding.myRecyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            input.add("Item " + i);
        }// define an adapter
        mAdapter = new ItemAdapter(input, this);
        mActivityScenarioOneBinding.myRecyclerView.setAdapter(mAdapter);

        List<Fragment> fragments = getFragments();
        pageAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        mActivityScenarioOneBinding.viewpager.setAdapter(pageAdapter);
        mActivityScenarioOneBinding.viewpager.setOnClickListener(this);

        mActivityScenarioOneBinding.btnRed.setOnClickListener(this);
        mActivityScenarioOneBinding.btnGreen.setOnClickListener(this);
        mActivityScenarioOneBinding.btnBlue.setOnClickListener(this);
        mActivityScenarioOneBinding.btnNext.setOnClickListener(this);
    }
    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<Fragment>();
        fList.add(new OneFragment());
        fList.add(new TwoFragment());
        fList.add(new ThreeFragment());
        fList.add(new FourFragment());
        return fList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_red:
                mActivityScenarioOneBinding.linearLayout.setBackgroundColor(Color.RED);
                break;
            case R.id.btn_green:
                mActivityScenarioOneBinding.linearLayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.btn_blue:
                mActivityScenarioOneBinding.linearLayout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.viewpager:
                Toast.makeText(this, getString(R.string.current_page) + " " + mActivityScenarioOneBinding.viewpager.getCurrentItem(), Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_next:
                Intent intent = new Intent(this, ScenarioTwoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(int item) {
        mActivityScenarioOneBinding.tvFour.setText("Current Clicked Item = "  + (item+1));
    }
}
