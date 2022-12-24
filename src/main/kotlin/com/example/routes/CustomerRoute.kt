package com.example.routes

import com.example.model.CustomerModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouter() {
    val customers = mutableListOf<CustomerModel>(
        CustomerModel("test", "test2")
    )

    suspend fun searchUserByEmail(call: ApplicationCall): Any {
        return call.parameters["email"] ?: return call.respondText(
            "no customers found",
            status = HttpStatusCode.NotFound
        )
    }

    route("/customer") {
        get() {
            if (customers.isNotEmpty()) call.respond(customers)
            else call.respondText("no found customers", status = HttpStatusCode.NotFound)
        }

        get("{email?}") {
            val email = searchUserByEmail(call) as String
            val customer = customers.find { it.email == email }
            if (customer != null) call.respond(customer)
            else call.respondText("no customer found", status = HttpStatusCode.NotFound)
        }

        post() {
            val body = call.receive<CustomerModel>()
            customers.add(body)
            call.respond(customers)
        }

        delete("{email?}") {
            val email = searchUserByEmail(call) as String
            customers.removeIf { it.email == email }
            call.respond(customers)
        }

        put("{email?}") {
            val email = searchUserByEmail(call) as String
            val body = call.receive<CustomerModel>()
            val customer = customers.find { it.email == email }
            if (customer != null) {
                customers.map { it.password = body.password }
                call.respond(customers)
            } else call.respondText("customer not found", status = HttpStatusCode.NotFound)
        }
    }
}