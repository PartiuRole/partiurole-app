package com.partiurole.partiurole.util;

import com.partiurole.partiurole.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface Api {
    String BASE_URL = "http://177.44.248.80:8080/api/";
    @GET("events/")
    Call<List<Event>> getEvents();

    @GET("events/{id}")
    Call<Event> getEvent(@Path("id") int id);

    @POST("events/{id}")
    Call<Event> postEvent(@Path("id") int id);

    @PUT("events/{id}")
    Call<Event> putEvent(@Path("id") int id);

    @DELETE("events/{id}")
    Call<Event> deleteEvent(@Path("id") int id);
}
