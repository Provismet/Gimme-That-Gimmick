package com.provismet.cobblemon.gimmick.api.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

public record EffectsData(
        String particle_apply,
        String particle_revert,
        String sound_apply,
        String sound_revert,
        Integer particle_apply_amplifier,
        Integer particle_revert_amplifier
) {
    public static final Codec<EffectsData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("particle_apply").forGetter(e -> e.particle_apply),
            Codec.STRING.fieldOf("particle_revert").forGetter(e -> e.particle_revert),
            Codec.STRING.fieldOf("sound_apply").forGetter(e -> e.sound_apply),
            Codec.STRING.fieldOf("sound_revert").forGetter(e -> e.sound_revert),
            Codec.INT.fieldOf("particle_apply_amplifier").forGetter(e -> e.particle_apply_amplifier),
            Codec.INT.fieldOf("particle_revert_amplifier").forGetter(e -> e.particle_revert_amplifier)
    ).apply(instance, EffectsData::new));

    public static final PacketCodec<RegistryByteBuf, EffectsData> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.STRING,
        EffectsData::particle_apply,
        PacketCodecs.STRING,
        EffectsData::particle_revert,
        PacketCodecs.STRING,
        EffectsData::sound_apply,
        PacketCodecs.STRING,
        EffectsData::sound_revert,
        PacketCodecs.INTEGER,
        EffectsData::particle_apply_amplifier,
        PacketCodecs.INTEGER,
        EffectsData::particle_revert_amplifier,
        EffectsData::new
    );
}
