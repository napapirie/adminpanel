package com.example.corporate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTest {
    @Autowired MockMvc mvc;
    @Test void anonymousCannotOpenAdmin(){try{mvc.perform(get("/admin")).andExpect(status().is3xxRedirection());}catch(Exception e){throw new RuntimeException(e);}}
    @Test @WithMockUser(roles="USER") void userCannotOpenAdmin(){try{mvc.perform(get("/admin")).andExpect(status().isForbidden());}catch(Exception e){throw new RuntimeException(e);}}
    @Test @WithMockUser(roles="ADMIN") void adminCanOpenAdmin(){try{mvc.perform(get("/admin")).andExpect(status().isOk());}catch(Exception e){throw new RuntimeException(e);}}
}
