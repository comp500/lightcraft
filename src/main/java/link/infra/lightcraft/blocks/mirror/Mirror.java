package link.infra.lightcraft.blocks.mirror;

import link.infra.lightcraft.LightCraft;
import link.infra.lightcraft.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Mirror extends Block {

	public Mirror() {
		super(Material.GLASS);
        setUnlocalizedName(LightCraft.MODID + ".mirror");
        setRegistryName("mirror");
        setCreativeTab(ModItems.tab);
        setHardness(1F);
	}
}
