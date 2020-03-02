package run.perihelion.models

data class File (
    val filename: String,
    val type: String,
    val language: String,
    val raw_url: String,
    val size: Long
)