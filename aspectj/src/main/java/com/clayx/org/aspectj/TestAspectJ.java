package com.clayx.org.aspectj;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2016/9/2.
 */
@Aspect
public class TestAspectJ extends Activity{

    private static final String METHOD_EXECUTION = "execution(* com.app.sinkinchan.smartstock..*(..))";
    private int index = 0;
    private String TAG = "Calyx";
    @Pointcut(METHOD_EXECUTION)
    public void methodExecution() {

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Around("methodExecution()")
    public void aroundMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        String result = "-----------------------------MethodExecution";
        Log.e(TAG,joinPoint.getSignature().getName());
        appendLog(joinPoint.getSignature().getName());
    }
    //将日志写在文件中log.txt
    public void appendLog(String text) {
        File logFile = new File("sdcard/log.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
