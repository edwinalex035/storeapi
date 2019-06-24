package com.applaudo.store.domain.repository;

import com.applaudo.store.config.StoreH2Config;
import com.applaudo.store.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
/*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { StoreH2Config.class },
        loader = AnnotationConfigContextLoader.class
)

 */
@Transactional
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

    //@Test
    public void whenLogin_thenUser() {
        User user = userRepository.findByUsernameAndPassword("ADMIN", "ADMIN");
        assertEquals("ADMIN", user.getUsername());
    }
}
