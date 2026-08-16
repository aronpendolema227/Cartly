package com.ap.cartly.data.repository

import com.ap.cartly.data.AppData
import com.ap.cartly.data.local.dao.UserDao
import com.ap.cartly.data.mapper.toEntity
import com.ap.cartly.data.mapper.toModel
import com.ap.cartly.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val userDao: UserDao
) {

    fun getUserProfile(userId: String): Flow<User?> {
        return userDao.getUserProfile(userId).map { entity ->
            entity?.toModel()
        }
    }

    val users: Flow<List<User>> =
        userDao.getAllUsers().map { entities ->
            entities.map { entity ->
                entity.toModel()
            }
        }

    suspend fun insert(user: User) {
        userDao.insert(user.toEntity())
    }

    suspend fun insertAll(users: List<User>) {
        userDao.insertAll(
            users.map { user ->
                user.toEntity()
            }
        )
    }

    suspend fun update(user: User) {
        userDao.update(user.toEntity())
    }

    suspend fun delete(user: User) {
        userDao.delete(user.toEntity())
    }

    suspend fun initializeUsersIfNeeded() {
        if (userDao.countUsers() == 0) {
            insertAll(AppData.usuarios)
        }
    }
}
