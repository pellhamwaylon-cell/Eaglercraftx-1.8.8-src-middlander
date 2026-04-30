package net.minecraft.client.resources;

import java.io.File;
import java.io.FileNotFoundException;

import net.lax1dude.eaglercraft.v1_8.HString;

public class ResourcePackFileNotFoundException extends FileNotFoundException {
	public ResourcePackFileNotFoundException(File parFile, String parString1) {
		super(HString.format("\'%s\' in ResourcePack \'%s\'", new Object[] { parString1, parFile }));
	}
}
