package cl.bootcamp.integrador1.repository

import cl.bootcamp.integrador1.datasource.RestDataSource
import cl.bootcamp.integrador1.model.User
import cl.bootcamp.integrador1.model.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserRepository {
    suspend fun getNewUser(): User
    suspend fun deleteUser(toDelete: User)
    fun getAllUsers(): Flow<List<User>>
}

class UserRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val userDao: UserDao
): UserRepository{
    override suspend fun getNewUser(): User {
        val name = dataSource.getUserName().results[0].name!!
        val picture = dataSource.getUserPicture().results[0].picture!!
        val location = dataSource.getUserLocation().results[0].location!!
        val user = User(
            name.first,
            name.last,
            picture.thumbnail,
            location.city,
            location.state
        )
        userDao.insert(user)
        return user
    }

    override suspend fun deleteUser(toDelete: User) = userDao.delete(toDelete)

    override fun getAllUsers(): Flow<List<User>> = userDao.getAll()
}