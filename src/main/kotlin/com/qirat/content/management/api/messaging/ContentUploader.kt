package com.qirat.content.management.api.messaging

import com.qirat.content.management.api.db.ContentStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.messaging.support.MessageBuilder
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

/**
 *
 * @author Tauriq Behardien
 */
@Component
@EnableScheduling
@EnableBinding(Source::class)
class ContentUploader {
    @Autowired
    private lateinit var source: Source
    @Autowired
    private lateinit var contentStore: ContentStore

    private lateinit var lastScheduledTime: OffsetDateTime

    @Scheduled(fixedRate = 1000)
    fun upload() {
        this.lastScheduledTime = OffsetDateTime.now()
        val contentObject = this.contentStore.findByCreationDate(this.lastScheduledTime)

        contentObject.forEach {
            source.output().send(MessageBuilder.withPayload(it).build())
        }
    }
}
