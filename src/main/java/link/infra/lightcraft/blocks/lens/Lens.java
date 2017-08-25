package link.infra.lightcraft.blocks.lens;

import link.infra.lightcraft.LightCraft;
import link.infra.lightcraft.ModItems;
import link.infra.lightcraft.beam.BeamRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Lens extends Block implements ITileEntityProvider {

	public Lens() {
		super(Material.GLASS);
		setUnlocalizedName(LightCraft.MODID + ".lens");
		setRegistryName("lens");
		setCreativeTab(ModItems.tab);
        setHardness(1F);
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        ClientRegistry.bindTileEntitySpecialRenderer(LensTileEntity.class, new BeamRenderer());
    }
	
	@Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }
    
    @Override
    public BlockRenderLayer getBlockLayer() {
    	return BlockRenderLayer.CUTOUT_MIPPED;
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new LensTileEntity();
	}
}
