package example.com.covid19.Views.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import example.com.covid19.Adapters.RecyclerViewAdapter;
import example.com.covid19.DataBases.Country.CountryModel;
import example.com.covid19.DataBases.History.HistoryModel;
import example.com.covid19.R;
import example.com.covid19.ViewModels.HistoryViewModel;
import example.com.covid19.ViewModels.HomeViewModel;

public class HistoryFragment extends Fragment implements View.OnClickListener , AdapterView.OnItemSelectedListener {
    private int mYear, mMonth, mDay;
    private HistoryViewModel historyViewModel;
    private HomeViewModel homeViewModel;
    private TextView btnDatePicker;
    private Spinner country;
    private RecyclerView content;
    private View root;
    private RecyclerViewAdapter adapter;
    private ArrayList<String> paths;

    private String[] list;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.history_fragment, container, false);

        initialViewModel();
        initialUI();
        //fetchTeams();
        //initRecycler();
        initSpinner();
        return root;
    }
    private void initialUI(){
    btnDatePicker = root.findViewById(R.id.dateTimePicker);
    btnDatePicker.setOnClickListener(this::onClick);
    country = root.findViewById(R.id.countrySpinner);
    country.setOnItemSelectedListener(this);
    content = root.findViewById(R.id.contentRecycler);

    }

    private void initialViewModel(){
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    private void initRecycler(String country, String day){
        historyViewModel.fetchTeams(country,day);
        content.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter();

        content.setOverScrollMode(View.OVER_SCROLL_NEVER);
        content.setLayoutManager(new LinearLayoutManager(getContext()));
        content.setNestedScrollingEnabled(false);
        content.setAdapter(adapter);

        historyViewModel.getAllTeamFixture(country).observe(getViewLifecycleOwner(), new Observer<List<HistoryModel>>() {
            @Override
            public void onChanged(List<HistoryModel> historyModels) {
                Log.d("TAG", "onChanged: "+ historyModels.size());
                        adapter.submitList(historyModels);
            }
        });
    }


    private void initSpinner(){


        homeViewModel.getmAllCountries().observe(getViewLifecycleOwner(), new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {
                spinnerContent(countryModels);
            }
        });

    }
    private void spinnerContent(List<CountryModel> data){
        list = new String[data.size()];
        for(int i =0;i<data.size();i++){
            list[i] = data.get(i).getCountry();
        }
        ArrayAdapter aa = new ArrayAdapter(HistoryFragment.this.getContext(),android.R.layout.simple_spinner_item,list);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        country.setAdapter(aa);
    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(root.getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            String tem = String.valueOf(monthOfYear+1);
                            if((tem.length()<2)){
                                String newMonth="0"+String.valueOf(monthOfYear+1);
                                        btnDatePicker.setText(year + "-" + newMonth + "-" + dayOfMonth);
                            }
                            else {
                                btnDatePicker.setText(year + "-" + monthOfYear+1 + "-" + dayOfMonth);
                            }

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // updateLabel();
        if(btnDatePicker.getText().toString().equals("")){
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            btnDatePicker.setText(currentDate);

            initRecycler(list[position],btnDatePicker.getText().toString());
        }
        else{
            initRecycler(list[position],btnDatePicker.getText().toString());
            country.setSelection(position);
        }
        Log.d("TAG", "onItemSelected:----------------------------------------------->>>>>>" + list[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
      }
}