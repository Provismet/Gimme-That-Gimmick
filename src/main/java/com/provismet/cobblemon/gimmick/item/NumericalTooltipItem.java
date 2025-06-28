package com.provismet.cobblemon.gimmick.item;

public interface NumericalTooltipItem {
    String getTranslationKey();

    default String getTooltipTranslationKey(int lineNumber) {
        return this.getTranslationKey() + ".tooltip." + lineNumber;
    }
}
