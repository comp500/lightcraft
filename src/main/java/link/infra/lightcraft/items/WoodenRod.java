package link.infra.lightcraft.items;

import link.infra.lightcraft.LightCraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WoodenRod extends Item {

	public WoodenRod() {
        setRegistryName("woodenrod");
        setUnlocalizedName(LightCraft.MODID + ".woodenrod");
    }
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
}
