package example.com.covid19.DataBases.History;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_table", primaryKeys = {"day", "time","country"})
public class HistoryModel {

    public int id;
    @NonNull
    private String country;
    private String cases;
    private String deaths;
    private String population;
    @NonNull
    private String day;
    @NonNull
    public String time;

    public HistoryModel(String country, String cases, String deaths, String population, String day, String time) {
        this.country = country;
        this.cases = cases;
        this.deaths = deaths;
        this.population = population;
        this.day = day;
        this.time = time;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
