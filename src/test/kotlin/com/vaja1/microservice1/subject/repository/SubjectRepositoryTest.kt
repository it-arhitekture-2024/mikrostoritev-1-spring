package com.vaja1.microservice1.subject.repository

import com.vaja1.microservice1.subject.Subject
import com.vaja1.microservice1.subject.SubjectRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@DataMongoTest
@SpringJUnitConfig
class SubjectRepositoryTest @Autowired constructor(
    private val subjectRepository: SubjectRepository
) {

    @Test
    fun testFindByProfessorId() {
        val professorId = "testProfessorId"
        subjectRepository.save(Subject("1", "Subject 1", "Description 1", professorId, "1"))
        subjectRepository.save(Subject("2", "Subject 2", "Description 2", professorId, "2"))

        val subjects = subjectRepository.findByProfessorId(professorId)

        assert(subjects.size == 2)
    }

    @Test
    fun testFindByAssistantId() {
        val assistantId = "testAssistantId"
        subjectRepository.save(Subject("3", "Subject 3", "Description 3", "1", assistantId))
        subjectRepository.save(Subject("4", "Subject 4", "Description 4", "2", assistantId))

        val subjects = subjectRepository.findByAssistantId(assistantId)

        assert(subjects.size == 2)
    }
}