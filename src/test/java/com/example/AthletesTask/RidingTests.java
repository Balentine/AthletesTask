package com.example.AthletesTask;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RidingTests {

    @Test
    public void correctValues_shouldCreateRidingObject() {
        Riding mockRiding = new Riding(1, 1, 1);

        assertEquals(1, mockRiding.knockingDown);
        assertEquals(1, mockRiding.refusal);
        assertEquals(1, mockRiding.disobedience);
    }

}
