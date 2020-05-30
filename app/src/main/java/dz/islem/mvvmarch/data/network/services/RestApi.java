package dz.islem.mvvmarch.data.network.services;

import java.util.List;

import dz.islem.mvvmarch.data.network.model.Answer;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface RestApi {

    @GET("test/")
    Single<List<Answer>> getAnswer();
}
