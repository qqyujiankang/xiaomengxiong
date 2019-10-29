//package com.example.et.Ustlis.http.api;
//
//
//import com.example.et.entnty.YanZhengMaBean;
//
//import io.reactivex.Observable;
//import retrofit2.http.GET;
//import retrofit2.http.POST;
//import retrofit2.http.Query;
//
///**
// * @author Dogal
// * @date
// */
//
//public interface ApiService {
//    /**
//     * 高德地图地理编码
//     */
////    @GET("geo?")
////    Observable<GaoDeAddressBean> getGaodeAddressData(@Query("key") String key,
////                                                     @Query("address") String address);
//
//    /**
//     * 发送短信
//     */
//    @POST("registermessage")
//    Observable<YanZhengMaBean> getregister
//    (@Query("phone") String phone, @Query("quhao") String quhao, @Query("types") String types);
//
//    /**
//     * @param phone
//     * @param pass
//     * @param phonecode
//     * @param equipment
//     * @return
//     */
//    @POST("login")
//    Observable<YanZhengMaBean> login(@Query("phone") String phone, @Query("pass") String pass, @Query("phonecode")
//            String phonecode, @Query("equipment") String equipment);
//}
