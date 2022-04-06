package net.lucypoulton.kyorify;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;

public class KyorifyExpansion extends PlaceholderExpansion {
    final MiniMessage mm = MiniMessage.miniMessage();
    @Override
    public @NotNull String getIdentifier() {
        return "kyorify";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Lucy Poulton (99% kyori code though)";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public @Nullable String onRequest(final OfflinePlayer player, @NotNull final String params) {
        String[] split = params.split("_", 2);
        return Optional.ofNullable(PlaceholderAPIPlugin.getInstance()
                .getLocalExpansionManager()
                .getExpansion(split[0]))
                .map(ex -> ex.onRequest(player, split.length >= 2 ? split[1] : ""))
                .map(str -> ChatColor.translateAlternateColorCodes('&', str))
                .map(str -> mm.serialize(LegacyComponentSerializer.legacySection().deserialize(str)))
                .orElse(null);
    }
}
