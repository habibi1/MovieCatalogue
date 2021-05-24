package com.project.moviecatalogue.utils

import com.project.moviecatalogue.R
import com.project.moviecatalogue.data.MovieEntity
import com.project.moviecatalogue.data.TvShowEntity

object DataDummy {

    fun generateDummyTvShow(): List<TvShowEntity> {
        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                0,
                "Action",
                "en",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19",
                R.drawable.img_poster_tv_show_1,
                1306.109,
                "The Falcon and the Winter Soldier",
                7.9,
                5484,
                "50 Menit",
                "6"
            )
        )

        tvShow.add(
            TvShowEntity(
                1,
                "Action",
                "en",
                "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
                "2021-05-07",
                R.drawable.img_poster_tv_show_2,
                1182.741,
                "Jupiter's Legacy",
                7.5,
                170,
                "45 Menit",
                "8"
            )
        )

        tvShow.add(
            TvShowEntity(
                2,
                "Animation",
                "en",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "2021-03-26",
                R.drawable.img_poster_tv_show_3,
                1055.08,
                "Invincible",
                8.9,
                1611,
                "45 Menit",
                "8"
            )
        )

        tvShow.add(
            TvShowEntity(
                3,
                "Drama",
                "en",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "2017-09-25",
                R.drawable.img_poster_tv_show_4,
                1045.03,
                "The Good Doctor",
                8.6,
                8365,
                "43 Menit",
                "20"
            )
        )

        tvShow.add(
            TvShowEntity(
                4,
                "Drama",
                "en",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07",
                R.drawable.img_poster_tv_show_5,
                1026.489,
                "The Flash",
                7.7,
                7596,
                "44 Menit",
                "13"
            )
        )

        tvShow.add(
            TvShowEntity(
                5,
                "Action",
                "en",
                "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
                "2021-05-04",
                R.drawable.img_poster_tv_show_6,
                916.35,
                "The Bad Batch",
                9.0,
                154,
                "1 Jam 15 Menit",
                "16"
            )
        )

        tvShow.add(
            TvShowEntity(
                6,
                "Drama",
                "en",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "2005-03-27",
                R.drawable.img_poster_tv_show_7,
                733.781,
                "Grey's Anatomy",
                8.2,
                6039,
                "43 Menit",
                "17"
            )
        )

        tvShow.add(
            TvShowEntity(
                7,
                "Crime",
                "en",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "2016-01-25",
                R.drawable.img_poster_tv_show_8,
                623.818,
                "Lucifer",
                8.5,
                8564,
                "45 Menit",
                "16"
            )
        )

        tvShow.add(
            TvShowEntity(
                8,
                "Drama",
                "en",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "2017-01-26",
                R.drawable.img_poster_tv_show_9,
                588.123,
                "Riverdale",
                8.6,
                11241,
                "45 Menit",
                "11"
            )
        )

        tvShow.add(
            TvShowEntity(
                9,
                "Drama",
                "en",
                "As Mexican-American Tejano singer Selena comes of age and realizes her dreams, she and her family make tough choices to hold on to love and music.",
                "2020-12-04",
                R.drawable.img_poster_tv_show_10,
                560.025,
                "Selena: The Series",
                7.5,
                1307,
                "40 Menit",
                "18"
            )
        )

        return tvShow
    }

    fun generateDummyMovie(): List<MovieEntity> {
        val movie = ArrayList<MovieEntity>()

        movie.add(
            MovieEntity(
                0,
                "Action",
                "en",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "2021-04-29",
                 R.drawable.img_poster_movie_1,
                4547.446,
                "Tom Clancy's Without Remorse",
                7.3,
                829,
                "Remaja",
                "1 Jam 49 Menit"
            )
        )

        movie.add(
            MovieEntity(
                1,
                "Action",
                "en",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021-04-07",
                 R.drawable.img_poster_movie_2,
                3345.294,
                "Mortal Kombat",
                7.7,
                2429,
                "Remaja",
                "1 Jam 50 Menit"
            )
        )

        movie.add(
            MovieEntity(
                2,
                "Action",
                "en",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "2021-03-24",
                 R.drawable.img_poster_movie_3,
                1833.731,
                "Godzilla vs. Kong",
                8.1,
                5455,
                "PG-13",
                "1 Jam 53 Menit"
            )
        )

        movie.add(
            MovieEntity(
                3,
                "Action",
                "en",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "2021-03-26",
                 R.drawable.img_poster_movie_4,
                1803.573,
                "Nobody",
                8.4,
                1423,
                "Remaja",
                "1 Jam 32 Menit"
            )
        )

        movie.add(
            MovieEntity(
                4,
                "Animation",
                "en",
                "Set before the events of ‘Soul’, 22 refuses to go to Earth, enlisting a gang of 5 new souls in attempt of rebellion. However, 22’s subversive plot leads to a surprising revelation about the meaning of life.",
                "2021-04-30",
                 R.drawable.img_poster_movie_5,
                1396.571,
                "22 vs. Earth",
                7.2,
                66,
                "G",
                "9 Menit"
            )
        )

        movie.add(
            MovieEntity(
                5,
                "Action",
                "en",
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "2021-04-16",
                 R.drawable.img_poster_movie_6,
                1346.949,
                "Vanquish",
                6.4,
                81,
                "Remaja",
                "1 Jam 36 Menit"
            )
        )

        movie.add(
            MovieEntity(
                6,
                "Horor",
                "en",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "2021-03-31",
                 R.drawable.img_poster_movie_7,
                1265.474,
                "The Unholy",
                5.6,
                84,
                "PG-13",
                "1 Jam 39 Menit"
            )
        )

        movie.add(
            MovieEntity(
                7,
                "Action",
                "en",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "2021-03-18",
                 R.drawable.img_poster_movie_8,
                1126.642,
                "Zack Snyder's Justice League",
                8.5,
                5419,
                "Remaja",
                "4 Jam 2 Menit"
            )
        )

        movie.add(
            MovieEntity(
                8,
                "Action",
                "ja",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "2020-10-16",
                 R.drawable.img_poster_movie_9,
                1122.478,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                8.4,
                926,
                "Remaja",
                "1 Jam 57 Menit"
            )
        )

        movie.add(
            MovieEntity(
                9,
                "Animation",
                "fr",
                "To join Adrien in Shanghai, Marinette is going to visit her uncle Wang who is celebrating his anniversary. But, as soon as she arrives in China, her purse gets stolen with Tikki inside, whom she needs to secretly transform into Ladybug! Without money and alone in the immense city, Marinette accepts the help of a young and resourceful girl, Fei. The two girls will ally and discover the existence of a new magical jewel, the Prodigious. Hawk Moth, also present in Shanghai, seeks to finding it since a long time...",
                "2021-04-04",
                 R.drawable.img_poster_movie_10,
                1116.416,
                "Miraculous World: Shanghai – The Legend of Ladydragon",
                7.9,
                297,
                "U",
                "52 Menit"
            )
        )

        return movie
    }

}