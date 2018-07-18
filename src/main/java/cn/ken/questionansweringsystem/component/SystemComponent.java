package cn.ken.questionansweringsystem.component;

import cn.ken.questionansweringsystem.common.Constant;
import cn.ken.questionansweringsystem.utils.Base;
import cn.ken.questionansweringsystem.utils.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * author: shangkun <br/>
 * date: 2018/7/18 <br/>
 * what: SystemComponent <br/>
 */
@Component
public class SystemComponent extends Base{
    @Value("${config.idWorkerNumber}")
    private int idWorkerNumber;

    @PostConstruct
    public void init(){
        logger.info(Constant.printPattern+"system initialication"+Constant.printPattern);
        new IdWorker(idWorkerNumber);
    }
}
