package com.example.spacexcrew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spacexcrew.Modal.Crew;
import com.example.spacexcrew.R;
import com.example.spacexcrew.ViewModal.CrewViewModal;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.http.PUT;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewViewHolder> {

    private Context context;
    private List<Crew> crewList;

    public CrewAdapter(Context context, List<Crew> crewList) {
        this.context = (Context) context;
        this.crewList = crewList;
    }
    public void setCrewList(List<Crew> crewList) {
        this.crewList = crewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CrewViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {

        Crew crew = crewList.get(position);
        holder.name.setText(crew.getName());
        holder.agency.setText(crew.getAgency());
        holder.status.setText(crew.getStatus());
        String url = crew.getImage();

//        Glide.with(context)
//                .load(crew.getImage())
//                .fitCenter()
//                .placeholder(R.drawable.placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .override(600, 200)
//                .into(holder.imageview);

//        RequestManager requestManager = Glide.with(context);
//        RequestBuilder requestBuilder = requestManager.load(crewList.get(position).getImage());
//        requestBuilder.into(holder.imageview);
        Picasso.get().load(crew.getImage())
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .fit()
                .noFade()
                .into(holder.imageview);

        holder.wikipedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = crew.getWikipedia();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);

            }
        });
    }
    public void getAllCrewMembers(List<Crew> crewList)
    {
        this.crewList = crewList;
    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }

    public static class CrewViewHolder extends RecyclerView.ViewHolder{
        public TextView name,agency,status;
        public ImageView imageview;
        public Button wikipedia;
        public CrewViewHolder(View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            agency= itemView.findViewById(R.id.agency);
            status = itemView.findViewById(R.id.status);
            wikipedia = itemView.findViewById(R.id.wikipedia);
            imageview = itemView.findViewById(R.id.imageView);
        }
    }
}
