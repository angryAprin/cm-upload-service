package com.qirat.content.management.api.v1

import com.qirat.content.management.utils.models.ContentStoreObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Pattern

/**
 *
 * @author Tauriq Behardien
 */
@RestController
class ApiController {
    @Autowired
    private lateinit var worker: Worker

    @GetMapping("/health")
    fun health(): String {
        return "running"
    }

    @PostMapping("/v1/{objectIdentifier}")
    fun updateContent(@Valid @Pattern(regexp = "[A-Za-z0-9]{9,}") @RequestParam(value = "objectIdentifier") objectIdentifier: String,
                      @RequestBody(required = false) contentObject: ContentStoreObject?): ResponseEntity<String> {

        return this.worker.updateContent(objectIdentifier, contentObject)
    }

    @PostMapping("/v1")
    fun uploadContent(@RequestBody(required = false) contentObject: ContentStoreObject?): ResponseEntity<String> {

        return this.worker.uploadContent(contentObject)
    }
}