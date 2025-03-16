package com.iza.milienhance.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record PlantPotData(BlockPos pos) implements BlockPosPayload{
    public static final PacketCodec<RegistryByteBuf,PlantPotData> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC,PlantPotData::pos,PlantPotData::new);
}
