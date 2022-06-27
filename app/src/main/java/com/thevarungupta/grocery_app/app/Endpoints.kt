package com.thevarungupta.grocery_app.app

import androidx.core.content.contentValuesOf

class Endpoints {

    companion object {
        private val URL_CATEGORY = "category"
        private val URL_SUB_CATEGORY_BY_CAT_ID = "subcategory/"
        private val URL_PRODUCT_BY_SUB_ID = "products/sub/"
        private val URL_REGISTER = "auth/register"
        private val URL_LOGIN = "auth/login"
        private val URL_ADDRESS = "address"

        fun getCategoryUrl(): String {
            return Config.BASE_URL + URL_CATEGORY
        }

        fun getSubCategoryByCatId(catId: Int): String {
            return Config.BASE_URL + URL_SUB_CATEGORY_BY_CAT_ID + catId
        }

        fun getProductBySubId(subId: Int): String {
            return Config.BASE_URL + URL_PRODUCT_BY_SUB_ID + subId
        }

        fun getRegisterUrl(): String {
            return Config.BASE_URL + URL_REGISTER
        }

        fun getLoginUrl(): String {
            return Config.BASE_URL + URL_LOGIN
        }

        fun saveAddress(): String {
            return Config.BASE_URL + URL_ADDRESS
        }

        fun getAddressByUserId(userId: String): String {
            return Config.BASE_URL + URL_ADDRESS + "/" + userId
        }

        fun editAddress(addressId: String): String {
            return Config.BASE_URL + URL_ADDRESS + "/" + addressId
        }

        fun deleteAddress(addressId: String): String {
            return Config.BASE_URL + URL_ADDRESS + "/" + addressId
        }
    }
}