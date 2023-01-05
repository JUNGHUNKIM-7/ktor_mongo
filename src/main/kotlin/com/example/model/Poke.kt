package com.example.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.*

@Serializable
data class Poke(
    @Contextual @SerialName("_id") val bsonId: Id<Poke> = newId(),
    val attack: Int,
    val defense: Int,
    val hp: Int,
    val id: Int,
    val name: String,
    @Contextual @SerialName("special_attack") val specialAttack: Int,
    @Contextual @SerialName("special_defense") val specialDefense: Int,
    val speed: Int,
    val type: List<String>
)

@Serializable
data class UpdatePoke(
    val attack: Int,
    val defense: Int,
    val id: Int,
)