package com.example.nativedemo;

import com.example.nativedemo.entity.Message;
import com.example.nativedemo.mapper.MyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppStartupRunner implements CommandLineRunner {
    @Autowired
    private MyMapper myMapper;

    @Override
    public void run(String... args) throws Exception {
        myMapper.insert(new Message(1, "Hello World!"));
        Message message = myMapper.select(1);
        log.info("{} {} ", message.getMessage(), message.getId());
    }
}
