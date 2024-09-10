package com.kantinku.domain.usecase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.kantinku.data.ShopData
import com.kantinku.domain.repo.ExploreRepository

class ExploreRepositoryImpl : ExploreRepository {
    private lateinit var db: FirebaseFirestore
    
    override fun getShop(
        shops: (ArrayList<ShopData>) -> Unit,
    ) {
        db = FirebaseFirestore.getInstance()
        val ref = db.collection("shops")
        ref.get().addOnSuccessListener { snapshot ->
            val shop = ArrayList<ShopData>()
            if (!snapshot.isEmpty) {
                for (document in snapshot.documents) {
                    val image = document.getString("image").orEmpty()
                    val name = document.getString("name").orEmpty()
                    val rating = document.getDouble("rating")?.toFloat() ?: 0f
                    val ratingCount = document.getLong("ratingCount")?.toInt() ?: 0
                    val location = document.getString("location").orEmpty()
                    val distance = document.getLong("distance")?.toInt() ?: 0
                    val type = document.getString("type").orEmpty()
                    val cheapest = document.getBoolean("cheapest") ?: false
                    val open = document.getBoolean("open") ?: false
                    val waitingTime = document.getLong("waitingTime")?.toInt() ?: 0
                    val discount = document.getLong("discount")?.toInt() ?: 0
                    val queue = document.getLong("queue")?.toInt() ?: 0
                    val onProcess = document.getLong("onProcess")?.toInt() ?: 0
                    shop.add(
                        ShopData(
                            image,
                            name,
                            rating,
                            ratingCount,
                            location,
                            distance,
                            type,
                            cheapest,
                            open,
                            waitingTime,
                            discount,
                            queue,
                            onProcess
                        )
                    )
                }
                shops(shop)
            }
        }.addOnFailureListener { error ->
            Log.d("ExploreRepo", "Error: ${error.message}")
        }
    }
}