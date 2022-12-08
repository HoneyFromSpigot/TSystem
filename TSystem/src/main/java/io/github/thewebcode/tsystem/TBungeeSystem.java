package io.github.thewebcode.tsystem;

import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.pluginmessaging.BungeeMessageListener;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public final class TBungeeSystem extends Plugin implements Listener {
    
    //The TBungeeSystem (also now as the instance)
    private static TBungeeSystem instance;
    
    //The TAPI
    private TAPI api;

    /** 
     * On load
     */
    @Override
    public void onLoad() {
        //Initialize the instance
        instance = this;
        
        //Initialize the api
        this.api = new TAPI(false);
    }

    /** 
     * On enable
     */
    @Override
    public void onEnable() {
        //Register the channel
        getProxy().registerChannel("tsystem:main");
        
        //Register the Listener
        getProxy().getPluginManager().registerListener(this, new BungeeMessageListener());
    }


    /** 
     * Gets the TAPI
     *
     * @return the TAPI
     */
    public TAPI getApi() {
        return api;
    }

    /** 
     * Gets the instance
     *
     * @return the instance
     */
    public static TBungeeSystem getInstance() {
        return instance;
    }
}
