package example.com.covid19.Utils.ApiServices;

import example.com.covid19.DataBases.History.HistoryModel;
import example.com.covid19.Models.CountryModel.ExampleResponse;
import example.com.covid19.Models.HistoryModels.Example;
import example.com.covid19.Models.StatisticsModels.ExampleStatistics;
import example.com.covid19.Models.StatisticsModels.ResponseStatistics;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GetService {
    @GET("history")
    Call<Example> getCountryHistory (@Header("x-rapidapi-key") String key,
                                     @Header("x-rapidapi-host") String host,
                                     @Query("country") String country,
                                     @Query("day")String day);

    @GET("countries")
    Call<ExampleResponse> getAllCountry(@Header("x-rapidapi-key") String key,
                                        @Header("x-rapidapi-host") String host);


    @GET("statistics")
    Call<ExampleStatistics> getCountryStatistics(@Header("x-rapidapi-key") String key,
                                                 @Header("x-rapidapi-host") String host, @Query("country") String country);


}
