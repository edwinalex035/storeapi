package com.applaudo.store.domain.repository;

import com.applaudo.store.config.StoreH2Config;
import com.applaudo.store.domain.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { StoreH2Config.class },
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class RoleRepositoryTest {
    private static Logger logger = LoggerFactory.getLogger(RoleRepositoryTest.class);
    @Resource
    //@Qualifier("roleRepository")
    //@Autowired
    private RoleRepository roleRepository;

    @Test
    public void whenFindAll_thenReturnRoles() {
        // given:
        List<Role> roles = roleRepository.findAll();
        assertEquals(2, roles.size());

    }
}
