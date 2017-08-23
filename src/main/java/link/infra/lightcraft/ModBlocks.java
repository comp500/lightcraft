package link.infra.lightcraft;

import link.infra.lightcraft.blocks.polisher.Polisher;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @GameRegistry.ObjectHolder("lightcraft:polisher")
    public static Polisher polisher;
    
    @SideOnly(Side.CLIENT)
    public static void initModels() {
        polisher.initModel();
    }
}