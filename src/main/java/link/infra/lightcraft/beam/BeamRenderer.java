package link.infra.lightcraft.beam;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BeamRenderer extends TileEntitySpecialRenderer<BeamSenderTileEntity> {
	
	public static final int MAX_LIGHT_X = 0xF000F0;
	public static final int MAX_LIGHT_Y = 0xF000F0;

	@Override
	public void render(BeamSenderTileEntity tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		if (tile instanceof BeamSenderTileEntity) {
			BeamSenderTileEntity sender = (BeamSenderTileEntity)tile;
			
			if (sender.hasTarget()) {
				BlockPos pos = sender.getPos();
				BlockPos targetPos = sender.getTarget();
				
				double posX = pos.getX();
				double posY = pos.getY();
				double posZ = pos.getZ();
				
				double targetX = targetPos.getX();
				double targetY = targetPos.getY();
				double targetZ = targetPos.getZ();
				
				renderLaser(posX+0.5, posY+0.5, posZ+0.5, targetX+0.5, targetY+0.5, targetZ+0.5, 120, 0.1F, 0.05, new float[]{255F/255F, 249F/255F, 150F/255F});
			}
		}
	}
	
	/*
	 * renderLaser function from Actually Additions
	 * don't DMCA me please if you don't want me to use your function
	 * just message me and I'll write my own
	 */

	//Thanks to feldim2425 for this.
	//I can't do rendering code. Ever.
	@SideOnly(Side.CLIENT)
	public static void renderLaser(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ, double rotationTime, float alpha, double beamWidth, float[] color){
		Tessellator tessy = Tessellator.getInstance();
		BufferBuilder render = tessy.getBuffer();
		World world = Minecraft.getMinecraft().world;

		float r = color[0];
		float g = color[1];
		float b = color[2];

		Vec3d vec1 = new Vec3d(firstX, firstY, firstZ);
		Vec3d vec2 = new Vec3d(secondX, secondY, secondZ);
		Vec3d combinedVec = vec2.subtract(vec1);

		double rot = rotationTime > 0 ? (360D*((world.getTotalWorldTime()%rotationTime)/rotationTime)) : 0;
		double pitch = Math.atan2(combinedVec.y, Math.sqrt(combinedVec.x*combinedVec.x+combinedVec.z*combinedVec.z));
		double yaw = Math.atan2(-combinedVec.z, combinedVec.x);

		double length = combinedVec.lengthVector();

		GlStateManager.pushMatrix();

		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		int func = GL11.glGetInteger(GL11.GL_ALPHA_TEST_FUNC);
		float ref = GL11.glGetFloat(GL11.GL_ALPHA_TEST_REF);
		GlStateManager.alphaFunc(GL11.GL_ALWAYS, 0);
		GlStateManager.translate(firstX-TileEntityRendererDispatcher.staticPlayerX, firstY-TileEntityRendererDispatcher.staticPlayerY, firstZ-TileEntityRendererDispatcher.staticPlayerZ);
		GlStateManager.rotate((float)(180*yaw/Math.PI), 0, 1, 0);
		GlStateManager.rotate((float)(180*pitch/Math.PI), 0, 0, 1);
		GlStateManager.rotate((float)rot, 1, 0, 0);

		/*if(r != r2 || g != g2 || b != b2){
	            render.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
	            Minecraft.getMinecraft().renderEngine.bindTexture(ClientUtil.LIGHT_BEAM_GRADIENT);
	            render.pos(length, -beamWidth, beamWidth).tex(0, 0).color(r, g, b, alpha).endVertex();
	            render.pos(length, beamWidth, beamWidth).tex(0, 1).color(r, g, b, alpha).endVertex();
	            render.pos(0, beamWidth, beamWidth).tex(1, 1).color(r, g, b, alpha).endVertex();
	            render.pos(0, -beamWidth, beamWidth).tex(1, 0).color(r, g, b, alpha).endVertex();
	            render.pos(length, -beamWidth, beamWidth).tex(1, 0).color(r2, g2, b2, alpha).endVertex();
	            render.pos(length, beamWidth, beamWidth).tex(1, 1).color(r2, g2, b2, alpha).endVertex();
	            render.pos(0, beamWidth, beamWidth).tex(0, 1).color(r2, g2, b2, alpha).endVertex();
	            render.pos(0, -beamWidth, beamWidth).tex(0, 0).color(r2, g2, b2, alpha).endVertex();
	            render.pos(length, beamWidth, -beamWidth).tex(0, 0).color(r, g, b, alpha).endVertex();
	            render.pos(length, -beamWidth, -beamWidth).tex(0, 1).color(r, g, b, alpha).endVertex();
	            render.pos(0, -beamWidth, -beamWidth).tex(1, 1).color(r, g, b, alpha).endVertex();
	            render.pos(0, beamWidth, -beamWidth).tex(1, 0).color(r, g, b, alpha).endVertex();
	            render.pos(length, beamWidth, -beamWidth).tex(1, 0).color(r2, g2, b2, alpha).endVertex();
	            render.pos(length, -beamWidth, -beamWidth).tex(1, 1).color(r2, g2, b2, alpha).endVertex();
	            render.pos(0, -beamWidth, -beamWidth).tex(0, 1).color(r2, g2, b2, alpha).endVertex();
	            render.pos(0, beamWidth, -beamWidth).tex(0, 0).color(r2, g2, b2, alpha).endVertex();
	            render.pos(length, beamWidth, beamWidth).tex(0, 0).color(r, g, b, alpha).endVertex();
	            render.pos(length, beamWidth, -beamWidth).tex(0, 1).color(r, g, b, alpha).endVertex();
	            render.pos(0, beamWidth, -beamWidth).tex(1, 1).color(r, g, b, alpha).endVertex();
	            render.pos(0, beamWidth, beamWidth).tex(1, 0).color(r, g, b, alpha).endVertex();
	            render.pos(length, beamWidth, beamWidth).tex(1, 0).color(r2, g2, b2, alpha).endVertex();
	            render.pos(length, beamWidth, -beamWidth).tex(1, 1).color(r2, g2, b2, alpha).endVertex();
	            render.pos(0, beamWidth, -beamWidth).tex(0, 1).color(r2, g2, b2, alpha).endVertex();
	            render.pos(0, beamWidth, beamWidth).tex(0, 0).color(r2, g2, b2, alpha).endVertex();
	            render.pos(length, -beamWidth, -beamWidth).tex(0, 0).color(r, g, b, alpha).endVertex();
	            render.pos(length, -beamWidth, beamWidth).tex(0, 1).color(r, g, b, alpha).endVertex();
	            render.pos(0, -beamWidth, beamWidth).tex(1, 1).color(r, g, b, alpha).endVertex();
	            render.pos(0, -beamWidth, -beamWidth).tex(1, 0).color(r, g, b, alpha).endVertex();
	            render.pos(length, -beamWidth, -beamWidth).tex(1, 0).color(r2, g2, b2, alpha).endVertex();
	            render.pos(length, -beamWidth, beamWidth).tex(1, 1).color(r2, g2, b2, alpha).endVertex();
	            render.pos(0, -beamWidth, beamWidth).tex(0, 1).color(r2, g2, b2, alpha).endVertex();
	            render.pos(0, -beamWidth, -beamWidth).tex(0, 0).color(r2, g2, b2, alpha).endVertex();
	            tessy.draw();
	        }
	        else{*/
		GlStateManager.disableTexture2D();
		render.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
		for(double i = 0; i < 4; i++){
			double width = beamWidth*(i/4.0);
			render.pos(length, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(0, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(0, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(length, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();

			render.pos(length, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(0, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(0, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(length, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();

			render.pos(length, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(0, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(0, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(length, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();

			render.pos(length, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(0, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(0, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
			render.pos(length, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
		}
		tessy.draw();

		GlStateManager.enableTexture2D();
		//}

		GlStateManager.alphaFunc(func, ref);
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	public boolean isGlobalRenderer(BeamSenderTileEntity tile){
		return true;
	}
}
