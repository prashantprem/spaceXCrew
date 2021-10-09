package com.example.spacexcrew.ViewModal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.spacexcrew.Modal.Crew;
import com.example.spacexcrew.Repository.CrewRepository;

import java.util.List;

public class CrewViewModal extends AndroidViewModel {
    private CrewRepository crewRepository;
    private LiveData<List<Crew>> getAllCrewMembers;
    public CrewViewModal(@NonNull Application application) {
        super(application);
        crewRepository = new CrewRepository(application);
        getAllCrewMembers = crewRepository.getGetAllCrewMembers();
    }

    public void insert(List<Crew> list){
        crewRepository.insert(list);
    }

    public LiveData<List<Crew>> getGetAllCrewMembers()
    {
        return  getAllCrewMembers;
    }
}
