package example.com.covid19.Views.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import example.com.covid19.Adapters.CountryRecycler;
import example.com.covid19.DataBases.Country.CountryModel;
import example.com.covid19.R;
import example.com.covid19.ViewModels.HomeViewModel;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private CountryRecycler adapter;
    private RecyclerView countryView;
    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        sendToast();
        initialViewModel();
        initialUI();
        initialViewModel();
        initRecycler();
        return root;
    }
    private void initialUI(){
        countryView = root.findViewById(R.id.countryRecycler);
        adapter = new CountryRecycler();
    }
    private void sendToast(){
        Toast.makeText(this.root.getContext(),getString(R.string.homeFragmentToast),Toast.LENGTH_SHORT).show();
    }
    private void initRecycler(){
        homeViewModel.fetchCountries();
        countryView.setHasFixedSize(true);
        countryView.setAdapter(adapter);

        countryView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        countryView.setLayoutManager(new LinearLayoutManager(getContext()));
        countryView.setNestedScrollingEnabled(false);
        countryView.setAdapter(adapter);
        homeViewModel.getmAllCountries().observe(getViewLifecycleOwner(), new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {
                adapter.submitList(countryModels);
            }
        });

    }
    private void initialViewModel(){
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

    }



}