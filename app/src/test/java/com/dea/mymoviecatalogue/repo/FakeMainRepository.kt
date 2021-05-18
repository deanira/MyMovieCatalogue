package com.dea.mymoviecatalogue.repo

import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.response.*
import com.dea.mymoviecatalogue.repo.repointerface.Repository
import com.dea.mymoviecatalogue.utils.DataDummyTest

class FakeMainRepository : Repository {
    override suspend fun getPopularMovies(): Resource<MovieResponse> {
        return DataDummyTest.generateMovies()
    }

    override suspend fun getNowPlayingMovies(): Resource<MovieResponse> {
        return DataDummyTest.generateMovies()
    }

    override suspend fun getTopRatedMovies(): Resource<MovieResponse> {
        return DataDummyTest.generateMovies()
    }

    override suspend fun getMovieById(id: Int): Resource<DetailMovieResponse> {
        return Resource.Success(
            DetailMovieResponse(
                "en",
                "tt0293429",
                false,
                "Mortal Kombat",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                50115000,
                listOf(
                    GenresItem(
                        "Fantasy",
                        14
                    ),
                    GenresItem(
                        "Action",
                        28
                    ),
                    GenresItem(
                        "Adventure",
                        12
                    ),
                    GenresItem(
                        "Science Fiction",
                        878
                    ),
                    GenresItem(
                        "Thriller",
                        53
                    )
                ),
                5088.686,
                listOf(
                    ProductionCountriesItem()
                ),
                460465,
                2053,
                20000000,
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "Mortal Kombat",
                110,
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                listOf(),
                listOf(),
                "2021-04-07",
                7.8,
                null,
                "Get over here.",
                false,
                "https://www.mortalkombatmovie.net",
                "Released"
            )
        )
    }

    override suspend fun getPopularShows(): Resource<TvShowResponse> {
        return DataDummyTest.generateTvShows()
    }

    override suspend fun getOnTheAirShows(): Resource<TvShowResponse> {
        return DataDummyTest.generateTvShows()
    }

    override suspend fun getTopRatedShows(): Resource<TvShowResponse> {
        return DataDummyTest.generateTvShows()
    }

    override suspend fun getTvShowById(id: Int): Resource<DetailTvShowResponse> {
        return Resource.Success(
            DetailTvShowResponse(
                "en",
                6,
                listOf(
                    NetworksItem(
                        "/gJ8VX6JSu3ciXHuC2dDGAo2lvwM.png",
                        "Disney+",
                        2739,
                        "US"
                    )
                ),
                "Miniseries",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                listOf(
                    GenresItem(
                        "Sci-Fi & Fantasy",
                        10765
                    ),
                    GenresItem(
                        "Action & Adventure",
                        10759
                    ),
                    GenresItem(
                        "Drama",
                        18
                    ),
                    GenresItem(
                        "War & Politics",
                        10768
                    )
                ),
                2204.916,
                listOf(),
                88396,
                1,
                5250,
                "2021-03-19",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                listOf(),
                listOf(),
                listOf(),
                LastEpisodeToAir(),
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                listOf(),
                listOf(),
                listOf(),
                "The Falcon and the Winter Soldier",
                7.9,
                "The Falcon and the Winter Soldier",
                "Honor the shield.",
                listOf(50),
                null,
                false,
                "2021-04-23",
                "https://www.disneyplus.com/series/the-falcon-and-the-winter-soldier/4gglDBMx8icA",
                "Ended"
            )
        )
    }
}