package com.david0926.sunrinthon2021.network

import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.auth.CommonResponse
import com.david0926.sunrinthon2021.data.auth.LoginRequest
import com.david0926.sunrinthon2021.data.auth.RegisterRequest
import com.david0926.sunrinthon2021.data.auth.UserInfoRequest
import com.david0926.sunrinthon2021.network.auth.AuthManager
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {

    private fun toPart(obj: Any?): RequestBody? {
        if (obj == null) return null
        return RequestBody.create(MediaType.parse("multipart/form-data"), obj.toString())
    }

    override fun register(
        registerRequest: RegisterRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.authService.register(registerRequest).enqueue(object: Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                if(response.body() == null) {
                    onResponse(
                        Gson().fromJson(
                            response.errorBody()!!.string(),
                            CommonResponse::class.java
                        )
                    )
                } else {
                    onResponse(response.body()!!)
                }
            }
        })
    }

    override fun login(
        loginRequest: LoginRequest,
        onResponse: (CommonResponse, UserModel?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.authService.login(loginRequest).enqueue(object: Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                if(response.body() == null) {
                    onResponse(
                        Gson().fromJson(response.errorBody()!!.string(),
                        CommonResponse::class.java),
                        null
                    )
                } else {
                    println(response.body()!!)
                    UserModel.nowUser.token = response.body()!!.data.toString()
                    getuser(response.body()!!.data.toString(), { response, usermodel ->
                        UserModel.nowUser = usermodel!!
                        onResponse(response, usermodel)
                    }, {
                        onFailure(it)
                    })
                }
            }
        })
    }

    override fun getuser(
        Authorization: String,
        onResponse: (CommonResponse, UserModel?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.authService.getUser("Bearer $Authorization").enqueue(object: Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                if(response.body() == null) {
                    onResponse(
                        Gson().fromJson(response.errorBody()!!.string(),
                            CommonResponse::class.java),
                        null
                    )
                } else {
                    println(response.body()!!)
                    val user: UserModel = Gson().fromJson(
                        Gson().toJson(response.body()!!.data),
                        UserModel::class.java
                    )
                    onResponse(response.body()!!, user)
                }
            }
        })
    }

    override fun updateUser(
        userInfoRequest: UserInfoRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.authService.updateUser(UserModel.nowUser.token, toPart(userInfoRequest.isExpert), toPart(userInfoRequest.information), toPart(userInfoRequest.career)).enqueue(object: Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                if(response == null) {
                    onResponse(
                        Gson().fromJson(response.errorBody()!!.string(),
                            CommonResponse::class.java)
                    )
                } else {
                    onResponse(response.body()!!)
                }
            }
        })
    }

    override fun getProfilePhoto(
        _id: String,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.authService.getProfilePhoto(_id).enqueue(object: Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                if(response == null) {
                    onResponse(
                        Gson().fromJson(response.errorBody()!!.string(),
                            CommonResponse::class.java)
                    )
                } else {
                    onResponse(response.body()!!)
                }
            }
        })
    }


}