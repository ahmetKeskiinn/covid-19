package example.com.covid19.DataBases.Country;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import example.com.covid19.DataBases.History.HistoryModel;

@Dao
public interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CountryModel team);

    @Update
    void update(CountryModel team);

    @Query("SELECT * FROM country_table ORDER BY country ASC")
    LiveData<List<CountryModel>> getAllCountries();
}
