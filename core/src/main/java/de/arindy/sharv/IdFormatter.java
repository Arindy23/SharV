package de.arindy.sharv;

import java.util.Formatter;

import static java.util.FormattableFlags.LEFT_JUSTIFY;

public class IdFormatter {

    private Formatter formatter;
    private int f;
    private int width;
    private int precision;

    public IdFormatter(Formatter formatter, int f, int width, int precision) {
        this.formatter = formatter;
        this.f = f;
        this.width = width;
        this.precision = precision;
    }

    public void formatTo(final String name, final String id) {
        final StringBuilder sb = new StringBuilder();
        final String out = String.format("%s [%s]", name, id);
        if (precision == -1 || out.length() < precision) {
            sb.append(out);
        } else {
            sb.append(out, 0, precision - 2).append("*]");
        }
        int len = sb.length();
        if (len < width) {
            for (int i = 0; i < width - len; i++)
                if ((f & LEFT_JUSTIFY) == LEFT_JUSTIFY) {
                    sb.append(' ');
                } else
                    sb.insert(0, ' ');
        }
        formatter.format(sb.toString());
    }
}
