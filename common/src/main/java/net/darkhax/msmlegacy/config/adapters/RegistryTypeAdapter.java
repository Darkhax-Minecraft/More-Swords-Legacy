package net.darkhax.msmlegacy.config.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.BlockStateData;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Display;

import java.io.IOException;

public class RegistryTypeAdapter<T> extends TypeAdapter<T> {

    public static RegistryTypeAdapter<MobEffect> MOB_EFFECT = new RegistryTypeAdapter(BuiltInRegistries.MOB_EFFECT);

    private final Registry<T> registry;

    public RegistryTypeAdapter(Registry<T> registry) {

        this.registry = registry;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {

        final ResourceLocation id = registry.getKey(value);

        if (id != null && registry.containsKey(id)) {

            out.value(id.toString());
        }

        else {
            throw new IOException("Value does not exist in registry. " + value);
        }
    }

    @Override
    public T read(JsonReader in) throws IOException {

        final String rawId = in.nextString();
        final ResourceLocation id = ResourceLocation.tryParse(rawId);

        if (id != null && registry.containsKey(id)) {

            return registry.get(id);
        }

        throw new IOException("Value does not exist in registry. " + rawId);
    }
}