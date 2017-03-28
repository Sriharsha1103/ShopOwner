package com.vvit.ammu.shopowner;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ammu on 28-03-2017.
 */

public class ShopOwner {

    private String shop_owner_id;
    private String shop_owner_name;
    private int shop_owner_mobile;
    private String shop_owner_login_name;
    private String shop_owner_password;

    public ShopOwner(){

    }
    public ShopOwner(String login,String password,String id){

        shop_owner_password = password;
        shop_owner_login_name = login;
        shop_owner_id = id;

    }
    public void setShop_ownwer_id(String id){
        shop_owner_id = id;
    }

    public int getShop_owner_mobile() {
        return shop_owner_mobile;
    }

    public String getShop_owner_id() {
        return shop_owner_id;
    }

    public String getShop_onwer_password() {
        return shop_owner_password;
    }

    public String getShop_owner_login_name() {
        return shop_owner_login_name;
    }

    public String getShop_owner_name() {
        return shop_owner_name;
    }

    public void setShop_onwer_password(String shop_onwer_password) {
        this.shop_owner_password = shop_onwer_password;
    }

    public void setShop_owner_name(String shop_owner_name) {
        this.shop_owner_name = shop_owner_name;
    }

    public void setShop_owner_login_name(String shop_owner_login_name) {
        this.shop_owner_login_name = shop_owner_login_name;
    }

    public void setShop_owner_mobile(int shop_owner_mobile) {
        this.shop_owner_mobile = shop_owner_mobile;
    }
}
