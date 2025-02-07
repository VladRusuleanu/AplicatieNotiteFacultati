package com.example.projectdam;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Universitate.class}, version = 1)
public abstract class UniversitateDB extends RoomDatabase {
    public abstract UniversitateDao getUniversityDao();
}
