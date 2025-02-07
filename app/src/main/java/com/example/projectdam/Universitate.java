package com.example.projectdam;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "universitati")
public class Universitate implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nume;
    private String adresa;
    private int anulFondarii;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getAnulFondarii() {
        return anulFondarii;
    }

    public void setAnulFondarii(int anulFondarii) {
        this.anulFondarii = anulFondarii;
    }
}
