package io.bespaltovyj.configuration

class KafkaContainerProperties {
    lateinit var cluster: String
    lateinit var properties: Map<String, String>
    var autoStartup: Boolean = true
    var batchListener: Boolean = false
    var concurrency: Int = 1
}