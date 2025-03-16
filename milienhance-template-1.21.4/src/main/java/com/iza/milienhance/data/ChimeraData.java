package com.iza.milienhance.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record ChimeraData(BlockPos pos) implements BlockPosPayload{
    public static final PacketCodec<RegistryByteBuf, ChimeraData> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, ChimeraData::pos, ChimeraData::new);
}
