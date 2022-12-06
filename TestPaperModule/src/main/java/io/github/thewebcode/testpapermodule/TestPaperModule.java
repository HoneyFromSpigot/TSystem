package io.github.thewebcode.testpapermodule;

import io.github.thewebcode.tsystem.TBungeeSystem;
import io.github.thewebcode.tsystem.TPaperSystem;
import io.github.thewebcode.tsystem.server.IAction;
import io.github.thewebcode.tsystem.server.IGetAction;
import io.github.thewebcode.tsystem.server.SourceType;
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
        IAction action = new IAction("io.github.thewebcode.tsystem.TestClass", "getText", SourceType.NEW_INSTANCE);
        TPaperSystem.getInstance().getApi().sendToServer(action);
        return true;
    }
}
