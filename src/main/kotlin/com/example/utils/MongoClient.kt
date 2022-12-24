package com.example.utils

import com.example.model.Poke
import com.mongodb.client.MongoCollection
import de.sharpmind.ktor.EnvConfig
import org.litote.kmongo.*

class MongoClient private constructor() {
    companion object {
        private val url = EnvConfig.getString("db_url")
        private val dbName = EnvConfig.getString("db_name")
        private val collName = EnvConfig.getString("coll_name")
        private val client =
            KMongo.createClient(connectionString = url)
        private val database = client.getDatabase(dbName)
        private val col = database.getCollection<Poke>(collName)

        fun getInstance(): MongoCollection<Poke> = col
    }
}