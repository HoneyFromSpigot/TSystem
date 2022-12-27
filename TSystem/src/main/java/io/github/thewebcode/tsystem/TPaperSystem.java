package io.github.thewebcode.tsystem;

import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.menu.InventoryService;
import org.bukkit.plugin.java.JavaPlugin;

public class TPaperSystem extends JavaPlugin {
    private static TPaperSystem instance;
    private TAPI api;
    private InventoryService inventoryService;

    @Override
    public void onLoad() {
        instance = this;
        this.api = new TAPI(true);
    }

    @Override
    public void onEnable() {
        this.inventoryService = new InventoryService();
    }

    public TAPI getApi() {
        return api;
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }

    public static TPaperSystem getInstance() {
        return instance;
    }
}
