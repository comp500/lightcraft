package link.infra.lightcraft.blocks.lens;

import java.util.Random;

import link.infra.lightcraft.LightCraft;
import link.infra.lightcraft.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Lens extends Block {

	public Lens() {
		super(Material.GLASS);
		setUnlocalizedName(LightCraft.MODID + ".lens");
		setRegistryName("lens");
		setCreativeTab(ModItems.tab);
        setHardness(1F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (worldIn.isRemote) {
			// first example:
			// spawn a vanilla particle of LAVA type (smoke from lava)
			//  The starting position is the [x,y,z] of the tip of the pole (i.e. at [0.5, 1.0, 0.5] relative to the block position)
			//  Set the initial velocity to zero.
			// When the particle is spawned, it will automatically add a random amount of velocity - see EntityLavaFX constructor and
			//   Particle constructor.  This can be a nuisance if you don't want your Particle to have a random starting velocity!  See
			//  second example below for more information.

			double xpos = pos.getX() + 0.5;
			double ypos = pos.getY() + 1.0;
			double zpos = pos.getZ() + 0.5;
			double velocityX = 0; // increase in x position every tick
			double velocityY = 0; // increase in y position every tick;
			double velocityZ = 0; // increase in z position every tick
			int [] extraInfo = new int[0];  // extra information if needed by the particle - in this case unused

			worldIn.spawnParticle(EnumParticleTypes.LAVA, xpos, ypos, zpos, velocityX, velocityY, velocityZ, extraInfo);
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
	@Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }
    
    @Override
    public BlockRenderLayer getBlockLayer() {
    	return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
