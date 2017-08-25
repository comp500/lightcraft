package link.infra.lightcraft;

import link.infra.lightcraft.items.WoodenRod;
import link.infra.lightcraft.items.Wrench;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	public static final CreativeTabs tab = new CreativeTabs("LightCraft") {
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(ModBlocks.crystal);
	    }
	};

	@GameRegistry.ObjectHolder("lightcraft:woodenrod")
    public static WoodenRod woodenrod;
	
	@GameRegistry.ObjectHolder("lightcraft:wrench")
    public static Wrench wrench;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		woodenrod.initModel();
		wrench.initModel();
	}
}
