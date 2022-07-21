package net.lucypoulton.kyorify;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Relational;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;

public class KyorifyExpansion extends PlaceholderExpansion implements Relational {
    @Override
    public @NotNull String getIdentifier() {
        return "kyorify";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Lucy Poulton";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.4";
    }

    private static String log(String str) {
        Bukkit.getLogger().info(str);
        return str;
    }

    @Override
    public @Nullable String onRequest(final OfflinePlayer player, @NotNull final String params) {
        String[] split = params.split("_", 2);
        if (split.length < 2) return null;

        return Optional.ofNullable(PlaceholderAPIPlugin.getInstance()
                .getLocalExpansionManager()
                .getExpansion(split[0]))
                .map(ex -> ex.onRequest(player, split[1]))
                .map(Kyorifier::kyorify)
                .orElse(null);
    }

    @Override
    public String onPlaceholderRequest(Player one, Player two, String params) {
        String[] split = params.split("_", 2);
        if (split.length < 2) return null;

        final PlaceholderExpansion expansion = PlaceholderAPIPlugin.getInstance()
            .getLocalExpansionManager()
            .getExpansion(split[0]);

        if (!(expansion instanceof final Relational relational)) return null;

        return Optional.of(relational)
            .map(ex -> ex.onPlaceholderRequest(one, two, split[1]))
            .map(Kyorifier::kyorify)
            .orElse(null);
    }
}
