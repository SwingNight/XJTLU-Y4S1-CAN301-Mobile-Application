package cn.edu.xjtlu.testapp1210;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Fragment3_user extends Fragment {

    private Fragment3UserViewModel mViewModel;

    public static Fragment3_user newInstance() {
        return new Fragment3_user();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment3_user_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Fragment3UserViewModel.class);
        // TODO: Use the ViewModel

        String name = LoginActivity.account;
        TextView myName = (TextView) getView().findViewById(R.id.name_data);
        TextView myEmail = (TextView) getView().findViewById(R.id.email_data);
        TextView myOffice = (TextView) getView().findViewById(R.id.office_data);
        TextView myCourse = (TextView) getView().findViewById(R.id.course_data);

        DBOpenHelper mDBOpenHelper = new DBOpenHelper(this.getActivity());
        ArrayList<User> data = mDBOpenHelper.getAllData();
        for (int i = 0; i < data.size(); i++) {
            User user1 = data.get(i);
            if (name.equals(user1.getName())) {
                myName.setText(user1.getName());
                myEmail.setText(user1.getEmail());
                myOffice.setText(user1.getOffice());
                myCourse.setText(user1.getCourse());
                break;
            }
        }

        Button btn = (Button) getActivity().findViewById(R.id.logout_button);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(getActivity());
                alertdialogbuilder.setMessage("Are you sure to exit the program？");
                alertdialogbuilder.setPositiveButton("Yes",click1);
                alertdialogbuilder.setNegativeButton("No",click2);
                AlertDialog alertdialog1=alertdialogbuilder.create();
                alertdialog1.show();
            }

            private DialogInterface.OnClickListener click1=new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0,int arg1) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            };

            private DialogInterface.OnClickListener click2=new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0,int arg1) {
                    arg0.cancel();
                }
            };
        });
    }




}