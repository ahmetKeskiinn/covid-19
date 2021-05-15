package example.com.covid19.DataBases.Country;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;



public class CountryRepository {
    private CountryDao countryDao;
    private LiveData<List<CountryModel>> allCountries;

    public CountryRepository(Application application) {
        CountryDataBase db = CountryDataBase.getDatabase(application);
        countryDao = db.countryDao();
        allCountries = countryDao.getAllCountries();
    }

    public LiveData<List<CountryModel>> getAllHistory() {
        return allCountries;
    }

    public void insert(CountryModel word) {
        new CountryRepository.insertAsyncTask(countryDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<CountryModel, Void, Void> {

        private CountryDao countryDao;

        insertAsyncTask(CountryDao dao) {
            countryDao = dao;
        }

        @Override
        protected Void doInBackground(final CountryModel... countryModels) {
            countryDao.insert(countryModels[0]);
            return null;
        }
    }

}