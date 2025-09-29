package com.example.pccomponentsapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "pc_components.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE components(
                id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                short_desc TEXT,
                long_desc TEXT
            );
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE images(
                id INTEGER PRIMARY KEY,
                component_id INTEGER NOT NULL,
                file_name TEXT NOT NULL,
                FOREIGN KEY(component_id) REFERENCES components(id)
            );
        """.trimIndent())

        db.execSQL("""INSERT INTO components VALUES
            (1,'CPU','Процесор — мозок ПК','CPU виконує інструкції програм, визначає загальну швидкодію системи. Важливі параметри: кількість ядер, потоки, частота, кеш, TDP.');""")
        db.execSQL("""INSERT INTO components VALUES
            (2,'GPU','Відеокарта — графіка','GPU обробляє графіку і відео, важлива для ігор, рендерингу, AI. Параметри: VRAM, шина пам’яті, енергоспоживання.');""")
        db.execSQL("""INSERT INTO components VALUES
            (3,'RAM','Оперативна пам’ять','Тимчасове швидке сховище для активних задач. Параметри: обсяг, частота, таймінги.');""")
        db.execSQL("""INSERT INTO components VALUES
            (4,'SSD','Твердотільний накопичувач','Швидке зберігання даних. Форм-фактори: 2.5\", M.2; інтерфейси: SATA, NVMe.');""")
        db.execSQL("""INSERT INTO components VALUES
            (5,'Motherboard','Материнська плата','З’єднує всі компоненти. Чіпсет, сокет, форм-фактор, слоти розширення.');""")
        db.execSQL("""INSERT INTO components VALUES
            (6,'PSU','Блок живлення','Живлення системи. Потужність, ефективність (80 Plus), стабільність.');""")

        db.execSQL("""INSERT INTO images VALUES (1,1,'cpu1');""")
        db.execSQL("""INSERT INTO images VALUES (2,1,'cpu2');""")
        db.execSQL("""INSERT INTO images VALUES (3,2,'gpu1');""")
        db.execSQL("""INSERT INTO images VALUES (4,2,'gpu2');""")
        db.execSQL("""INSERT INTO images VALUES (5,3,'ram1');""")
        db.execSQL("""INSERT INTO images VALUES (6,4,'ssd1');""")
        db.execSQL("""INSERT INTO images VALUES (7,5,'mb1');""")
        db.execSQL("""INSERT INTO images VALUES (8,6,'psu1');""")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS images")
        db.execSQL("DROP TABLE IF EXISTS components")
        onCreate(db)
    }
}
