package com.payhere.recruit.homework.repository

import com.payhere.recruit.homework.domain.entity.MemberJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MemberRepository : JpaRepository<MemberJpaEntity, Long> {

    fun findByPhoneNumber(phoneNumber: String) : Optional<MemberJpaEntity>
    fun existsByPhoneNumber(phoneNumber: String) : Boolean
}