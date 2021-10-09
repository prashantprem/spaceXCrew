package com.example.spacexcrew.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.spacexcrew.Dao.CrewDao;
import com.example.spacexcrew.Database.CrewDatabase;
import com.example.spacexcrew.Modal.Crew;

import java.util.List;

public class CrewRepository {
    private CrewDatabase database;
    private LiveData<List<Crew>> getAllCrewMembers;
    public CrewRepository(Application application)
    {
        database = CrewDatabase.getInstance(application);
        getAllCrewMembers = database.crewDao().getAllCrewMembers();

    }
    public void  insert(List<Crew> crewList){
        new InsertAsyncTask(database).execute(crewList);

    }
    public LiveData<List<Crew>> getGetAllCrewMembers()
    {
        return getAllCrewMembers;
    }

    static class InsertAsyncTask extends AsyncTask<List<Crew>,Void,Void>{
        private CrewDao crewDao;
        InsertAsyncTask(CrewDatabase crewDatabase)
        {
            crewDao = crewDatabase.crewDao();
        }
        @Override
        protected Void doInBackground(List<Crew>... lists) {
            crewDao.insert(lists[0]);
            return null;
        }
    }
}
