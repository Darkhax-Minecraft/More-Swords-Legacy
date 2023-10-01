package net.darkhax.msmlegacy.config.types;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelScaledFloat {

    public static final TypeAdapter<LevelScaledFloat> ADAPTER = new LevelScaledFloat.Adapter();

    private float[] values;

    public float getValue(int level) {

        if (values.length == 1) {

            return values[0] * level;
        }

        final int valueIndex = level - 1;

        if (valueIndex < 0) {

            return values[0];
        }

        else if (valueIndex > (values.length - 1)) {

            return values[values.length - 1];
        }

        return values[valueIndex];
    }

    public LevelScaledFloat(float f) {

        this(new float[] {f});
    }

    public LevelScaledFloat(float[] values) {

        this.values = values;
    }

    public static class Adapter extends TypeAdapter<LevelScaledFloat> {

        @Override
        public void write(JsonWriter out, LevelScaledFloat value) throws IOException {

            if (value.values.length == 1) {

                out.value(value.values[0]);
            }

            else {
                out.beginArray();

                for (float fval : value.values) {

                    out.value(fval);
                }

                out.endArray();
            }
        }

        @Override
        public LevelScaledFloat read(JsonReader in) throws IOException {

            if (in.hasNext()) {

                final JsonToken type = in.peek();

                if (type == JsonToken.BEGIN_ARRAY) {

                    final List<Float> values = new ArrayList<>();

                    in.beginArray();

                    while(in.hasNext()) {

                        values.add((float) in.nextDouble());
                    }

                    in.endArray();

                    return new LevelScaledFloat(ArrayUtils.toPrimitive(values.toArray(new Float[0])));
                }

                else {

                    return new LevelScaledFloat((float) in.nextDouble());
                }
            }

            throw new JsonParseException("Value expected to be a float or float array.");
        }
    }
}