package cl.bootcamp.wallet.repository

import cl.bootcamp.wallet.datasource.RestDataSource
import cl.bootcamp.wallet.model.User
import cl.bootcamp.wallet.model.UserDao
import cl.bootcamp.wallet.model.UserWallet
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface WalletRepository {
    suspend fun getUserById(id: Int): UserWallet
    suspend fun getAllUserApi(): ArrayList<UserWallet>
    fun getAllUserRoom(): Flow<List<User>>
}

class WalletRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val userDao: UserDao
) : WalletRepository {
    override suspend fun getUserById(id: Int): UserWallet {
        val data = dataSource.getUserWalletById(id).body()!!
        val user = UserWallet(
            id = data.id,
            nombre = data.nombre,
            pais = data.pais,
            imagenLink = data.imagenLink,
            cuenta = data.cuenta,
            saldo = data.saldo,
            depositos = data.depositos
        )


        return user
    }

    override suspend fun getAllUserApi(): ArrayList<UserWallet> {
        val data = dataSource.getUserWallet()
        data.forEach {
            val user = User(
                id = it.id,
                nombre = it.nombre,
                pais = it.pais,
                imagenLink = it.imagenLink,
                cuenta = it.cuenta,
                saldo = it.saldo,
                depositos = it.depositos
            )
            userDao.insert(user)
        }
        return ArrayList(data)
    }

    override fun getAllUserRoom(): Flow<List<User>> = userDao.getAll()

}