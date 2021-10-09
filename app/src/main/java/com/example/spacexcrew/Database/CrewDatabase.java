package com.example.spacexcrew.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.spacexcrew.Dao.CrewDao;
import com.example.spacexcrew.Modal.Crew;
import com.example.spacexcrew.converter.DataConverter;

@Database(entities = {Crew.class}, version = 2)
@TypeConverters({DataConverter.class})
public abstract class CrewDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "CrewDatabase";

    public abstract CrewDao crewDao();

    private static volatile CrewDatabase INSTANCE;

    public static CrewDatabase getInstance(Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (CrewDatabase.class){
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context,CrewDatabase.class,DATABASE_NAME).
                            addCallback(callback).
                            fallbackToDestructiveMigration().
                            build();
                }
            }
        }
            return INSTANCE;
    }
    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE);
        }
    };
    static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{
        private CrewDao crewDao;
        PopulateAsyncTask(CrewDatabase crewDatabase)
        {
            crewDao = crewDatabase.crewDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            crewDao.deleteAll();
            return null;
        }
    }
}
