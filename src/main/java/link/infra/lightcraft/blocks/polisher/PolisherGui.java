package link.infra.lightcraft.blocks.polisher;

import java.awt.Color;

import link.infra.lightcraft.LightCraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class PolisherGui extends GuiContainer {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;

    private static final ResourceLocation background = new ResourceLocation(LightCraft.MODID, "textures/gui/polisher.png");
    
    private PolisherTileEntity te;

    public PolisherGui(PolisherTileEntity tileEntity, PolisherContainer container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
        this.te = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        int turns = te.getTurns();
        if (turns > 0) {
        	int arrowWidth = (int) ((24F / 5F) * turns);
        	drawTexturedModalRect(guiLeft + 79, guiTop + 35, 176, 0, arrowWidth, 17);
        }
        String text = "Polisher"; // TODO: Change to something better
        int textX = ((xSize/2) - (fontRenderer.getStringWidth(text)/2));
        fontRenderer.drawString(text, guiLeft + textX, guiTop + 6, Color.darkGray.getRGB());
        fontRenderer.drawString("Inventory", guiLeft + 8, guiTop + 72, Color.darkGray.getRGB());
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        super.renderHoveredToolTip(mouseX, mouseY);
    }
}