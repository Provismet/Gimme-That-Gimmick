package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class RevealGlassItem extends AbstractFormChangeToggleItem {
    private static final String FEATURE = "mirror_forme";
    private static final Set<String> ALLOWED = Set.of(
        "cobblemon:landorus",
        "cobblemon:thundurus",
        "cobblemon:tornadus",
        "cobblemon:enamorus"
    );

    public RevealGlassItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return ALLOWED.contains(pokemon.getSpecies().getResourceIdentifier().toString());
    }

    @Override
    protected boolean shouldApplySpecialForm (Pokemon pokemon) {
        return !pokemon.getAspects().contains("therian-forme");
    }

    @Override
    protected void applySpecialForm (ServerPlayerEntity player, Pokemon pokemon) {
        new StringSpeciesFeature(FEATURE, "therian").apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.mirror.therian", pokemon.getDisplayName()), true);
    }

    @Override
    protected void removeSpecialForm (ServerPlayerEntity player, Pokemon pokemon) {
        new StringSpeciesFeature(FEATURE, "incarnate").apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.mirror.incarnate", pokemon.getDisplayName()), true);
    }

    @Override
    protected void postFormChange (ServerPlayerEntity player, ItemStack stack, Pokemon pokemon) {
        player.getWorld().playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1f , 1f, true);
    }
}
