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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import example.com.covid19.Adapters.CountryRecyclerViewAdapter;
import example.com.covid19.DataBases.Country.CountryModel;
import example.com.covid19.R;
import example.com.covid19.ViewModels.HomeViewModel;
import example.com.covid19.ViewModels.StatisticsViewModel;

public class StatisticsFragment extends Fragment {

    private StatisticsViewModel slideshowViewModel;
    private HomeViewModel homeViewModel;
    private View root;
    private RecyclerView countryRecycler;
    private CountryRecyclerViewAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.statistics_fragment, container, false);
        sendToast();
        initialUI();
        initialViewModel();
        initRecycler();
        return root;
    }
    private void initialUI(){
        adapter = new CountryRecyclerViewAdapter();
        countryRecycler = root.findViewById(R.id.statisticRecycler);
    }
    private void initialViewModel(){
        slideshowViewModel = ViewModelProviders.of(this).get(StatisticsViewModel.class);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getmAllCountries().observe(getViewLifecycleOwner(), new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {
                adapter.submitList(countryModels);
            }
        });
    }
    private void sendToast(){
        Toast.makeText(this.root.getContext(),getString(R.string.statisticFragmentToast),Toast.LENGTH_SHORT).show();
    }
    private void initRecycler(){
        countryRecycler.setOverScrollMode(View.OVER_SCROLL_NEVER);
        countryRecycler.setVisibility(View.VISIBLE);
        countryRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        countryRecycler.setHasFixedSize(true);

        countryRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new CountryRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CountryModel country) {

                Fragment fragment = new CountryStatistics();
                Bundle bundle = new Bundle();
                bundle.putString("name", country.getCountry());
                fragment.setArguments(bundle);
                Navigation.findNavController(root).navigate(R.id.action_nav_slideshow_to_countryStatistics, bundle);
            }
        });
    }
}