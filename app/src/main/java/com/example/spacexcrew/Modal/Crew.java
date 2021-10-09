package com.example.spacexcrew.Modal;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Crew", indices = @Index(value = {"id"},unique = true))
public class Crew {
    @PrimaryKey(autoGenerate = true)
    private int uniqueId;

    public Crew(String name, String agency, String image, String wikipedia, String status, String id) {
        this.name = name;
        this.agency = agency;
        this.image = image;
        this.wikipedia = wikipedia;
        this.launches = launches;
        this.status = status;
        this.id = id;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("agency")
    @Expose
    private String agency;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("wikipedia")
    @Expose
    private String wikipedia;
    @SerializedName("launches")
    @Expose
    private List<String> launches;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id")
    @Expose
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public List<String> getLaunches() {
        return launches;
    }

    public void setLaunches(List<String> launches) {
        this.launches = launches;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "uniqueId=" + uniqueId +
                ", name='" + name + '\'' +
                ", agency='" + agency + '\'' +
                ", image='" + image + '\'' +
                ", wikipedia='" + wikipedia + '\'' +
                ", launches=" + launches +
                ", status='" + status + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}