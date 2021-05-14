package example.com.covid19.DataBases.Statistics;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {StatisticsModel.class}, version = 1)
public abstract class StatisticsDataBase extends RoomDatabase {
    public abstract StatisticsDao statisticsDao();

    private static StatisticsDataBase INSTANCE;

    static StatisticsDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StatisticsDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StatisticsDataBase.class, "countryInfo")
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