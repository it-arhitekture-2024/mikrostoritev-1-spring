package com.vaja1.microservice1.subject

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/subjects")
class SubjectController(private val subjectService: SubjectService) {

    @GetMapping
    fun getAllSubjects(): List<Subject> {
        return subjectService.getAllSubjects()
    }

    @GetMapping("/{subjectId}")
    fun getSubjectById(@PathVariable subjectId: String): Subject {
        return subjectService.getSubjectById(subjectId)
    }

    @GetMapping("/professor/{professorId}")
    fun getSubjectsByProfessorId(@PathVariable professorId: String): List<Subject> {
        return subjectService.getSubjectsByProfessorId(professorId)
    }

    @GetMapping("/assistant/{assistantId}")
    fun getSubjectsByAssistantId(@PathVariable assistantId: String): List<Subject> {
        return subjectService.getSubjectsByAssistantId(assistantId)
    }

    @PostMapping
    fun insertSubject(@RequestBody subject: Subject): Subject {
        return subjectService.insertSubject(subject)
    }

    @PutMapping
    fun updateSubject(@RequestBody subjectId: String, @RequestBody updatedSubject: Subject): Subject {
        return subjectService.updateSubject(subjectId, updatedSubject)
    }

    @DeleteMapping
    fun deleteSubject(@RequestBody subjectId: String) {
        return subjectService.deleteSubject(subjectId)
    }
}