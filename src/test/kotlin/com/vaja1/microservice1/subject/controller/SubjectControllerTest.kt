package com.vaja1.microservice1.subject.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.vaja1.microservice1.subject.Subject
import com.vaja1.microservice1.subject.SubjectController
import com.vaja1.microservice1.subject.SubjectService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.hamcrest.Matchers.*

@WebMvcTest(controllers = [SubjectController::class])
class SubjectControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
    @MockBean private val subjectService: SubjectService
) {

    @Test
    fun testGetAllSubjects() {
        val subjects = listOf(
            Subject("1", "Subject 1", "Description 1", "1", "1"),
            Subject("2", "Subject 2", "Description 2", "2", "2")
        )

        `when`(subjectService.getAllSubjects()).thenReturn(subjects)

        mockMvc.perform(get("/subjects"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize<Any>(2)))
            .andExpect(jsonPath("$[0].name", equalTo("Subject 1")))
            .andExpect(jsonPath("$[1].name", equalTo("Subject 2")))
    }

    @Test
    fun testGetSubjectById() {
        val subjectId = "1"
        val subject = Subject(subjectId, "Test Subject", "Test Description", "1", "1")

        `when`(subjectService.getSubjectById(subjectId)).thenReturn(subject)

        mockMvc.perform(get("/subjects/{subjectId}", subjectId))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(subjectId))
            .andExpect(jsonPath("$.name").value("Test Subject"))
            .andExpect(jsonPath("$.description").value("Test Description"))
    }

    @Test
    fun testGetSubjectsByProfessorId() {
        val professorId = "1"
        val subjects = listOf(
            Subject("1", "Subject 1", "Description 1", professorId, "1"),
            Subject("2", "Subject 2", "Description 2", professorId, "2")
        )

        `when`(subjectService.getSubjectsByProfessorId(professorId)).thenReturn(subjects)

        mockMvc.perform(get("/subjects/professor/{professorId}", professorId))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize<Any>(2)))
            .andExpect(jsonPath("$[0].professorId", equalTo(professorId)))
            .andExpect(jsonPath("$[1].professorId", equalTo(professorId)))
    }

    @Test
    fun testGetSubjectsByAssistantId() {
        val assistantId = "1"
        val subjects = listOf(
            Subject("1", "Subject 1", "Description 1", "1", assistantId),
            Subject("2", "Subject 2", "Description 2", "2", assistantId)
        )

        `when`(subjectService.getSubjectsByAssistantId(assistantId)).thenReturn(subjects)

        mockMvc.perform(get("/subjects/assistant/{assistantId}", assistantId))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize<Any>(2)))
            .andExpect(jsonPath("$[0].assistantId", equalTo(assistantId)))
            .andExpect(jsonPath("$[1].assistantId", equalTo(assistantId)))
    }

    @Test
    fun testInsertSubject() {
        val subjectToInsert = Subject(null, "New Subject", "New Description", "1", "1")
        val insertedSubject = Subject("1", "New Subject", "New Description", "1", "1")

        `when`(subjectService.insertSubject(subjectToInsert)).thenReturn(insertedSubject)

        mockMvc.perform(
            post("/subjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subjectToInsert))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("New Subject"))
            .andExpect(jsonPath("$.description").value("New Description"))
    }

    @Test
    fun testUpdateSubject() {
        val subjectId = "1"
        val updatedSubject = Subject(subjectId, "Updated Subject", "Updated Description", "1", "1")

        `when`(subjectService.updateSubject(subjectId, updatedSubject)).thenReturn(updatedSubject)

        mockMvc.perform(
            put("/subjects/{subjectId}", subjectId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedSubject))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(subjectId))
            .andExpect(jsonPath("$.name").value("Updated Subject"))
            .andExpect(jsonPath("$.description").value("Updated Description"))
    }

    @Test
    fun testDeleteSubject() {
        val subjectId = "1"

        mockMvc.perform(delete("/subjects/{subjectId}", subjectId))
            .andExpect(status().isOk)

        verify(subjectService, times(1)).deleteSubject(subjectId)
    }
}