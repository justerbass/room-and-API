package cl.bootcamp.individual1.di

import android.content.Context
import androidx.room.Room
import cl.bootcamp.individual1.room.ContactsDao
import cl.bootcamp.individual1.room.ContatcsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object appModule{

    @Singleton
    @Provides
    fun providesContactsDao( contactsDataBase: ContatcsDataBase) : ContactsDao {
        return contactsDataBase.contactsDao()
    }

    @Singleton
    @Provides
    fun providesContactsDataBase(@ApplicationContext context: Context) : ContatcsDataBase {
        return Room.databaseBuilder(
            context,
            ContatcsDataBase::class.java,
            "contacts_database"
        ).fallbackToDestructiveMigration()
            .build()

    }
}