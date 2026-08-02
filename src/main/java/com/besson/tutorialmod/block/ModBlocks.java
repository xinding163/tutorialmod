package com.besson.tutorialmod.block;

import com.besson.tutorialmod.TutorialMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    //注册方块
    public static final Block ICE_ETHER_BLOCK =
            register("ice_ether_block",
                    new Block(AbstractBlock
                            .Settings.create()
                            .strength(3.0f,3.0f)));

    public static final Block ICE_ETHER_ORE =
            register("ice_ether_ore",
                    new Block(AbstractBlock
                            .Settings.create().
                            requiresTool().//手挖不掉落
                            strength(2.5f,6.0f)));

    public static final Block RAW_ICE_ETHER_BLOCK =
            register("raw_ice_ether_block",
                    new Block(AbstractBlock
                            .Settings.create()
                            .requiresTool()
                            .strength(3.0f,3.0f)));

    public static void registerBlockItems(String id, Block block) {//注册方块物品
        Item item = Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID,id), new BlockItem(block, new Item.Settings()));
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
    }

    public static Block register(String id, Block block) {//注册方块
        registerBlockItems(id,block);
        return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID,id), block);
    }

    public static void registerModBlocks() {//启动方法
        TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);
    }
}
