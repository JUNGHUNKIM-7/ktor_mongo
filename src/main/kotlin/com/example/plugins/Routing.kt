package com.example.plugins

import com.example.repository.PokeService
import com.example.routes.pokeRouter
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*

fun Application.pokeRouting(pokeService: PokeService) {
    routing {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml") {
            version = "4.15.5"
        }
        pokeRouter(pokeService = pokeService)
    }
}
