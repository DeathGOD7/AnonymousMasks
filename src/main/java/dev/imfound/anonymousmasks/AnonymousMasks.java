package dev.imfound.anonymousmasks;

import dev.imfound.anonymousmasks.commands.CommandManager;
import dev.imfound.anonymousmasks.config.FileManager;
import dev.imfound.anonymousmasks.config.Files;
import dev.imfound.anonymousmasks.config.enums.Settings;
import dev.imfound.anonymousmasks.events.EventManager;
import dev.imfound.anonymousmasks.utils.DependsUtils;
import dev.imfound.anonymousmasks.utils.Metrics;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnonymousMasks extends JavaPlugin {

    @Getter
    static AnonymousMasks instance;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
       getLogger().info("\n  __   __ _   __   __ _  _  _  _  _   __   _  _  ____  _  _   __   ____  __ _  ____ \n" +
               " / _\\ (  ( \\ /  \\ (  ( \\( \\/ )( \\/ ) /  \\ / )( \\/ ___)( \\/ ) / _\\ / ___)(  / )/ ___)\n" +
               "/    \\/    /(  O )/    / )  / / \\/ \\(  O )) \\/ (\\___ \\/ \\/ \\/    \\\\___ \\ )  ( \\___ \\\n" +
               "\\_/\\_/\\_)__) \\__/ \\_)__)(__/  \\_)(_/ \\__/ \\____/(____/\\_)(_/\\_/\\_/(____/(__\\_)(____/\n");

        getLogger().info("--[AnonymousMasks v5.0]--");
        long startTime = System.currentTimeMillis();
        getLogger().info("Loading configs...");
        new Files();
        getLogger().info("Configs loaded!");
        if (DependsUtils.hasTab()) {
            if(Settings.METHOD.getString().equalsIgnoreCase("Native")) {
                getLogger().warning("I found that you have TAB but in the settings you have put the method in Native, i'm setting the method to TAB");
                getLogger().warning("TAB manage the scoreboards, so I can't manage scoreboards");
                Files.SETTINGS.getConfiguration().set(".method", "TAB");
                Files.SETTINGS.getConfiguration().save(Files.SETTINGS.getFile());
            }
            getLogger().info("TAB found!");
        } else {
            if (Settings.METHOD.getString().equalsIgnoreCase("TAB")) {
                for(int i = 10; i < 10; ++i) {
                    getLogger().severe("TAB NOT FOUND! ABORTING START!");
                }
                Bukkit.getPluginManager().getPlugin("AnonymousMasks").onDisable();
                return;
            }
        }
        getLogger().info("Registering commands...");
        new CommandManager(this);
        getLogger().info("Commands registered!");
        getLogger().info("Loading listeners...");
        new EventManager(this);
        getLogger().info("Listeners loaded!");
        Metrics m = new Metrics(this, 13312);
        long endTime = System.currentTimeMillis() - startTime;
        getLogger().info("Plugin loaded in " + endTime + "ms");
        getLogger().info("--[AnonymousMasks v5.0]--");
    }

    @Override
    public void onDisable() {

    }

}
