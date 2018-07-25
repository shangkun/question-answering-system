package cn.ken.questionansweringsystem.utils;

import cn.ken.questionansweringsystem.model.common.ServerInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

/**
 * author: shangkun <br/>
 * date: 2018/7/24 <br/>
 * what: SigarUtils <br/>
 */
public class SigarUtils {
    /**
     * 获取部分服务器信息
     * @return
     */
    public static ServerInfo property(){
        ServerInfo serverInfo = new ServerInfo();
        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress();
            serverInfo.setIp(ip);
            serverInfo.setHostName(addr.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");
        serverInfo.setUserName(userName);
        String computerName = map.get("COMPUTERNAME");
        serverInfo.setComputerName(computerName);
        String userDomain = map.get("USERDOMAIN");
        serverInfo.setUserDomain(userDomain);
        serverInfo.setTotalMemory(r.totalMemory());
        serverInfo.setFreeMemory(r.freeMemory());
        serverInfo.setAvailableProcessors(r.availableProcessors()+"");
        serverInfo.setJavaVersion(props.getProperty("java.version"));
        serverInfo.setJavaVendor(props.getProperty("java.vendor"));
        serverInfo.setJavaVendorUrl(props.getProperty("java.vendor.url"));
        serverInfo.setJavaHome(props.getProperty("java.home"));
        serverInfo.setOsName(props.getProperty("os.name"));
        serverInfo.setOsArch(props.getProperty("os.arch"));
        serverInfo.setOsVersion(props.getProperty("os.version"));
        return serverInfo;
    }
}
