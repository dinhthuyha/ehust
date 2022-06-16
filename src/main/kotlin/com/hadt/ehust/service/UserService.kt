package com.hadt.ehust.service

import com.hadt.ehust.entities.ClassStudent
import com.hadt.ehust.entities.Subject
import com.hadt.ehust.entities.User
import com.hadt.ehust.model.Project
import com.hadt.ehust.model.Role
import com.hadt.ehust.repository.PairingRepository
import com.hadt.ehust.repository.UserRepository
import com.hadt.ehust.security.JwtUtils
import com.hadt.ehust.security.UserDetailsImpl
import com.hadt.ehust.utils.Utils
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val pairingRepository: PairingRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils
) {
    fun signIn(id: Int, password: String): Map<String, Any> {
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(id, password))
        return jwtUtils.generateAuthToken(authentication.principal as UserDetailsImpl)
    }

    fun findUserByIdAndRole(id: Int, roleId: com.hadt.ehust.model.Role): ResponseEntity<User> {
        return userRepository.findById(id).map {
            if (it.role == roleId) {
                ResponseEntity.ok(
                    User(
                        id = it.id,
                        fullName = it.fullName,
                        instituteOfManagement = it.instituteOfManagement,
                        gender = it.gender,
                        grade = it.grade,
                        course = it.course,
                        email = it.email,
                        cadreStatus = it.cadreStatus ?: "",
                        unit = it.unit,
                        role = it.role,
                        imageAvatar = it.imageAvatar,
                        imageBackground = it.imageBackground
                    )
                )
            } else {
                ResponseEntity.notFound().build()
            }
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findUserByFullName(fullName: String): ResponseEntity<User> {
        return userRepository.findUserByFullName(fullName).map {
            ResponseEntity.ok(
                User(
                    id = it.id,
                    fullName = it.fullName,
                    instituteOfManagement = it.instituteOfManagement,
                    gender = it.gender,
                    grade = it.grade,
                    course = it.course,
                    email = it.email,
                    cadreStatus = it.cadreStatus ?: "",
                    unit = it.unit,
                    role = it.role,
                    imageAvatar = it.imageAvatar,
                    imageBackground = it.imageBackground
                )
            )
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findProfileById(id: Int): ResponseEntity<User> {
        return userRepository.findById(id).map {
            ResponseEntity.ok(
                User(
                    id = it.id,
                    fullName = it.fullName,
                    instituteOfManagement = it.instituteOfManagement,
                    gender = it.gender,
                    grade = it.grade,
                    course = it.course,
                    email = it.email,
                    cadreStatus = it.cadreStatus ?: "",
                    unit = it.unit,
                    role = it.role,
                    imageAvatar = it.imageAvatar,
                    imageBackground = it.imageBackground
                )
            )
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findALlStudentInClass(grade: String): ResponseEntity<List<User>> {
        val listUser = mutableListOf<User>()
        return userRepository.getListStudentInClass(grade).map { users ->
            users.forEach {
                listUser.add(
                    User(
                        id = it.id,
                        fullName = it.fullName,
                        instituteOfManagement = it.instituteOfManagement,
                        gender = it.gender,
                        grade = it.grade,
                        course = it.course,
                        email = it.email,
                        cadreStatus = it.cadreStatus ?: "",
                        unit = it.unit,
                        role = it.role,
                        imageAvatar = it.imageAvatar,
                        imageBackground = it.imageBackground
                    )
                )
            }
            ResponseEntity.ok(listUser.toList())
        }.orElse(ResponseEntity.notFound().build())
    }

    fun findAllProjectsByIdStudent(id: Int): ResponseEntity<List<Project>> {
        val projects = mutableListOf<Project>()

      return  userRepository.findById(id).map { user ->
                when(Utils.hasRole(Role.ROLE_TEACHER)){
                    true -> {

                        user.userSubjects
                            ?.filter { it.isProject == true  }
                            ?.forEach {
                                projects.add(
                                    Project(
                                        codeCourse = it.id,
                                        name = it.name
                                    )
                                )
                            }
                    }
                    false -> {
                        user.userSubjects?.filter { it.isProject == true }?.forEach {
                            it.listClass?.forEach { classStu ->
                                projects.add(
                                    Project(
                                        codeClass = classStu.codeClass,
                                        codeCourse = classStu.subjectClass?.id,
                                        name = classStu.subjectClass?.name,
                                        semester = classStu.semester,
                                        nameTeacher = getTeacherAssignedUser(classStu.subjectClass?.name,id)
                                    )
                                )
                            }
                        }
                        projects.sortByDescending { it.semester }
                    }

                }

            ResponseEntity.ok(projects.toList())
        }.orElse(ResponseEntity.notFound().build())

    }

    private fun getTeacherAssignedUser(nameCourse: String?, idStudent: Int): String{
        var nameTeacher = ""
        pairingRepository.findUserByIdStudentAndNameProject(idStudent, nameCourse).map { pair ->
            userRepository.findById(pair.idTeacher).map {
                nameTeacher = it.fullName
            }
        }
        return nameTeacher
    }
    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findByScheduleByIdStudent(id: Int): ResponseEntity<List<ClassStudent>> {
        val classStudents = mutableListOf<ClassStudent>()
        return userRepository.findById(id).map { user ->
            user.userSubjects
                ?.toList()
                ?.filter { it.isProject == false }
                ?.forEach { subject ->
                    val semesterCurrent = subject.listClass?.toList()?.maxOfOrNull { it.semester!! }
                    subject.listClass
                        ?.filter { it.semester == semesterCurrent }
                        ?.forEach {
                            val subject = Subject(
                                id = it.subjectClass?.id!!,
                                name = it.subjectClass?.name!!
                            )
                            classStudents.add(
                                ClassStudent(
                                    codeClass = it.codeClass,
                                    startTime = it.startTime,
                                    finishTime = it.finishTime,
                                    dateStudy = it.dateStudy,
                                    subjectClass = subject,
                                    dateFinishCourse = it.dateFinishCourse,
                                    dateStartCourse = it.dateStartCourse
                                )
                            )
                        }

                }
            ResponseEntity.ok(classStudents.toList())
        }.orElse(ResponseEntity.notFound().build())
    }
}