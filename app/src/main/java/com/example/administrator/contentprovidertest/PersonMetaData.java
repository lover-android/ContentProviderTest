package com.example.administrator.contentprovidertest;



import android.provider.BaseColumns;

/**
 * Created by administrator on 18-3-23.
 */

public final class PersonMetaData  {

    public static abstract class Person implements BaseColumns {

        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String TABLE_NAME = "person";

    }
}
