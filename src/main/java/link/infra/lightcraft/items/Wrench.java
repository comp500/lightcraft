package link.infra.lightcraft.items;

import link.infra.lightcraft.LightCraft;
import link.infra.lightcraft.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Wrench extends Item {
	
	public Wrench() {
		setRegistryName("wrench");
	    setUnlocalizedName(LightCraft.MODID + ".wrench");
	    setCreativeTab(ModItems.tab);
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
	public int getItemStackLimit(ItemStack i) {
		return 1;
	}
}
