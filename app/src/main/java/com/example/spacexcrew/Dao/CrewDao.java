package com.example.spacexcrew.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.spacexcrew.Modal.Crew;

import java.util.List;

@Dao
public interface CrewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Crew> crewList);

    @Query("SELECT * FROM crew")
    LiveData<List<Crew>> getAllCrewMembers();

    @Query("DELETE FROM crew")
    void deleteAll();
}
