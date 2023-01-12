package com.icss.aasharambhoj.network;

import com.google.gson.JsonArray;
import com.icss.aasharambhoj.model_class.AddChefUtensilsresponse;
import com.icss.aasharambhoj.model_class.AddMenuResponse;
import com.icss.aasharambhoj.model_class.CategoryResponse;
import com.icss.aasharambhoj.model_class.DishByCategoryResponse;
import com.icss.aasharambhoj.model_class.GetDishesNew;
import com.icss.aasharambhoj.model_class.GetDishesResponse;
import com.icss.aasharambhoj.model_class.GetMenuByDateResponse;
import com.icss.aasharambhoj.model_class.GetMenuResponse;
import com.icss.aasharambhoj.model_class.IngrModelMainData;
import com.icss.aasharambhoj.model_class.LoginBuyerResponse;
import com.icss.aasharambhoj.model_class.LoginResponse;
import com.icss.aasharambhoj.model_class.PrefixDishResponse;
import com.icss.aasharambhoj.model_class.SubmitMenuResponse;
import com.icss.aasharambhoj.model_class.remember.RememberInitRes;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface APIInterface {

    @FormUrlEncoded
    @POST("login_as_chef")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("login_as_buyer")
    Call<LoginBuyerResponse> login_buyer(
            @Field("email") String email,
            @Field("password") String password

    );


    @GET("get_dishes")
    Call<AddMenuResponse> GetMenu();

    @GET("get_dish_types")
    Call<GetDishesResponse> GetMenuNew();

    @GET("get_categories")
    Call<CategoryResponse> Get_categ();

    @FormUrlEncoded
    @POST("add_menu")
    Call<SubmitMenuResponse> AddMenu(
            @Field("menu_name") String menu_name,
            @Field("chef_id") String chef_id,
            @Field("date") String date,
            @Field("category_id") String category_id,
            @Field("menu_array") String menu_array

    );

    @FormUrlEncoded
    @POST("add_menu_utensils")
    Call<SubmitMenuResponse> AddUtensil(
            @Field("menu_name") String menu_name,
            @Field("chef_id") String chef_id,
            @Field("date") String date,
            @Field("category_id") String category_id,
            @Field("utensil_array") String utensil_array

    );

    @GET("get_utensils")
    Call<AddChefUtensilsresponse> GetUtensil();

    @FormUrlEncoded
    @POST("get_menu_data_for_pdf")
    Call<IngrModelMainData> getIngredi(
            @Field("date") String date

    );

    @FormUrlEncoded
    @POST("get_menu_by_date")
    Call<GetMenuByDateResponse> getMenu(
            @Field("date") String date

    );

    @FormUrlEncoded
    @POST("get_dishes_by_dish_type")
    Call<DishByCategoryResponse> getDish(
            @Field("dish_type_id") String dish_type_id

    );

    @FormUrlEncoded
    @POST("getDishByType")
    Call<DishByCategoryResponse> getDishByType(
            @Field("dish_type") String dish_type_id

    );

    @FormUrlEncoded
    @POST("delete_menu")
    Call<DishByCategoryResponse> deleteMenu(
            @Field("menu_id") String menu_id

    );

    @FormUrlEncoded
    @POST("get_menu_by_date")
    Call<GetMenuResponse> get_Menu(
            @Field("date") String date
    );

    @GET("get_prefix_menus")
    Call<PrefixDishResponse> get_PrefixMenu(
    );

    @FormUrlEncoded
    @POST("points_remember")
    Call<RememberInitRes> getPointsRemember(
            @Field("dish_id") String date
    );
}


/**
 * @POST("login_as_chef")
 * @POST("login_as_buyer")
 * @GET("get_dishes")
 * @GET("get_dish_types")
 * @GET("get_categories")
 * @POST("add_menu")
 * @POST("add_menu_utensils")
 * @GET("get_utensils")
 * @POST("get_menu_data_for_pdf")
 * @POST("get_menu_by_date")
 * @POST("get_dishes_by_dish_type")
 * @POST("delete_menu")
 * @GET("get_prefix_menus")
 */