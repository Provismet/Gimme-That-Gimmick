package com.provismet.cobblemon.gimmick.api.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public abstract class AbstractGTGProvider<T> extends FabricDynamicRegistryProvider {
    private final RegistryKey<Registry<T>> registryKey;

    public AbstractGTGProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, RegistryKey<Registry<T>> key) {
        super(output, registriesFuture);
        this.registryKey = key;
    }

    @Override
    protected void configure (RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        BiConsumer<Identifier, T> consumer = (id, item) -> entries.add(RegistryKey.of(this.registryKey, id), item);
        this.generate(wrapperLookup, consumer);
    }

    protected abstract void generate (RegistryWrapper.WrapperLookup wrapperLookup, BiConsumer<Identifier, T> consumer);
}
