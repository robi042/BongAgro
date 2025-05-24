package com.project.bongoagro.Retrofit;

import com.project.bongoagro.Model.AreaModel;
import com.project.bongoagro.Model.CategoryModel;
import com.project.bongoagro.Model.ChannelDataResponse;
import com.project.bongoagro.Model.ColdStorageResponseModelClass;
import com.project.bongoagro.Model.CultivationGuide;
import com.project.bongoagro.Model.CustomerModel;
import com.project.bongoagro.Model.LoginModel;
import com.project.bongoagro.Model.LoginRequest;
import com.project.bongoagro.Model.PriceItem;
import com.project.bongoagro.Model.PriceResponse;
import com.project.bongoagro.Model.PriceUpdateRequest;
import com.project.bongoagro.Model.ProductModel;
import com.project.bongoagro.Model.ProductTypeModel;
import com.project.bongoagro.Model.RegistrationModel;
import com.project.bongoagro.Model.ResetPassResponse;
import com.project.bongoagro.Model.ResetPasswordRequest;
import com.project.bongoagro.Model.RoleModel;
import com.project.bongoagro.Model.SellerModel;
import com.project.bongoagro.Model.SellerTypeModel;
import com.project.bongoagro.Model.SellerUpdateRequest;
import com.project.bongoagro.Model.SubCategoryModel;
import com.project.bongoagro.Model.TranspotationModel;
import com.project.bongoagro.Model.WeatherResponse;
import com.project.bongoagro.Model.productDetailsModel;
import com.project.bongoagro.Model.signupJavaRequest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @POST("auth/signup")
    Call<RegistrationModel> registration(@Body signupJavaRequest request);
    @POST("auth/login")
    Call<LoginModel> login(@Body LoginRequest request);

    @GET("auth/getRoles")
    Call<List<RoleModel>> role();

    @GET("category/getCategories")
    Call<List<CategoryModel>> category(@Header("Authorization") String token);

    @GET("category/getSubCategoriesByCategoryId")
    Call<List<SubCategoryModel>> sub_category(@Header("Authorization") String token, @Query("categoryId") int categoryId);

    @GET("product/getProductBySubCategoryId")
    Call<List<ProductModel>> product(@Header("Authorization") String token, @Query("subCategoryId") int subCategoryId);

    @GET("product/getCultivationGuideByProductId")
    Call<List<productDetailsModel>> product_details(@Header("Authorization") String token, @Query("productId") int productId);

    @POST("auth/resetPassword")
    Call<ResetPassResponse> resetPass(@Body ResetPasswordRequest request);

    @GET("coldStorage/getColdStorageDetail")
    Call<List<ColdStorageResponseModelClass>> coldStorage(@Header("Authorization") String token);

    @GET("product/getProductComponentDetailByProductId")
    Call<List<CultivationGuide>> getProductComponentDetailByProductId(@Header("Authorization") String token, @Query("productId") String productId, @Query("code") String code);

    @GET("forecast.json")
    Call<WeatherResponse> getWeather(
            @Query("key") String apiKey,
            @Query("q") String latlon,
            @Query("days") Integer days
    );

    @GET("seller/getSellerByUserId")
    Call<SellerModel> getSellerInfo(@Header("Authorization") String token);

    @GET("seller/getAreas")
    Call<List<AreaModel>> getSellerArea(@Header("Authorization") String token);

    @GET("seller/getSellerTypes")
    Call<List<SellerTypeModel>> getSellerType(@Header("Authorization") String token);

    @GET("seller/getProductType")
    Call<List<ProductTypeModel>> getProductType(@Header("Authorization") String token);

    @POST("seller/createOrUpdateSeller")
    Call<ResponseBody> sellerUpdate(@Body SellerUpdateRequest request, @Header("Authorization") String token);

    @GET("cart/getCustomerByUserId")
    Call<CustomerModel> getCustomerInfo(@Header("Authorization") String token);

    @POST("cart/createOrUpdateCustomer")
    Call<ResponseBody> customerUpdate(@Body CustomerModel request, @Header("Authorization") String token);

    @GET("transport/getTransportByUserId")
    Call<TranspotationModel> getTranportInfo(@Header("Authorization") String token);

    @GET("transport/getVehicleTypes")
    Call<List<SellerTypeModel>> getVehicleType(@Header("Authorization") String token);

    @POST("transport/createOrUpdateTransport")
    Call<ResponseBody> tranportUpdate(@Body TranspotationModel request, @Header("Authorization") String token);

    @GET("product/getProducts")
    Call<List<ProductModel>> getProducts(@Header("Authorization") String token);

    @GET("cart/getPriceByProductId")
    Call<PriceResponse> getPriceList(@Header("Authorization") String token, @Query("productId") int productId);

    @POST("cart/createOrUpdatePrice")
    Call<ResponseBody> priceUpdate(@Body PriceUpdateRequest request, @Header("Authorization") String token);

    @GET("ThinkSpeakAPI")
    Call<ChannelDataResponse> getChartData(@Header("Authorization") String token, @Query("channelName") String channelName);
}
