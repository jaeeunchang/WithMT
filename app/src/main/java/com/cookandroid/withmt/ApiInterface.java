package com.cookandroid.withmt;

import com.cookandroid.withmt.MainPage.MainList;
import com.cookandroid.withmt.MyPage.MyInfo;
import com.cookandroid.withmt.MyWriting.MyWritingResponse;
import com.cookandroid.withmt.PreferenceCheck.Preference;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.cookandroid.withmt.Login.LoginRequest;
import com.cookandroid.withmt.SignUp.SignUpRequest;
import com.cookandroid.withmt.SignUp.SignUpResponse;
import com.cookandroid.withmt.BoardDetail.BoardDetailResponse;
import com.cookandroid.withmt.Writing.WritingRequest;
import com.cookandroid.withmt.Writing.WritingView;

public interface ApiInterface {
    // 로그인
    @POST("login")
    Call<String> postLogin(@Body LoginRequest loginRequest);

    //닉네임 중복 확인
    @GET("users/double")
    Call<String> getNickname(@Query("nickname") String nickname);
    //아이디 중복 확인
    @GET("users/double")
    Call<String> getID(@Query("userId") String userId);

    //회원가입
    @POST("users")
    Call<SignUpResponse> postSignUp(@Body SignUpRequest signupRequest);

    //설문조사 입력 및 수정
    @PUT("users/taste")
    Call<Preference> putPreference(@Body Preference param);

    //마이페이지-사용자 정보조회
    @GET("users")
    Call<MyInfo> getUserInfo(@Query("userId") String userId);

    //로그아웃
    @POST("logout")
    Call<String> postLogout();

    //게시글 최신순 불러오기
//    @GET("board")
//    Call<List<MainList>> getAll();

    @GET("board")
    Call<MainList> getAll();

    //게시글 추천순 불러오기
    @GET("board/recommend")
//    Call<List<MainList>> getRecommend();
    Call<MainList> getRecommend();

    //게시글 작성
    @POST("board/write")
    Call<Integer> postWriting(@Body WritingRequest writingRequest);

    //마이페이지-글 조회
    @GET("board")
    Call<List<MyWritingResponse>> getMyWriting(@Query("userId") String userid);

    //게시글 상세 조회
    @GET("board/{boardId}")
    Call<List<BoardDetailResponse>> getBoard(@Path("boardId") int boardId);

    //게시글 삭제
    @DELETE("board/{boardId}")
    Call<String> deleteBoard(@Path("boardId") int boardId);

    //게시글 수정
    @PUT("board/update/{boardId}")
    Call<String> putBoard(@Path("boardId") int boardId, @Body WritingRequest writingRequest);
}
