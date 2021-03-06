package soexample.umeng.com.jindong.net;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitService {

    //加注解get post responsebody  get  括号里加注解url  字符型url
    @GET
    Call<ResponseBody> get(@Url String url, @QueryMap Map<String,String> map);

    //post
    @POST
    Call<ResponseBody> post(@Url String url,@QueryMap Map<String,String> map);


}
