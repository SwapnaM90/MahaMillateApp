package com.mninetytechnology.mahamillateapp.network.reposervices;

import com.google.gson.JsonObject;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.Leaderboard;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.OrganisationLoginObject;
import com.mninetytechnology.mahamillateapp.models.viewmodelobj.UserLoginObject;
import com.mninetytechnology.mahamillateapp.network.Contants;
import com.mninetytechnology.mahamillateapp.network.responsemodel.BlogLikeResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.BlogResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.ClassResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.DistrictResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.DistrictTalukaVillageResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.DivisionResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.LoginResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OrganisationListResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OrganisationLoginResponseModel;
import com.mninetytechnology.mahamillateapp.network.responsemodel.OrganisationUserListResponseModel;
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
import retrofit2.http.Query;

public interface ApiService {

    /**
     * @param email
     * @param phone_number
     * @param password
     * @return
     * @date 1-3-2022
     * Gets Login Response from server
     * Response has status, key, user_id and user_role
     */
    @FormUrlEncoded
    @POST(Contants.GET_LOGIN)
    Call<LoginResponseModel> getLoginResponse(
            @Field("email") String email,
            @Field("phone_number") String phone_number,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST(Contants.GET_ORGANISATION_LOGIN)
    Call<OrganisationLoginResponseModel> getOrganisationLoginResponse(
            @Field("email") String email,
            @Field("phone_number") String phone_number,
            @Field("password") String password
    );


    /**
     * @param name
     * @param email
     * @param phone_number
     * @param password
     * @param district
     * @param village
     * @param classText
     * @return
     * @date 1-8-2023
     * Register User
     * Response has data and code
     */
    @FormUrlEncoded
    @POST(Contants.REGISTER_USER)
    Call<RegisterResponseModel> registerUser(
             @Field("name") String name,
             @Field("email") String email,
             @Field("phone_number") String phone_number,
             @Field("state") String state,
             @Field("division") String division,
             @Field("district") String district,
             @Field("taluka") String taluka,
             @Field("village") String village,
             @Field("class") String classText,
             @Field("password") String password,
             @Field("referral_code") String referral_code
//             @Field("organization_id") String organization_id
    );

    @FormUrlEncoded
    @POST(Contants.REGISTER_ORGANISATION)
    Call<OrganisationLoginResponseModel> registerOrganisation(
           @Field("name") String name,
           @Field("reg_number") String reg_number,
           @Field("contact_person") String contact_person,
           @Field("contact_person_email") String contact_person_email,
           @Field("password") String password,
           @Field("contact_person_phone_number") String contact_person_phone_number,
           @Field("state") String state,
           @Field("division") String division,
           @Field("district") String district,
           @Field("taluka") String taluka,
           @Field("village") String village
    );

    /**
     * @param zip_code
     * @return
     * @date 2-8-2023
     * get State, district, village etc from zip code
     * Response has data and code
     */
    @FormUrlEncoded
    @POST(Contants.REGISTER_USER)
    Call<RegisterResponseModel> getPostalDetails(
            @Field("zip_code") String zip_code
    );

    /**
     * @return
     * @date 3-8-2023
     * gets all blogs
     */
    @GET(Contants.BLOG)
    Call<BlogResponseModel> getBlogs();

    /**
     * @return
     * @date 3-8-2023
     * update like
     */
    @FormUrlEncoded
    @POST(Contants.UPDATE_LIKE + "/{blogId}")
    Call<BlogLikeResponseModel> updateLike(
            @Field("type") String type,
            @Path("blogId") String blogId
    );

    /**
     * @return
     * @date 3-8-2023
     * update dislike
     */
    @FormUrlEncoded
    @POST(Contants.UPDATE_DISLIKE + "/{blogId}")
    Call<BlogLikeResponseModel> updateDislike(
            @Field("type") String type,
            @Path("blogId") String blogId
    );

    /**
     * @return
     * @date 3-8-2023
     * update view
     */
    //@FormUrlEncoded
    @POST(Contants.UPDATE_VIEW + "/{blogId}")
    Call<BlogLikeResponseModel> updateView(
            @Path("blogId") String blogId
    );

    /**
     * @return
     * @date 3-8-2023
     * update share
     */
    //@FormUrlEncoded
    @POST(Contants.UPDATE_SHARE + "/{blogId}")
    Call<BlogLikeResponseModel> updateShare(
            @Path("blogId") String blogId
    );

    /**
     * @return
     * @date 3-8-2023
     * gets all video
     */
    @GET(Contants.VIDEOS)
    Call<VideoResponseModel> getVideos();

    /**
     * @return
     * @date 3-8-2023
     * gets all quiz
     */
    @GET(Contants.QUIZ + "/{id}")
    Call<QuizResponseModel> getAllQuiz(
            @Path("id") String userId
    );

    /**
     * @return
     * @date 7-8-2023
     * gets leaderboard user list
     */
    @GET(Contants.LEADERBORAD + "/{id}/?level=firstLevel")
    Call<List<UserLoginObject>> getLeaderboards(
            @Path("id") String userId
    );

    /**
     * @return
     * @date 7-8-2023
     * gets organisation user list
     */
    @GET(Contants.ORGANISATION_USERS + "/{id}")
    Call<OrganisationUserListResponseModel> getOrganisationUserList(
            @Path("id") String userId
    );

    /**
     * @return
     * @date 3-8-2023
     * gets all quiz data
     */
    @GET(Contants.GET_QUIZ_DATA + "/{userId}")
    Call<LoginResponseModel> getAllQuizData(
            @Path("userId") String userId
    );

    /**
     * @return
     * @date 8-8-2023
     * gets otp
     */
    @FormUrlEncoded
    @POST(Contants.GET_VERIFY_OTP)
    Call<OtpResponseModel> getOtp(
            @Field("number") String number,
            @Field("otp") String otp
    );

    /**
     * @return
     * @date 3-8-2023
     * gets all quiz data
     */
    @FormUrlEncoded
    @POST(Contants.UPDATE_SCORE+"/{userId}")
    Call<QuizScoreResponseModel> updateScore(
            @Path("userId") String userId,
            @Field("firstLevel") String firstLevel,
            @Field("result") boolean result
    );

    /**
     * @return
     * @date 3-8-2023
     * gets all class
     */
    @GET(Contants.GET_CLASS)
    Call<ClassResponseModel> getClassData();

    /**
     * @return
     * @date 3-8-2023
     * gets all division
     */
    @GET(Contants.GET_DIVISION)
    Call<DivisionResponseModel> getDivisions();

    /**
     * @return
     * @date 3-8-2023
     * gets all district
     */
    @GET(Contants.GET_DISTRICTCODE)
    Call<DistrictResponseModel> getDistricts(
            //@Path("division") String division
    );

    /**
     * @return
     * @date 3-8-2023
     * gets all taluka
     */
    @GET(Contants.GET_TALUKACODE + "/{district}")
    Call<TalukaResponseModel> getTaluka(
            @Path("district") String district
    );

    /**
     * @return
     * @date 3-8-2023
     * gets all village
     */
    @GET(Contants.GET_VILLAGECODE + "/{taluka}")
    Call<VillageResponseModel> getVillage(
            @Path("taluka") String taluka
    );

    /**
     * @date 17-8-2023
     * get district, taluka, village updated
     */
    /**
     * @return
     * @date 3-8-2023
     * gets all district
     */
    @GET(Contants.GET_DISTRICT)
    Call<DistrictTalukaVillageResponseModel> getUpdatedDistricts(
            //@Path("division") String division
    );

    /**
     * @return
     * @date 3-8-2023
     * gets all taluka
     */
    @GET(Contants.GET_TALUKA + "/{district}")
    Call<DistrictTalukaVillageResponseModel> getUpdatedTaluka(
            @Path("district") String district
    );

    /**
     * @return
     * @date 3-8-2023
     * gets all village
     */
    @GET(Contants.GET_VILLAGE + "/{taluka}")
    Call<DistrictTalukaVillageResponseModel> getUpdatedVillage(
            @Path("taluka") String taluka
    );

    /**
     * @return
     * @date 3-8-2023
     * gets all village
     */
    @GET(Contants.GET_CERTIFICATE + "/{id}")
    Call<JsonObject> getCertificate(
            @Path("id") String id
    );

    /**
     * @return
     * @date 3-8-2023
     * update user district
     */
    @FormUrlEncoded
    @POST(Contants.UPDATE_USER_DISTRICT + "/{id}")
    Call<LoginResponseModel> updateUserAddress(
            @Path("id") String id,
            @Field("district") String district,
            @Field("taluka") String taluka,
            @Field("village") String village
    );

    /**
     * @return
     * @date 3-8-2023
     * update organisation district
     */
    @FormUrlEncoded
    @POST(Contants.UPDATE_ORGANISATION_DISTRICT + "/{id}")
    Call<OrganisationLoginResponseModel> updateOrganisationAddress(
            @Path("id") String id,
            @Field("district") String district,
            @Field("taluka") String taluka,
            @Field("village") String village
    );


    /**
     * @return
     * @date 3-8-2023
     * gets all village
     */
    @GET(Contants.GET_ORGANISATION)
    Call<OrganisationListResponseModel> getOrganisationListByPage(
            @Query("page") String page
    );

    /**
     * @return
     * @date 3-8-2023
     * updates organisation
     */
    @FormUrlEncoded
    @POST(Contants.UPDATE_ORGANISATION+"/{id}")
    Call<LoginResponseModel> updateOrganisation(
            @Path("id") String id,
            @Field("organization_id") String organization_id
    );

    /**
     * @return
     * @date 3-8-2023
     * gets organisation
     */
    @GET(Contants.GET_ORGANISATION+"/{id}")
    Call<OrganisationLoginResponseModel> getOrganisationByID(
            @Path("id") String id
    );
}

