package com.david0926.sunrinthon2021.network

import android.util.Log
import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.auth.*
import com.david0926.sunrinthon2021.data.post.Post
import com.david0926.sunrinthon2021.data.post.PostRequest
import com.david0926.sunrinthon2021.network.auth.AuthManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType
import okhttp3.MultipartBody
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
//                    onResponse(
//                        Gson().fromJson(response.errorBody()!!.string(),
//                        CommonResponse::class.java),
//                        null
//                    )
                onFailure(Throwable(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java).message))
                } else {
                    println(response.body()!!)
                    UserModel.token = response.body()!!.data.toString()
                    if(UserModel.token.isEmpty() || UserModel.token == null) {
                        onFailure(Throwable(response.body()!!.message))
                        return;
                    }
                    getuser(response.body()!!.data.toString(), { response, usermodel ->
                        onResponse(response, usermodel!!.user)
                    }, {
                        onFailure(it)
                    })
                }
            }
        })
    }

    override fun getuser(
        Authorization: String,
        onResponse: (CommonResponse, User?) -> Unit,
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
//                    onResponse(
//                        Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java),
//                        null
//                    )

                    onFailure(Throwable(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java).message))
                } else {
                    println(response.body()!!)
                    val user: User = Gson().fromJson(
                        Gson().toJson(response.body()!!.data),
                        User::class.java
                    )
                    if(user == null) {
                        onFailure(Throwable(response.body()!!.message))
                        return;
                    }
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
        StockerRetrofit.authService.updateUser("Bearer $UserModel.token", toPart(userInfoRequest.isExpert), toPart(userInfoRequest.information), toPart(userInfoRequest.career)).enqueue(object: Callback<CommonResponse> {
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

    override fun updateProfilePhoto(
        image: MultipartBody.Part,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.authService.updateProfilePhoto("Bearer $UserModel.token", image).enqueue(object: Callback<CommonResponse> {
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


    override fun getPortfolioImage(
        _id: String,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.authService.getPortfolioImage(_id).enqueue(object: Callback<CommonResponse> {
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

    override fun updatePortfolioImage(
        image: MultipartBody.Part,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.authService.updatePortfolioImage("Bearer $UserModel.token", image).enqueue(object: Callback<CommonResponse> {
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

    override fun uploadPost(
        postRequest: PostRequest,
        onResponse: (CommonResponse, Post?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.postService.uploadPost("Bearer $UserModel.token", postRequest).enqueue(object: Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                if (response.body() == null) {
//                    onResponse(
//                        Gson().fromJson(
//                            response.errorBody()!!.string(),
//                            CommonResponse::class.java
//                        ), null
//                    )
                    onFailure(Throwable(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java).message))

                } else {
                    val post: Post = Gson().fromJson(
                        Gson().toJson(response.body()!!.data),
                        Post::class.java
                    )
                    if(post == null) {
                        onFailure(Throwable(response.body()!!.message))
                        return;
                    }
                    onResponse(response.body()!!, post)
                }
            }
        })
    }

    override fun getPosts(
        limit: Int?,
        page: Int?,
        onResponse: (CommonResponse, ArrayList<Post>?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.postService.getPosts(limit,page).enqueue(object: Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                try {
                    if (response.body() == null) {
//                        onResponse(
//                            Gson().fromJson(
//                                response.errorBody()!!.string(),
//                                CommonResponse::class.java
//                            ), null
//                        )
                        onFailure(Throwable(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java).message))
                    } else {
                        val type = object : TypeToken<ArrayList<Post>>() {}.type
                        val posts: ArrayList<Post> = Gson().fromJson(
                            Gson().toJson(response.body()!!.data), type
                        )

                        if(posts == null) {
                            onFailure(Throwable(response.body()!!.message))
                            return;
                        }

                        onResponse(response.body()!!, posts)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        })
    }

    override fun getPost(
        _id: String,
        onResponse: (CommonResponse, Post?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.postService.getPost(_id).enqueue(object: Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                if(response.body() == null) {
//                    onResponse(
//                        Gson().fromJson(response.errorBody()!!.string(),
//                            CommonResponse::class.java),
//                        null
//                    )
                    onFailure(Throwable(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java).message))
                } else {
                    println(response.body()!!)
                    val post: Post = Gson().fromJson(
                        Gson().toJson(response.body()!!.data),
                        Post::class.java
                    )

                    if(post == null) {
                        onFailure(Throwable(response.body()!!.message))
                        return;
                    }

                    onResponse(response.body()!!, post)
                }
            }
        })
    }

    override fun getPostPortfolioImage(
        _id: String,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.postService.getPost(_id).enqueue(object: Callback<CommonResponse> {
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

    override fun addComment(
        _id: String,
        contents: String,
        onResponse: (CommonResponse, Post?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.postService.addComment(_id, "Bearer $UserModel.token", contents).enqueue(object: Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                if (response.body() == null) {
//                    onResponse(
//                        Gson().fromJson(
//                            response.errorBody()!!.string(),
//                            CommonResponse::class.java
//                        ), null
//                    )
                    onFailure(Throwable(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java).message))

                } else {
                    val post: Post = Gson().fromJson(
                        Gson().toJson(response.body()!!.data),
                        Post::class.java
                    )
                    if(post == null) {
                        onFailure(Throwable(response.body()!!.message))
                        return;
                    }
                    onResponse(response.body()!!, post)
                }
            }
        })
    }

    override fun voteComment(
        _id: String,
        comment_id: String,
        onResponse: (CommonResponse, Post?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        StockerRetrofit.postService.voteComment(_id, comment_id, "Bearer ${UserModel.token}").enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                if (response.body() == null) {
//                       onResponse(
//                           Gson().fromJson(
//                               response.errorBody()!!.string(),
//                               CommonResponse::class.java
//                           ), null
//                       )
                    onFailure(Throwable(Gson().fromJson(response.errorBody()!!.string(), CommonResponse::class.java).message))
                   } else {
                        val post: Post = Gson().fromJson(
                            Gson().toJson(response.body()!!.data),
                            Post::class.java
                        )
                    if(post == null) {
                        onFailure(Throwable(response.body()!!.message))
                        return;
                    }
                    onResponse(response.body()!!, post)
                }
            }
        })
    }

}