package com.qirat.content.management.api.v1

import com.qirat.content.management.api.db.ContentStore
import com.qirat.content.management.api.models.ContentObject
import com.qirat.content.management.utils.models.ContentStoreObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/**
 *
 * @author Tauriq Behardien
 */
class Worker {
    @Autowired
    private lateinit var contentStore: ContentStore

    fun uploadContent(contentObject: ContentStoreObject?): ResponseEntity<String> {
        if (contentObject == null)
            return ResponseEntity("Don't be an idiot...", HttpStatus.BAD_REQUEST)

        val content = ContentObject(
                data = contentObject.data,
                type = contentObject.type,
                clientId = contentObject.clientData.identifier
        )

        this.contentStore.insert(content)

        return ResponseEntity(HttpStatus.CREATED)
    }

    fun updateContent(objectIdentifier: String, contentObject: ContentStoreObject?): ResponseEntity<String> {
        if (contentObject == null)
            return ResponseEntity("Don't be an idiot...", HttpStatus.BAD_REQUEST)

        val content = this.contentStore.findContentById(objectIdentifier)
        content.id = contentObject.id
        content.creationDate = contentObject.creationDate
        content.data = contentObject.data
        content.type = contentObject.type
        content.clientId = contentObject.clientData.identifier


        this.contentStore.save(content)

        return ResponseEntity(HttpStatus.ACCEPTED)
    }
}