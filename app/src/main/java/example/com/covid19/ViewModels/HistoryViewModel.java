package example.com.covid19.ViewModels;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import example.com.covid19.DataBases.History.HistoryModel;
import example.com.covid19.DataBases.History.HistoryRepository;
import example.com.covid19.Models.HistoryModels.Example;
import example.com.covid19.R;
import example.com.covid19.Utils.Api;
import example.com.covid19.Utils.ApiServices.GetService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryViewModel extends AndroidViewModel {

    private static final String token = "966dd09665mshfee82873a81b1f5p1a2d3bjsnb22c69531b3e";
    private static final String host = "covid-193.p.rapidapi.com";
    private MutableLiveData<List<HistoryModel>> mText;
    private HistoryRepository mRepository;
    private LiveData<List<HistoryModel>> mAllHistory;
    private LiveData<List<HistoryModel>> histMod;
    public HistoryViewModel(Application application) {
        super(application);
        mRepository = new HistoryRepository(application);
        mAllHistory = mRepository.getAllHistory();
        mText = new MutableLiveData<>();
    }


    public LiveData<List<HistoryModel>> getmAllHistory() {

        return mText;
    }
    public void deleteAll(String countryName){
        mRepository.deleteAll(countryName);
    }
    public void fetchTeams(String country, String day) {
        GetService service = Api.createService(GetService.class);
        List<HistoryModel> historyModelList = new ArrayList<HistoryModel>();
        Call<Example> call = service.getCountryHistory(token,host,country,day);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()) {
                    historyModelList.clear();
                    if (response != null) {
                        List<example.com.covid19.Models.HistoryModels.Response> list = response.body().getResponse();
                        for(int i = 0;i<list.size();i++){
                            HistoryModel model = new HistoryModel(list.get(i).getCountry(),list.get(i).getCases().getNew(),
                                    list.get(i).getDeaths().getNew(), list.get(i).getPopulation().toString(),list.get(i).getDay(),list.get(i).getTime());
                            historyModelList.add(model);
                            insert(model);
                        }
                    }
                    else{
                    }
                }
                mText.postValue(historyModelList);

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
            }

        });
    }
    public void insert(HistoryModel word) {
        mRepository.insert(word);
    }
}