package net.minecraft.client.gui.inventory;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiScreenHorseInventory extends GuiContainer {
	private static final ResourceLocation horseGuiTextures = new ResourceLocation("textures/gui/container/horse.png");
	private IInventory playerInventory;
	private IInventory horseInventory;
	private EntityHorse horseEntity;
	private float mousePosx;
	private float mousePosY;

	public GuiScreenHorseInventory(IInventory playerInv, IInventory horseInv, EntityHorse horse) {
		super(new ContainerHorseInventory(playerInv, horseInv, horse, Minecraft.getMinecraft().thePlayer));
		this.playerInventory = playerInv;
		this.horseInventory = horseInv;
		this.horseEntity = horse;
		this.allowUserInput = false;
	}

	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		this.fontRendererObj.drawString(this.horseInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
		this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8,
				this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(horseGuiTextures);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		if (this.horseEntity.isChested()) {
			this.drawTexturedModalRect(i + 79, j + 17, 0, this.ySize, 90, 54);
		}

		if (this.horseEntity.canWearArmor()) {
			this.drawTexturedModalRect(i + 7, j + 35, 0, this.ySize + 54, 18, 18);
		}

		GuiInventory.drawEntityOnScreen(i + 51, j + 60, 17, (float) (i + 51) - this.mousePosx,
				(float) (j + 75 - 50) - this.mousePosY, this.horseEntity);
	}

	public void drawScreen(int i, int j, float f) {
		this.mousePosx = (float) i;
		this.mousePosY = (float) j;
		super.drawScreen(i, j, f);
	}
}
