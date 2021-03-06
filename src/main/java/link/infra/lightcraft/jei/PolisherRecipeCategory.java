package link.infra.lightcraft.jei;

import link.infra.lightcraft.LightCraft;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PolisherRecipeCategory implements IRecipeCategory<IRecipeWrapper> {
	
	private final IDrawable background;

	public PolisherRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation(LightCraft.MODID, "textures/gui/polisher.png");
		background = guiHelper.createDrawable(location, 55, 30, 82, 26);
	}
	
	@Override
	public String getUid() {
		return "lightcraft.polisher";
	}

	@Override
	public String getTitle() {
		return "Polisher"; // TODO: make this langified
	}

	@Override
	public String getModName() {
		return "LightCraft";
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 0, 4);
		guiItemStacks.init(1, false, 60, 4);
		guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
		guiItemStacks.set(1, ingredients.getOutputs(ItemStack.class).get(0));
	}

}
