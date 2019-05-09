package com.qirat.content.management.api.models

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import java.time.OffsetDateTime
import java.util.*

/**
 *
 * @author Tauriq Behardien
 */
class ContentObject {
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Id
    var id = UUID.randomUUID().toString()
    var creationDate = OffsetDateTime.now()

    var data: String
    var type: String

    lateinit var clientId: String

    constructor(data: String, type: String) {
        this.data = data
        this.type = type
    }

    constructor(data: String, type: String, clientId: String) {
        this.data = data
        this.type = type
        this.clientId = clientId
    }

    override fun toString(): String {
        return this.objectMapper.writeValueAsString(this)
    }
}