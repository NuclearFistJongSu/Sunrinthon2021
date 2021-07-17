package com.david0926.sunrinthon2021.network

import com.david0926.sunrinthon2021.network.auth.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  StockerRetrofit {

    private const val baseUrl = "https://sunrinthon.herokuapp.com/";

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    val authService: AuthService = retrofit.create(AuthService::class.java)
//    val postService: PostService = retrofit.create(PostService::class.java)
//    val commentService: CommentService = retrofit.create(CommentService::class.java)
//    val personalityService: PersonalityService = retrofit.create(PersonalityService::class.java)
//    val topicService: TopicService = retrofit.create(TopicService::class.java)
//    val mailService: MailService = retrofit.create(MailService::class.java)


}