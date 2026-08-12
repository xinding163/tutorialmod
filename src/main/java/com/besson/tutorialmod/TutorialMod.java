package com.besson.tutorialmod;

import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.item.ModItemGroups;
import com.besson.tutorialmod.item.ModItems;
import com.besson.tutorialmod.mixin.GrassColorsMixin;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.util.Identifier;

import net.minecraft.world.biome.GrassColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
        ModBlocks.registerModBlocks();

        int [] colorMap = GrassColorsMixin.getColorMap();
        LOGGER.info("Grass color map length: {}" , colorMap.length);

        int [] newColorMap = new int[128];
        GrassColorsMixin.setColorMap(newColorMap);
        LOGGER.info("New Grass color map length: {}" , GrassColorsMixin.getColorMap().length);

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
