package io.exam.match.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class SecretGenerate {

    @Test
    void generate() {
        String fir = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
        String sec = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");

        String bind = fir + sec;

        List<Character> random = new ArrayList<>();

        int pointer = 0;

        while (pointer < 64) {

            Random ran = new Random();

            int init = ran.nextInt(64);

            random.add(bind.charAt(init));

            pointer++;
        }

        StringBuilder builder = new StringBuilder();

        for (Character character : random) {
            builder.append(character);
        }

        System.out.println(builder.toString().length());

        System.out.println(builder);
    }


}
