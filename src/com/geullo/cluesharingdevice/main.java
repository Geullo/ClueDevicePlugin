package com.geullo.cluesharingdevice;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.*;
import java.util.logging.*;

import static com.geullo.cluesharingdevice.sendData.sendData;
import static com.geullo.cluesharingdevice.sendData.sendDataAll;

public class main extends JavaPlugin {
    public final static Logger log = Logger.getLogger("Minecraft");
    private Event events = new Event();
    private static main plugin;
    public static main getPlugin(){
        return plugin;
    }

    public void onEnable() {
        plugin = this;
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "cluesharingdevice");
        Bukkit.getServer().getPluginManager().registerEvents(events,this);
        log("Clue Sharing Device Plugin Enabled.", Level.INFO);
    }
    public void onDisable() {
        log("Clue Sharing Device Plugin Disabled.", Level.INFO);
    }
    public static void log(String string, Level level) {
        log.log(level, "[Clue Sharing Device Plugin]" + string);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ("csd".equals(cmd.getName())) {
            Player player = Bukkit.getPlayer(sender.getName());
            if (args.length == 0&&player!=null) return true;
            if ("open".equals(args[0]))
            {
                if (args.length == 1) return true;
                if ("door".equals(args[1])){
                    if (args.length==2) return true;
                    if (args.length==3) {
                        sendData("lockdor" + args[2], player, this);
                    }else if (args.length==4){
                        if ("all".equals(args[3])){
                            sendDataAll("lockdor" + args[2], this);
                            return true;
                        }
                        if (Bukkit.getPlayer(args[3])!=null) {
                            sendData("lockdor" + args[2], Bukkit.getPlayer(args[3]), this);
                        }
                    }
                    return true;
                }
                else if ("direction".equals(args[1])){
                    if (args.length==2) return true;
                    if (args.length==3) {
                        sendData("lockdir" + args[2], player, this);
                    }else if (args.length==4) {
                        if ("all".equals(args[3])) {
                            sendDataAll("lockdor" + args[2], this);
                            return true;
                        }
                        if (Bukkit.getPlayer(args[3])!=null) {
                            sendData("lockdir" + args[2], Bukkit.getPlayer(args[2]), this);
                        }
                    }
                    return true;
                }
                else if ("spin".equals(args[1])){
                    if (args.length==2) return true;
                    if (args.length==3) {
                        sendData("lockspi" + args[2], player, this);
                    }else if (args.length==4) {
                        if ("all".equals(args[3])) {
                            sendDataAll("lockspi" + args[2], this);
                            return true;
                        }
                        if ( Bukkit.getPlayer(args[3])!=null) {
                            sendData("lockspi" + args[2], Bukkit.getPlayer(args[3]), this);
                        }
                    }
                    return true;
                }
                return true;
            }
            else if ("unlock".equals(args[0]))
            {
                if (args.length == 1) return true;
                if ("door".equals(args[1])){
                    if (args.length==2) return true;
                    sendDataAll("unlkdor"+args[2],this);
                    return true;
                }
                else if ("direction".equals(args[1])){
                    if (args.length==2) return true;
                    sendDataAll("unlkdir"+args[2],this);
                    return true;
                }
                else if ("spin".equals(args[1])){
                    if (args.length==2) return true;
                    sendDataAll("unlkspi"+args[2],this);
                    return true;
                }
            }
            else if ("lock".equals(args[0]))
            {
                if (args.length == 1) return true;
                if ("door".equals(args[1])){
                    if (args.length<=3) return true;
                    sendData("stlkdor"+args[2]+(args[3].equalsIgnoreCase("true")?"T":"F"),player,this);
                    return true;
                }
                else if ("direction".equals(args[1])){
                    if (args.length<=3) return true;
                    sendData("stlkdir"+args[2]+(args[3].equalsIgnoreCase("true")?"T":"F"),player,this);
                    return true;
                }
                else if ("spin".equals(args[1])){
                    if (args.length<=3) return true;
                    sendData("stlkspi"+args[2]+(args[3].equalsIgnoreCase("true")?"T":"F"),player,this);
                    return true;
                }
            }
            else if ("alarm".equals(args[0]))
            {
                String alarm =  "",member = "d7,sa,Da,No,Hu,Ko,Ru,Se";
                if (args.length == 1){
                    alarm = player.getName().substring(0,2);
                    if (!member.contains(alarm)) alarm = "Un";
                    sendDataAll("cuealar"+alarm,this);
                    return true;
                }else if (args.length == 2) {
                    alarm = args[1].substring(0,2);
                    if (!member.contains(alarm)) alarm = "Un";
                    sendDataAll("cuealar"+alarm,this);
                } else {
                    sender.sendMessage(ChatColor.RED + "/csd alarm <player>");
                }
                return true;
            }
            else if ("set".equals(args[0])){
                if (args.length<=2) return true;
                if (args.length==3) {
                    sendData("cuevset" + (args[1].equals("true") ? "T" : "F") + player.getName().substring(0, 2) + "T" + args[2], player, this);
                }else if (args.length==4){
                    sendData("cuevset" + (args[1].equals("true") ? "T" : "F") + player.getName().substring(0, 2) + (args[3].equals("true") ? "T" : "F") + args[2], player, this);
                }else if (args.length==5){
                    if ("all".equals(args[4])){
                        sendDataAll("cuevset" + (args[1].equals("true") ? "T" : "F") + player.getName().substring(0, 2) + (args[3].equals("true") ? "T" : "F") + args[2],  this);
                    }else {
                        Player p = Bukkit.getPlayer(args[4]);
                        if (p != null) {
                            sendData("cuevset" + (args[1].equals("true") ? "T" : "F") + player.getName().substring(0, 2) + (args[3].equals("true") ? "T" : "F") + args[2], p, this);
                        }
                    }
                    return true;
                }
                return true;
            }else if ("reset".equals(args[0])){
                if (args.length==1){
                    sendDataAll("cuerset", this);
                    return true;
                }else if (args.length==2){
                    if ("all".equals(args[1])){
                        sendDataAll("allrset", this);
                        return true;
                    }else if ("pw".equals(args[1])){
                        sendDataAll("pwdrset",this);
                        return true;
                    }
                    return true;
                }
                return true;
            }else if ("playmusic".equals(args[0])){
                if (args.length==2) {sendDataAll("musipla" + args[1], this);return true;}
                Player target = Bukkit.getPlayer(args[2]);
                if (target!=null) {
                    sendData("musipla" + args[1], target, this);
                }else{
                    player.sendMessage(ChatColor.RED+"ERROR");
                }
                return true;
            }else if ("clean-chat".equals(args[0])){
                if (args.length==1) {sendData("cleanch",player,main.getPlugin());return true;}
                if (args[1].equals("all")) {sendDataAll("cleanch",main.getPlugin());return true;}
                Player target = Bukkit.getPlayer(args[1]);
                if (target!=null) {
                    sendData("cleanch", target, this);
                }else{
                    player.sendMessage(ChatColor.RED+"ERROR");
                }
                return true;
            }
            return true;
        }else if ("quiz".equals(cmd.getName())){
            Player player = Bukkit.getPlayer(sender.getName());
            if (args.length == 0&&player!=null) return true;
            if ("show".equals(args[0])) {
                if (args.length==1) return true;
                else if (args.length==2) sendData("showqui" + args[1], player, this);
                else if (args.length==3) {
                    Player a = Bukkit.getPlayer(args[2]);
                    if (a!=null) sendData("showqui" + args[1], a, this);
                    else sender.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"END :: "+ChatColor.WHITE+"해당 플레이어는 접속중인 플레이어가 아닙니다.");
                }
                return true;
            }else if ("reset".equals(args[0])){
                sendDataAll("pwdrset", this);
                return true;
            }
            return true;
        }
        return false;
    }
}
