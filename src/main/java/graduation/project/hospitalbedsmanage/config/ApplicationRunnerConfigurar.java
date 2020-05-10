package graduation.project.hospitalbedsmanage.config;

import graduation.project.hospitalbedsmanage.service.BedsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动时初始化数据
 */

@Slf4j
@Component
public class ApplicationRunnerConfigurar implements ApplicationRunner {

    @Autowired
    BedsService bedsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //自动分配病床
        bedsService.autoModeBeds(0);
    }
}
