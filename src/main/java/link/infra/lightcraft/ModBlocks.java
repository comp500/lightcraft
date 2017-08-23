package link.infra.lightcraft;

import link.infra.lightcraft.blocks.polisher.Polisher;
import link.infra.lightcraft.blocks.prism.Prism;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @GameRegistry.ObjectHolder("lightcraft:polisher")
    public static Polisher polisher;
    
    @GameRegistry.ObjectHolder("lightcraft:prism")
    public static Prism prism;
    
    @SideOnly(Side.CLIENT)
    public static void initModels() {
        polisher.initModel();
        prism.initModel();
    }
}