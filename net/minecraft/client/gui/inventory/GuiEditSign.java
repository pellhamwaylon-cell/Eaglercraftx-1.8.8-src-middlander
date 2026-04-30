package net.minecraft.client.gui.inventory;

import net.lax1dude.eaglercraft.v1_8.Display;
import net.lax1dude.eaglercraft.v1_8.Keyboard;
import net.lax1dude.eaglercraft.v1_8.PointerInputAbstraction;
import net.lax1dude.eaglercraft.v1_8.minecraft.GuiScreenVisualViewport;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ChatComponentText;

public class GuiEditSign extends GuiScreenVisualViewport {
	private TileEntitySign tileSign;
	private int updateCounter;
	private int editLine;
	private GuiButton doneBtn;

	public GuiEditSign(TileEntitySign teSign) {
		this.tileSign = teSign;
	}

	public void initGui() {
		this.buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120,
				I18n.format("gui.done", new Object[0])));
		this.tileSign.setEditable(false);
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		NetHandlerPlayClient nethandlerplayclient = this.mc.getNetHandler();
		if (nethandlerplayclient != null) {
			nethandlerplayclient
					.addToSendQueue(new C12PacketUpdateSign(this.tileSign.getPos(), this.tileSign.signText));
		}

		this.tileSign.setEditable(true);
	}

	public void updateScreen0() {
		++this.updateCounter;
	}

	protected void actionPerformed(GuiButton parGuiButton) {
		if (parGuiButton.enabled) {
			if (parGuiButton.id == 0) {
				this.tileSign.markDirty();
				this.mc.displayGuiScreen((GuiScreen) null);
			}

		}
	}

	protected void keyTyped(char parChar1, int parInt1) {
		if (parInt1 == 200) {
			this.editLine = this.editLine - 1 & 3;
		}

		if (parInt1 == 208 || parInt1 == 28 || parInt1 == 156) {
			this.editLine = this.editLine + 1 & 3;
		}

		String s = this.tileSign.signText[this.editLine].getUnformattedText();
		if (parInt1 == 14 && s.length() > 0) {
			s = s.substring(0, s.length() - 1);
		}

		if (ChatAllowedCharacters.isAllowedCharacter(parChar1)
				&& this.fontRendererObj.getStringWidth(s + parChar1) <= 90) {
			s = s + parChar1;
		}

		this.tileSign.signText[this.editLine] = new ChatComponentText(s);
		if (parInt1 == 1) {
			this.actionPerformed(this.doneBtn);
		}

	}

	public void drawScreen0(int i, int j, float f) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, I18n.format("sign.edit", new Object[0]), this.width / 2, 40,
				16777215);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) (this.width / 2), 0.0F, 50.0F);
		float f1 = 93.75F;
		GlStateManager.scale(-f1, -f1, -f1);
		GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
		Block block = this.tileSign.getBlockType();
		if (block == Blocks.standing_sign) {
			float f2 = (float) (this.tileSign.getBlockMetadata() * 360) / 16.0F;
			GlStateManager.rotate(f2, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(0.0F, -1.0625F, 0.0F);
		} else {
			int k = this.tileSign.getBlockMetadata();
			float f3 = 0.0F;
			if (k == 2) {
				f3 = 180.0F;
			}

			if (k == 4) {
				f3 = 90.0F;
			}

			if (k == 5) {
				f3 = -90.0F;
			}

			GlStateManager.rotate(f3, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(0.0F, -1.0625F, 0.0F);
		}

		if (this.updateCounter / 6 % 2 == 0) {
			this.tileSign.lineBeingEdited = this.editLine;
		}

		try {
			TileEntitySignRenderer.disableProfanityFilter = true;
			TileEntityRendererDispatcher.instance.renderTileEntityAt(this.tileSign, -0.5D,
					(PointerInputAbstraction.isTouchMode() && (Display.getVisualViewportH() / mc.displayHeight) < 0.75f)
							? -0.25D
							: -0.75D,
					-0.5D, 0.0F);
		} finally {
			TileEntitySignRenderer.disableProfanityFilter = false;
		}
		this.tileSign.lineBeingEdited = -1;
		GlStateManager.popMatrix();
		super.drawScreen0(i, j, f);
	}

	public boolean blockPTTKey() {
		return true;
	}

}
