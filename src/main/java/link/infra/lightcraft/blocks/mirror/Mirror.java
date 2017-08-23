package link.infra.lightcraft.blocks.mirror;

import link.infra.lightcraft.LightCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Mirror extends Block {

	public Mirror() {
		super(Material.GLASS);
        setUnlocalizedName(LightCraft.MODID + ".mirror");
        setRegistryName("mirror");
	}
}
