package io.github.thewebcode.testpapermodule;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.github.thewebcode.testpapermodule.test.TestClass;
import io.github.thewebcode.tsystem.TPaperSystem;
import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.reflections.MethodMap;
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
        Module m = new Module();
        TPaperSystem.getInstance().getApi().registerModule(m);
        getCommand("test").setExecutor(this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "tsystem:main");
        MethodMap map = TAPI.map(m, TestClass.class);
        TAPI.get().register(map);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("hello");
        sender.getServer().sendPluginMessage(this, "tsystem:main", output.toByteArray());
        return true;
    }
}
