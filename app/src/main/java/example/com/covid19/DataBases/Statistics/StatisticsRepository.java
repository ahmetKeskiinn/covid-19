package example.com.covid19.DataBases.Statistics;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;


public class StatisticsRepository {
    private StatisticsDao statisticsDao;
    private LiveData<StatisticsModel> allInfo;

    public StatisticsRepository(Application application) {
        StatisticsDataBase db = StatisticsDataBase.getDatabase(application);
        statisticsDao = db.statisticsDao();
    }

    public LiveData<StatisticsModel> getAllInfo() {
        return statisticsDao.getAllInfo();
    }
    public LiveData<StatisticsModel> selectedWeek(String week) {
        return statisticsDao.selectedWeek(week);
    }
    public void insert(StatisticsModel word) {
        new StatisticsRepository.insertAsyncTask(statisticsDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<StatisticsModel, Void, Void> {

        private StatisticsDao statisticsDao;

        insertAsyncTask(StatisticsDao dao) {
            statisticsDao = dao;
        }

        @Override
        protected Void doInBackground(final StatisticsModel... statisticsModels) {
            statisticsDao.insert(statisticsModels[0]);
            return null;
        }
    }

}