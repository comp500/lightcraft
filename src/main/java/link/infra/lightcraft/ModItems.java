package link.infra.lightcraft;

import link.infra.lightcraft.items.WoodenRod;
import link.infra.lightcraft.items.Wrench;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

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
