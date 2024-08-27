package com.kantinku.domain.usecase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kantinku.data.FoodData
import com.kantinku.data.ShopData
import com.kantinku.domain.repo.ExploreRepository

class ExploreRepositoryImpl : ExploreRepository {
    private lateinit var db: DatabaseReference
    
    override fun getShopData(
        shops: (ArrayList<ShopData>) -> Unit,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    ) {
        db = FirebaseDatabase.getInstance().reference
        val shopRef = db.child("shop")
        shopRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val shopsData = ArrayList<ShopData>()
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val name: String =
                            dataSnapshot.child("name").getValue(String::class.java).toString()
                        val address: String =
                            dataSnapshot.child("address").getValue(String::class.java).toString()
                        val rating: Float? =
                            dataSnapshot.child("rating").getValue(Float::class.java)
                        val imageUrl: String =
                            dataSnapshot.child("imageUrl").getValue(String::class.java).toString()
                        shopsData.add(ShopData(name, address,  rating,  imageUrl))
                    }
                    shops(shopsData)
                }
            }
            
            override fun onCancelled(error: DatabaseError) {
                Log.d("ExploreRepo" ,"Error: ${error.message}")
            }
        })
    }
    
    override fun getShopMenus(menus: List<FoodData>, onSuccess: () -> Unit, onFailure: () -> Unit) {

    }
}