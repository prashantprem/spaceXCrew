package com.example.spacexcrew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.spacexcrew.Adapter.CrewAdapter;
import com.example.spacexcrew.Database.CrewDatabase;
import com.example.spacexcrew.Modal.Crew;
import com.example.spacexcrew.Network.Api;
import com.example.spacexcrew.Repository.CrewRepository;
import com.example.spacexcrew.ViewModal.CrewViewModal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private CrewViewModal crewViewModal;
    private RecyclerView recyclerView;
    private Button delete,refresh;
    private CrewAdapter crewAdapter;
    private List<Crew> crewList;
    private CrewRepository crewRepository;
    private static final String URL = "https://api.spacexdata.com/v4/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        crewRepository = new CrewRepository(getApplication());
        crewList = new ArrayList<>();
        crewAdapter = new CrewAdapter(this,crewList);

        crewViewModal= new ViewModelProvider(this).get(CrewViewModal.class);
        crewViewModal.getGetAllCrewMembers().observe(this, new Observer<List<Crew>>() {
            @Override
            public void onChanged(List<Crew> crewList) {
                crewAdapter.getAllCrewMembers(crewList);
                recyclerView.setAdapter(crewAdapter);
            }
        });
        networkRequest();
        delete = findViewById(R.id.delete);
        refresh = findViewById(R.id.refresh);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRecyclerView();
                deleteAllCrew();
                Toast.makeText(MainActivity.this, "Database Cleared", Toast.LENGTH_SHORT).show();

//                loadCrewList();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                networkRequest();
                initRecyclerView();
                Toast.makeText(MainActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        crewAdapter = new CrewAdapter(this,crewList);
        recyclerView.setAdapter(crewAdapter);
    }
//    private void loadCrewList() {
//        CrewDatabase db = CrewDatabase.getInstance(this.getApplicationContext());
//        LiveData<List<Crew>> crewList = db.crewDao().getAllCrewMembers();
//        crewAdapter.setCrewList((List<Crew>) crewList);
//
//    }

    private void networkRequest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Crew>> call = api.getAllCrewMembers();
        call.enqueue(new Callback<List<Crew>>() {
            @Override
            public void onResponse(Call<List<Crew>> call, Response<List<Crew>> response) {
            if(response != null)
            {
                crewRepository.insert(response.body());
            }
            }

            @Override
            public void onFailure(Call<List<Crew>> call, Throwable t) {
                t.getMessage();
                Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void deleteAllCrew()
    {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                CrewDatabase db  = CrewDatabase.getInstance(getApplicationContext());
                db.crewDao().deleteAll();
            }
        });


    }
}