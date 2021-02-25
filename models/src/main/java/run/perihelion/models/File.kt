package run.perihelion.models

data class File (
    val filename: String? = null,
    val type: String? = null,
    val language: String? = null,
    val raw_url: String? = null,
    val size: Long? = null
)