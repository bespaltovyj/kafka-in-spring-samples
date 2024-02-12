package io.bespaltovyj.feature.order

import io.bespaltovyj.configuration.USER_LIKE_ACTION_KAFKA_LISTENER_CONTAINER_FACTORY
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class UserLikeActionKafkaListener {

    @KafkaListener(
        id = "onUserLikeAction",
        idIsGroup = false,
        topics = ["user.action.like"],
        containerFactory = USER_LIKE_ACTION_KAFKA_LISTENER_CONTAINER_FACTORY,
    )
    fun onUserLikeAction(action: UserActionDto) = Unit
}