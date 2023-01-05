package com.example

import com.example.plugins.*
import com.example.repository.PokeService
import com.example.utils.MongoClient
import de.sharpmind.ktor.EnvConfig
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module(testing: Boolean = false) {
    EnvConfig.initConfig(environment.config)
    val col = MongoClient.getInstance()
    val pokeService = PokeService(col)
    graphqlModule(pokeService = pokeService)
    pokeRouting(pokeService = pokeService)
    configureCors()
    configureSerialization()
}
