package net.minecraft.client.multiplayer;

public class ServerAddress {
	private final String ipAddress;
	private final int serverPort;

	public ServerAddress(String parString1, int parInt1) {
		this.ipAddress = parString1;
		this.serverPort = parInt1;
	}

	public String getIP() {
		return this.ipAddress;
	}

	public int getPort() {
		return this.serverPort;
	}

}
