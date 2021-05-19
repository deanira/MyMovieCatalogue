package com.dea.mymoviecatalogue.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dea.myTvShowcatalogue.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow
import com.dea.mymoviecatalogue.repo.fakerepo.FakeFavoriteTvShowRepository
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
class FavoriteTvShowViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    @Mock
    private lateinit var observer: Observer<PagedList<FavoriteTvShow>>

    @Mock
    lateinit var observer2 : Observer<FavoriteTvShow>

    @Mock
    private lateinit var repository: FakeFavoriteTvShowRepository

    private lateinit var viewModel: FavoriteTvShowViewModel

    val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(50)
        .setPageSize(50)
        .build()

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(repository)
    }
}