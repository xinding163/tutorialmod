package com.besson.tutorialmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent CHEESE = new FoodComponent.Builder()
            .nutrition(8)
            .saturationModifier(0.8f)
            .build();
    public static final FoodComponent STRAWBERRY = new FoodComponent.Builder()
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,600),0.5f)
            .nutrition(4)
            .saturationModifier(0.3f)
            .build();
}
