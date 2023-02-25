package com.kq.project.once;

import com.alibaba.excel.EasyExcel;

import java.util.List;

public class ImportExcel {

    public static void main(String[] args) {
        String fileName =  "E:\\project\\user-center-backend\\src\\main\\resources\\test.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        readByListener(fileName);
        synchronousRead(fileName);
    }

    public  static  void readByListener(String fileName){
        EasyExcel.read(fileName, XingQiuUserInfo.class,new TableListener()).sheet().doRead();
    }
    public  static  void synchronousRead(String fileName){

        List<XingQiuUserInfo> totalDataList =
                EasyExcel.read(fileName).head(XingQiuUserInfo.class).sheet().doReadSync();
        for (XingQiuUserInfo xingQiuUserInfo : totalDataList) {
            System.out.println(xingQiuUserInfo);
        }
    }

}
