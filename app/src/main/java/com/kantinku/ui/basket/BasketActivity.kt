package com.kantinku.ui.basket

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.kantinku.data.MenuData
import com.kantinku.databinding.ActivityBasketBinding
import com.kantinku.domain.usecase.BasketRepositoryImpl
import com.kantinku.ui.basket.component.BasketAdapter
import com.kantinku.ui.shop.ShopViewModel.ShopViewModel.orders

class BasketActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityBasketBinding
    private val binding get() = _binding
    private lateinit var rvBasket: RecyclerView
    
    private val repository = BasketRepositoryImpl()
    private var auth = FirebaseAuth.getInstance()
    private var orderList: ArrayList<MenuData> = ArrayList()
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.btnBack.setOnClickListener {
            finish()
        }
        
        val shopName = intent.getStringExtra("shopName")
        
        orders.forEach {
            orderList += it
        }
        
        var totalPrice = 0
        orderList.forEach { element ->
            totalPrice += element.price * element.quantity
        }
        
        binding.shopName.text = shopName
        binding.tvPrice.text = totalPrice.toString()
        
        val fees = totalPrice / 20
        
        if (totalPrice != 0) {
            binding.tvFee.text = fees.toString()
        } else {
            "N/A".also { binding.tvFee.text = it } // or handle the error appropriately
        }
        val totalPayment = fees + totalPrice
        binding.tvTotalPay.text = totalPayment.toString()
        binding.tvTotalPayment.text = totalPayment.toString()
        
        rvBasket = binding.rvBasket
        rvBasket.adapter = BasketAdapter(orderList)
        rvBasket.layoutManager = LinearLayoutManager(this)

//        initMidtransSDK()

//        binding.btnKitabTs.setOnClickListener { goToPayment() }
        
        binding.btnPayment.setOnClickListener {
            val userId = auth.currentUser?.uid.toString()
            Log.d("userId", userId)
            repository.createOrder(userId, orderList)
            Intent(this, BasketConfirmActivity::class.java).also {
                startActivity(it)
            }
        }
    }
    
    fun increment(index: Int) {
        orderList[index].quantity++
    }
    
    fun decrement(index: Int) {
        orderList[index].quantity--
    }
    
    override fun onStart() {
        super.onStart()
        orders = ArrayList()
    }

//    fun initMidtransSDK {
//        SdkUIFlowBuilder.init()
//            .setClientKey(CLIENT_KEY) // client_key is mandatory
//            .setContext(CONTEXT) // context is mandatory
//            .setTransactionFinishedCallback {
//                // Handle finished transaction here.
//            } // set transaction finish callback (sdk callback)
//            .setMerchantBaseUrl(BASE_URL) //set merchant url (required)
//            .enableLog(true) // enable sdk log (optional)
//            .setColorTheme(
//                CustomColorTheme(
//                    "#FFE51255",
//                    "#B61548",
//                    "#FFE51255"
//                )
//            ) // set theme. it will replace theme on snap theme on MAP ( optional)
//            .setLanguage("en") //`en` for English and `id` for Bahasa
//            .buildSDK()
//    }
//
//    fun goToPayment() {
//        val qty = 2
//        val harga = 95000.0
//        val amount = qty * harga
//
//        val transactionRequest =
//            TransactionRequest("Hammad-StoreID-" + System.currentTimeMillis().toShort(), amount)
//        val detail = ItemDetails("BUKU_101", harga, qty, "Tadzkirotus Sami")
//
//        val itemDetails = ArrayList<ItemDetails>()
//        itemDetails.add(detail)
//
//        uiKitDetails(transactionRequest)
//        transactionRequest.itemDetails = itemDetails
//
//        MidtransSDK.getInstance().transactionRequest = transactionRequest
//        MidtransSDK.getInstance().startPaymentUiFlow(this)
//    }
//
//    private fun uiKitDetails(transactionRequest: TransactionRequest) {
//        val customerDetails = CustomerDetails()
//        customerDetails.customerIdentifier = "Asep Sutisna"
//        customerDetails.phone = "081775205889"
//        customerDetails.firstName = "Asep"
//        customerDetails.lastName = "Sutisna"
//        customerDetails.email = "yummi2102@gmail.com"
//
//        val shippingAddress = ShippingAddress()
//        shippingAddress.address = "cikeas, Nagrak"
//        shippingAddress.city = "Bogor"
//        shippingAddress.postalCode = "16967"
//        customerDetails.shippingAddress = shippingAddress
//
//        val billingAddress = BillingAddress()
//        billingAddress.address = "Cikeas, Nagrak"
//        billingAddress.city = "Bogor"
//        billingAddress.postalCode = "16967"
//        customerDetails.billingAddress = billingAddress
//
//        transactionRequest.customerDetails = customerDetails
//    }
//
//    override fun onTransactionFinished(result: TransactionResult) {
//        if (result.response != null) {
//            when (result.status) {
//                TransactionResult.STATUS_SUCCESS -> Toast.makeText(
//                    this,
//                    "Transaction Finished. ID: " + result.response.transactionId,
//                    Toast.LENGTH_LONG
//                ).show()
//                TransactionResult.STATUS_PENDING -> Toast.makeText(
//                    this,
//                    "Transaction Pending. ID: " + result.response.transactionId,
//                    Toast.LENGTH_LONG
//                ).show()
//                TransactionResult.STATUS_FAILED -> Toast.makeText(
//                    this,
//                    "Transaction Failed. ID: " + result.response.transactionId.toString() + ". Message: " + result.response.statusMessage,
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//        } else if (result.isTransactionCanceled) {
//            Toast.makeText(this, "Transaction Canceled", Toast.LENGTH_LONG).show()
//        } else {
//            if (result.status.equals(TransactionResult.STATUS_INVALID, true)) {
//                Toast.makeText(this, "Transaction Invalid", Toast.LENGTH_LONG).show()
//            } else {
//                Toast.makeText(this, "Transaction Finished with failure.", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
}