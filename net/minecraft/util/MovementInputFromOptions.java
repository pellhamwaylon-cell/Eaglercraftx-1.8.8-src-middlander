package net.minecraft.util;

import net.lax1dude.eaglercraft.v1_8.touch_gui.EnumTouchControl;
import net.lax1dude.eaglercraft.v1_8.touch_gui.TouchControls;
import net.minecraft.client.settings.GameSettings;

public class MovementInputFromOptions extends MovementInput {
	private final GameSettings gameSettings;

	public MovementInputFromOptions(GameSettings gameSettingsIn) {
		this.gameSettings = gameSettingsIn;
	}

	public void updatePlayerMoveState() {
		this.moveStrafe = 0.0F;
		this.moveForward = 0.0F;
		if (this.gameSettings.keyBindForward.isKeyDown() || TouchControls.isPressed(EnumTouchControl.DPAD_UP)
				|| TouchControls.isPressed(EnumTouchControl.DPAD_UP_LEFT)
				|| TouchControls.isPressed(EnumTouchControl.DPAD_UP_RIGHT)) {
			++this.moveForward;
		}

		if (this.gameSettings.keyBindBack.isKeyDown() || TouchControls.isPressed(EnumTouchControl.DPAD_DOWN)) {
			--this.moveForward;
		}

		if (this.gameSettings.keyBindLeft.isKeyDown() || TouchControls.isPressed(EnumTouchControl.DPAD_LEFT)
				|| TouchControls.isPressed(EnumTouchControl.DPAD_UP_LEFT)) {
			++this.moveStrafe;
		}

		if (this.gameSettings.keyBindRight.isKeyDown() || TouchControls.isPressed(EnumTouchControl.DPAD_RIGHT)
				|| TouchControls.isPressed(EnumTouchControl.DPAD_UP_RIGHT)) {
			--this.moveStrafe;
		}

		this.jump = this.gameSettings.keyBindJump.isKeyDown() || TouchControls.isPressed(EnumTouchControl.JUMP)
				|| TouchControls.isPressed(EnumTouchControl.FLY_UP);
		this.sneak = this.gameSettings.keyBindSneak.isKeyDown() || TouchControls.getSneakToggled()
				|| TouchControls.isPressed(EnumTouchControl.FLY_DOWN);
		if (this.sneak) {
			this.moveStrafe = (float) ((double) this.moveStrafe * 0.3D);
			this.moveForward = (float) ((double) this.moveForward * 0.3D);
		}

	}
}
