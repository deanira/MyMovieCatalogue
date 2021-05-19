package com.dea.mymoviecatalogue.utils

import com.dea.mymoviecatalogue.Resource
import com.dea.mymoviecatalogue.data.favorite.FavoriteMovie
import com.dea.mymoviecatalogue.data.favorite.FavoriteTvShow
import com.dea.mymoviecatalogue.data.response.*

object DataDummyTest {
    fun generateMovies(): Resource<MovieResponse> {
        val response = MovieResponse()
        val movies = ArrayList<MovieResultsItem>()

        movies.add(
            MovieResultsItem(
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "Mortal Kombat",
                false,
                "Mortal Kombat",
                listOf(
                    14,
                    28,
                    12,
                    878,
                    53
                ),
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "2021-04-07",
                8009.461,
                7.8,
                460465,
                false,
                1959
            )
        )
        movies.add(
            MovieResultsItem(
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "en",
                "Vanquish",
                false,
                "Vanquish",
                listOf(
                    28,
                    80,
                    53
                ),
                "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                "/mb3fcmQzXd8aUf7r6izZfMHSJmz.jpg",
                "2021-04-16",
                3914.918,
                6.2,
                804435,
                false,
                45
            )
        )
        movies.add(
            MovieResultsItem(
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "en",
                "Godzilla vs. Kong",
                false,
                "Godzilla vs. Kong",
                listOf(
                    878,
                    28,
                    18
                ),
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "2021-03-24",
                4025.879,
                8.2,
                399566,
                false,
                5188
            )
        )
        movies.add(
            MovieResultsItem(
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "ja",
                "劇場版「鬼滅の刃」無限列車編",
                false,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                listOf(
                    16,
                    28,
                    12,
                    14,
                    18
                ),
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "/3FVe3OAdgz060JaxIAaUl5lo6cx.jpg",
                "2020-10-16",
                2659.934,
                8.3,
                635302,
                false,
                768
            )
        )
        movies.add(
            MovieResultsItem(
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "en",
                "Nobody",
                false,
                "Nobody",
                listOf(
                    28,
                    53,
                    80
                ),
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg",
                "2021-03-26",
                3110.282,
                8.4,
                615457,
                false,
                1063
            )
        )
        response.results = movies
        return Resource.Success(response)
    }

    fun generateTvShows(): Resource<TvShowResponse> {
        val response = TvShowResponse()
        val tvShows = ArrayList<TvShowResultsItem>()

        tvShows.add(
            TvShowResultsItem(
                "2021-03-19",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "en",
                listOf(
                    10765,
                    10759,
                    18,
                    10768
                ),
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                listOf("US"),
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "The Falcon and the Winter Soldier",
                2204.916,
                7.9,
                "The Falcon and the Winter Soldier",
                88396,
                5237
            )
        )
        tvShows.add(
            TvShowResultsItem(
                "2021-03-19",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "en",
                listOf(
                    10765,
                    10759,
                    18,
                    10768
                ),
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                listOf("US"),
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "The Falcon and the Winter Soldier",
                2204.916,
                7.9,
                "The Falcon and the Winter Soldier",
                88396,
                5237
            )
        )
        tvShows.add(
            TvShowResultsItem(
                "2021-03-19",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "en",
                listOf(
                    10765,
                    10759,
                    18,
                    10768
                ),
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                listOf("US"),
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "The Falcon and the Winter Soldier",
                2204.916,
                7.9,
                "The Falcon and the Winter Soldier",
                88396,
                5237
            )
        )
        tvShows.add(
            TvShowResultsItem(
                "2021-03-19",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "en",
                listOf(
                    10765,
                    10759,
                    18,
                    10768
                ),
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                listOf("US"),
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "The Falcon and the Winter Soldier",
                2204.916,
                7.9,
                "The Falcon and the Winter Soldier",
                88396,
                5237
            )
        )
        tvShows.add(
            TvShowResultsItem(
                "2021-03-19",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "en",
                listOf(
                    10765,
                    10759,
                    18,
                    10768
                ),
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                listOf("US"),
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "The Falcon and the Winter Soldier",
                2204.916,
                7.9,
                "The Falcon and the Winter Soldier",
                88396,
                5237
            )
        )
        response.results = tvShows
        return Resource.Success(response)
    }

    fun generateDetailMovie(): Resource<DetailMovieResponse> {
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

    fun generateDetailTvShow(): Resource<DetailTvShowResponse> {
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

    fun generateFavouriteMovie(): List<FavoriteMovie> {
        return listOf(
            FavoriteMovie(1, 1, "", "", 0.0, ""),
            FavoriteMovie(2, 2, "", "", 0.0, ""),
        )
    }

    fun generateFavouriteTv(): List<FavoriteTvShow> {
        return listOf(
            FavoriteTvShow(1, 1, "", "", 0.0, ""),
            FavoriteTvShow(2, 2, "", "", 0.0, ""),
        )
    }
}