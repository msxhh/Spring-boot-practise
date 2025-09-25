package top.sxmeng.utils;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataFakerUtilTest {

    @Resource
    private DataFakerUtil dataFakerUtil;
    @Test
    void generateBatch() {
        dataFakerUtil.generateBatch();
    }
}