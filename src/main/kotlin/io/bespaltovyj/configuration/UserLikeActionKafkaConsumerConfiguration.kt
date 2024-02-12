package io.bespaltovyj.configuration

import io.bespaltovyj.feature.order.UserActionDto
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

const val USER_LIKE_ACTION_KAFKA_LISTENER_CONTAINER_FACTORY = "userLikeActionKafkaListenerContainerFactory"

@EnableKafka
@Configuration(proxyBeanMethods = true)
class UserLikeActionKafkaConsumerConfiguration(
    private val kafkaClusterProperties: KafkaClusterProperties
) {

    @Bean
    @ConfigurationProperties("user.like.action.kafka.consumer")
    fun userLikeActionKafkaConsumerProperties() = KafkaContainerProperties()

    @Bean
    fun userLikeActionKafkaConsumerFactory(): DefaultKafkaConsumerFactory<String, UserActionDto> {
        return DefaultKafkaConsumerFactory(
            kafkaClusterProperties.buildConsumerProperties(userLikeActionKafkaConsumerProperties()),
            StringDeserializer(),
            buildDefaultJsonDeserializer<UserActionDto>()
        )
    }


    @Bean(USER_LIKE_ACTION_KAFKA_LISTENER_CONTAINER_FACTORY)
    fun userLikeActionKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, UserActionDto> {
        return with(userLikeActionKafkaConsumerProperties()) {
            ConcurrentKafkaListenerContainerFactory<String, UserActionDto>().also {
                it.consumerFactory = userLikeActionKafkaConsumerFactory()
                it.setConcurrency(concurrency)
                it.setAutoStartup(autoStartup)
                it.setBatchListener(batchListener)
            }
        }
    }
}