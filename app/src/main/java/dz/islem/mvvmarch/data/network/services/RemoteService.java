package dz.islem.mvvmarch.data.network.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteService {
    private static final String BASE_URL ="http://demo6483760.mockable.io/";

    private static RemoteService mInstance;
    private RestApi mRestApi;

    public static synchronized RemoteService getInstance() {
        return mInstance == null ? mInstance= new RemoteService() : mInstance;
    }

    private RemoteService(){
        Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mRestApi = mRetrofit.create(RestApi.class);
    }

    public RestApi getRestApi() {
        return mRestApi;
    }

}


