package soexample.umeng.com.jindong.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private Retrofit retrofit;
    private RetrofitListener retrofitListener;
    //单例模式
    //本类的构造方法改成私有的

    private RetrofitUtils() {
    }

    //公共静态 本类名 加个 get
    public static  RetrofitUtils getRetrofitUtils(){
        //判断提上去的本类名为空
        if(retrofitUtils==null){
            //new 本类名 返回值 提上去
            retrofitUtils = new RetrofitUtils();
        }
        //直接返回提上去的本类名
        return retrofitUtils;
    }

    //公共的 初始化方法 不传值
    public void init(){
        //new retrofit 先返回值 提上去 在.builder .baseurl 拼接网址加 /.build 提交
         retrofit = new Retrofit.Builder().baseUrl("http://www.zhaoapi.cn/").build();
    }

    //公共的 大写本类 get 方法 括号里传接口的两个参数
    public RetrofitUtils get(String url, Map<String,String> map){
        //先判断集合为空
        if(map==null){
            //map=new hasmap 泛型为空
            map=new HashMap<>();
        }

        //调用初始化retrofit 新建 调用接口的类.get 传传进来的两个参数 .enqueue 异步请求 new 快捷键
        retrofit.create(RetrofitService.class).get(url,map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //成功方法
                //用传进来的response.body .小写字符串 先抓异常 在返回字符串值
                try {
                    String s = response.body().string();
                    //去下面定义接口回调
                    //调用接口成功方法 传字符串
                    retrofitListener.success(s);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //失败方法
                //调用接口失败方法不传值
                retrofitListener.fail();
            }
        });


        //记得返回this
        return this;
    }

    //post  复制上面的 get 方法  改两个位置post

    //公共的 大写本类 get 方法 括号里传接口的两个参数
    public RetrofitUtils post(String url, Map<String,String> map){
        //先判断集合为空
        if(map==null){
            //map=new hasmap 泛型为空
            map=new HashMap<>();
        }

        //调用初始化retrofit 新建 调用接口的类.get 传传进来的两个参数 .enqueue 异步请求 new 快捷键
        retrofit.create(RetrofitService.class).post(url,map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //成功方法
                //用传进来的response.body .小写字符串 先抓异常 在返回字符串值
                try {
                    String s = response.body().string();
                    //去下面定义接口回调
                    //调用接口成功方法 传字符串
                    retrofitListener.success(s);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //失败方法
                //调用接口失败方法不传值
                retrofitListener.fail();
            }
        });


        //记得返回this
        return this;
    }

    //回调接口方法
    public void result(RetrofitListener retrofitListener){
        //接口返回值 this. 提上去
        this.retrofitListener = retrofitListener;
    }

    //定义接口
    public interface RetrofitListener{
        //定义成功方法传字符串
        void success(String s);
        //定义失败方法不传值
        void fail();
    }
}
