package com.aatk.pmanager.accounts.domain;

import android.arch.persistence.room.TypeConverter;

public enum Role {
    ADMIN(0),
    USER(1),
    MANAGER(2);

    private final int numeral;

    Role(int numeral) {
        this.numeral = numeral;
    }

    @TypeConverter
    public static Role getDownloadStatus(Integer numeral){
        for(Role ds : values()){
            if(ds.numeral == numeral){
                return ds;
            }
        }
        return null;
    }

    @TypeConverter
    public static Integer getDownloadStatusInt(Role status){
        return status.numeral;
    }

}