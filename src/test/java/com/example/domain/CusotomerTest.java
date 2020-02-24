package com.example.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CusotomerTest {

    @Test
    void _値が正しく設定されていること() {
        Customer customer = new Customer(1, "名", "姓");
        assertEquals(new Integer(1), customer.getId());
        assertEquals("名", customer.getFirstName());
        assertEquals("姓", customer.getLastName());
    }
}
