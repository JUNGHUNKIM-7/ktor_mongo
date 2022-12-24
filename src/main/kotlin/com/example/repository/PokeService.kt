package com.example.repository

import com.example.model.Poke
import com.example.model.UpdatePoke
import com.example.utils.MongoClient
import org.litote.kmongo.*

interface IPokeService {
    suspend fun getPokes(): List<Poke>
    suspend fun getPoke(id: String): Poke?
    suspend fun postPoke(body: Poke): Boolean
    suspend fun updatePoke(body: UpdatePoke): Boolean
    suspend fun deletePoke(body: Poke): Boolean
}

class PokeService : IPokeService {
    private val col = MongoClient.getInstance()

    override suspend fun getPokes(): List<Poke> = col.find().toList()
    override suspend fun getPoke(id: String): Poke? = col.findOne(Poke::id eq id.toInt())

    override suspend fun postPoke(body: Poke): Boolean {
        val poke = getPoke(body.id.toString())
        return if (poke != null) false
        else {
            col.insertOne(body)
            true
        }
    }

    override suspend fun updatePoke(body: UpdatePoke): Boolean {
        val poke = getPoke(body.id.toString())
        return if (poke != null) {
            col.updateOne(
                "{id: ${body.id}}", "{\$set:{attack:${body.attack}, defense:${body.defense}}}",
                updateOnlyNotNullProperties = true
            )
            true
        } else false
    }

    override suspend fun deletePoke(body: Poke): Boolean {
        val poke = getPoke(body.id.toString())
        return if (poke != null) {
            col.deleteOne(Poke::id eq body.id)
            true
        } else false
    }
}