package com.letscode.commentNoteSystem.repository;

import com.letscode.commentNoteSystem.model.dto.OmdbDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbRepository {
    @GET("/")
    Call<OmdbDTO> getMovie(@Query("apikey") String apikey, @Query("t") String title, @Query("i") String id);
    @GET("/")
    Call<OmdbDTO> getMovieByTitle(@Query("apikey") String apikey, @Query("t") String title);
    @GET("/")
    Call<OmdbDTO> getMovieById(@Query("apikey") String apikey, @Query("i") String id);
}
