package link.infra.lightcraft.proxy;

import link.infra.lightcraft.LightCraft;
import link.infra.lightcraft.ModBlocks;
import link.infra.lightcraft.blocks.crystal.Crystal;
import link.infra.lightcraft.blocks.lens.Lens;
import link.infra.lightcraft.blocks.mirror.Mirror;
import link.infra.lightcraft.blocks.polisher.Polisher;
import link.infra.lightcraft.blocks.polisher.PolisherTileEntity;
import link.infra.lightcraft.blocks.prism.Prism;
import link.infra.lightcraft.items.WoodenRod;
import link.infra.lightcraft.items.Wrench;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(LightCraft.instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	event.getRegistry().register(new Polisher());
    	GameRegistry.registerTileEntity(PolisherTileEntity.class, LightCraft.MODID + "_testcontainerblock");
    	event.getRegistry().register(new Prism());
    	event.getRegistry().register(new Lens());
    	event.getRegistry().register(new Mirror());
    	event.getRegistry().register(new Crystal());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	event.getRegistry().register(new ItemBlock(ModBlocks.polisher).setRegistryName(ModBlocks.polisher.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(ModBlocks.prism).setRegistryName(ModBlocks.prism.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(ModBlocks.lens).setRegistryName(ModBlocks.lens.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(ModBlocks.mirror).setRegistryName(ModBlocks.mirror.getRegistryName()));
    	event.getRegistry().register(new WoodenRod());
    	event.getRegistry().register(new Wrench());
    	event.getRegistry().register(new ItemBlock(ModBlocks.crystal).setRegistryName(ModBlocks.crystal.getRegistryName()));
    }
}