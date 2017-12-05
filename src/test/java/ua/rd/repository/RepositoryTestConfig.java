package ua.rd.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/repositoryTestContext.xml" })
@ActiveProfiles(profiles = "dev")
public class RepositoryTestConfig {
    @Test
    public void runtest(){

    }
}
