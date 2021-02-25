package run.perihelion.models

data class Gist(
    val url: String? = null,
    val forks_url: String? = null,
    val commits_url: String? = null,
    val id: String? = null,
    val node_id: String? = null,
    val git_pull_url: String? = null,
    val git_push_url: String? = null,
    val html_url: String? = null,
    val files: Map<String, File?>? = null,
    val public: Boolean? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val description: String? = null,
    val comments: Int? = null,
    val user: String? = null,
    val comments_url: String? = null,
    val owner: Owner? = null,
    val truncated: Boolean? = null
)