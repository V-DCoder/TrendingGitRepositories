package com.example.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.cache.LocalCache
import com.example.data.cache.RepositoryDAO
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.TrampolineScheduler
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class QueryTest {
    private lateinit var repositoryDAO: RepositoryDAO
    private lateinit var db: LocalCache
    private var insertTimeStamp: Long = 0
    private var THRESHOLD: Long = 1000 * 10 //5 sec threshold
    private val disposables: CompositeDisposable = CompositeDisposable()
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, LocalCache::class.java
        ).build()
        repositoryDAO = db.repositoryDAO()
        insertTimeStamp = System.currentTimeMillis()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
        disposables.dispose()
    }

    @Test
    fun checkOldCacheDoesNotExist() {
        val observer = TestObserver<Int>()
        val dis = repositoryDAO.searchValidTimestamp(insertTimeStamp)
            .subscribeOn(TrampolineScheduler.instance()).observeOn(TrampolineScheduler.instance())
        (dis.subscribeWith(observer) as? Disposable)?.let {
            disposables.add(it)
        }
        observer.onComplete()
        observer.assertNoErrors()
        Assert.assertEquals(0, observer.values()[0])
    }


}