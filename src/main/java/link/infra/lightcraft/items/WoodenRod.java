package link.infra.lightcraft.items;

import link.infra.lightcraft.LightCraft;
import link.infra.lightcraft.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WoodenRod extends Item {

	public WoodenRod() {
        setRegistryName("woodenrod");
        setUnlocalizedName(LightCraft.MODID + ".woodenrod");
        setCreativeTab(ModItems.tab);
    }
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
}
