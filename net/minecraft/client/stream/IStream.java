package net.minecraft.client.stream;

public interface IStream {
	void shutdownStream();

	void func_152935_j();

	void func_152922_k();

	boolean func_152936_l();

	boolean isReadyToBroadcast();

	boolean isBroadcasting();

	boolean isPaused();

	void requestCommercial();

	void pause();

	void unpause();

	void updateStreamVolume();

	void func_152930_t();

	void stopBroadcasting();

	void func_152909_x();

	boolean func_152908_z();

	int func_152920_A();

	boolean func_152927_B();

	String func_152921_C();

	void func_152917_b(String var1);

	boolean func_152928_D();

	boolean func_152913_F();

	void muteMicrophone(boolean var1);

	boolean func_152929_G();

	IStream.AuthFailureReason func_152918_H();

	public static enum AuthFailureReason {
		ERROR, INVALID_TOKEN;
	}
}
