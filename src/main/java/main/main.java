package main;

import commands.commands;
import core.logger;
import event.eventlistener;
import handler.timer;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

import static main.whes1015.VersionCode;

public class main extends JavaPlugin implements Listener {

    Integer configVer=1;

    public static Integer Support=22031;

    public static File folder;
    public static Integer PermittedViolationLevel;
    public static Integer PlayerBanTime;
    public static Boolean ShowPlayerInfo;

    @Override
    public void onEnable() {
        ShowPlayerInfo=getConfig().getBoolean("ShowPlayerInfo");
        PlayerBanTime=getConfig().getInt("PlayerBanTime");
        PermittedViolationLevel=getConfig().getInt("PermittedViolationLevel");
        saveDefaultConfig();
        if (getConfig().getInt("ConfigVersion") < configVer) {
            logger.log("WARN", "BanSystem_Update", "Please delete the old Config.yml file, the plugin will automatically generate a new Config.yml file!");
            Bukkit.getServer().shutdown();
        }else if(VersionCode>=Support) {
            folder = getDataFolder();
            timer.main();
            getServer().getPluginManager().registerEvents(new eventlistener(), this);
            Objects.requireNonNull(getCommand("bs")).setExecutor(new commands(this));
            logger.log("INFO", "BanSystem_onEnable", "Loading Success! - Designed by ExpTech.tw!");
        }else {
            logger.log("WARN","BanSystem_onEnable","Please update your Core version");
            Bukkit.getPluginManager().disablePlugins();
        }
    }
    @Override
    public void onDisable(){
        logger.log("INFO","BanSystem_onDisable","Closing! Version: "+getDescription().getVersion());
    }
}