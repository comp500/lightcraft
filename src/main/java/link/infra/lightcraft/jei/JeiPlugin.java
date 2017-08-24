package link.infra.lightcraft.jei;

import java.util.ArrayList;
import java.util.List;

import link.infra.lightcraft.ModBlocks;
import link.infra.lightcraft.ModItems;
import link.infra.lightcraft.blocks.polisher.PolisherContainer;
import link.infra.lightcraft.blocks.polisher.PolisherGui;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JeiPlugin implements IModPlugin {
	
	private List<PolisherRecipe> recipes = new ArrayList<>();

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		final IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		registry.addRecipeCategories(new PolisherRecipeCategory(guiHelper));
	}

	@Override
	public void register(IModRegistry registry) {
		recipes.add(new PolisherRecipe(new ItemStack(Items.STICK), new ItemStack(ModItems.woodenrod)));
		//recipes.add(new PolisherRecipe(new ItemStack(Items.QUARTZ), new ItemStack(ModItems.woodenrod)));
		recipes.add(new PolisherRecipe(new ItemStack(Items.GLASS_BOTTLE), new ItemStack(ModBlocks.lens)));
		recipes.add(new PolisherRecipe(new ItemStack(Blocks.GLASS), new ItemStack(ModBlocks.prism)));
		recipes.add(new PolisherRecipe(new ItemStack(Blocks.GLASS_PANE), new ItemStack(ModBlocks.mirror)));
		
		registry.addRecipes(recipes, "lightcraft.polisher");
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.polisher), "lightcraft.polisher");
		registry.addRecipeClickArea(PolisherGui.class, 79, 35, 24, 17, "lightcraft.polisher");
		
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
		recipeTransferRegistry.addRecipeTransferHandler(PolisherContainer.class, "lightcraft.polisher", 0, 1, 2, 36);
	}

}
