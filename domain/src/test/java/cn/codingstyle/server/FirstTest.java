package cn.codingstyle.server;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

class FirstTest {
    @Test
    void Given_When_Then() {
        assertThat(1, is(1));
    }
}
