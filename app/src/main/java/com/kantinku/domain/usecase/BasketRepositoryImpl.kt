package com.kantinku.domain.usecase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kantinku.data.BasketData
import com.kantinku.data.MenuData
import com.kantinku.domain.repo.BasketRepository
import kotlin.random.Random

class BasketRepositoryImpl : BasketRepository {
    private lateinit var db: DatabaseReference
    
    override fun createOrder(
        userId: String,
        data: ArrayList<MenuData>,
    ) {
        var quantity = 0
        var temp = 0
        data.forEach {
            quantity += it.quantity
        }
        db = FirebaseDatabase.getInstance().reference
        db.child("queue").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val random = Random.nextInt(1, 3)
                    val queue = snapshot.child(random.toString()).getValue(Int::class.java) as Int
                    temp += queue
                    db.child("queue").child(random.toString()).setValue(queue + quantity)
                }
            }
            
            override fun onCancelled(error: DatabaseError) {
                Log.d("BasketRepo", "Error: ${error.message}")
            }
        })
        val post = db.child("transaction").child(userId).push()
        post.child("order").setValue(data)
        post.child("notes").setValue("tidak ada")
        post.child("waitingTime").setValue("Tunggu selama ${Random.nextInt(20, 50)} min")
        post.child("proceed").setValue("Antrian yang diproses: ${temp + quantity}")
        post.child("queue").setValue(quantity)
        post.child("status").setValue("Pesanan sudah dikonfirmasi penjual")
    }
    
    override fun getData(
        userId: String,
        menumenu: (BasketData) -> Unit,
    ) {
        db = FirebaseDatabase.getInstance().reference
        val orders = db.child("transaction")
        orders.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val datalist: ArrayList<MenuData> = ArrayList()
                        for (ds in dataSnapshot.child("order").children) {
                            val name: String =
                                ds.child("name").getValue(String::class.java).toString()
                            val image: String =
                                ds.child("image").getValue(String::class.java).toString()
                            val desc: String =
                                ds.child("desc").getValue(String::class.java)
                                    .toString()
                            val price: Int =
                                ds.child("price").getValue(Int::class.java) as Int
                            val stock: Int =
                                ds.child("stock").getValue(Int::class.java) as Int
                            val category: Int =
                                ds.child("category").getValue(Int::class.java) as Int
                            val quantity: Int =
                                ds.child("quantity").getValue(Int::class.java) as Int
                            val data = MenuData(image, name, desc, price, stock, quantity, category)
                            datalist.add(data)
                        }
                        val notes: String =
                            dataSnapshot.child("notes").getValue(String::class.java).toString()
                        val waitingTime: String =
                            dataSnapshot.child("waitingTime").getValue(String::class.java)
                                .toString()
                        val proceed: String =
                            dataSnapshot.child("proceed").getValue(String::class.java).toString()
                        val queue: Int =
                            dataSnapshot.child("queue").getValue(Int::class.java) as Int
                        val status: String =
                            dataSnapshot.child("status").getValue(String::class.java).toString()
                        
                        menumenu(BasketData(datalist, notes, waitingTime, proceed, queue, status))
                    }
                }
            }
            
            override fun onCancelled(error: DatabaseError) {
                Log.d("BasketRepo", "Error: ${error.message}")
            }
        })
    }
    
    override fun updateStatus(basketData: BasketData) {
        db = FirebaseDatabase.getInstance().reference
        db.child("transaction").child("status").setValue("Pesanan sudah diterima")
    }
}