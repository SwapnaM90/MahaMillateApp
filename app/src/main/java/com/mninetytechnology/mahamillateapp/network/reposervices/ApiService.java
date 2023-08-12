package com.mninetytechnology.mahamillateapp.network.reposervices;

import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Leaderboard;
import com.mninetytechnology.mahamillateapp.network.Contants;
import com.mninetytechnology.mahamillateapp.network.responsemodel.BlogResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.ClassResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.DistrictResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.DivisionResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.LoginResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OtpResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.QuizLevelResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.QuizResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.QuizScoreResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.RegisterResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.TalukaResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.VideoResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.VillageResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    /**
     * @date 1-3-2022
     * Gets Login Response from server
     * Response has status, key, user_id and user_role
     * @param email
     * @param phone_number
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(Contants.GET_LOGIN)
    Call<LoginResponseModel> getLoginResponse(
            @Field("email") String email,
            @Field("phone_number") String phone_number,
            @Field("password") String password
    );


    /**
     * @date 1-8-2023
     * Register User
     * Response has data and code
     * @param name
     * @param email
     * @param phone_number
     * @param password
     * @param district
     * @param city
     * @param village
     * @param classText
     * @return
     */
    @FormUrlEncoded
    @POST(Contants.REGISTER_USER)
    Call<RegisterResponseModel> registerUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone_number") String phone_number,
            @Field("password") String password,
            @Field("state") String state,
            @Field("district") String district,
            @Field("city") String city,
            @Field("village") String village,
            @Field("class") String classText
    );

    /**
     * @date 2-8-2023
     * get State, district, village etc from zip code
     * Response has data and code
     * @param zip_code
     * @return
     */
    @FormUrlEncoded
    @POST(Contants.REGISTER_USER)
    Call<RegisterResponseModel> getPostalDetails(
            @Field("zip_code") String zip_code
    );

    /**
     * @date 3-8-2023
     * gets all blogs
     * @return
     */
    @GET(Contants.BLOG)
    Call<BlogResponseModel> getBlogs();

    /**
     * @date 3-8-2023
     * update like
     * @return
     */
    @FormUrlEncoded
    @POST(Contants.UPDATE_LIKE)
    Call<BlogResponseModel> updateLike(
            @Field("type") String type
    );

    /**
     * @date 3-8-2023
     * update dislike
     * @return
     */
    @FormUrlEncoded
    @POST(Contants.UPDATE_DISLIKE)
    Call<BlogResponseModel> updateDislike(
            @Field("type") String type
    );

    /**
     * @date 3-8-2023
     * update view
     * @return
     */
    @GET(Contants.UPDATE_VIEW)
    Call<BlogResponseModel> updateView();

    /**
     * @date 3-8-2023
     * update share
     * @return
     */
    @GET(Contants.UPDATE_SHARE)
    Call<BlogResponseModel> updateShare();

    /**
     * @date 3-8-2023
     * gets all video
     * @return
     */
    @GET(Contants.VIDEOS)
    Call<VideoResponseModel> getVideos();

     /**
     * @date 3-8-2023
     * gets all quiz
     * @return
     */
    @GET(Contants.QUIZ+"/{id}")
    Call<QuizResponseModel> getAllQuiz(
            @Path("id") String userId
    );

    /**
     * @date 7-8-2023
     * gets leaderboard user list
     * @return
     */
    @GET(Contants.LEADERBORAD+"/{id}")
    Call<List<Leaderboard>> getLeaderboards(
            @Path("id") String userId
    );

    /**
     * @date 3-8-2023
     * gets all quiz data
     * @return
     */
    @GET(Contants.GET_QUIZ_DATA+"/{userId}")
    Call<QuizLevelResponseModel> getAllQuizData(
            @Path("userId") String userId
    );

    /**
     * @date 8-8-2023
     * gets otp
     * @return
     */
    @FormUrlEncoded
    @POST(Contants.GET_VERIFY_OTP)
    Call<OtpResponseModel> getOtp(
            @Field("number") String number,
            @Field("otp") String otp
    );

    /**
     * @date 3-8-2023
     * gets all quiz data
     * @return
     */
    @FormUrlEncoded
    @POST(Contants.UPDATE_SCORE)
    Call<QuizScoreResponseModel> updateScore(
            @Field("userId") String userId,
            @Field("score") String firstLevel
    );

    /**
     * @date 3-8-2023
     * gets all class
     * @return
     */
    @GET(Contants.GET_CLASS)
    Call<ClassResponseModel> getClassData();

    /**
     * @date 3-8-2023
     * gets all division
     * @return
     */
    @GET(Contants.GET_DIVISION)
    Call<DivisionResponseModel> getDivisions();

    /**
     * @date 3-8-2023
     * gets all district
     * @return
     */
    @GET(Contants.GET_DISTRICT)
    Call<DistrictResponseModel> getDistricts(
            //@Path("division") String division
    );

    /**
     * @date 3-8-2023
     * gets all taluka
     * @return
     */
    @GET(Contants.GET_TALUKA+"/{district}")
    Call<TalukaResponseModel> getTaluka(
            @Path("district") String district
    );

    /**
     * @date 3-8-2023
     * gets all village
     * @return
     */
    @GET(Contants.GET_VILLAGE+"/{taluka}")
    Call<VillageResponseModel> getVillage(
            @Path("taluka") String taluka
    );
}

