package util

import android.content.Context
import android.net.NetworkInfo

class ConnectionManager {

    fun checkconnectivity(context:Context):Boolean{
        val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectionManager
        val activeNetwork:NetworkInfo=connectivityManager.activeNetworkInfo
        if(activeNetwork?.isConnected!=null){
            return activeNetwork.isConnected
        }
        else{
            return  false
        }

    }
}