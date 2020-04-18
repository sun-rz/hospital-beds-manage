package graduation.project.hospitalbedsmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("graduation.project.hospitalbedsmanage.mapper") //扫描的mapper
@SpringBootApplication
public class HospitalBedsManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalBedsManageApplication.class, args);
    }

}
