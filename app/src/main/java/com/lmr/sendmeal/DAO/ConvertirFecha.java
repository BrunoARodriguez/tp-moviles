package com.lmr.sendmeal.DAO;

import androidx.room.Database;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Calendar;
import java.util.Date;


public class ConvertirFecha {

    @TypeConverter
    public  static Calendar fromTimestamp (Long valor){
        Calendar c1=        Calendar.getInstance();
                c1.setTime(new Date(valor));
return valor == null ? null : c1;
    }

    @TypeConverter
    public static Long dateToTimestamp(Calendar c1){
        Date d1= c1.getTime();
        return d1 == null ? null : d1.getTime();
    }


}
