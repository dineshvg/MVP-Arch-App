package de.animebook.core.remote

const val AND = "&"
const val BASE_URL = "https://comicvine.gamespot.com/api/"
const val API_KEY = "XXXXXX"
const val JSON = "json"
const val LIMIT = 50

const val ASCENDING = "field:asc"
const val DESCENDING = "field:desc"

const val SERIES_SECTION = "series_list/"
const val MOVIES_SECTION = "movies/"
const val CHARACTERS_SECTION = "characters/"

const val SERIES_FIELD_LIST =
    "name,deck,description,first_episode,last_episode,count_of_episodes,image,id"
const val MOVIES_FIELD_LIST =
    "field_list=deck,description,first_episode,last_episode,count_of_episodes,image"
const val CHARACTERS_FIELD_LIST =
    "field_list=real_name,name,origin,birth,gender,first_appeared_in_issue, deck,description,image"
