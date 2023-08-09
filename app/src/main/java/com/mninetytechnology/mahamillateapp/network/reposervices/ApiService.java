package com.mninetytechnology.mahamillateapp.network.reposervices;

import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Leaderboard;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.QuizLevelData;
import com.mninetytechnology.mahamillateapp.network.Contants;
import com.mninetytechnology.mahamillateapp.network.responsemodel.BlogResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.LeaderboardResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.LoginResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OtpCheckResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OtpResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.QuizResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.QuizScoreResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.RegisterResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.VideoResponseModel;

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
     * @param state
     * @param district
     * @param city
     * @param village
     * @param zip_code
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
            @Field("zip_code") String zip_code,
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
    @GET(Contants.QUIZ)
    Call<QuizResponseModel> getAllQuiz();

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
    @FormUrlEncoded
    @POST(Contants.GET_QUIZ_DATA)
    Call<QuizLevelData> getAllQuizData(
            @Field("userId") String userId,
            @Field("firstLevel") String firstLevel
    );

    /**
     * @date 8-8-2023
     * gets otp
     * @return
     */
    @FormUrlEncoded
    @POST(Contants.GET_VERIFY_OTP+"?user=gramup&password=gramup&senderid=MTCDSS&channel=Trans&DCS=0&flashsms=0&number={phone_number}&text={text}&peid=1201159203365304053&DLTTemplateId=1407169096161883164")
    Call<OtpResponseModel> getOtp(
            @Path("phone_number") String phone_number,
            @Path("text") String text
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


}

