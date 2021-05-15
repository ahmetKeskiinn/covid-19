package example.com.covid19.DataBases.History;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HistoryRepository {
    private HistoryDao historyDao;
    private LiveData<List<HistoryModel>> allHistory;

    public HistoryRepository(Application application) {
        HistoryDataBase db = HistoryDataBase.getDatabase(application);
        historyDao = db.historyDao();
    }

    public LiveData<List<HistoryModel>> getAllHistory() {
        return historyDao.getAllHistory();
    }
    public LiveData<List<HistoryModel>> selectedWeek(String week) {
        return historyDao.selectedWeek(week);
    }
    public void insert(HistoryModel word) {
        new insertAsyncTask(historyDao).execute(word);
    }

    public void deleteAll(String countryName){
        historyDao.deleteCurrentHistory(countryName);
    }

    private static class insertAsyncTask extends AsyncTask<HistoryModel, Void, Void> {

        private HistoryDao historyDao;

        insertAsyncTask(HistoryDao dao) {
            historyDao = dao;
        }

        @Override
        protected Void doInBackground(final HistoryModel... historyModels) {
            historyDao.insert(historyModels[0]);
            return null;
        }
    }

}