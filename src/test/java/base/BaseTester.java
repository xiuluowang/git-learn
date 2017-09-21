package base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ordinov.app.config.ErspConfig;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(classes={ErspConfig.class})
public class BaseTester {
}
