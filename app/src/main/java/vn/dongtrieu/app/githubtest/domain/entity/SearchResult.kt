package vn.dongtrieu.app.githubtest.domain.entity

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("total_count"        ) var totalCount        : Int?             = null,
    @SerializedName("incomplete_results" ) var incompleteResults : Boolean?         = null,
    @SerializedName("items"              ) var items             : ArrayList<GithubUser> = arrayListOf()
)
