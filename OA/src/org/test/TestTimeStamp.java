package org.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTimeStamp {
	public static void main(String[] args) {
		long time = System.currentTimeMillis();  
        String t = String.valueOf(time/1000);
        System.out.println(t);
	}
	
	public String stampToDate(String t){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.format(t);
        long lt = new Long(t);
        Date date = new Date(lt );
        res = simpleDateFormat.format(date);
        System.out.println(res);
        return res;
    }
}
