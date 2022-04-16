package net.lucypoulton.kyorify;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;

public class KyorifyExpansion extends PlaceholderExpansion {
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
        return "1.2";
    }

    private static String log(String str) {
        Bukkit.getLogger().info(str);
        return str;
    }

    @Override
    public @Nullable String onRequest(final OfflinePlayer player, @NotNull final String params) {
        String[] split = params.split("_", 2);
        return Optional.ofNullable(PlaceholderAPIPlugin.getInstance()
                .getLocalExpansionManager()
                .getExpansion(split[0]))
                .map(ex -> ex.onRequest(player, split.length >= 2 ? split[1] : ""))
                .map(Kyorifier::kyorify)
                .orElse(null);
    }
}
