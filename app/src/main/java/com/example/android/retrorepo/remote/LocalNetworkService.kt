package com.example.android.retrorepo.remote
import com.example.android.retrorepo.remote.data.Repository
import retrofit2.Call

class LocalNetworkService private constructor() : NetworkService {

    companion object {

        val sInstance = LocalNetworkService()
        /*   @Volatile
           private var sInstance: LocalNetworkService? = null

           val instance: LocalNetworkService?
               get() {
                   if (sInstance == null) {
                       synchronized(LocalNetworkService::class.java) {
                           if (sInstance == null) {
                               sInstance = LocalNetworkService()
                           }
                       }
                   }
                   return sInstance
               }*/
    }


    override fun getRepositories(keyword: String, page: Int): Call<Repository> {
        val service = ServiceGenerator.createService(NetworkService::class.java)
        return service.getRepositories(keyword, page)
    }
}
