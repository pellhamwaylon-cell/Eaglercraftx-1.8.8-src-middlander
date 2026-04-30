package net.minecraft.world;

public enum EnumDifficulty {
	PEACEFUL(0, "options.difficulty.peaceful"), EASY(1, "options.difficulty.easy"),
	NORMAL(2, "options.difficulty.normal"), HARD(3, "options.difficulty.hard");

	private static final EnumDifficulty[] difficultyEnums = new EnumDifficulty[4];
	private final int difficultyId;
	private final String difficultyResourceKey;

	private EnumDifficulty(int difficultyIdIn, String difficultyResourceKeyIn) {
		this.difficultyId = difficultyIdIn;
		this.difficultyResourceKey = difficultyResourceKeyIn;
	}

	public int getDifficultyId() {
		return this.difficultyId;
	}

	public static EnumDifficulty getDifficultyEnum(int parInt1) {
		return difficultyEnums[parInt1 % difficultyEnums.length];
	}

	public String getDifficultyResourceKey() {
		return this.difficultyResourceKey;
	}

	static {
		EnumDifficulty[] types = values();
		for (int i = 0; i < types.length; ++i) {
			difficultyEnums[types[i].difficultyId] = types[i];
		}

	}
}
