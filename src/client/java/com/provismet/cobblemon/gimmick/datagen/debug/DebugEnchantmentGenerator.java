package com.provismet.cobblemon.gimmick.datagen.debug;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.registry.GTGEnchantmentComponents;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class DebugEnchantmentGenerator extends FabricDynamicRegistryProvider {
    public DebugEnchantmentGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure (RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        BiConsumer<Identifier, Enchantment.Builder> register = (identifier, builder) -> entries.add(RegistryKey.of(RegistryKeys.ENCHANTMENT, identifier), builder.build(identifier));
        register.accept(
            GimmeThatGimmickMain.identifier("enchanted_leftovers"),
            new Enchantment.Builder(
                Enchantment.definition(
                    wrapperLookup.getWrapperOrThrow(RegistryKeys.ITEM).getOrThrow(ItemTags.CAT_FOOD),
                    1,
                    1,
                    Enchantment.constantCost(1),
                    Enchantment.constantCost(1),
                    1,
                    AttributeModifierSlot.ANY
                )
            ).addNonListEffect(
                GTGEnchantmentComponents.SHOWDOWN_ID, "leftovers"
            )
        );
    }

    @Override
    public String getName () {
        return "debug enchantments";
    }
}
