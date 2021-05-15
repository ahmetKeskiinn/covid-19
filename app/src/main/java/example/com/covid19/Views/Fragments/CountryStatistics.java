package example.com.covid19.Views.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import example.com.covid19.DataBases.Statistics.StatisticsModel;
import example.com.covid19.R;
import example.com.covid19.ViewModels.StatisticsViewModel;


public class CountryStatistics extends Fragment {
    private StatisticsViewModel slideshowViewModel;
    private TextView continent,country,newCases,casesTotal,casesCritical,casesOneMPop,casesActive,deathsNew,deathsOneMPop,deathsTotal,testOneMPop,testTotal,day,time;
    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(StatisticsViewModel.class);
        root = inflater.inflate(R.layout.fragment_country_statistics, container, false);
        initialUI();
        Bundle bundle = getArguments();
        String object =  bundle.getString("name");
        country.setText(object);
        slideshowViewModel.fetchCountries(object);
        initialViewModel();
        return root;
    }
    private void initialUI(){
        continent = root.findViewById(R.id.continentCountry);
        country = root.findViewById(R.id.country);
        newCases = root.findViewById(R.id.newCasesCountCountry);
        casesTotal = root.findViewById(R.id.totalCasesCountry);
        casesCritical = root.findViewById(R.id.criticalCasesCountry);
        casesOneMPop = root.findViewById(R.id.oneMPopCasesCountry);
        casesActive = root.findViewById(R.id.activeCasesCountCountry);
        deathsNew = root.findViewById(R.id.deathsCountCountry);
        deathsOneMPop = root.findViewById(R.id.oneMDeathsPop);
        deathsTotal = root.findViewById(R.id.totalDeathsCountry);
        testOneMPop = root.findViewById(R.id.oneMTestCountry);
        testTotal = root.findViewById(R.id.totalTestCountry);
        day = root.findViewById(R.id.dayCountry);
        time = root.findViewById(R.id.timeCountry);
    }
    private void initialViewModel(){
        slideshowViewModel = ViewModelProviders.of(this).get(StatisticsViewModel.class);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<StatisticsModel>() {
            @Override
            public void onChanged(StatisticsModel statisticsModel) {
                try {
                    continent.setText(statisticsModel.getContinent());
                } catch (Exception e) {
                    continent.setText("Earth");
                }
                try {
                    newCases.setText(statisticsModel.getNewCases());
                } catch (Exception e) {
                    newCases.setText(getString(R.string.noInfo));
                }
                try {
                    casesTotal.setText(statisticsModel.getTotalCases());
                } catch (Exception e) {
                    casesTotal.setText(getString(R.string.noInfo));
                }
                try {
                    casesCritical.setText(statisticsModel.getCriticalCases());
                } catch (Exception e) {
                    casesCritical.setText(getString(R.string.noInfo));
                }
                try {
                    casesOneMPop.setText(statisticsModel.getOneMPopCases());
                } catch (Exception e) {
                    casesOneMPop.setText(getString(R.string.noInfo));
                }

                try {
                    casesActive.setText(statisticsModel.getActiveCases());
                } catch (Exception e) {
                    casesActive.setText(getString(R.string.noInfo));
                }

                try {
                    deathsNew.setText(statisticsModel.getDeathsNew());
                } catch (Exception e) {
                    deathsNew.setText(getString(R.string.noInfo));
                }

                try {
                    deathsOneMPop.setText(statisticsModel.getDeathsOneMPop());
                } catch (Exception e) {
                    deathsOneMPop.setText(getString(R.string.noInfo));
                }

                try {
                    deathsTotal.setText(statisticsModel.getDeathsTotal());
                } catch (Exception e) {
                    deathsTotal.setText(getString(R.string.noInfo));
                }

                try {
                    testOneMPop.setText(statisticsModel.getTestOneMPop());
                } catch (Exception e) {
                    testOneMPop.setText(getString(R.string.noInfo));
                }

                try {
                    testTotal.setText(statisticsModel.getTotalTest());
                } catch (Exception e) {
                    testTotal.setText(getString(R.string.noInfo));
                }

                try {
                    day.setText(statisticsModel.getDay());
                } catch (Exception e) {
                    day.setText(getString(R.string.noInfo));
                }
                try {
                    String[] timeData = statisticsModel.getTime().split("T");
                    time.setText(timeData[1]);
                } catch (Exception e) {
                    time.setText(getString(R.string.noInfo));
                }
            }
        });
    }
}