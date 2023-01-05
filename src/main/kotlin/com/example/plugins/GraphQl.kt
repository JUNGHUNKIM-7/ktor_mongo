package com.example.plugins

import com.apurebase.kgraphql.GraphQL
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.example.repository.PokeService
import io.ktor.server.application.*

fun Application.graphqlModule(pokeService: PokeService, testing: Boolean = false) {
    install(GraphQL) {
        useDefaultPrettyPrinter = true
        playground = true
        endpoint = "/"
        schema { pokeSchema() }
    }
}


fun SchemaBuilder.pokeSchema() {
    query("hello") {
        resolver { -> "world" }
    }

    query("world") {
        resolver { -> "another world" }
    }
}
