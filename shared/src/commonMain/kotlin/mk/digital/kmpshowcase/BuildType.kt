package mk.digital.kmpshowcase;

enum class BuildType {
    DEBUG, RELEASE;

    val isDebug: Boolean get() = this == DEBUG
    val isRelease: Boolean get() = this == RELEASE
}