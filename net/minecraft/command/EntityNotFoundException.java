package net.minecraft.command;

public class EntityNotFoundException extends CommandException {
	public EntityNotFoundException() {
		this("commands.generic.entity.notFound", new Object[0]);
	}

	public EntityNotFoundException(String parString1, Object... parArrayOfObject) {
		super(parString1, parArrayOfObject);
	}
}
