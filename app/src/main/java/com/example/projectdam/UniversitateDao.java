package com.example.projectdam;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UniversitateDao {
    @Query("SELECT * FROM universitati")
    List<Universitate> getUniversitati();

    @Insert
    void insertUniversitate(Universitate universitate);
}
