package com.dicoding.githubapplication.view.details.follows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubapplication.api.ApiConfig
import com.dicoding.githubapplication.model.response.ResponseFollow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel() {

    private val followers = MutableLiveData<ArrayList<ResponseFollow>>()
    val getFollowers: LiveData<ArrayList<ResponseFollow>> = followers

    private val following = MutableLiveData<ArrayList<ResponseFollow>>()
    val getFollowing: LiveData<ArrayList<ResponseFollow>> = following

    private val isLoading = MutableLiveData<Boolean>()
    val getIsLoading: LiveData<Boolean> = isLoading

    fun followers(username: String) {
        try {
            isLoading.value = true
            val client = ApiConfig.getApiService().followers(username)
            client.enqueue(object : Callback<ArrayList<ResponseFollow>> {
                override fun onResponse(
                    call: Call<ArrayList<ResponseFollow>>,
                    response: Response<ArrayList<ResponseFollow>>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        followers.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ArrayList<ResponseFollow>>, t: Throwable) {
                    isLoading.value = false
                }
            })
        } catch (e: Exception) {
        }
    }

    fun following(username: String) {
        try {
            isLoading.value = true
            val client = ApiConfig.getApiService().following(username)
            client.enqueue(object : Callback<ArrayList<ResponseFollow>> {
                override fun onResponse(
                    call: Call<ArrayList<ResponseFollow>>,
                    response: Response<ArrayList<ResponseFollow>>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        following.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ArrayList<ResponseFollow>>, t: Throwable) {
                    isLoading.value = false
                }
            })
        } catch (e: Exception) {
        }
    }

    companion object {
        private const val TAG = "FollowViewModel"
    }
}
