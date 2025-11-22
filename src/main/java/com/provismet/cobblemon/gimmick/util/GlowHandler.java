package com.provismet.cobblemon.gimmick.util;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.api.types.tera.elemental.ElementalTypeTeraType;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.provismet.cobblemon.gimmick.item.zmove.TypedZCrystalItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Formatting;

import java.util.Locale;

public class GlowHandler {
    public static void applyDynamaxGlow(PokemonEntity pokemonEntity) {
        if (pokemonEntity.getWorld() instanceof ServerWorld serverLevel) {
            pokemonEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, Integer.MAX_VALUE, 0, false, false));
            ServerScoreboard scoreboard = serverLevel.getScoreboard();

            String teamName;
            Formatting teamColour;
            if (pokemonEntity.getPokemon().getSpecies().getName().equalsIgnoreCase("calyrex")) {
                teamName = "glow_dynamax_blue";
                teamColour = Formatting.BLUE;
            } else {
                teamName = "glow_dynamax_red";
                teamColour = Formatting.RED;
            }

            Team team = scoreboard.getTeam(teamName);
            if (team == null) {
                team = scoreboard.addTeam(teamName);
                team.setColor(teamColour);
            }
            scoreboard.addScoreHolderToTeam(pokemonEntity.getNameForScoreboard(), team);
        }
    }

    public static void applyTeraGlow (PokemonEntity pokemon) {
        if (pokemon.getWorld() instanceof ServerWorld serverLevel) {
            pokemon.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, Integer.MAX_VALUE, 0, false, false));
            ServerScoreboard scoreboard = serverLevel.getScoreboard();
            String teamName = "glow_tera_" + pokemon.getPokemon().getTeraType().showdownId();

            Team team = scoreboard.getTeam(teamName);

            Formatting color = getGlowForTera(pokemon.getPokemon().getTeraType());
            if (team == null) {
                team = scoreboard.addTeam(teamName);
                team.setColor(color);
            }

            scoreboard.addScoreHolderToTeam(pokemon.getNameForScoreboard(), team);
        }
    }

    public static void applyZGlow (PokemonEntity pokemon) {
        ElementalType type;
        if (pokemon.getPokemon().heldItem().getItem() instanceof TypedZCrystalItem crystal) {
            type = crystal.type;
        } else { // Only possible if the crystal is a custom item not controlled by GTG.
            type = pokemon.getPokemon().getPrimaryType();
        }

        if (pokemon.getWorld() instanceof ServerWorld serverLevel) {
            pokemon.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 140, 0, false, false));
            ServerScoreboard scoreboard = serverLevel.getScoreboard();
            String teamName = "glow_type_" + type.getName().toLowerCase(Locale.ROOT);

            Team team = scoreboard.getTeam(teamName);

            Formatting color = getGlowForElemental(type);
            if (team == null) {
                team = scoreboard.addTeam(teamName);
                team.setColor(color);
            }

            scoreboard.addScoreHolderToTeam(pokemon.getNameForScoreboard(), team);
        }
    }

    private static Formatting getGlowForTera(TeraType teraType) {
        if (teraType instanceof ElementalTypeTeraType elementalTera) {
            return getGlowForElemental(elementalTera.getType());
        }
        return Formatting.WHITE;
    }

    private static Formatting getGlowForElemental(ElementalType type) {
        if (type.equals(ElementalTypes.BUG)) return Formatting.DARK_GREEN;
        if (type.equals(ElementalTypes.DARK)) return Formatting.BLACK;
        if (type.equals(ElementalTypes.DRAGON)) return Formatting.DARK_BLUE;
        if (type.equals(ElementalTypes.ELECTRIC)) return Formatting.YELLOW;
        if (type.equals(ElementalTypes.FAIRY)) return Formatting.LIGHT_PURPLE;
        if (type.equals(ElementalTypes.FIGHTING)) return Formatting.DARK_RED;
        if (type.equals(ElementalTypes.FIRE)) return Formatting.RED;
        if (type.equals(ElementalTypes.FLYING)) return Formatting.GRAY;
        if (type.equals(ElementalTypes.GHOST)) return Formatting.DARK_PURPLE;
        if (type.equals(ElementalTypes.GRASS)) return Formatting.GREEN;
        if (type.equals(ElementalTypes.GROUND)) return Formatting.DARK_RED;
        if (type.equals(ElementalTypes.ICE)) return Formatting.AQUA;
        if (type.equals(ElementalTypes.NORMAL)) return Formatting.WHITE;
        if (type.equals(ElementalTypes.POISON)) return Formatting.DARK_PURPLE;
        if (type.equals(ElementalTypes.PSYCHIC)) return Formatting.LIGHT_PURPLE;
        if (type.equals(ElementalTypes.ROCK)) return Formatting.DARK_GRAY;
        if (type.equals(ElementalTypes.STEEL)) return Formatting.GRAY;
        if (type.equals(ElementalTypes.WATER)) return Formatting.BLUE;
        return Formatting.WHITE;
    }
}
