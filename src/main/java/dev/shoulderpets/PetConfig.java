package dev.shoulderpets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;

public class PetConfig {
    public enum Shoulder {
        LEFT, RIGHT, BOTH;

        public Shoulder next() {
            Shoulder[] all = values();
            return all[(ordinal() + 1) % all.length];
        }
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("shoulderpets.json");
    private static PetConfig instance;

    public boolean enabled = true;
    public PetType type = PetType.FOX;
    public int variant = 0;
    public Shoulder shoulder = Shoulder.LEFT;

    public static PetConfig get() {
        if (instance == null) load();
        return instance;
    }

    public static void load() {
        try {
            if (Files.exists(FILE)) {
                instance = GSON.fromJson(Files.readString(FILE), PetConfig.class);
            }
        } catch (Exception e) {
            ShoulderPetsClient.LOGGER.warn("Could not read config, using defaults", e);
        }
        if (instance == null) instance = new PetConfig();
        if (instance.type == null) instance.type = PetType.FOX;
        if (instance.shoulder == null) instance.shoulder = Shoulder.LEFT;
    }

    public void save() {
        try {
            Files.writeString(FILE, GSON.toJson(this));
        } catch (Exception e) {
            ShoulderPetsClient.LOGGER.warn("Could not save config", e);
        }
    }

    public PetType.Variant currentVariant() {
        return type.variant(variant);
    }
}
