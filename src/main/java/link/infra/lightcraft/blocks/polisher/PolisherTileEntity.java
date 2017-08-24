package link.infra.lightcraft.blocks.polisher;

import link.infra.lightcraft.blocks.lens.Lens;
import link.infra.lightcraft.blocks.mirror.Mirror;
import link.infra.lightcraft.blocks.prism.Prism;
import link.infra.lightcraft.items.WoodenRod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class PolisherTileEntity extends TileEntity implements ITickable {
    
    private int turnsPerInterval = 0;
    private int delayCounter = 10;
    private int turns = 0;

    private ItemStackHandler inputStackHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            PolisherTileEntity.this.markDirty();
        }
        
        private boolean isPolishable(ItemStack stack) {
        	if (stack.isItemEqual(new ItemStack(Items.STICK))) {
        		return true; // makes Polished Stick
        	}
        	if (stack.isItemEqual(new ItemStack(Items.QUARTZ))) {
        		return true; // makes White Crystal
        	}
        	if (stack.isItemEqual(new ItemStack(Items.GLASS_BOTTLE))) {
        		return true; // makes Lens
        	}
        	if (stack.isItemEqual(new ItemStack(Blocks.GLASS))) {
        		return true; // makes Prism
        	}
        	if (stack.isItemEqual(new ItemStack(Blocks.GLASS_PANE))) {
        		return true; // makes Mirror
        	}
        	return false;
        }
        
        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			if (isPolishable(stack)) {
				return super.insertItem(slot, stack, simulate);
			} else {
				return stack;
			}
        }
    };
    
    private class OutHandler extends ItemStackHandler {
    	public OutHandler(int i) {
    		super(i);
    	}
    	
    	
    	protected ItemStack internalInsertItem(int slot, ItemStack stack, boolean simulate) {
    		return super.insertItem(slot, stack, simulate);
    	}

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			return stack;
        }
    }
    
    private OutHandler outputStackHandler = new OutHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            PolisherTileEntity.this.markDirty();
        }
    };

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("input")) {
            inputStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("input"));
        }
        if (compound.hasKey("output")) {
            outputStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("output"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("input", inputStackHandler.serializeNBT());
        compound.setTag("output", outputStackHandler.serializeNBT());
        return compound;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
        	if (facing == EnumFacing.WEST || facing == EnumFacing.DOWN || facing == EnumFacing.SOUTH) {
        		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(outputStackHandler);
        	} else if (facing == EnumFacing.EAST || facing == EnumFacing.UP || facing == EnumFacing.NORTH) {
        		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inputStackHandler);
        	} else {
        		CombinedInvWrapper wrapper = new CombinedInvWrapper(inputStackHandler, outputStackHandler);
        		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(wrapper);
        	}
        }
        return super.getCapability(capability, facing);
    }
    
    @Override
    public void update() {
    	if (world.isRemote) {
            return;
        }
    	delayCounter--; // Only activate every 10 ticks
    	if (delayCounter <= 0) {
    		delayCounter = 10;
    		if (turnsPerInterval > 0) {
    			turnsPerInterval = 0; // Only allow one turn per 10 ticks
    			System.out.println("Turned!");
    			turns++;
    			if (turns >= 5) {
    				ItemStack item = inputStackHandler.extractItem(0, 1, false);
    				if (item != ItemStack.EMPTY) {
    					turns = 0;
    					if (item.isItemEqual(new ItemStack(Items.STICK))) {
    						outputStackHandler.internalInsertItem(0, new ItemStack(new WoodenRod()), false);
    					} else if (item.isItemEqual(new ItemStack(Items.QUARTZ))) {
    						// crystal not implemented yet
    					} else if (item.isItemEqual(new ItemStack(Items.GLASS_BOTTLE))) {
    						outputStackHandler.internalInsertItem(0, new ItemStack(new Lens()), false);
    					} else if (item.isItemEqual(new ItemStack(Blocks.GLASS))) {
    						outputStackHandler.internalInsertItem(0, new ItemStack(new Prism()), false);
    					} else if (item.isItemEqual(new ItemStack(Blocks.GLASS_PANE))) {
    						outputStackHandler.internalInsertItem(0, new ItemStack(new Mirror()), false);
    					}
    				}
    			}
    		}
    	}
    }
    
    public void addTurn() {
    	if (world.isRemote) {
            return;
        }
    	turnsPerInterval++;
    }

}
