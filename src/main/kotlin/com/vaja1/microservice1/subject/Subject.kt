package com.vaja1.microservice1.subject

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Subject")
data class Subject(
    @Id
    val id: String? = null,
    var name: String,
    var description: String,
    var professorId: String?,
    var assistantId: String?
)