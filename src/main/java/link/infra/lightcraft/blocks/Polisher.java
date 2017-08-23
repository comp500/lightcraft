package link.infra.lightcraft.blocks;

import link.infra.lightcraft.LightCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Polisher extends Block {
    public Polisher() {
        super(Material.ROCK);
        setUnlocalizedName(LightCraft.MODID + ".polisher");     // Used for localization (en_US.lang)
        setRegistryName("polisher");        // The unique name (within your mod) that identifies this block
    }
}