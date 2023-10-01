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

public class LevelScaledDouble {

    public static final TypeAdapter<LevelScaledDouble> ADAPTER = new Adapter();

    private double[] values;

    public double getValue(int level) {

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

    public LevelScaledDouble(double f) {

        this(new double[] {f});
    }

    public LevelScaledDouble(double[] values) {

        this.values = values;
    }

    public static class Adapter extends TypeAdapter<LevelScaledDouble> {

        @Override
        public void write(JsonWriter out, LevelScaledDouble value) throws IOException {

            if (value.values.length == 1) {

                out.value(value.values[0]);
            }

            else {

                out.beginArray();

                for (double fval : value.values) {

                    out.value(fval);
                }

                out.endArray();
            }
        }

        @Override
        public LevelScaledDouble read(JsonReader in) throws IOException {

            if (in.hasNext()) {

                final JsonToken type = in.peek();

                if (type == JsonToken.BEGIN_ARRAY) {

                    final List<Double> values = new ArrayList<>();

                    in.beginArray();

                    while(in.hasNext()) {

                        values.add(in.nextDouble());
                    }

                    in.endArray();

                    return new LevelScaledDouble(ArrayUtils.toPrimitive(values.toArray(new Double[0])));
                }

                else {

                    return new LevelScaledDouble(in.nextDouble());
                }
            }

            throw new JsonParseException("Value expected to be a double or double array.");
        }
    }
}