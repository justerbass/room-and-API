package cl.bootcamp.wallet.datasource

import android.hardware.usb.UsbEndpoint
import cl.bootcamp.wallet.model.UserWallet
import cl.bootcamp.wallet.util.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestDataSource {

    @GET(ENDPOINT)
    suspend fun getUserWallet(): List<UserWallet>

    @GET("$ENDPOINT/{id}")
    suspend fun getUserWalletById(@Path("id") id: Int): Response<UserWallet>

}