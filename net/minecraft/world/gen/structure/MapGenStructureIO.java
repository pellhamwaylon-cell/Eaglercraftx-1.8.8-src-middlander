package net.minecraft.world.gen.structure;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;

public class MapGenStructureIO {
	private static final Logger logger = LogManager.getLogger();
	private static Map<String, Class<? extends StructureStart>> startNameToClassMap = Maps.newHashMap();
	private static Map<String, Supplier<? extends StructureStart>> startNameToSupplierMap = Maps.newHashMap();
	private static Map<Class<? extends StructureStart>, String> startClassToNameMap = Maps.newHashMap();
	private static Map<String, Class<? extends StructureComponent>> componentNameToClassMap = Maps.newHashMap();
	private static Map<String, Supplier<? extends StructureComponent>> componentNameToSupplierMap = Maps.newHashMap();
	private static Map<Class<? extends StructureComponent>, String> componentClassToNameMap = Maps.newHashMap();

	private static void registerStructure(Class<? extends StructureStart> startClass,
			Supplier<? extends StructureStart> startSupplier, String structureName) {
		startNameToClassMap.put(structureName, startClass);
		startNameToSupplierMap.put(structureName, startSupplier);
		startClassToNameMap.put(startClass, structureName);
	}

	static void registerStructureComponent(Class<? extends StructureComponent> componentClass,
			Supplier<? extends StructureComponent> startSupplier, String componentName) {
		componentNameToClassMap.put(componentName, componentClass);
		componentNameToSupplierMap.put(componentName, startSupplier);
		componentClassToNameMap.put(componentClass, componentName);
	}

	public static String getStructureStartName(StructureStart start) {
		return (String) startClassToNameMap.get(start.getClass());
	}

	public static String getStructureComponentName(StructureComponent component) {
		return (String) componentClassToNameMap.get(component.getClass());
	}

	public static StructureStart getStructureStart(NBTTagCompound tagCompound, World worldIn) {
		StructureStart structurestart = null;

		try {
			Supplier<? extends StructureStart> oclass = startNameToSupplierMap.get(tagCompound.getString("id"));
			if (oclass != null) {
				structurestart = oclass.get();
			}
		} catch (Exception exception) {
			logger.warn("Failed Start with id " + tagCompound.getString("id"));
			logger.warn(exception);
		}

		if (structurestart != null) {
			structurestart.readStructureComponentsFromNBT(worldIn, tagCompound);
		} else {
			logger.warn("Skipping Structure with id " + tagCompound.getString("id"));
		}

		return structurestart;
	}

	public static StructureComponent getStructureComponent(NBTTagCompound tagCompound, World worldIn) {
		StructureComponent structurecomponent = null;

		try {
			Supplier<? extends StructureComponent> oclass = componentNameToSupplierMap.get(tagCompound.getString("id"));
			if (oclass != null) {
				structurecomponent = oclass.get();
			}
		} catch (Exception exception) {
			logger.warn("Failed Piece with id " + tagCompound.getString("id"));
			logger.warn(exception);
		}

		if (structurecomponent != null) {
			structurecomponent.readStructureBaseNBT(worldIn, tagCompound);
		} else {
			logger.warn("Skipping Piece with id " + tagCompound.getString("id"));
		}

		return structurecomponent;
	}

	static {
		registerStructure(StructureMineshaftStart.class, StructureMineshaftStart::new, "Mineshaft");
		registerStructure(MapGenVillage.Start.class, MapGenVillage.Start::new, "Village");
		registerStructure(MapGenNetherBridge.Start.class, MapGenNetherBridge.Start::new, "Fortress");
		registerStructure(MapGenStronghold.Start.class, MapGenStronghold.Start::new, "Stronghold");
		registerStructure(MapGenScatteredFeature.Start.class, MapGenScatteredFeature.Start::new, "Temple");
		registerStructure(StructureOceanMonument.StartMonument.class, StructureOceanMonument.StartMonument::new,
				"Monument");
		StructureMineshaftPieces.registerStructurePieces();
		StructureVillagePieces.registerVillagePieces();
		StructureNetherBridgePieces.registerNetherFortressPieces();
		StructureStrongholdPieces.registerStrongholdPieces();
		ComponentScatteredFeaturePieces.registerScatteredFeaturePieces();
		StructureOceanMonumentPieces.registerOceanMonumentPieces();
	}
}
