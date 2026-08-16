package com.ap.cartly.data.mapper

import com.ap.cartly.data.local.entity.UserEntity
import com.ap.cartly.model.MembershipLevel
import com.ap.cartly.model.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        email = email,
        membershipLevel = membershipLevel.name
    )
}

fun UserEntity.toModel(): User {
    return User(
        id = id,
        name = name,
        email = email,
        membershipLevel = MembershipLevel.valueOf(membershipLevel)
    )
}

