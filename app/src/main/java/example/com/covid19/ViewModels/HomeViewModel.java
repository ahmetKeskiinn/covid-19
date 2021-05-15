package example.com.covid19.ViewModels;

import android.app.Application;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import java.util.ArrayList;
import java.util.List;

import example.com.covid19.DataBases.Country.CountryModel;
import example.com.covid19.DataBases.Country.CountryRepository;
import example.com.covid19.DataBases.History.HistoryModel;
import example.com.covid19.Models.CountryModel.ExampleResponse;
import example.com.covid19.Utils.Api;
import example.com.covid19.Utils.ApiServices.GetService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private static final String token = "966dd09665mshfee82873a81b1f5p1a2d3bjsnb22c69531b3e";
    private static final String host = "covid-193.p.rapidapi.com";
    private CountryRepository mRepository;
    private LiveData<List<CountryModel>> mAllCountries;


    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        mRepository = new CountryRepository(application);
        mAllCountries = mRepository.getAllHistory();
    }
    public LiveData<List<CountryModel>> getmAllCountries() {
        return mAllCountries;
    }

    public void fetchCountries() {
        GetService service = Api.createService(GetService.class);
        List<HistoryModel> historyModelList = new ArrayList<HistoryModel>();
        Call<ExampleResponse> call = service.getAllCountry(token,host);
        call.enqueue(new Callback<ExampleResponse>() {
            @Override
            public void onResponse(Call<ExampleResponse> call, Response<ExampleResponse> response) {
                if (response.isSuccessful()) {
                    if (response != null) {
                        for(int i = 0 ; i<response.body().getResponse().size();i++){
                            CountryModel model = new CountryModel(response.body().getResponse().get(i));
                            insert(model);
                        }
                    }
                    else{

                    }
                }

            }

            @Override
            public void onFailure(Call<ExampleResponse> call, Throwable t) {
            }

        });
    }

    public void insert(CountryModel country) {
        mRepository.insert(country);
    }
}