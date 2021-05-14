package example.com.covid19.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import example.com.covid19.DataBases.History.HistoryModel;
import example.com.covid19.DataBases.Statistics.StatisticsModel;
import example.com.covid19.DataBases.Statistics.StatisticsRepository;
import example.com.covid19.Models.StatisticsModels.ExampleStatistics;
import example.com.covid19.Models.StatisticsModels.ResponseStatistics;
import example.com.covid19.Utils.Api;
import example.com.covid19.Utils.ApiServices.GetService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsViewModel extends AndroidViewModel {
    private static final String token = "966dd09665mshfee82873a81b1f5p1a2d3bjsnb22c69531b3e";
    private static final String host = "covid-193.p.rapidapi.com";
    private MutableLiveData<StatisticsModel> mText;
    private StatisticsRepository mRepository;
    private LiveData<StatisticsModel> mAllHistory;
    private LiveData<StatisticsModel> histMod;
    public StatisticsViewModel(Application application) {
        super(application);
        //fetchTeams();
        mRepository = new StatisticsRepository(application);
        mText = new MutableLiveData<>();
        //mAllHistory =
    }

    public LiveData<StatisticsModel> getText() {
        return  mText;
    }
    public void fetchCountries(String country) {
        GetService service = Api.createService(GetService.class);
        List<HistoryModel> historyModelList = new ArrayList<HistoryModel>();
        List<String> returnedData = new ArrayList<String>();
        Call<ExampleStatistics> call = service.getCountryStatistics(token,host, country);
        call.enqueue(new Callback<ExampleStatistics>() {
            @Override
            public void onResponse(Call<ExampleStatistics> call, Response<ExampleStatistics> response) {
                Log.d("TAG", "onResponse:---------------------------->>>>>>>> "+ response.code());
                Log.d("TAG", "onResponse: " + response.message());
                Log.d("TAG", "onResponse: "+ response.body().getResponse().get(0).getTests().getTotal());
               // Log.d("TAG", "onResponse: " + response.body().getTests().getTotal().toString());
                Log.d("TAG", "onResponse:sadasdasda ");
                if (response.isSuccessful()) {
                    if (response != null) {
                        String continent;
                        String newCases;
                        String casesTotal;
                        String criticalCase;
                        String oneMPopCase;
                        String activeCases;
                        String newDeaths;
                        String oneMPopDeaths;
                        String totalDeaths;
                        String totalTests;
                        String oneMPopTests;
                        String day;
                        String time;
                        //Log.d("TAG", "onResponse: " + response.body().getCases().getActive() + " " + response.body().getDay());
                        try {
                            assert response.body() != null;
                            continent = response.body().getResponse().get(0).getContinent();
                        }
                        catch(Exception e){
                            continent = "Earth";
                        }
                        try {
                            assert response.body() != null;
                            newCases =  response.body().getResponse().get(0).getCases().getNew();
                        }
                        catch(Exception e){
                            newCases = "Unknown";
                        }
                        try {
                            assert response.body() != null;
                            casesTotal =  response.body().getResponse().get(0).getCases().getTotal().toString();
                        }
                        catch(Exception e){
                            casesTotal = "Unknown";
                        }
                        try {
                            assert response.body() != null;
                            criticalCase=  response.body().getResponse().get(0).getCases().getCritical().toString();
                        }
                        catch(Exception e){
                            criticalCase = "Unknown";
                        }
                        try {
                            assert response.body() != null;
                            oneMPopCase =  response.body().getResponse().get(0).getCases().get1MPop();
                        }
                        catch(Exception e){
                            oneMPopCase = "Unknown";
                        }

                        try {
                            assert response.body() != null;
                            activeCases=  response.body().getResponse().get(0).getCases().getActive().toString();
                        }
                        catch(Exception e){
                            activeCases= "Unkown";
                        }

                        try {
                            assert response.body() != null;
                            newDeaths =  response.body().getResponse().get(0).getDeaths().getNew().toString();
                        }
                        catch(Exception e){
                            newDeaths = "Unkown";
                        }

                        try {
                            assert response.body() != null;
                            oneMPopDeaths =  response.body().getResponse().get(0).getDeaths().get1MPop();
                        }
                        catch(Exception e){
                            oneMPopDeaths = "Unknown";
                        }

                        try {
                            assert response.body() != null;
                            totalDeaths =  response.body().getResponse().get(0).getDeaths().getTotal().toString();
                        }
                        catch(Exception e){
                            totalDeaths = "Unknown";
                        }

                        try {
                            assert response.body() != null;
                            oneMPopTests =  response.body().getResponse().get(0).getTests().get1MPop().toString();
                        }
                        catch(Exception e){
                            oneMPopTests = "Unknown";
                        }

                        try {
                            assert response.body() != null;
                            totalTests =  response.body().getResponse().get(0).getTests().getTotal().toString();
                        }
                        catch(Exception e){
                            totalTests = "Unknown";
                        }

                        try {
                            assert response.body() != null;
                            day =  response.body().getResponse().get(0).getDay().toString();
                        }
                        catch(Exception e){
                            day = "Unknown";
                        }
                        try {
                            assert response.body() != null;
                            time =  response.body().getResponse().get(0).getTime();
                        }
                        catch(Exception e){
                            time= "unknown";
                        }
                        Log.d("TAG", "onResponse: " + continent + " " + newCases +" " + casesTotal +" " +criticalCase+ " " + time+" " + day);
                        StatisticsModel model = new StatisticsModel(country,continent,newCases,casesTotal,criticalCase,oneMPopCase,activeCases,newDeaths,oneMPopDeaths,totalDeaths,oneMPopTests,totalTests,day,time);
                        insert(model);
                        mText.postValue(model);
                    }
                    else{
                        // Toast.makeText(getApplication().getBaseContext(),getString(R.string.wentwrong),Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ExampleStatistics> call, Throwable t) {
            }

        });

    }
    public void insert(StatisticsModel word) {
        mRepository.insert(word);
    }
}