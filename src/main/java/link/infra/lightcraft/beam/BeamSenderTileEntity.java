package link.infra.lightcraft.beam;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class BeamSenderTileEntity extends TileEntity {
	
	private BlockPos target;

	public BlockPos getTarget() {
		return target;
	}

	public void setTarget(BlockPos target) {
		this.target = target;
	}
	
	public boolean hasTarget() {
		return target != null;
	}

}
