package xyz.angeloanan.nocreepergrief;

import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoCreeperGrief extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
  }

  @EventHandler
  public void onCreeperIgnite(EntityExplodeEvent e) {
    if (e.getEntityType() != EntityType.CREEPER) return;

    Creeper creeper = (Creeper) e.getEntity();

    if (creeper.isPowered()) return;

    Location creeperLoc = creeper.getLocation();

    e.setCancelled(true);
    creeper.getWorld().createExplosion(creeperLoc, 3F, false, false);
    creeper.remove();
  }
}
