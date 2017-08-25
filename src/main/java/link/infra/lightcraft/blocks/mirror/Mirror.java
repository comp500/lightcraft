package link.infra.lightcraft.blocks.mirror;

import link.infra.lightcraft.LightCraft;
import link.infra.lightcraft.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Mirror extends Block {

	public Mirror() {
		super(Material.GLASS);
        setUnlocalizedName(LightCraft.MODID + ".mirror");
        setRegistryName("mirror");
        setCreativeTab(ModItems.tab);
        setHardness(1F);
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
	@Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }
}
