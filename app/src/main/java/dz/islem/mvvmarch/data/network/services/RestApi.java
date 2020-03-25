package dz.islem.mvvmarch.data.network.services;

import java.util.List;

import dz.islem.mvvmarch.data.network.model.Answer;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET("test/")
    Call<List<Answer>> getAnswer();
}
