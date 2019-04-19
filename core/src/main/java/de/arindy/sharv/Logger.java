package de.arindy.sharv;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Logger {

    private static final Map<String, Logger> LOGGERS = new HashMap<>();
    private static Level logLevel = Level.INFO;
    private static Collection<Handler> handlers = new HashSet<>();

    static {
        final java.util.logging.Logger rootLogger = java.util.logging.Logger.getLogger("");
        final Handler[] rootHandlers = rootLogger.getHandlers();
        for (Handler handler : rootHandlers) {
            rootLogger.removeHandler(handler);
        }
    }

    private final java.util.logging.Logger logger;

    private Logger(final String name) {
        logger = java.util.logging.Logger.getLogger(name);
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
        for (Handler handler : handlers) {
            logger.addHandler(handler);
        }
        logger.setLevel(logLevel);
    }

    public static Logger get(final String name) {
        if (LOGGERS.get(name) == null) {
            LOGGERS.put(name, new Logger(name));
        }
        return LOGGERS.get(name);
    }

    public static boolean isDebugLevel() {
        return logLevel == Level.FINER;
    }

    public static void setDebugLevel() {
        setLogLevel(Level.FINER);
    }

    public static void setInfoLevel() {
        setLogLevel(Level.INFO);
    }

    private static void setLogLevel(final Level newLogLevel) {
        logLevel = newLogLevel;
        for (Logger logger : LOGGERS.values()) {
            for (Handler handler : logger.logger.getHandlers()) {
                handler.setLevel(logLevel);
            }
            logger.logger.setLevel(logLevel);
        }
    }

    public static void addHandler(final Handler handler) {
        handler.setFormatter(new LogFormatter());
        handler.setLevel(logLevel);
        handlers.add(handler);
        for (Logger logger : LOGGERS.values()) {
            logger.logger.addHandler(handler);
        }
    }

    public static void removeHandler(final Handler handler) {
        for (Logger logger : LOGGERS.values()) {
            logger.logger.removeHandler(handler);
        }
        handlers.remove(handler);
    }

    private static boolean shouldLog(final Level level) {
        return level.intValue() >= logLevel.intValue();
    }

    private static String className() {
        return caller().getClassName();
    }

    private static String methodName() {
        return caller().getMethodName();
    }

    private static String callerRef() {
        return Thread.currentThread().getStackTrace()[4].toString();
    }

    private static StackTraceElement caller() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public void info(final String msg) {
        log(Level.INFO, msg);
    }

    public void entering(final Object... objects) {
        final StringBuilder variables = new StringBuilder();
        for (Object object : objects) {
            if (variables.length() > 0) {
                variables.append(", ");
            }
            variables.append(object);
        }
        log(Level.FINER, String.format("<-- %s.%s(%s)", className(), methodName(), variables.toString()));
    }

    public <T> T returning(final T result) {
        log(Level.FINER, String.format("--> %s.%s => [%s]", className(), methodName(), result));
        return result;
    }

    public void debug(final String msg) {
        log(Level.FINE, msg);
    }

    public void warning(final String msg) {
        log(Level.WARNING, formatMessage(msg));
    }

    public void error(final String msg) {
        log(Level.SEVERE, formatMessage(msg));
    }

    public void exception(final String msg, final Throwable cause) {
        log(Level.SEVERE, formatMessage(msg), cause);
    }

    private void log(final Level level, final String msg) {
        log(level, msg, null);
    }

    private void log(final Level level, final String msg, final Throwable cause) {
        if (shouldLog(level)) {
            logger.log(level, msg, cause);
        }
    }

    private String formatMessage(final String msg) {
        return String.format("%s : %s", callerRef(), msg);
    }

    public static class LogFormatter extends Formatter {

        @Override
        public String format(LogRecord record) {
            final String stackTrace;
            Throwable thrown = record.getThrown();
            if (thrown == null) {
                stackTrace = "";
            } else {
                final StringWriter stacktraceWriter = new StringWriter();
                try (PrintWriter writer = new PrintWriter(stacktraceWriter)) {
                    thrown.printStackTrace(writer);
                }
                stackTrace = stacktraceWriter.toString();
            }
            return String.format(
                    "[%-7s] : %s : [%s] : %s%n%s",
                    record.getLevel(),
                    DateTimeFormatter.ofPattern("YYY.MM.dd-HH.mm.ss").format(ZonedDateTime.now(Clock.systemUTC())),
                    record.getLoggerName(),
                    record.getMessage(),
                    stackTrace
            );
        }
    }

}
