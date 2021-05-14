package example.com.covid19.DataBases.History;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(HistoryModel team);

    @Update
    void update(HistoryModel team);

    @Query("delete from history_table WHERE country != :countryName")
    public void deleteCurrentHistory(String countryName);

    @Query("SELECT * FROM history_table ORDER BY country DESC")
    LiveData<List<HistoryModel>> getAllHistory();

    @Query("SELECT * FROM history_table WHERE country LIKE '%' || (:weekNo) || '%'")
    LiveData<List<HistoryModel>> selectedWeek(String weekNo);
}