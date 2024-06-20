package com.aostar.trade.controller;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/encryptionCard/")
public class EncryptionCardController {

    @PostMapping("gogo")
    public void demo(@RequestBody Map<String, String> map) {
        System.out.println("===============================开始=================================");
        System.out.println(map.get("ip"));
        int ip = ipStrtoInt(map.get("ip"));
        short port = 7002;
        String dataStr = map.get("data");
        System.out.println("data:" + dataStr);
        System.out.println("dataStr.getBytes().length:" + dataStr.getBytes().length);
        int ret = CLibrary.INSTANCE.isolate_service_send(ip, port, dataStr.getBytes(), dataStr.getBytes().length);
        System.out.println("ret is :" + ret);
        if (ret == CLibrary.SEND_SUCCESS) {
            System.out.println("send interface return success");
        } else {
            System.out.println("send interface return fail, return value is " + ret);
        }

        System.out.println("================================================================");
        System.out.println("data:" + dataStr);
        System.out.println("dataStr.length:" + dataStr.length());
        ret = CLibrary.INSTANCE.isolate_service_send(ip, port, dataStr.getBytes(), dataStr.length());
        if (ret != CLibrary.SEND_SUCCESS) {
            System.out.println("send interface return success");
        } else {
            System.out.println("send interface return fail, return value is " + ret);
        }
    }

    //自定义工具，传入参数类似 *.*.*.*
    public static int ipStrtoInt(String ipStr) {
        String[] ipArr = ipStr.split("\\.");
        int val = 0;
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;

        v1 = Integer.parseInt(ipArr[0]);
        v2 = Integer.parseInt(ipArr[1]);
        v3 = Integer.parseInt(ipArr[2]);
        v4 = Integer.parseInt(ipArr[3]);

        System.out.println("v1 is " + v1);
        System.out.println("v2 is " + v2);
        System.out.println("v3 is " + v3);
        System.out.println("v4 is " + v4);

        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            //bit endian (192.168.2.1:3232235777)
            val += v1 << 24;
            val += v2 << 16;
            val += v3 << 8;
            val += v4;

            System.out.println("string ip change1 " + val);
        } else {
            //little endian (192.168.2.1:16885952)
            val += v4 << 24;
            val += v3 << 16;
            val += v2 << 8;
            val += v1;

            System.out.println("string ip change2 " + val);
        }

        return val;
    }

    public static short portCovert(short port) {
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            System.out.println("BIG_ENDIAN ");
            return port;
        } else {
            System.out.println("LIT_ENDIAN ");
            return (short) (((port & 0xFF) << 8) | ((port & 0xFF00) >>> 8));
        }
    }

    public static byte[] readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }


    @GetMapping("start")
    public String start() {
        int ret;
        String conf_path = "/home/xunjian/4lib/SEND4LIB_V1.1.1/conf";
        int path_len = conf_path.length();
        System.out.println(conf_path);
        //请在init接口返回成功之后，再去调用send接口，避免出错
        //初始化接口仅需调用一次
        ret = CLibrary.INSTANCE.isolate_service_init(conf_path, path_len);

        if (ret == CLibrary.STATE_SUCCESS) {
            System.out.println("init interface return success");
            return "init interface return success";
        } else {
            System.out.println("init interface return fail, return value is " + ret);
            return "init interface return fail, return value is " + ret;
        }

    }

}
