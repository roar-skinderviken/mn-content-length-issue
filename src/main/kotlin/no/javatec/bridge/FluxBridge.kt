package no.javatec.bridge

import io.micronaut.context.annotation.Prototype
import jakarta.annotation.PostConstruct
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

@Prototype
/** class is stateful, hence create a new instance for every access */
class FluxBridge(private val myEventProcessor: MyEventProcessor) {

    private lateinit var bridge: Flux<Int>

    @PostConstruct
    fun init() {
        /** initialize the bridge that is used by method produceData */
        bridge = Flux.create { sink: FluxSink<Int> ->
            myEventProcessor.register(
                Thread.currentThread().id,
                /** anonymous implementation of MyEventListener */
                object : MyEventListener<Int> {
                    override fun onDataChunk(chunk: List<Int>) {
                        for (s in chunk) {
                            sink.next(s)
                        }
                    }

                    /** not used in this example */
                    override fun processComplete() {
                        sink.complete()
                    }
                })
        }
    }

    /** just return the bridge which is a Flux<Int> */
    fun getFlux(): Flux<Int> = bridge
}