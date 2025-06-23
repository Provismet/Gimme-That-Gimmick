package com.provismet.cobblemon.gimmick.util;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.api.types.tera.TeraTypes;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Formatting;

import java.util.UUID;

public class GlowHandler {
    public static void applyDynamaxGlow(PokemonEntity pokemonEntity){
        if (pokemonEntity.getWorld() instanceof ServerWorld serverLevel) {
            pokemonEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, Integer.MAX_VALUE, 0, false, false));
            ServerScoreboard scoreboard = serverLevel.getScoreboard();
            String teamName = "glow_" + UUID.randomUUID().toString().substring(0, 8);
            Team team = scoreboard.getTeam(teamName);
            if (team == null) {
                team = scoreboard.addTeam(teamName);
                if (pokemonEntity.getPokemon().getSpecies().getName().equals("Calyrex")) {
                    team.setColor(Formatting.BLUE);
                } else {
                    team.setColor(Formatting.RED);
                }
            }
            scoreboard.addScoreHolderToTeam(pokemonEntity.getUuid().toString(), team);
        }
    }

    public static void applyTeraGlow(PokemonEntity pokemon){
        if (pokemon.getWorld() instanceof ServerWorld serverLevel) {
            pokemon.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, Integer.MAX_VALUE, 0, false, false));
            ServerScoreboard scoreboard = serverLevel.getScoreboard();
            String teamName = "glow_" + UUID.randomUUID().toString().substring(0, 8);

            Team team = scoreboard.getTeam(teamName);

            Formatting color = getGlowColorForTeraType(pokemon.getPokemon().getTeraType());
            if (team == null) {
                team = scoreboard.addTeam(teamName);
                team.setColor(color);
            }

            scoreboard.addScoreHolderToTeam(pokemon.getUuid().toString(), team);
        }
    }

    public static void applyZGlow(PokemonEntity pokemon){
        if (pokemon.getWorld() instanceof ServerWorld serverLevel) {
            pokemon.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, Integer.MAX_VALUE, 0, false, false));
            ServerScoreboard scoreboard = serverLevel.getScoreboard();
            String teamName = "glow_" + UUID.randomUUID().toString().substring(0, 8);

            Team team = scoreboard.getTeam(teamName);

            Formatting color = getGlowColorZMoves(pokemon.getPokemon().getHeldItem$common());
            if (team == null) {
                team = scoreboard.addTeam(teamName);
                team.setColor(color);
            }

            scoreboard.addScoreHolderToTeam(pokemon.getUuid().toString(), team);
        }
    }

    private static Formatting getGlowColorForTeraType(TeraType teraType) {
        if (teraType.equals(TeraTypes.getBUG())) return Formatting.DARK_GREEN;
        if (teraType.equals(TeraTypes.getDARK())) return Formatting.BLACK;
        if (teraType.equals(TeraTypes.getDRAGON())) return Formatting.DARK_BLUE;
        if (teraType.equals(TeraTypes.getELECTRIC())) return Formatting.YELLOW;
        if (teraType.equals(TeraTypes.getFAIRY())) return Formatting.LIGHT_PURPLE;
        if (teraType.equals(TeraTypes.getFIGHTING())) return Formatting.DARK_RED;
        if (teraType.equals(TeraTypes.getFIRE())) return Formatting.RED;
        if (teraType.equals(TeraTypes.getFLYING())) return Formatting.GRAY;
        if (teraType.equals(TeraTypes.getGHOST())) return Formatting.DARK_PURPLE;
        if (teraType.equals(TeraTypes.getGRASS())) return Formatting.GREEN;
        if (teraType.equals(TeraTypes.getGROUND())) return Formatting.DARK_RED;
        if (teraType.equals(TeraTypes.getICE())) return Formatting.BLUE;
        if (teraType.equals(TeraTypes.getNORMAL())) return Formatting.WHITE;
        if (teraType.equals(TeraTypes.getPOISON())) return Formatting.DARK_PURPLE;
        if (teraType.equals(TeraTypes.getPSYCHIC())) return Formatting.LIGHT_PURPLE;
        if (teraType.equals(TeraTypes.getROCK())) return Formatting.DARK_GRAY;
        if (teraType.equals(TeraTypes.getSTEEL())) return Formatting.GRAY;
        if (teraType.equals(TeraTypes.getWATER())) return Formatting.BLUE;
        if (teraType.equals(TeraTypes.getSTELLAR())) return Formatting.WHITE;
        else {
            return Formatting.WHITE;
        }
    }
    private static Formatting getGlowColorZMoves(ItemStack heldItem) {
        if (heldItem.isOf(GTGItems.ALORAICHIUM_Z)) return Formatting.YELLOW;
        if (heldItem.isOf(GTGItems.BUGINIUM_Z)) return Formatting.DARK_GREEN;
        if (heldItem.isOf(GTGItems.DARKINIUM_Z)) return Formatting.BLACK;
        if (heldItem.isOf(GTGItems.DRAGONIUM_Z) || heldItem.isOf(GTGItems.KOMMONIUM_Z)) return Formatting.DARK_BLUE;
        if (heldItem.isOf(GTGItems.EEVIUM_Z) || heldItem.isOf(GTGItems.SNORLIUM_Z) || heldItem.isOf(GTGItems.NORMALIUM_Z))
            return Formatting.WHITE;
        if (heldItem.isOf(GTGItems.ELECTRIUM_Z) || heldItem.isOf(GTGItems.PIKANIUM_Z) || heldItem.isOf(GTGItems.PIKASHUNIUM_Z))
            return Formatting.YELLOW;
        if (heldItem.isOf(GTGItems.FAIRIUM_Z) || heldItem.isOf(GTGItems.TAPUNIUM_Z)) return Formatting.LIGHT_PURPLE;
        if (heldItem.isOf(GTGItems.FIGHTINIUM_Z)) return Formatting.DARK_RED;
        if (heldItem.isOf(GTGItems.FIRIUM_Z) || heldItem.isOf(GTGItems.INCINIUM_Z)) return Formatting.RED;
        if (heldItem.isOf(GTGItems.FLYINIUM_Z)) return Formatting.GRAY;
        if (heldItem.isOf(GTGItems.GHOSTIUM_Z) || heldItem.isOf(GTGItems.MARSHADIUM_Z) || heldItem.isOf(GTGItems.MIMIKIUM_Z))
            return Formatting.DARK_PURPLE;
        if (heldItem.isOf(GTGItems.GRASSIUM_Z) || heldItem.isOf(GTGItems.DECIDIUM_Z)) return Formatting.GREEN;
        if (heldItem.isOf(GTGItems.GROUNDIUM_Z) || heldItem.isOf(GTGItems.LYCANIUM_Z)) return Formatting.DARK_RED;
        if (heldItem.isOf(GTGItems.ICIUM_Z)) return Formatting.BLUE;
        if (heldItem.isOf(GTGItems.POISONIUM_Z)) return Formatting.DARK_PURPLE;
        if (heldItem.isOf(GTGItems.PSYCHIUM_Z) || heldItem.isOf(GTGItems.MEWNIUM_Z)) return Formatting.LIGHT_PURPLE;
        if (heldItem.isOf(GTGItems.ROCKIUM_Z)) return Formatting.DARK_GRAY;
        if (heldItem.isOf(GTGItems.STEELIUM_Z)) return Formatting.GRAY;
        if (heldItem.isOf(GTGItems.WATERIUM_Z) || heldItem.isOf(GTGItems.PRIMARIUM_Z)) return Formatting.BLUE;
        if (heldItem.isOf(GTGItems.SOLGANIUM_Z) || heldItem.isOf(GTGItems.LUNALIUM_Z) || heldItem.isOf(GTGItems.ULTRANECROZIUM_Z))
            return Formatting.GOLD;

        return Formatting.WHITE;
    }
}
