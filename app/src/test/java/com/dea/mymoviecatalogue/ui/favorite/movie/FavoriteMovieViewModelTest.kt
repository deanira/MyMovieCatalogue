package com.dea.mymoviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie
import com.dea.mymoviecatalogue.repo.fakerepo.FakeFavoriteMovieRepository
import com.dea.mymoviecatalogue.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class FavoriteMovieViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    @Mock
    private lateinit var observer: Observer<PagedList<FavoriteMovie>>

    @Mock
    lateinit var observer2 : Observer<FavoriteMovie>

    @Mock
    private lateinit var repository: FakeFavoriteMovieRepository

    private lateinit var viewModel: FavoriteMovieViewModel

    val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(50)
        .setPageSize(50)
        .build()

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(repository)
    }
}