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

import java.util.Objects;

@SuppressWarnings("unused")
public final class TestPaperModule extends JavaPlugin implements CommandExecutor {

    //Defined the TestPaperModule
    private static TestPaperModule instance;

    /**
     * The onEnable Function
     */
    @Override
    public void onEnable() {

        //Initialize the instance
        instance = this;

        //Initialize the Module
        Module module = new Module();

        //Register the module
        TPaperSystem.getInstance().getApi().registerModule(module);

        //Register the command
        Objects.requireNonNull(getCommand("test")).setExecutor(this);

        //Register the OutgoingPluginChannel
        getServer().getMessenger().registerOutgoingPluginChannel(this, "tsystem:main");

        //Initialize the MethodMap
        MethodMap methodMap = TAPI.map(module, TestClass.class);

        //Register the methodMap
        TAPI.get().register(methodMap);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        //Initialize the ByteArrayDataOutput
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        
        //Write the UTF
        output.writeUTF("hello");
        
        //Send a Message
        sender.getServer().sendPluginMessage(this, "tsystem:main", output.toByteArray());
        return true;
    }

    /**
     * Returns the {@link TestPaperModule} instance
     * @return Returns the instance
     */
    public static TestPaperModule getInstance(){
        return instance;
    }
}
