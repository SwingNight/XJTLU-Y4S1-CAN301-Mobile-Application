package cn.edu.xjtlu.testapp1210;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    private Fragment2ViewModel mViewModel;

    public int isStu = LoginActivity.category;
    public String input = "";

    public SearchView searchView;
    public ListView listView;
    public TextView textView;

    public static ArrayList<Professor> book_stuList = new ArrayList<Professor>(){
        {
            add(new Professor("Teacher1", "SA111", "Monday", "10:40", "13:40"));
            add(new Professor("Teacher2", "SA112", "Tuesday", "9:00", "10:00"));
            add(new Professor("Teacher3", "SA113", "Friday", "15:00", "17:00"));
            add(new Professor("Teacher4", "SA114", "Wednesday", "14:25", "16:25"));
        }
    };
    public static ArrayList<Student> book_proList = new ArrayList<Student>();
    public static ArrayList<Professor> search_stuList = new ArrayList<Professor>(){
        {
            add(new Professor("Teacher5", "SA115", "Tuesday","14:00","14:30"));
            add(new Professor("Teacher6", "SA116", "Monday","9:45","10:45"));
            add(new Professor("Teacher7", "SA117", "Wednesday","10:00","11:30"));
            add(new Professor("Teacher8", "SA118", "Monday","13:00","17:00"));
            add(new Professor("Teacher9","SA119","Thursday","16:45","17:25"));
            add(new Professor("Da.Liu","SA119","Friday","12:45","14:25"));
            add(new Professor("Da.Liu","SA119","Thursday","15:45","16:25"));
        }
    };
    private ArrayList<Professor> searchBackupList = new ArrayList<Professor>();

    Search_stuAdapter search_stuAdapter;
    Book_stuAdapter book_stuAdapter;

    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view;

        //initBook_stuList();
        initSearchBackupList();
        initBook_proList();

        if(isStu == 0){
            view = View.inflate(getActivity(),R.layout.activity_studetail,null);

            book_stuAdapter = new Book_stuAdapter(getActivity());
            search_stuAdapter = new Search_stuAdapter(getActivity());

            textView = view.findViewById(R.id.info_title);
            listView = view.findViewById(R.id.booklist_stu);
            searchView = view.findViewById(R.id.search_bar);

            searchView.setIconifiedByDefault(false);
            searchView.setSubmitButtonEnabled(true);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    textView.setText("  Appointment Information:");
                    if(TextUtils.isEmpty(query)) {
                        listView.clearTextFilter();
                        search_stuAdapter.getFilter().filter("");
                    }
                    else {
                        search_stuAdapter.getFilter().filter(query);
                        input = query;
                        listView.setAdapter(search_stuAdapter);
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    textView.setText("  Appointment Information:");
                    if(TextUtils.isEmpty(newText)) {
                        listView.clearTextFilter();
                        search_stuAdapter.getFilter().filter("");

                        textView.setText("  Reserved Information:");
                        listView.setAdapter(book_stuAdapter);
                    }
                    else {
                        search_stuAdapter.getFilter().filter(newText);
                        input = newText;
                        listView.setAdapter(search_stuAdapter);
                    }
                    return true;
                }
            });

            if(TextUtils.isEmpty(input)){
                listView.setAdapter(book_stuAdapter);
            }
        }else{
            view = View.inflate(getActivity(),R.layout.activity_prodetail,null);

            listView = view.findViewById(R.id.bookList_pro);

            Book_proAdapter book_proAdapter = new Book_proAdapter(getActivity());
            listView.setAdapter(book_proAdapter);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Fragment2ViewModel.class);
        // TODO: Use the ViewModel


    }

    public void initBook_stuList() {
        Professor info1 = new Professor("Teacher1", "SA111", "Monday", "10:40", "13:40");
        Professor info2 = new Professor("Teacher2", "SA112", "Tuesday", "9:00", "10:00");
        Professor info3 = new Professor("Teacher3", "SA113", "Friday", "15:00", "17:00");
        Professor info4 = new Professor("Teacher4", "SA114", "Wednesday", "14:25", "16:25");
        book_stuList.add(info1);
        book_stuList.add(info2);
        book_stuList.add(info3);
        book_stuList.add(info4);

    }

    public void initBook_proList() {
        Student info1 = new Student("Student1", "SA111","Monday","10:00","10:15");
        Student info2 = new Student("Student2", "SA112","Friday","10:10","10:25");
        Student info3 = new Student("Student3", "SA113","Thursday ","10:20","10:35");
        Student info4 = new Student("Student4", "SA114","Wednesday","10:30","10:45");
        Student info5 = new Student("Student5", "SA115","Tuesday","10:40","10:55");
        Student info6 = new Student("Student6", "SD116","Monday","10:50","11:05");
        Student info7 = new Student("Student7", "SA117","Thursday ","11:00","11:15");
        Student info8 = new Student("Student8", "SA118", "Thursday ","12:00","12:15");
        Student info9 = new Student("Student9", "SB119","Wednesday","13:00","13:15");
        Student info10 = new Student("Student10", "SA110","Tuesday","14:00","14:15");
        book_proList.add(info1);
        book_proList.add(info2);
        book_proList.add(info3);
        book_proList.add(info4);
        book_proList.add(info5);
        book_proList.add(info6);
        book_proList.add(info7);
        book_proList.add(info8);
        book_proList.add(info9);
        book_proList.add(info10);
    }

    public void initSearchBackupList() {


        for(Professor professor : search_stuList){
            searchBackupList.add(new Professor(professor.getTeacher(),professor.getLocation(),professor.getWorkDay(),professor.getStarTime(),professor.getEndTime()));
        }

    }

    public void bookItem(int position){
        String newStart = "";
        String newEnd = "";

        Professor addItem = new Professor(search_stuList.get(position).getTeacher(),
                search_stuList.get(position).getLocation(),
                search_stuList.get(position).getWorkDay(),
                search_stuList.get(position).getStarTime(),
                search_stuList.get(position).getEndTime());

        String oldStartTime = search_stuList.get(position).getStarTime();
        String oldEndTime = search_stuList.get(position).getEndTime();

        String[] arrOldStart = oldStartTime.split(":");
        String[] arrOldEnd = oldEndTime.split(":");

        int minuteLimit = 60;
        int oldStartHour = Integer.parseInt(arrOldStart[0]);
        int oldStartMinute = Integer.parseInt(arrOldStart[1]);
        int oldEndHour = Integer.parseInt(arrOldEnd[0]);
        int oldEndMinute = Integer.parseInt(arrOldEnd[1]);

        int newEndHour = oldStartHour;
        int newEndMinute = oldStartMinute + 30;

        if (newEndMinute >= minuteLimit){
            newEndHour += 1;
            newEndMinute = newEndMinute - 60;
        }

        if(newEndHour == oldEndHour){
            if(newEndMinute >= oldEndMinute){
                newEndMinute = oldEndMinute;
            }
        }

        newEnd = String.valueOf(newEndHour) + ":" + String.valueOf(newEndMinute);
        newStart = newEnd;

        addItem.setEndTime(newEnd);
        book_stuList.add(addItem);
        book_stuAdapter.notifyDataSetChanged();
        listView.setAdapter(book_stuAdapter);
        searchView.setQuery("",false);
        textView.setText("  Reserved Information:");

        if(newEndHour == oldEndHour){
            if(newEndMinute >= oldEndMinute){
                search_stuList.remove(position);
                search_stuAdapter.notifyDataSetChanged();
            }else{
                search_stuList.get(position).setStartTime(newStart);
            }
        }else {
            search_stuList.get(position).setStartTime(newStart);
        }

        search_stuAdapter.notifyDataSetChanged();

    }

    public void deleteItem(int position){
        Professor newItem = new Professor(book_stuList.get(position).getTeacher(),
                book_stuList.get(position).getLocation(),
                book_stuList.get(position).getWorkDay(),
                book_stuList.get(position).getStarTime(),
                book_stuList.get(position).getEndTime());

        book_stuList.remove(position);
        book_stuAdapter.notifyDataSetChanged();

        search_stuList.add(newItem);
        search_stuAdapter.notifyDataSetChanged();

    }

    public void deleteDialog(int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Notice");
        dialog.setMessage("Confirm to cancel the reservation?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItem(position);
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    public void bookDialog(int position){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Notice");
        dialog.setMessage("Confirm the appointment for this time (30 minutes)?");
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bookItem(position);
            }
        });
        dialog.show();
    }

    //book_stuAdapter
    public final class stuViewHolder {
        public TextView teacher;
        public TextView location;
        public TextView workday;
        public TextView startTime;
        public TextView endTime;
        public Button clickBtn;
    }

    public class Book_stuAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public Book_stuAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return book_stuList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            stuViewHolder holder = null;
            if (convertView == null) {
                holder = new stuViewHolder();

                convertView = mInflater.inflate(R.layout.bookinginfo_stu, null);
                holder.teacher = (TextView) convertView.findViewById(R.id.teacher_name);
                holder.location = (TextView) convertView.findViewById(R.id.teacher_location);

                holder.workday = (TextView) convertView.findViewById(R.id.booked_day);
                holder.startTime = (TextView) convertView.findViewById(R.id.booked_startTime);
                holder.endTime = (TextView) convertView.findViewById(R.id.booked_endTime);

                holder.clickBtn = (Button) convertView.findViewById(R.id.btn_delete);
                convertView.setTag(holder);

            } else {

                holder = (stuViewHolder) convertView.getTag();
            }

            holder.teacher.setText((String) book_stuList.get(position).getTeacher());
            holder.location.setText((String) book_stuList.get(position).getLocation());

            holder.workday.setText((String)book_stuList.get(position).getWorkDay());
            holder.startTime.setText((String)book_stuList.get(position).getStarTime() );
            holder.endTime.setText((String)book_stuList.get(position).getEndTime());


            holder.clickBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    deleteDialog(position);
                }
            });

            return convertView;
        }

    }

    //book_proAdapter
    public final class proViewHolder {
        public TextView student;
        public TextView location;
        public TextView workDay;
        public TextView startTime;
        public TextView endTime;
    }

    public class Book_proAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public Book_proAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return book_proList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            proViewHolder holder = null;
            if (convertView == null) {
                holder = new proViewHolder();

                convertView = mInflater.inflate(R.layout.bookinginfo_pro, null);
                holder.student = (TextView) convertView.findViewById(R.id.student_name);
                holder.location =  (TextView) convertView.findViewById(R.id.student_location);
                holder.workDay = (TextView) convertView.findViewById(R.id.stuBooked_day);
                holder.startTime = (TextView) convertView.findViewById(R.id.stuBooked_startTime);
                holder.endTime = (TextView) convertView.findViewById(R.id.stuBooked_endTime);

                convertView.setTag(holder);

            } else {

                holder = (proViewHolder) convertView.getTag();
            }

            holder.student.setText((String) book_proList.get(position).getStudent());
            holder.location.setText((String) book_proList.get(position).getLocation());
            holder.workDay.setText((String) book_proList.get(position).getWorkDay());
            holder.startTime.setText((String) book_proList.get(position).getStartTime());
            holder.endTime.setText((String) book_proList.get(position).getEndTime());

            return convertView;
        }

    }

    //search_stuAdapter
    public class Search_stuAdapter extends BaseAdapter implements Filterable {
        private LayoutInflater mInflater;
        private ArrayList<Professor> backupList ;

        SearchFilter mFilter;

        public Search_stuAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
            backupList = search_stuList;
        }

        @Override
        public int getCount() {
            return search_stuList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            stuViewHolder holder = null;
            if (convertView == null) {
                holder = new stuViewHolder();

                convertView = mInflater.inflate(R.layout.searchinfo_stu, null);
                holder.teacher = (TextView) convertView.findViewById(R.id.search_teacher);
                holder.location = (TextView) convertView.findViewById(R.id.search_location);

                holder.workday = (TextView) convertView.findViewById(R.id.book_day);
                holder.startTime = (TextView) convertView.findViewById(R.id.book_startTime);
                holder.endTime = (TextView) convertView.findViewById(R.id.book_endTime);
                holder.clickBtn = (Button) convertView.findViewById(R.id.btn_book);

                convertView.setTag(holder);
            } else {
                holder = (stuViewHolder) convertView.getTag();
            }

            holder.teacher.setText((String) search_stuList.get(position).getTeacher());
            holder.location.setText((String) search_stuList.get(position).getLocation());

            holder.workday.setText((String)search_stuList.get(position).getWorkDay());
            holder.startTime.setText((String)search_stuList.get(position).getStarTime());
            holder.endTime.setText((String)search_stuList.get(position).getEndTime());

            holder.clickBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookDialog(position);
                }
            });

            return convertView;
        }

        @Override
        public Filter getFilter() {
            if(mFilter == null){
                mFilter = new SearchFilter();
            }
            return mFilter;
        }

        //We need to define a filter class to define filtering rules
        class SearchFilter extends Filter{
            //performFiltering(CharSequence charSequence),Filter rules are defined in this method
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults result = new FilterResults();
                ArrayList<Professor> list;
                if (TextUtils.isEmpty(charSequence)){//When the filter keyword is empty, we display all data
                    list = backupList;
                }else {//Otherwise, the eligible data objects are added to the collection
                    list = new ArrayList<>();
                    for (Professor ss : backupList){
                        if (ss.getTeacher().toUpperCase().contains(charSequence.toString().toUpperCase())){ //要匹配的item中的view
                            list.add(ss);
                        }
                    }
                }
                result.values = list; //Save the resulting collection in the value variable of FilterResults
                result.count = list.size();//Save the size of the collection in the count variable of FilterResults

                return result;
            }
            //The adapter is told to update the interface in the publishResults method
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                search_stuList = (ArrayList<Professor>) filterResults.values;
                if (filterResults.count>0){
                    notifyDataSetChanged();
                }else {
                    notifyDataSetInvalidated();
                }
            }
        }


    }



}