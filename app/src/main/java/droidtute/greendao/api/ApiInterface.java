package droidtute.greendao.api;

import droidtute.greendao.model.ResponseMode;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("top-headlines?sources=techcrunch&apiKey=801d8c3455fe475bbab9f7e4c6944aa3")
    Call<ResponseMode> getLatestNews();


}