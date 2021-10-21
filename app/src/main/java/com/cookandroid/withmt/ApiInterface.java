package com.cookandroid.withmt;

import com.cookandroid.withmt.MainPage.MainList;
import com.cookandroid.withmt.MyPage.Logout;
import com.cookandroid.withmt.MyPage.MyInfo;
import com.cookandroid.withmt.PreferenceCheck.Preference;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //초기 설문조사값 입력
    @PUT("users/taste")
    Call<Preference> putPreference(@Body Preference param);
//    Call<Preference> putPreference(@Field("data") String data);

    //마이페이지-사용자 정보조회
//    @GET("users?userId={userid}")
//    Call<MyInfo> getUserInfo(@Path("userid") String userId);
    @GET("users")
    Call<MyInfo> getUserInfo(@Query("userId") String userid);


    //로그아웃
    @FormUrlEncoded
    @POST("logout")
    Call<Logout> postLogout(@Body Logout post);
//    Call<Data> postData(@FieldMap HashMap<String, Object> param);

    //게시글 최신순 불러오기
    @GET("board")
    Call<List<MainList>> getFunc(@Query("data") String data);

//    @FormUrlEncoded
//    @PUT("users/update/1")
//    Call<ResponseBody> putFunc(@Field("data") String data);


}
