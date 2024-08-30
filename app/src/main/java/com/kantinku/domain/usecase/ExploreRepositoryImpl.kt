package com.kantinku.domain.usecase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kantinku.data.MarketData
import com.kantinku.domain.repo.ExploreRepository

class ExploreRepositoryImpl : ExploreRepository {
    private lateinit var db: DatabaseReference
    
    override fun getMarkets(
        markets: (ArrayList<MarketData>) -> Unit,
    ) {
        db = FirebaseDatabase.getInstance().reference
        val shopRef = db.child("shops")
        shopRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val marketsData = ArrayList<MarketData>()
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val image: String =
                            dataSnapshot.child("image").getValue(String::class.java).toString()
                        val name: String =
                            dataSnapshot.child("name").getValue(String::class.java).toString()
                        val category: String =
                            dataSnapshot.child("category").getValue(String::class.java).toString()
                        val ratingCount: String =
                            dataSnapshot.child("ratingCount").getValue(String::class.java).toString()
                        val rating: Float? =
                            dataSnapshot.child("rating").getValue(Float::class.java)
                        val canBeTaken: String =
                            dataSnapshot.child("canBeTaken").getValue(String::class.java).toString()
                        val discount: String =
                            dataSnapshot.child("discount").getValue(String::class.java).toString()
                        val distance: String =
                            dataSnapshot.child("distance").getValue(String::class.java).toString()
                        val location: String =
                            dataSnapshot.child("location").getValue(String::class.java).toString()
                        marketsData.add(MarketData(image, name,  category,  ratingCount, rating,  canBeTaken, discount, distance, location))
                    }
                    markets(marketsData)
                }
            }
            
            override fun onCancelled(error: DatabaseError) {
                Log.d("ExploreRepo" ,"Error: ${error.message}")
            }
        })
    }
}