package link.infra.lightcraft.blocks.lens;

import link.infra.lightcraft.LightCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Lens extends Block {
	
	public Lens() {
		super(Material.GLASS);
        setUnlocalizedName(LightCraft.MODID + ".lens");
        setRegistryName("lens");
	}
}
