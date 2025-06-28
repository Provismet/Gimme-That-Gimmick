package com.provismet.cobblemon.gimmick.registry;

import com.mojang.serialization.Codec;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.MegaEvolution;
import eu.pb4.polymer.core.api.other.PolymerComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Unit;

import java.util.function.UnaryOperator;

public abstract class GTGItemDataComponents {
    public static final ComponentType<Unit> KEY_STONE = register("key_stone", builder -> builder.codec(Unit.CODEC).packetCodec(PacketCodec.unit(Unit.INSTANCE)));
    public static final ComponentType<Unit> Z_RING = register("z_ring", builder -> builder.codec(Unit.CODEC).packetCodec(PacketCodec.unit(Unit.INSTANCE)));
    public static final ComponentType<Unit> DYNAMAX_BAND = register("dynamax_band", builder -> builder.codec(Unit.CODEC).packetCodec(PacketCodec.unit(Unit.INSTANCE)));
    public static final ComponentType<Unit> TERA_ORB = register("tera_orb", builder -> builder.codec(Unit.CODEC).packetCodec(PacketCodec.unit(Unit.INSTANCE)));
    public static final ComponentType<String> SHOWDOWN_ID = register("showdown_id", builder -> builder.codec(Codec.STRING).packetCodec(PacketCodecs.STRING));
    public static final ComponentType<MegaEvolution> MEGA_EVOLUTION = register("mega_evolution", builder -> builder.codec(MegaEvolution.CODEC).packetCodec(MegaEvolution.PACKET_CODEC));

    private static <T> ComponentType<T> register (String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        ComponentType<T> component = Registry.register(Registries.DATA_COMPONENT_TYPE, GimmeThatGimmickMain.identifier(name), builderOperator.apply(ComponentType.builder()).build());
        PolymerComponent.registerDataComponent(component);
        return component;
    }

    public static void init () {}
}
