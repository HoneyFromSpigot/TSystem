package io.github.thewebcode.testpapermodule;

import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.ServerRequest;
import io.github.thewebcode.tsystem.server.ServerResponse;
import net.kyori.adventure.text.Component;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;


public final class TestPaperModule extends JavaPlugin implements CommandExecutor {
    private Module module;

    @Override
    public void onEnable() {
        this.module = new Module();
        TAPI.get().registerModule(module);
        getCommand("test").setExecutor(this);
    }

    @Override
    public void onDisable() {
        TAPI.get().unregisterModule(module);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return true;
    }
}
