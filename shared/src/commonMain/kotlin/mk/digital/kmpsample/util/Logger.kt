package mk.digital.kmpsample.util

expect object Logger {

    fun e(log: String)
    
    fun e(e: Throwable)
    
    fun d(log: String)
}
