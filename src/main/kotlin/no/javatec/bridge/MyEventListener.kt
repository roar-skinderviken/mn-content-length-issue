package no.javatec.bridge

/** anonymous implementation inside FluxBridge */
interface MyEventListener<T> {
    fun onDataChunk(chunk: List<T>)
    fun processComplete()
}