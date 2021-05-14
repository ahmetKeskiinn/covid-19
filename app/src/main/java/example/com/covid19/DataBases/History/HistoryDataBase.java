package example.com.covid19.DataBases.History;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {HistoryModel.class}, version = 1)
public abstract class HistoryDataBase extends RoomDatabase {
    public abstract HistoryDao historyDao();

    private static HistoryDataBase INSTANCE;

    static HistoryDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HistoryDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HistoryDataBase.class, "teams")
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
