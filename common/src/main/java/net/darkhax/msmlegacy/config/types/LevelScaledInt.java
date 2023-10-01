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

public class LevelScaledInt {

    public static final TypeAdapter<LevelScaledInt> ADAPTER = new LevelScaledInt.Adapter();

    private int[] values;

    public int getValue(int level) {

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

    public LevelScaledInt(int f) {

        this(new int[]{f});
    }

    public LevelScaledInt(int... values) {

        this.values = values;
    }

    public static class Adapter extends TypeAdapter<LevelScaledInt> {

        @Override
        public void write(JsonWriter out, LevelScaledInt value) throws IOException {

            if (value.values.length == 1) {

                out.value(value.values[0]);
            }

            else {
                out.beginArray();

                for (int fval : value.values) {

                    out.value(fval);
                }

                out.endArray();
            }
        }

        @Override
        public LevelScaledInt read(JsonReader in) throws IOException {

            if (in.hasNext()) {

                final JsonToken type = in.peek();

                if (type == JsonToken.BEGIN_ARRAY) {

                    final List<Integer> values = new ArrayList<>();

                    in.beginArray();

                    while (in.hasNext()) {

                        values.add(in.nextInt());
                    }

                    in.endArray();

                    return new LevelScaledInt(ArrayUtils.toPrimitive(values.toArray(new Integer[0])));
                }

                else {

                    return new LevelScaledInt(in.nextInt());
                }
            }

            throw new JsonParseException("Value expected to be a double or double array.");
        }
    }
}