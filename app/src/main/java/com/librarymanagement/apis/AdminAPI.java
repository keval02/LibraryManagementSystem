package com.librarymanagement.apis;

import com.librarymanagement.constant.ApiURLs;
import com.librarymanagement.model.AppModel;
import com.librarymanagement.model.LoginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by keval on 12-04-2018.
 */

public interface AdminAPI {


    @FormUrlEncoded
    @POST(ApiURLs.LOGIN)
    Call<LoginModel> LOGIN_MODEL_CALL(@Field("email") String emailId , @Field("password") String password);


    @FormUrlEncoded
    @POST(ApiURLs.REGISTER)
    Call<LoginModel> REGISTER_MODEL_CALL(@Field("username") String username ,@Field("address") String address ,@Field("email") String emailId , @Field("password") String password , @Field("mobileno") String mobileNo);


    @FormUrlEncoded
    @POST(ApiURLs.BOOKISSUE)
    Call<AppModel> BOOK_ISSUE_DETAILS(@Field("username") String username ,@Field("email") String emailId , @Field("bookname") String bookname , @Field("booktype") String booktype , @Field("date") String date);



}
