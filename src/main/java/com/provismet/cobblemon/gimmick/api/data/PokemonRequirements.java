package com.provismet.cobblemon.gimmick.api.data;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;

public record PokemonRequirements (String speciesShowdownId, List<String> requiredAspects, List<String> blacklistedAspects) {
    public static final Codec<PokemonRequirements> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codecs.NON_EMPTY_STRING.fieldOf("showdownId").forGetter(PokemonRequirements::speciesShowdownId),
        Codecs.NON_EMPTY_STRING.listOf().optionalFieldOf("requiredAspects", List.of()).forGetter(PokemonRequirements::requiredAspects),
        Codecs.NON_EMPTY_STRING.listOf().optionalFieldOf("blacklistedAspects", List.of()).forGetter(PokemonRequirements::blacklistedAspects)
    ).apply(instance, PokemonRequirements::new));

    public static final PacketCodec<RegistryByteBuf, PokemonRequirements> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.STRING,
        PokemonRequirements::speciesShowdownId,
        PacketCodecs.STRING.collect(PacketCodecs.toList()),
        PokemonRequirements::requiredAspects,
        PacketCodecs.STRING.collect(PacketCodecs.toList()),
        PokemonRequirements::blacklistedAspects,
        PokemonRequirements::new
    );

    public static PokemonRequirements fromSpecies (String speciesId) {
        return new PokemonRequirements(speciesId, List.of(), List.of());
    }

    public boolean matches (Pokemon pokemon) {
        if (!pokemon.getSpecies().showdownId().equalsIgnoreCase(this.speciesShowdownId)) return false;

        for (String aspect : this.requiredAspects) {
            if (!pokemon.getAspects().contains(aspect)) return false;
        }
        for (String aspect : this.blacklistedAspects) {
            if (pokemon.getAspects().contains(aspect)) return false;
        }

        return true;
    }
}
