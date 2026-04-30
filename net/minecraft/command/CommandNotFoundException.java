package net.minecraft.command;

public class CommandNotFoundException extends CommandException {

	public CommandNotFoundException() {
		this("commands.generic.notFound", new Object[0]);
	}

	public CommandNotFoundException(String parString1, Object... parArrayOfObject) {
		super(parString1, parArrayOfObject);
	}
}
