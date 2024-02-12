package io.bespaltovyj.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("kafka")
data class KafkaClusterProperties(
    val clusters: Map<String, KafkaCluster>
)

class KafkaCluster {
    lateinit var bootstrapServers: List<String>
    lateinit var properties: Map<String, String>
}