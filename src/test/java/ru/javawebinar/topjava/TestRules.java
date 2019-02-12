package ru.javawebinar.topjava;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class TestRules {

    static {
        SLF4JBridgeHandler.install();
    }

    private static Map<String, Long> timeAllTests = new HashMap<>();
    private static LocalDateTime fromDate;
    private static final Logger log = LoggerFactory.getLogger(TestRules.class);


    @AfterClass
    public static void afterTests() {
        log.debug("TEST NAME DURATION TIME");
        timeAllTests.forEach((key, value) -> log.info("{} {}", key, value));
    }

    @Rule
    public TestWatcher watcher = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            fromDate = LocalDateTime.now();
            log.debug("Starting test: " + description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            LocalDateTime toDate = LocalDateTime.now();
            long duration = ChronoUnit.MILLIS.between(fromDate, toDate);
            timeAllTests.put(description.getMethodName(), duration);
            log.info("-------------------------------------------------------------------------------------------------------------------------------");
            log.info("End test " + description.getMethodName());
            log.info("Duration time {}ms", duration);
            log.info("-------------------------------------------------------------------------------------------------------------------------------");
        }
    };

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TestRule timeout = new Timeout(5000);


}
