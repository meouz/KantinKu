package com.kantinku.domain.usecase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kantinku.data.MenuData

class ShopRepositoryImpl {
    private lateinit var db: DatabaseReference
    
    fun getMenuUtama(
        shopName: String,
        resp: (ArrayList<MenuData>) -> Unit,
    ) {
        db = FirebaseDatabase.getInstance().reference
        val parent = db.child("shops").child(shopName).child("menus").child("utama")
        parent.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = ArrayList<MenuData>()
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        Log.d("HSUDFHDSUFDSF", dataSnapshot.toString())
                        val image: String =
                            dataSnapshot.child("image").getValue(String::class.java).toString()
                        val name: String =
                            dataSnapshot.child("name").getValue(String::class.java).toString()
                        val desc: String =
                            dataSnapshot.child("description").getValue(String::class.java)
                                .toString()
                        val price: Int =
                            dataSnapshot.child("price").getValue(Int::class.java) as Int
                        val stock: Int =
                            dataSnapshot.child("stock").getValue(Int::class.java) as Int
                        val category: Int =
                            dataSnapshot.child("category").getValue(Int::class.java) as Int
                        val data =
                            MenuData(
                                image = image,
                                name = name,
                                desc = desc,
                                price = price,
                                stock = stock,
                                quantity = 0,
                                category = category
                            )
                        dataList.add(data)
                    }
                }
                resp(dataList)
            }
            
            override fun onCancelled(error: DatabaseError) {
                Log.d("ShopRepositoryImpl", "Error: ${error.message}")
            }
        })
    }
    
    fun getMenuTakHabis(
        shopName: String,
        resp: (ArrayList<MenuData>) -> Unit,
    ) {
        db = FirebaseDatabase.getInstance().reference
        val parent = db.child("shops").child(shopName).child("menus").child("tak-habis")
        parent.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = ArrayList<MenuData>()
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val image: String =
                            dataSnapshot.child("image").getValue(String::class.java).toString()
                        val name: String =
                            dataSnapshot.child("name").getValue(String::class.java).toString()
                        val desc: String =
                            dataSnapshot.child("description").getValue(String::class.java)
                                .toString()
                        val price: Int =
                            dataSnapshot.child("price").getValue(Int::class.java) as Int
                        val stock: Int =
                            dataSnapshot.child("stock").getValue(Int::class.java) as Int
                        val category: Int =
                            dataSnapshot.child("category").getValue(Int::class.java) as Int
                        val data =
                            MenuData(
                                image = image,
                                name = name,
                                desc = desc,
                                price = price,
                                stock = stock,
                                quantity = 0,
                                category = category
                            )
                        dataList.add(data)
                    }
                }
                resp(dataList)
            }
            
            override fun onCancelled(error: DatabaseError) {
                Log.d("ShopRepositoryImpl", "Error: ${error.message}")
            }
        })
    }
    
    fun getMenuBest(
        shopName: String,
        resp: (ArrayList<MenuData>) -> Unit,
    ) {
        db = FirebaseDatabase.getInstance().reference
        val parent = db.child("shops").child(shopName).child("menus").child("best")
        parent.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = ArrayList<MenuData>()
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val image: String =
                            dataSnapshot.child("image").getValue(String::class.java).toString()
                        val name: String =
                            dataSnapshot.child("name").getValue(String::class.java).toString()
                        val desc: String =
                            dataSnapshot.child("description").getValue(String::class.java)
                                .toString()
                        val price: Int =
                            dataSnapshot.child("price").getValue(Int::class.java) as Int
                        val stock: Int =
                            dataSnapshot.child("stock").getValue(Int::class.java) as Int
                        val category: Int =
                            dataSnapshot.child("category").getValue(Int::class.java) as Int
                        val data =
                            MenuData(
                                image = image,
                                name = name,
                                desc = desc,
                                price = price,
                                stock = stock,
                                quantity = 0,
                                category = category
                            )
                        dataList.add(data)
                    }
                }
                resp(dataList)
            }
            
            override fun onCancelled(error: DatabaseError) {
                Log.d("ShopRepositoryImpl", "Error: ${error.message}")
            }
        })
    }
}