package no.javatec.bridge

import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton
import java.util.concurrent.ConcurrentHashMap

/** class that produces 1..10 every 5 seconds */
@Singleton
class MyEventProcessor {

    /** map threadId -> MyEventListener */
    /** NOTE: Not for production use */
    private val listeners = ConcurrentHashMap<Long, MyEventListener<Int>>()

    fun register(
        threadId: Long,
        incomingListener: MyEventListener<Int>
    ) {
        listeners[threadId] = incomingListener
    }

    @Scheduled(cron = "*/5 * * * * *")
    fun scheduled() {
        listeners.values.forEach {
            it.onDataChunk((1..10).toList())
        }
    }
}
