package example.com.covid19.DataBases.Statistics;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;



@Dao
public interface StatisticsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(StatisticsModel team);

    @Update
    void update(StatisticsModel team);


    @Query("SELECT * FROM country_Info_Table")
    LiveData<StatisticsModel> getAllInfo();

    @Query("SELECT * FROM country_Info_Table WHERE country LIKE '%' || (:weekNo) || '%'")
    LiveData<StatisticsModel> selectedWeek(String weekNo);
}
