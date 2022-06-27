package com.thevarungupta.grocery_app.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.thevarungupta.grocery_app.app.Config.Companion.DATABASE_NAME
import com.thevarungupta.grocery_app.app.Config.Companion.DATABASE_VERSION
import com.thevarungupta.grocery_app.helpers.OrderSummary
import com.thevarungupta.grocery_app.models.Product
import java.sql.SQLData

class DbHelper(val mContext: Context) :
    SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION) {

    var db: SQLiteDatabase = writableDatabase

    companion object {

        private const val TABLE_NAME = "cart"
        private const val COLUMN_ID = "id"
        private const val COLUMN_PID = "pid"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_IMAGE = "image"
        private const val COLUMN_MRP = "mrp"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_QTY = "qty"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "create table if not exists $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_PID CHAR(100), " +
                "$COLUMN_NAME CHAR(100), " +
                "$COLUMN_IMAGE CHAR(100)," +
                "$COLUMN_MRP INTEGER, " +
                "$COLUMN_PRICE INTEGER, " +
                "$COLUMN_QTY INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }

    // check item in cart
    private fun isItemInCart(product: Product): Boolean {
        val query = "Select * from $TABLE_NAME where $COLUMN_PID = ?"
        var cursor = db.rawQuery(query, arrayOf(product._id))
        var count = cursor.count
        return count != 0
    }

    // add item into cart
    fun addItemInCart(product: Product) {
        if (!isItemInCart(product)) {
            // we don't have so let add to cart
            val contentValue = ContentValues()
            contentValue.put(COLUMN_PID, product._id)
            contentValue.put(COLUMN_NAME, product.productName)
            contentValue.put(COLUMN_IMAGE, product.image)
            contentValue.put(COLUMN_MRP, product.mrp)
            contentValue.put(COLUMN_PRICE, product.price)
            contentValue.put(COLUMN_QTY, product.quality)
            db.insert(TABLE_NAME, null, contentValue)
        } else {
            // we already have item so lets increase quantity
        }
    }

    // update quantity of existing item
    fun updateItemQty(product: Product) {
        var contentValue = ContentValues()
        contentValue.put(COLUMN_QTY, product.quality)
        val whereClause = "$COLUMN_PID = ?"
        val whereArgs = arrayOf(product._id)
        db.update(TABLE_NAME, contentValue, whereClause, whereArgs)
    }

    // delete item from the cart
    fun deleteItemFromCart(product: Product) {
        val whereClause = "$COLUMN_PID = ?"
        val whereArgs = arrayOf(product._id)
        db.delete(TABLE_NAME, whereClause, whereArgs)
    }

    @SuppressLint("Range")
    fun getItemQuantityFromCart(product: Product): Int {
        var quantity = 0
        val query = "SELECT * from $TABLE_NAME where $COLUMN_PID = ?"
        var cursor = db.rawQuery(query, arrayOf(product._id))
        if (cursor.moveToFirst()) {
            quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QTY))
            cursor.close()
            return quantity
        }
        return quantity
    }

    // get cart total number of quantity
//    fun getCartTotalQuantity(): Int {
//
//    }

    @SuppressLint("Range")
    fun getOrderSummary(): OrderSummary {
        var totalMrp = 0
        var totalPrice = 0
        var discount = 0
        var deliveryChanges = 0
        var orderAmount = 0

        var columns = arrayOf(
            COLUMN_QTY,
            COLUMN_MRP,
            COLUMN_PRICE
        )

        var cursor = db.query(TABLE_NAME, columns, null, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                var t = cursor.getInt(cursor.getColumnIndex(COLUMN_MRP)).toInt()
                var p = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE)).toInt()
                var q = cursor.getInt(cursor.getColumnIndex(COLUMN_QTY)).toInt()

                totalMrp += t * q
                totalPrice += p * q

            } while (cursor.moveToNext())
        }
        discount = totalMrp - totalPrice
        if(totalPrice < 300){
            deliveryChanges = 30
        }
        orderAmount = totalPrice + deliveryChanges

        return OrderSummary(
            totalMrp = totalMrp,
            totalPrice = totalPrice,
            discount = discount,
            deliveryCharges = deliveryChanges,
            orderAmount = orderAmount
        )
    }


}