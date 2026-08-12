package com.besson.tutorialmod.item;

import com.besson.tutorialmod.TutorialMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;

public class ModItems {
    //注册物品
    public static final Item ICE_ETHER = registerItems("ice_ether", new Item(new Item.Settings()));
    public static final Item RAW_ICE_ETHER = registerItems("raw_ice_ether", new Item(new Item.Settings()));
    public static final Item CHEESE = registerItems("cheese", new Item(new Item.Settings().food(ModFoodComponents.CHEESE)));
    public static final Item STRAWBERRY = registerItems("strawberry", new Item(new Item.Settings().food(ModFoodComponents.STRAWBERRY)));
    public static final Item ANTHRACITE = registerItems("anthracite", new Item(new Item.Settings()));

    //mod中item的注册方法
    private static Item registerItems(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID,id), item);
    }

    private static void addItemToIG(FabricItemGroupEntries fabricItemGroupEntries) {//添加到原版物品组
        fabricItemGroupEntries.add(ICE_ETHER);
        fabricItemGroupEntries.add(RAW_ICE_ETHER);
    }

    public static void registerModItems(){//启动程序
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToIG);//添加到“原料”中
        TutorialMod.LOGGER.info("Registering Mod Items");
    }
}
