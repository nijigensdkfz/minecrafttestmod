package com.iza.milienhance.sound;

import com.iza.milienhance.Milienhance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
    public static final SoundEvent AMMO = registerSound("ammo");
    public static final SoundEvent AMMOEMPTY = registerSound("ammoempty");
    public static final SoundEvent FIRESHOT = registerSound("fireshot");
    public static final SoundEvent POWERUP = registerSound("powerup");
    public static final SoundEvent INTODARK = registerSound("intodark");
    public static final SoundEvent OUTDARK = registerSound("outdark");
    public static final SoundEvent OREALARM = registerSound("orealarm");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(Milienhance.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }
    public static void initialize(){}
}
