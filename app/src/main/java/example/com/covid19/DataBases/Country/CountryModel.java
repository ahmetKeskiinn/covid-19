package example.com.covid19.DataBases.Country;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "country_table")
public class CountryModel {
    @PrimaryKey
    @NonNull
    private String country;

    public CountryModel(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
