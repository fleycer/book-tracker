package com.fleycer.booktracker.enums;


import com.fleycer.booktracker.controllers.BookController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class EnumMappingIntegrationTest {
    class EnumMappingAppTests {
        @Autowired
        private MockMvc mockMvc;

        @Test
        void whenPassingValidEnumConstant_thenConvert() throws Exception {
            mockMvc.perform(get("/findby/readingstatus?param=now"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(ReadingStatus.NOW.name()));
        }
    }
}