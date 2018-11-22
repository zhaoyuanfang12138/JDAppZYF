package com.example.lenovo.jdappzyf.utils;

import com.example.lenovo.jdappzyf.cart.bean.Cart;
import com.example.lenovo.jdappzyf.first.bean.AddCart;
import com.example.lenovo.jdappzyf.first.bean.CarouselBean;
import com.example.lenovo.jdappzyf.first.bean.Product;
import com.example.lenovo.jdappzyf.first.bean.SearchPage;
import com.example.lenovo.jdappzyf.first.bean.Sudoku;
import com.example.lenovo.jdappzyf.login.bean.AddAddressBean;
import com.example.lenovo.jdappzyf.login.bean.AddreseeBean;
import com.example.lenovo.jdappzyf.login.bean.LoginBean;
import com.example.lenovo.jdappzyf.regist.bean.RegistBean;
import com.example.lenovo.jdappzyf.sort.bean.CataLeft;
import com.example.lenovo.jdappzyf.sort.bean.CataRight;
import com.example.lenovo.jdappzyf.sort.bean.DetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2018/11/7.
 */

public interface Api {

    //登录
    @GET("user/login")
    Observable<LoginBean> login1(@Query("mobile") String mobile, @Query("password") String password);

    //注册
    @GET("user/reg")
    Observable<RegistBean> regist1(@Query("mobile") String mobile, @Query("password") String password);

    //轮播图
    @GET("ad/getAd")
     Observable<CarouselBean> carousel1();

    //九宫格
    @GET("product/getCatagory")
    Observable<Sudoku> sudoku1();

      //商品瀑布流
    @GET("product/getCarts")
    Observable<Product> product1(@Query("uid") int uid, @Query("source") String source);

    //搜索
    @GET("product/searchProducts")
    Observable<SearchPage> getSearchPage1(@Query("keywords") String keywords, @Query("page") int page, @Query("source") String source);

     //添加购物车
    @GET("product/addCart")
    Observable<AddCart> getAddCart(@Query("uid") int uid, @Query("pid") int pid);



    //分类左侧
    @GET("product/getCatagory")
    Observable<CataLeft> cataleft1();
    //分类右侧
    @GET("product/getProductCatagory")
    Observable<CataRight> cataright1(@Query("cid") int cid);
      //分类详情
    @GET("product/getProducts")
    Observable<DetailBean> detail1(@Query("pscid") int pscid);


    //购物车
    @GET("product/getCarts")
    Observable<Cart> cart1(@Query("uid") int uid);


    //展示地址
    @GET("user/getAddrs")
    Observable<AddreseeBean> getAddr(@Query("uid") int uid);

    @GET("user/addAddr")
    Observable<AddAddressBean> addAddress(@Query("uid") int uid, @Query("addr") String addr, @Query("mobile") String mobile, @Query("name") String name);

}
