package com.vaja1.microservice1.subject

import org.springframework.data.mongodb.repository.MongoRepository

interface SubjectRepository : MongoRepository<Subject, String> {
    fun findByProfessorId(professorId: String): List<Subject>
    fun findByAssistantId(assistantId: String): List<Subject>
}