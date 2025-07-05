package com.provismet.cobblemon.gimmick.api.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.DataItemStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * Represents the base data for generic items created through data-driven features.
 * <p>
 * Used as a component for the data-driven items.
 *
 * @param name The custom item name.
 * @param tooltip A list of tooltip lines.
 * @param baseItem The id for the vanilla item to use as the base.
 * @param customModelData The custom model data.
 * @param rarity The item's rarity.
 */
public record DataItem (String name, List<String> tooltip, Identifier baseItem, Optional<Integer> customModelData, Rarity rarity) implements DataItemStack {
    public static final Codec<DataItem> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codec.STRING.fieldOf("name").forGetter(DataItem::name),
        Codec.STRING.listOf().optionalFieldOf("tooltip", List.of()).forGetter(DataItem::tooltip),
        Identifier.CODEC.fieldOf("vanillaItem").forGetter(DataItem::baseItem),
        Codecs.NONNEGATIVE_INT.optionalFieldOf("customModelData").forGetter(DataItem::customModelData),
        Rarity.CODEC.optionalFieldOf("rarity", Rarity.COMMON).forGetter(DataItem::rarity)
    ).apply(instance, DataItem::new));

    public static final PacketCodec<RegistryByteBuf, DataItem> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.STRING,
        DataItem::name,
        PacketCodecs.STRING.collect(PacketCodecs.toList()),
        DataItem::tooltip,
        Identifier.PACKET_CODEC,
        DataItem::baseItem,
        PacketCodecs.optional(PacketCodecs.INTEGER),
        DataItem::customModelData,
        Rarity.PACKET_CODEC,
        DataItem::rarity,
        DataItem::new
    );

    @Override
    @Nullable
    public ItemStack create () {
        Optional<Item> item = Registries.ITEM.getOrEmpty(this.baseItem);
        if (item.isPresent()) {
            ItemStack stack = new ItemStack(item.get());
            this.applyTo(stack);
            return stack;
        }
        return null;
    }

    public void applyTo (ItemStack stack) {
        stack.set(DataComponentTypes.CUSTOM_NAME, Text.literal(this.name).styled(style -> style.withItalic(false)));

        if (!this.tooltip.isEmpty()) {
            stack.set(DataComponentTypes.LORE, new LoreComponent(
                this.tooltip
                    .stream()
                    .map(line -> (Text)Text.literal(line).styled(style -> style.withItalic(false).withFormatting(Formatting.GRAY)))
                    .toList()
            ));
        }
        stack.set(DataComponentTypes.RARITY, this.rarity);
        this.customModelData.ifPresent(integer -> stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(integer)));
    }
}
