package com.provismet.cobblemon.gimmick.api.data;

import com.cobblemon.mod.common.api.moves.Move;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;

/**
 * A predicate to apply to a Pokémon. Allows a Pokémon to be checked in terms of its species, form, and aspects.
 *
 * @param speciesShowdownIds List of valid Showdown ids for the species. Ignored if empty.
 * @param formShowdownIds List of valid Showdown ids for the forms. Ignored if empty.
 * @param requiredAspects List of aspects the Pokémon must have to pass the test. Ignored if empty.
 * @param blacklistedAspects List of aspects the Pokémon must not have to pass the test. Ignored if empty.
 */
public record PokemonRequirements (
    List<String> speciesShowdownIds,
    List<String> formShowdownIds,
    List<String> requiredAspects,
    List<String> blacklistedAspects,
    List<String> requiredMoves,
    List<String> blacklistedMoves
) {
    public static final Codec<PokemonRequirements> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codec.STRING.listOf().optionalFieldOf("speciesShowdownIds", List.of()).forGetter(PokemonRequirements::speciesShowdownIds),
        Codec.STRING.listOf().optionalFieldOf("formShowdownIds", List.of()).forGetter(PokemonRequirements::formShowdownIds),
        Codecs.NON_EMPTY_STRING.listOf().optionalFieldOf("requiredAspects", List.of()).forGetter(PokemonRequirements::requiredAspects),
        Codecs.NON_EMPTY_STRING.listOf().optionalFieldOf("blacklistedAspects", List.of()).forGetter(PokemonRequirements::blacklistedAspects),
        Codecs.NON_EMPTY_STRING.listOf().optionalFieldOf("requiredMoves", List.of()).forGetter(PokemonRequirements::requiredMoves),
        Codecs.NON_EMPTY_STRING.listOf().optionalFieldOf("blacklistedMoves", List.of()).forGetter(PokemonRequirements::blacklistedMoves)
    ).apply(instance, PokemonRequirements::new));

    public static final PacketCodec<RegistryByteBuf, PokemonRequirements> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.STRING.collect(PacketCodecs.toList()),
        PokemonRequirements::speciesShowdownIds,
        PacketCodecs.STRING.collect(PacketCodecs.toList()),
        PokemonRequirements::formShowdownIds,
        PacketCodecs.STRING.collect(PacketCodecs.toList()),
        PokemonRequirements::requiredAspects,
        PacketCodecs.STRING.collect(PacketCodecs.toList()),
        PokemonRequirements::blacklistedAspects,
        PacketCodecs.STRING.collect(PacketCodecs.toList()),
        PokemonRequirements::requiredMoves,
        PacketCodecs.STRING.collect(PacketCodecs.toList()),
        PokemonRequirements::blacklistedMoves,
        PokemonRequirements::new
    );

    public static final PokemonRequirements TRUE = new PokemonRequirements(List.of(), List.of(), List.of(), List.of(), List.of(), List.of());
    public static final PokemonRequirements FALSE = new PokemonRequirements(List.of("IMPOSSIBLE_ID"), List.of(), List.of(), List.of(), List.of(), List.of()); // Ids are always lowercase.

    public static PokemonRequirements species (String speciesId) {
        return new PokemonRequirements(List.of(speciesId), List.of(), List.of(), List.of(), List.of(), List.of());
    }

    public static PokemonRequirements speciesForm (String speciesId, String formId) {
        return new PokemonRequirements(List.of(speciesId), List.of(formId), List.of(), List.of(), List.of(), List.of());
    }

    public boolean matches (Pokemon pokemon) {
        if (!this.speciesShowdownIds.isEmpty() && !this.speciesShowdownIds.contains(pokemon.getSpecies().showdownId())) return false;
        if (!this.formShowdownIds.isEmpty() && !this.formShowdownIds.contains(pokemon.getForm().formOnlyShowdownId())) return false;

        for (String aspect : this.requiredAspects) {
            if (!pokemon.getAspects().contains(aspect)) return false;
        }
        for (String aspect : this.blacklistedAspects) {
            if (pokemon.getAspects().contains(aspect)) return false;
        }
        for (String moveName : this.requiredMoves) {
            boolean hasMove = false;
            for (Move move : pokemon.getMoveSet()) {
                if (move.getName().equalsIgnoreCase(moveName)) {
                    hasMove = true;
                    break;
                }
            }
            if (!hasMove) return false;
        }
        for (String moveName : this.blacklistedMoves) {
            for (Move move : pokemon.getMoveSet()) {
                if (move.getName().equalsIgnoreCase(moveName)) return false;
            }
        }

        return true;
    }
}
