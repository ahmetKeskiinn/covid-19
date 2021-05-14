package example.com.covid19.DataBases.Statistics;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "country_Info_Table")
public class StatisticsModel {
    @PrimaryKey
    @NonNull
    private String country;
    private String continent;
    private String newCases;
    private String totalCases;
    private String criticalCases;
    private String oneMPopCases;
    private String activeCases;
    private String deathsNew;
    private String deathsOneMPop;
    private String deathsTotal;
    private String testOneMPop;
    private String totalTest;
    private String day;
    private String time;

    public StatisticsModel(@NonNull String country, String continent, String newCases, String totalCases, String criticalCases,
                           String oneMPopCases, String activeCases, String deathsNew, String deathsOneMPop, String deathsTotal,
                           String testOneMPop, String totalTest, String day, String time) {
        this.country = country;
        this.continent = continent;
        this.newCases = newCases;
        this.totalCases = totalCases;
        this.criticalCases = criticalCases;
        this.oneMPopCases = oneMPopCases;
        this.activeCases = activeCases;
        this.deathsNew = deathsNew;
        this.deathsOneMPop = deathsOneMPop;
        this.deathsTotal = deathsTotal;
        this.testOneMPop = testOneMPop;
        this.totalTest = totalTest;
        this.day = day;
        this.time = time;
    }

    @NonNull
    public String getCountry() {
        return country;
    }

    public void setCountry(@NonNull String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getCriticalCases() {
        return criticalCases;
    }

    public void setCriticalCases(String criticalCases) {
        this.criticalCases = criticalCases;
    }

    public String getOneMPopCases() {
        return oneMPopCases;
    }

    public void setOneMPopCases(String oneMPopCases) {
        this.oneMPopCases = oneMPopCases;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getDeathsNew() {
        return deathsNew;
    }

    public void setDeathsNew(String deathsNew) {
        this.deathsNew = deathsNew;
    }

    public String getDeathsOneMPop() {
        return deathsOneMPop;
    }

    public void setDeathsOneMPop(String deathsOneMPop) {
        this.deathsOneMPop = deathsOneMPop;
    }

    public String getDeathsTotal() {
        return deathsTotal;
    }

    public void setDeathsTotal(String deathsTotal) {
        this.deathsTotal = deathsTotal;
    }

    public String getTestOneMPop() {
        return testOneMPop;
    }

    public void setTestOneMPop(String testOneMPop) {
        this.testOneMPop = testOneMPop;
    }

    public String getTotalTest() {
        return totalTest;
    }

    public void setTotalTest(String totalTest) {
        this.totalTest = totalTest;
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
