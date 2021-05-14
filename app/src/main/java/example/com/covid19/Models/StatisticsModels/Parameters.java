package example.com.covid19.Models.StatisticsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameters {

    @SerializedName("country")
    @Expose
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
