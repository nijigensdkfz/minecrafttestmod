package com.iza.milienhance.component.type;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.SoundEvents;

import java.util.List;

public class ModConsumableComponents {
    public static final ConsumableComponent DISASTER;

    public static ConsumableComponent.Builder drink() {
        return ConsumableComponent.builder().consumeSeconds(1.6F).useAction(UseAction.DRINK).sound(SoundEvents.ENTITY_GENERIC_DRINK).consumeParticles(false);
    }
    static {
        DISASTER = drink().consumeSeconds(2.0F).consumeEffect(new ApplyEffectsConsumeEffect(
                List.of(
                        new StatusEffectInstance(StatusEffects.SLOWNESS,20),
                        new StatusEffectInstance(StatusEffects.MINING_FATIGUE,20),
                        new StatusEffectInstance(StatusEffects.NAUSEA,20),
                        new StatusEffectInstance(StatusEffects.BLINDNESS,20),
                        new StatusEffectInstance(StatusEffects.WEAKNESS,20),
                        new StatusEffectInstance(StatusEffects.POISON,20),
                        new StatusEffectInstance(StatusEffects.WITHER,20),
                        new StatusEffectInstance(StatusEffects.LEVITATION,20),
                        new StatusEffectInstance(StatusEffects.DARKNESS,20),
                        new StatusEffectInstance(StatusEffects.GLOWING,20),
                        new StatusEffectInstance(StatusEffects.HUNGER,20),
                        new StatusEffectInstance(StatusEffects.OOZING,20),
                        new StatusEffectInstance(StatusEffects.TRIAL_OMEN,20),
                        new StatusEffectInstance(StatusEffects.BAD_OMEN,20),
                        new StatusEffectInstance(StatusEffects.RAID_OMEN,20),
                        new StatusEffectInstance(StatusEffects.WIND_CHARGED,20),
                        new StatusEffectInstance(StatusEffects.WEAVING,20),
                        new StatusEffectInstance(StatusEffects.INFESTED,20)
                )
        )).build();
    }
}
