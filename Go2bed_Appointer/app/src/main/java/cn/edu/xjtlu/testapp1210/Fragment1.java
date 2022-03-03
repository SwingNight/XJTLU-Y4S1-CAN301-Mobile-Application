package cn.edu.xjtlu.testapp1210;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.edu.xjtlu.testapp1210.util.DensityUtil;


public class Fragment1 extends Fragment {

    private Fragment1ViewModel mViewModel;
    private FrameLayout frameLayout;
    private int tvHeight;
    private int marginTop;

    private int isStu = LoginActivity.category;
    private String userName = LoginActivity.account;

    private ArrayList<Professor> stuBookedList = Fragment2.book_stuList;
    private ArrayList<Professor> profBookList = Fragment2.search_stuList;

    private String profName;


    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Set different visions for Professor/Student ->student cannot add available booking time
        if(isStu==1){
            setHasOptionsMenu(true);
        }

        return inflater.inflate(R.layout.fragment1_fragment, container, false);
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(Fragment1ViewModel.class);

        //Draw the Personal Timetable based on existed data list
        if(isStu == 0){
            //student's personal booked timetable
            for(int i = 0;i < stuBookedList.size();i++){
                Professor professor = stuBookedList.get(i);
                addTV(professor, i);
            }
        }else {
            //professor's personal available timetable
            for(int i = 0;i < profBookList.size();i++){
                Professor professor = profBookList.get(i);
                profName = professor.getTeacher();
                if (profName.equalsIgnoreCase(userName)) {
                    addTV(professor,i);
                }
            }
        }

        //Using broadcast method to get refresh information from activity:"add"
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CART_BROADCAST");
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent){
                String msg = intent.getStringExtra("data");
                if("refresh".equals(msg)){
                    //Professor add a new available time successfully> Refresh the timetable
                    refresh();
                }
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);
    }


    //    Refresh fragment1 to add the new available time
    private void refresh() {
        Fragment fragment = getParentFragment();
        fragment.onDestroy();
        for(int i = 0;i < profBookList.size();i++){
            Professor professor = profBookList.get(i);
            profName = professor.getTeacher();
            if (profName.equalsIgnoreCase(userName)) {
                addTV(professor,i);
            }
        }
    }


    private void setTvPara(int startTime, int endTime){
        int duration = endTime-startTime;
        int unit = DensityUtil.dip2px(getActivity(),80);
        tvHeight = unit * duration;
        marginTop = 1+ (startTime-9)*unit;
    }


    //draw each time's TextView
    private void addTV(Professor professor,int position){
        String day = professor.getWorkDay();
        String startTime=professor.getStarTime();
        String endTime=professor.getEndTime();

        String[] arrStartTime = startTime.split(":");
        String[] arrEndTime = endTime.split(":");

        int sTimeHour = Integer.parseInt(arrStartTime[0]);
        int eTimeHour = Integer.parseInt(arrEndTime[0]);
        int eTimeMin = Integer.parseInt(arrEndTime[1]);

        if(day.equalsIgnoreCase("monday")){ frameLayout = getActivity().findViewById(R.id.monday);}
        else if(day.equalsIgnoreCase("tuesday")){ frameLayout = getActivity().findViewById(R.id.tuesday);}
        else if(day.equalsIgnoreCase("wednesday")){ frameLayout = getActivity().findViewById(R.id.wednesday);}
        else if(day.equalsIgnoreCase("thursday")){ frameLayout = getActivity().findViewById(R.id.thursday);}
        else if(day.equalsIgnoreCase("friday")){ frameLayout = getActivity().findViewById(R.id.friday);}
        else if(day.equalsIgnoreCase("saturday")){ frameLayout = getActivity().findViewById(R.id.saturday);}
        else{ frameLayout = getActivity().findViewById(R.id.sunday);}

        FrameLayout fl = new FrameLayout(getActivity());
        fl.setBackgroundColor(0);

        if(eTimeMin>0 && eTimeHour<17){
            eTimeHour += 1 ;
        }
        setTvPara(sTimeHour,eTimeHour);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(1,marginTop,1,1);
        frameLayout.addView(fl);


        TextView tv = new TextView(getActivity());
        tv.setText("From"+"\n"+startTime+"\n"+" To "+"\n"+endTime);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(14);
        tv.setBackgroundResource(R.drawable.border_tv);//set the border
        tv.setHeight(tvHeight);
        tv.setClickable(true);
        fl.addView(tv,params);

        //DELETE function
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View tv) {
                AlertDialog.Builder diadlog = new AlertDialog.Builder(getActivity());
                diadlog.setTitle("Kindly Reminder");
                diadlog.setMessage("Are you want to cancel this book？");
                diadlog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                diadlog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv.setVisibility(View.GONE);//hide the textView first
                        fl.removeView(tv);//remove the textView from corresponding frameLayout
                        profBookList.remove(position);//delete data from the data list
                    }
                });
                diadlog.show();
            }
        });

    }

    //ADD data menu in right-top corner (skip to "add" activity page)
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.main,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Intent intent = new Intent(getActivity(), add.class);
                startActivity(intent);
                break;
            default:
        }
        return true;
    }

}