package me.KeepPvP;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class KeepPvP extends JavaPlugin implements Listener
{
    FileConfiguration config = this.getConfig();

    //
    @Override
    public void onDisable() 
    {
        getLogger().info("KeepPvP has been disabled");
    }

    //
    @Override
    public void onEnable() 
    {
        config.addDefault("swords", true);
        config.addDefault("armor", true);
        
        getLogger().info("KeepPvP has been enabled");
    }
    
  //  
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) 
  { 
    Player p = (Player) event.getEntity().getPlayer();  
    float exp = p.getExp();    
    boolean continue1 = false; 
    boolean diamond = false;
    boolean iron = false;
    boolean stone = false;
    boolean wood = false;
    boolean swords = config.getBoolean("swords");
    boolean armor = config.getBoolean("armor");
    ItemStack helm = p.getInventory().getHelmet().clone();
    ItemStack chest = p.getInventory().getChestplate().clone();
    ItemStack legs = p.getInventory().getLeggings().clone();
    ItemStack boots = p.getInventory().getBoots().clone();
    
    //
    if(p.getInventory().contains(Material.DIAMOND_SWORD)) 
    {
        diamond = true;
    }
    if(p.getInventory().contains(Material.IRON_SWORD)) 
    {
        iron = true;
    }
    if(p.getInventory().contains(Material.STONE_SWORD)) 
    {
        stone = true;
    }
    if(p.getInventory().contains(Material.WOOD_SWORD)) 
    {
        wood = true;
    }
    
    //
    if (event.getKeepInventory() == true)
    {
        getLogger().warning("Keep inventory is enabled so we are not running any script.");
        continue1 = true;
    }
    
    //
    if (event.getKeepInventory() && continue1 == false)
    {
       //
       if (!p.hasPermission("KeepPvP.keep") || !p.hasPermission("KeepPvp.*"))
        {
          p.sendMessage(ChatColor.RED + "You have died and lost your inventory!");
          p.resetMaxHealth();
          p.closeInventory();
          p.setExp(0);
        }
       //  
       if (p.hasPermission("KeepPvp.keep") || p.hasPermission("KeepPvp.*"))
       {
           if (armor == true && p.getHealth() >= 0.1)
           {
               p.getInventory().setBoots(helm);
               p.getInventory().setBoots(chest);
               p.getInventory().setBoots(legs);
               p.getInventory().setBoots(boots);
           }
           if (swords == true && p.getHealth() >= 0.1)
           {
               p.getInventory().clear();
               if (diamond == true)
               {
                  p.getInventory().clear();
                  p.getInventory().first(Material.DIAMOND_SWORD);
               }
               if (iron == true)
               {
                  p.getInventory().clear();
                  p.getInventory().first(Material.IRON_SWORD);
               }
               if (stone == true)
               {
                  p.getInventory().clear();
                  p.getInventory().first(Material.STONE_SWORD);
               }
               if (wood == true)
               {
                  p.getInventory().clear();
                  p.getInventory().first(Material.WOOD_SWORD);
               }
           }
       }
    }
 }
    
  // 
  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
  {
      Player p = (Player) sender;
      
      if (cmd.getName().equalsIgnoreCase("keeppvp"))
      {
        p.sendMessage(ChatColor.RED + "|------- KeepPvP -------|");
        p.sendMessage(ChatColor.YELLOW + "If you have the permission");
        p.sendMessage(ChatColor.YELLOW + "you will keep the things");
        p.sendMessage(ChatColor.YELLOW + "the server has allowed");
        p.sendMessage(ChatColor.YELLOW + "you get to keep them.");
        p.sendMessage(ChatColor.RED + "|-----------------------|");
      }  
  return true;        
  }
}

