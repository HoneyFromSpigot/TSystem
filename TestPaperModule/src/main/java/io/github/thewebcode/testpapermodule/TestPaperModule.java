package io.github.thewebcode.testpapermodule;

import io.github.thewebcode.tsystem.TPaperSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class TestPaperModule extends JavaPlugin implements CommandExecutor {
    private static TestPaperModule instance;

    @Override
    public void onEnable() {
        instance = this;
        TPaperSystem.getInstance().getApi().registerModule(new Module());
        getCommand("test").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        System.out.println("Trying to send to server!");

        TPaperSystem.getInstance().getApi().sendToServer("Hello from Paper!");
        return true;
    }
}
