package io.bespaltovyj.feature.order

import java.util.UUID

data class UserActionDto(
    val userId: UUID,
    val postId: UUID,
)