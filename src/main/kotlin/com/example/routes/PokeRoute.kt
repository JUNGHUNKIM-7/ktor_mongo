package com.example.routes

import com.example.model.Poke
import com.example.model.UpdatePoke
import com.example.repository.PokeService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.pokeRouter(pokeService: PokeService) {
    route("/pokes") {
        get {
            val pokes = pokeService.getPokes()
            if (pokes.isEmpty()) call.respondText("poke is empty", status = HttpStatusCode.NotFound)
            call.respond(pokes)
        }

        get("{id?}") {
            val id =
                call.parameters["id"] ?: call.respondText("id not found", status = HttpStatusCode.BadRequest)
            call.parameters["id"] ?: call.respondText("id not found", status = HttpStatusCode.BadRequest)
            val poke = pokeService.getPoke(id as String) ?: call.respondText(
                "poke:$id not found",
                status = HttpStatusCode.NotFound
            )
            call.respond(poke as Poke)
        }

        post {
            val body = call.receive<Poke>()
            val ok = pokeService.postPoke(body)
            if (ok) call.respond(body) else call.respondText("failed", status = HttpStatusCode.BadRequest)
        }

        patch {
            val body = call.receive<UpdatePoke>()
            val ok = pokeService.updatePoke(body)
            if (ok) call.respond(body) else call.respondText("failed", status = HttpStatusCode.BadRequest)
        }
        patch {
            val body = call.receive<UpdatePoke>()
            val ok = pokeService.updatePoke(body)
            if (ok) call.respond(body) else call.respondText("failed", status = HttpStatusCode.BadRequest)
        }

        delete {
            val body = call.receive<Poke>()
            val ok = pokeService.deletePoke(body)
            if (ok) call.respond(body) else call.respondText("failed", status = HttpStatusCode.BadRequest)
        }
    }
}
