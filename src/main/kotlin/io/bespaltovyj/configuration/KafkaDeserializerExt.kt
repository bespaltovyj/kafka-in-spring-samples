package io.bespaltovyj.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Deserializer
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer

inline fun <reified T> buildDefaultJsonDeserializer(): Deserializer<T> {
    return ErrorHandlingDeserializer(
        JsonDeserializer(
            T::class.java,
            ObjectMapper().findAndRegisterModules()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        ).also {
            // Десериализация на основе заголовков не очень удачная, так как если в событие есть хэдер с названием класса, то
            // этот только этот может быть использован для десериализация. Это не очень удобно, поэтому лучше сразу отключить это
            it.ignoreTypeHeaders()
        }
    )
}