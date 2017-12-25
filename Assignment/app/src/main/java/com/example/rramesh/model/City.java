package com.example.rramesh.model;

import com.google.gson.annotations.SerializedName;



/**
 * Created by Ramesh on 12/23/2017.
 */

public class City {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("fromcentral")
    public FromCentral fromCentral;
    @SerializedName("location")
    public Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FromCentral getFromCentral() {
        return fromCentral;
    }

    public void setFromCentral(FromCentral fromCentral) {
        this.fromCentral = fromCentral;
    }

    public class FromCentral{
        @SerializedName("car")
        public String car;

        public String getTrain() {
            return train;
        }

        public void setTrain(String train) {
            this.train = train;
        }

        public String getCar() {
            return car;
        }

        public void setCar(String car) {
            this.car = car;
        }

        @SerializedName("train")
        public String train;
    }

    public class Location{
        public float latitude;
        public float longitude;
    }
}
