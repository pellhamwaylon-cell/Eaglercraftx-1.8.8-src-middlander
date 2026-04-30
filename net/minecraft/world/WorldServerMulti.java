package net.minecraft.world;

import net.minecraft.server.MinecraftServer;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.border.IBorderListener;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.world.storage.ISaveHandler;

public class WorldServerMulti extends WorldServer {
	private WorldServer delegate;

	public WorldServerMulti(MinecraftServer server, ISaveHandler saveHandlerIn, int dimensionId, WorldServer delegate) {
		super(server, saveHandlerIn, new DerivedWorldInfo(delegate.getWorldInfo()), dimensionId);
		this.delegate = delegate;
		delegate.getWorldBorder().addListener(new IBorderListener() {
			public void onSizeChanged(WorldBorder var1, double d0) {
				WorldServerMulti.this.getWorldBorder().setTransition(d0);
			}

			public void onTransitionStarted(WorldBorder var1, double d0, double d1, long i) {
				WorldServerMulti.this.getWorldBorder().setTransition(d0, d1, i);
			}

			public void onCenterChanged(WorldBorder var1, double d0, double d1) {
				WorldServerMulti.this.getWorldBorder().setCenter(d0, d1);
			}

			public void onWarningTimeChanged(WorldBorder var1, int i) {
				WorldServerMulti.this.getWorldBorder().setWarningTime(i);
			}

			public void onWarningDistanceChanged(WorldBorder var1, int i) {
				WorldServerMulti.this.getWorldBorder().setWarningDistance(i);
			}

			public void onDamageAmountChanged(WorldBorder var1, double d0) {
				WorldServerMulti.this.getWorldBorder().setDamageAmount(d0);
			}

			public void onDamageBufferChanged(WorldBorder var1, double d0) {
				WorldServerMulti.this.getWorldBorder().setDamageBuffer(d0);
			}
		});
	}

	public World init() {
		this.mapStorage = this.delegate.getMapStorage();
		this.worldScoreboard = this.delegate.getScoreboard();
		String s = VillageCollection.fileNameForProvider(this.provider);
		VillageCollection villagecollection = (VillageCollection) this.mapStorage.loadData(VillageCollection.class, s);
		if (villagecollection == null) {
			this.villageCollectionObj = new VillageCollection(this);
			this.mapStorage.setData(s, this.villageCollectionObj);
		} else {
			this.villageCollectionObj = villagecollection;
			this.villageCollectionObj.setWorldsForAll(this);
		}

		return this;
	}
}
