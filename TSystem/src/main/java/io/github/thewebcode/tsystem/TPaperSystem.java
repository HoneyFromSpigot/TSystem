package io.github.thewebcode.tsystem;

import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.pluginmessaging.MessageListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

public class TPaperSystem extends JavaPlugin {
    //The TPaperSystem also know as instance
    private static TPaperSystem instance;
    
    //The TAPI
    private TAPI api;

    /** 
     * On load
     */
    @Override
    public void onLoad() {
        //Initialize the instance
        instance = this;
        
        //Initialize the TAPI
        this.api = new TAPI(true);
    }

    /** 
     * On enable
     */
    @Override
    public void onEnable() {
        //Register the IncomingPluginChannel
        getServer().getMessenger().registerIncomingPluginChannel(this, "tsystem:main", new MessageListener());
    }

    /**
     * Gets the api
     *
     * @return the api
     */
    public TAPI getApi() {
        return api;
    }

    /** 
     * Gets the instance
     *
     * @return the instance
     */
    public static TPaperSystem getInstance() {
        return instance;
    }
}
