package dev.shoulderpets;

import net.minecraft.resources.Identifier;

/** Pet types. Textures/models are vanilla ones, so no extra assets are needed. */
public enum PetType {
    FOX("Fox", 0.36f,
            new Variant("Red", "textures/entity/fox/fox.png"),
            new Variant("Snow", "textures/entity/fox/snow_fox.png")),
    CAT("Cat", 0.36f,
            new Variant("Black", "textures/entity/cat/all_black.png"),
            new Variant("Tabby", "textures/entity/cat/tabby.png"),
            new Variant("Tuxedo", "textures/entity/cat/tuxedo.png"),
            new Variant("Red", "textures/entity/cat/red.png"),
            new Variant("Siamese", "textures/entity/cat/siamese.png"),
            new Variant("British Shorthair", "textures/entity/cat/british_shorthair.png"),
            new Variant("Calico", "textures/entity/cat/calico.png"),
            new Variant("Persian", "textures/entity/cat/persian.png"),
            new Variant("Ragdoll", "textures/entity/cat/ragdoll.png"),
            new Variant("White", "textures/entity/cat/white.png"),
            new Variant("Jellie", "textures/entity/cat/jellie.png")),
    WARDEN("Mini Warden", 0.12f,
            new Variant("Warden", "textures/entity/warden/warden.png"));

    public record Variant(String name, String path) {
        public Identifier texture() {
            return Identifier.withDefaultNamespace(path);
        }
    }

    public final String displayName;
    public final float scale;
    public final Variant[] variants;

    PetType(String displayName, float scale, Variant... variants) {
        this.displayName = displayName;
        this.scale = scale;
        this.variants = variants;
    }

    public Variant variant(int index) {
        return variants[Math.floorMod(index, variants.length)];
    }

    public PetType next() {
        PetType[] all = values();
        return all[(ordinal() + 1) % all.length];
    }
}
