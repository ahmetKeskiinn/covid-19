package example.com.covid19.DataBases.Country;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import example.com.covid19.DataBases.History.HistoryDao;
import example.com.covid19.DataBases.History.HistoryModel;

@Database(entities = {CountryModel.class}, version = 1)
public abstract class CountryDataBase extends RoomDatabase {
    public abstract CountryDao countryDao();

    private static CountryDataBase INSTANCE;

    static CountryDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CountryDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CountryDataBase.class, "countries")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

}