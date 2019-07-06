package com.zandero.rest;

import com.zandero.rest.test.TestBeanReaderRest;
import com.zandero.rest.test.TestReaderRest;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(VertxExtension.class)
class BeanReaderTest extends VertxTest {

    @BeforeAll
    static void start() {

        before();

        Router router = RestRouter.register(vertx, TestBeanReaderRest.class);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(PORT);
    }

    @Test
    void testCustomInput(VertxTestContext context) {

        client.post(PORT, HOST, "/read/bean")
                .as(BodyCodec.string())
                .sendBuffer(Buffer.buffer("The quick brown fox jumps over the red dog!"),
                        context.succeeding(response -> context.verify(() -> {
                            assertEquals(200, response.statusCode());
                            assertEquals("Header: null, Path: null, Query: null", response.body());
                            context.completeNow();
                        })));
    }
}