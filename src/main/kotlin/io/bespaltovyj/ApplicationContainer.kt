package io.bespaltovyj

import io.bespaltovyj.configuration.KafkaClusterProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableConfigurationProperties(
    value = [
        KafkaClusterProperties::class,
    ]
)
class ApplicationContainer

fun main(args: Array<String>) {
    runApplication<ApplicationContainer>(*args)
}