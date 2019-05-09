package com.qirat.content.management.api.db

import com.qirat.content.management.api.models.ContentObject
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.OffsetDateTime
import java.util.*

interface ContentStore: MongoRepository<ContentObject, String> {
    @Query("SELECT u FROM ContentObject u WHERE u.creationDate > ?1")
    fun findByCreationDate(creationDate: OffsetDateTime): List<ContentObject>

    fun save(content: Optional<ContentObject>)

    fun findContentById(obj: String): ContentObject
}

