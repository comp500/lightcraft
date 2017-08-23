package link.infra.lightcraft;

import link.infra.lightcraft.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import org.apache.logging.log4j.Logger;

@Mod(modid = LightCraft.MODID, name = LightCraft.MODNAME, version = LightCraft.VERSION, useMetadata = true)
public class LightCraft {

    public static final String MODID = "lightcraft";
    public static final String MODNAME = "LightCraft";
    public static final String VERSION = "1.0.0.2";

    @SidedProxy(clientSide = "link.infra.lightcraft.proxy.ClientProxy", serverSide = "link.infra.lightcraft.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static LightCraft instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}