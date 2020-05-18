package org.drombler.event.core;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AllDayEventDurationTest {

    @Nested
    class IsSingleDay {

        @Test
        void sameDay_single() {
            LocalDate localDate = LocalDate.of(2020, 5, 18);
            AllDayEventDuration allDayEventDuration = new AllDayEventDuration(localDate, localDate);

            assertTrue(allDayEventDuration.isSingleDay());
        }

        @Test
        void startAndEndDateDifferent_notSingle() {
            LocalDate startDate = LocalDate.of(2020, 5, 18);
            LocalDate endDate = LocalDate.of(2020, 5, 20);
            AllDayEventDuration allDayEventDuration = new AllDayEventDuration(startDate, endDate);

            assertFalse(allDayEventDuration.isSingleDay());
        }
    }
}