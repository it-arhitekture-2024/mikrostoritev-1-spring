package com.vaja1.microservice1.subject

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SubjectService(private val subjectRepository: SubjectRepository) {
    private val logger: Logger = LoggerFactory.getLogger(SubjectService::class.java)

    fun getAllSubjects(): List<Subject> {
        logger.info("Fetching all subjects.")
        val subjects = subjectRepository.findAll()
        logger.info("Found {} subjects.", subjects.size)
        return subjects
    }

    fun getSubjectById(subjectId: String): Subject {
        logger.info("Fetching subject by ID: {}", subjectId)
        try {
            val subject = subjectRepository.findById(subjectId).orElseThrow {
                NoSuchElementException("Subject not found.")
            }
            logger.info("Found subject: {}", subject)
            return subject
        } catch (ex: Exception) {
            logger.error("Error fetching subject by ID: {}", subjectId)
            throw ex
        }
    }

    fun getSubjectsByProfessorId(professorId: String): List<Subject> {
        logger.info("Fetching subjects by professor ID: {}", professorId)
        val subjects = subjectRepository.findByProfessorId(professorId).toList()
        logger.info("Found {} subjects.", subjects.size)
        return subjects
    }

    fun getSubjectsByAssistantId(assistantId: String): List<Subject> {
        logger.info("Fetching subjects by assistant ID: {}", assistantId)
        val subjects = subjectRepository.findByAssistantId(assistantId).toList()
        logger.info("Found {} subjects.", subjects.size)
        return subjects
    }

    fun insertSubject(subject: Subject): Subject {
        logger.info("Inserting new subject: {}", subject)
        val savedSubject = subjectRepository.save(subject)
        logger.info("Subject inserted successfully.")
        return savedSubject
    }

    fun updateSubject(subjectId: String, updatedSubject: Subject): Subject {
        logger.info("Updating subject with ID: {}", subjectId)
        try {
            val subject = subjectRepository.findById(subjectId).orElseThrow {
                NoSuchElementException("Subject not found.")
            }

            subject.name = updatedSubject.name
            subject.description = updatedSubject.description
            subject.professorId = updatedSubject.professorId
            subject.assistantId = updatedSubject.assistantId

            val updatedSubjectResult = subjectRepository.save(subject)
            logger.info("Subject updated successfully: {}", updatedSubjectResult)
            return updatedSubjectResult
        } catch (ex: Exception) {
            logger.error("Error updating subject by ID: {}", subjectId, ex)
            throw ex
        }
    }

    fun deleteSubject(subjectId: String) {
        logger.info("Deleting subject with ID: {}", subjectId)
        if (subjectRepository.existsById(subjectId)) {
            subjectRepository.deleteById(subjectId)
            logger.info("Subject deleted successfully.")
        } else {
            logger.error("Subject not found for deletion. ID: {}", subjectId)
            throw NoSuchElementException("Subject not found.")
        }
    }
}