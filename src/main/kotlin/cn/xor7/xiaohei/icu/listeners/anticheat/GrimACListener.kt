package cn.xor7.xiaohei.icu.listeners.anticheat

import cn.xor7.xiaohei.icu.antiCheatListener
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

class GrimACListener : Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onFlag(e: Event) {
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