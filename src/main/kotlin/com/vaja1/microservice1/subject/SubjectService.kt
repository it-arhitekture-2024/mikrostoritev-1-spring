package com.vaja1.microservice1.subject

import org.springframework.stereotype.Service

@Service
class SubjectService(private val subjectRepository: SubjectRepository) {

    fun getAllSubjects(): List<Subject> {
        return subjectRepository.findAll()
    }

    fun getSubjectById(subjectId: String): Subject {
        return subjectRepository.findById(subjectId).orElseThrow {
            NoSuchElementException("Subject not found.")
        }
    }

    fun getSubjectsByProfessorId(professorId: String): List<Subject> {
        return subjectRepository.findByProfessorId(professorId).toList()
    }

    fun getSubjectsByAssistantId(assistantId: String): List<Subject> {
        return subjectRepository.findByAssistantId(assistantId).toList()
    }

    fun insertSubject(subject: Subject): Subject {
        return subjectRepository.save(subject)
    }

    fun updateSubject(subjectId: String, updatedSubject: Subject): Subject {
        val subject = subjectRepository.findById(subjectId).orElseThrow {
            throw NoSuchElementException("Subject not found.")
        }

        subject.name = updatedSubject.name
        subject.description = updatedSubject.description
        subject.professorId = updatedSubject.professorId
        subject.assistantId = updatedSubject.assistantId

        return subjectRepository.save(subject)
    }

    fun deleteSubject(subjectId: String) {
        if (subjectRepository.existsById(subjectId)) {
            subjectRepository.deleteById(subjectId).run {
                "Subject is deleted."
            }
        } else {
            throw NoSuchElementException("Subject not found.")
        }
    }
}

//{}