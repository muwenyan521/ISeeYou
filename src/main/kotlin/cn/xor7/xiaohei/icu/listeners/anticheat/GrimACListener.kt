package cn.xor7.xiaohei.icu.listeners.anticheat

import cn.xor7.xiaohei.icu.listeners.anticheat.antiCheatListener
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.Listener
import org.bukkit.plugin.EventExecutor

class GrimACListener : Listener, EventExecutor {
    
    override fun execute(listener: Listener, e: Event) {
        if (e.javaClass.name == "ac.grim.grimac.api.event.events.FlagEvent") {
            try {
                GrimACSupport.handle(e)
            } catch (ex: Throwable) {
                
            }
        }
    }
    private object GrimACSupport {
        fun handle(e: Event) {
            val event = e as? ac.grim.grimac.api.event.events.FlagEvent ?: return
            Bukkit.getPlayer(event.player.uniqueId)?.let { player ->
                antiCheatListener.onAntiCheatAction(player)
            }
        }
    }
}