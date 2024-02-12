package io.bespaltovyj.configuration

import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.common.config.SslConfigs
import org.springframework.util.ResourceUtils
import java.io.IOException

val FILE_PROPERTIES_NAMES = hashSetOf(
    SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,
    SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
)


fun KafkaClusterProperties.getCluster(name: String): KafkaCluster {
    return this.clusters[name] ?: throw IllegalArgumentException("Not found kafka-cluster")
}

fun KafkaClusterProperties.buildConsumerProperties(
    containerProperties: KafkaContainerProperties
): Map<String, Any> {
    val cluster = getCluster(containerProperties.cluster)

    cluster.properties

    val commonProperties = hashMapOf(
        CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG to cluster.bootstrapServers,
    )

    val result = (commonProperties + cluster.properties + containerProperties.properties).mapValues {
        if (FILE_PROPERTIES_NAMES.contains(it.key)) {
            resourceToPath(it.value as String)
        } else {
            it.value
        }
    }

    return result
}


private fun resourceToPath(path: String): String {
    try {
        return ResourceUtils.getFile(path).absolutePath
    } catch (ex: IOException) {
        throw IllegalStateException("Resource '$path' must be on a file system", ex)
    }
}