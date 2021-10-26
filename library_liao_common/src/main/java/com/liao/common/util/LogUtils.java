package com.liao.common.util;

import android.util.Log;

import androidx.annotation.CheckResult;

import com.liao.common.LiaoUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.lib_utils
 * @ClassName: LogUtils
 * @Description: 日志工具
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/15 10:58
 */
public class LogUtils {

    public static final String DEFAULT_TAG = "LogUtils";

    public static final int V = Log.VERBOSE;
    public static final int D = Log.DEBUG;
    public static final int I = Log.INFO;
    public static final int W = Log.WARN;
    public static final int E = Log.ERROR;
    public static final int A = Log.ASSERT;
    private static final char[] T = new char[]{'V', 'D', 'I', 'W', 'E', 'A'};

    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final String LINE_SEP = System.getProperty("line.separator");

    private static final String ARGS = "args";
    private static final String NOTHING = "log nothing";
    private static final String NULL = "null";

    /**
     * 日志存储目录
     */
    private static String mDir = LiaoUtils.getApp().getExternalFilesDir(null).getAbsolutePath() + "/log/";
    /**
     * 日志保存天数
     */
    private static int mSaveDays = -1;
    /**
     * 日志开关
     */
    private static boolean isLogSwitch = true;
    /**
     * 日志存储本地开关
     */
    private static boolean isLog2FileSwitch = false;
    /**
     * 日志文件前缀
     */
    private static String mFilePrefix;

    private static final ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    private static SimpleDateFormat simpleDateFormat;

    public static void v(String... msg) {
        log(V, msg);
    }

    public static void vTag(String tag, String... msg) {
        log(V, tag, true, msg);
    }

    public static void d(String... msg) {
        log(D, msg);
    }

    public static void dTag(String tag, String... msg) {
        log(D, tag, true, msg);
    }

    public static void i(String... msg) {
        log(I, msg);
    }

    public static void iTag(String tag, String... msg) {
        log(I, tag, true, msg);
    }

    public static void w(String... msg) {
        log(W, msg);
    }

    public static void wTag(String tag, String... msg) {
        log(W, tag, true, msg);
    }

    public static void e(String... msg) {
        log(E, msg);
    }

    public static void e(String msg, Throwable tr) {
        log(E, msg + "\n" + Log.getStackTraceString(tr));
    }

    public static void eTag(String tag, String... msg) {
        log(E, tag, true, msg);
    }

    public static void eTag(String tag, String msg, Throwable tr) {
        log(E, tag, true, msg + "\n" + Log.getStackTraceString(tr));
    }

    public static void a(String... msg) {
        log(A, msg);
    }

    public static void aTag(String tag, String... msg) {
        log(A, tag, true, msg);
    }


    private static void log(int type, String... msg) {
        log(type, DEFAULT_TAG, true, msg);
    }

    /**
     * 日志打印操作
     *
     * @param type          日志类型
     * @param tag           日志tag
     * @param isDefaultFile 日志是否保存在统一日志文件里;true 是；false，独立日志文件
     * @param msgs
     */
    private static void log(int type, String tag, boolean isDefaultFile, String... msgs) {
        if (!isLogSwitch) {
            return;
        }
        String body = processBody(msgs);
        Log.println(type, tag, body);

        if (isLog2FileSwitch) {
            mExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    //将日志存储到文件中
                    print2File(type, tag, isDefaultFile, body);
                }
            });
        }
    }

    /**
     * 处理日志内容
     *
     * @param msgs
     * @return
     */
    private static String processBody(String... msgs) {
        String body = NULL;
        if (msgs != null) {
            if (msgs.length == 1) {
                body = msgs[0];
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0, len = msgs.length; i < len; ++i) {
                    String content = msgs[i];
                    sb.append(ARGS)
                            .append("[")
                            .append(i)
                            .append("]")
                            .append(" = ")
                            .append(content)
                            .append(LINE_SEP);
                }
                body = sb.toString();
            }
        }
        return body.length() == 0 ? NOTHING : body;
    }

    /**
     * 将日志保存到日志文件中
     *
     * @param type
     * @param tag
     * @param isDefaultFile
     * @param msg
     */
    private static void print2File(final int type, final String tag, boolean isDefaultFile, final String msg) {
        Date d = new Date();
        String format = getSdf().format(d);
        String date = format.substring(0, 10);
        //日志文件路径
        String currentLogFilePath = getCurrentLogFilePath(d, isDefaultFile ? DEFAULT_TAG : tag);
        //判断文件路径是否存在，不存在则创建
        if (!createOrExistsFile(currentLogFilePath, date)) {
            Log.e("LogUtils", "create " + currentLogFilePath + " failed!");
            return;
        }
        String time = format.substring(11);
        //日志内容格式：时间+日志类型+tag
        final String content = time +
                T[type - V] +
                "/" +
                tag +
                ": " +
                msg +
                LINE_SEP;

        boolean isInput = input2File(currentLogFilePath, content);
        if (!isInput) {
            Log.e(DEFAULT_TAG, "日志写入文件失败！");
        }
    }

    /**
     * 字符串写入文件操作
     *
     * @param filePath
     * @param content
     * @return
     */
    private static boolean input2File(final String filePath, final String content) {
        File file = new File(filePath);

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断文件路径是否存在，不存在则创建
     *
     * @param filePath
     * @param date
     * @return
     */
    private static boolean createOrExistsFile(final String filePath, final String date) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            deleteDueLogs(filePath, date);
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除已过期日志文件
     *
     * @param filePath
     * @param date     当前日期时间
     */
    private static void deleteDueLogs(final String filePath, final String date) {
        if (mSaveDays <= 0) {
            return;
        }
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        File[] files = parentFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return isMatchLogFileName(name);
            }
        });
        if (files == null || files.length <= 0) {
            return;
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault());
        try {
            long dueMillis = sdf.parse(date).getTime() - mSaveDays * 86400000L;
            for (final File aFile : files) {
                String name = aFile.getName();
                int l = name.length();
                String logDay = findDate(name);
                if (sdf.parse(logDay).getTime() <= dueMillis) {
                    mExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            boolean delete = aFile.delete();
                            if (!delete) {
                                Log.e("LogUtils", "delete " + aFile + " failed!");
                            }
                        }
                    });
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static final Pattern FIND_DATE_PATTERN = Pattern.compile("[0-9]{4}_[0-9]{2}_[0-9]{2}");

    private static String findDate(String str) {
        Matcher matcher = FIND_DATE_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    private static boolean isMatchLogFileName(String name) {
        return name.matches("^" + mFilePrefix + "_[0-9]{4}_[0-9]{2}_[0-9]{2}_.*$");
    }

    /**
     * 判断文件是否存在，不存在则创建
     *
     * @param file
     * @return
     */
    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /***
     * 获取日志文件路径
     * @param d
     * @param tag
     * @return
     */
    private static String getCurrentLogFilePath(Date d, String tag) {
        String format = getSdf().format(d);
        String date = format.substring(0, 10);
        return mDir + mFilePrefix + "_"
                + date + "_" + tag;
    }

    private static SimpleDateFormat getSdf() {
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss.SSS ", Locale.getDefault());
        }
        return simpleDateFormat;
    }

    /**
     * 获取日志文件夹路径
     *
     * @return
     */
    public static String getLogFilesDir() {
        return mDir;
    }

    /**
     * 日志相关功能配置内部类
     */
    public static class LogConfig {
        /**
         * 日志存储目录
         */
        private String mDir;
        /**
         * 日志保存天数
         */
        private int mSaveDays = -1;
        /**
         * 日志开关
         */
        private boolean isLogSwitch = true;
        /**
         * 日志存储本地开关
         */
        private boolean isLog2FileSwitch = false;
        /**
         * 日志文件前缀
         */
        private String mFilePrefix = "Liao";

        private LogConfig() {
        }

        @CheckResult
        public static LogConfig getInstance() {
            return new LogConfig();
        }

        public LogConfig setSaveDays(int mSaveDays) {
            this.mSaveDays = mSaveDays;
            return this;
        }

        public LogConfig setLogSwitch(boolean isLogSwitch) {
            this.isLogSwitch = isLogSwitch;
            return this;
        }

        public LogConfig setLog2FileSwitch(boolean isLog2FileSwitch) {
            this.isLog2FileSwitch = isLog2FileSwitch;
            return this;
        }

        public LogConfig setFilePrefix(String filePrefix) {
            this.mFilePrefix = filePrefix;
            return this;
        }

        public void apply() {
            LogUtils.mSaveDays = mSaveDays;
            LogUtils.isLogSwitch = isLogSwitch;
            LogUtils.isLog2FileSwitch = isLog2FileSwitch;
            LogUtils.mFilePrefix = mFilePrefix;
        }
    }
}
