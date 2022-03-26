package com.moringaschool.bookiva.client;

import com.moringaschool.bookiva.model.ResponseBooks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookApi {
    @GET("volumes")
    Call<ResponseBooks> getRecommendedBook(@Query("q") String recommendedBook);

    @GET("volumes")
    Call<ResponseBooks> geteditorsBook(@Query("q") String editorsBook);
}
