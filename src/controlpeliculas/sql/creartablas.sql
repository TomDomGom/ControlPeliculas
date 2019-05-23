/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Tomas
 * Created: 23-may-2019
 */

DROP TABLE DatosCategoria;
DROP TABLE DatosPeliculas;

CREATE TABLE DatosCategoria (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,

    NOMBRE VARCHAR(30) NOT NULL,
    DESCRIPCION VARCHAR(150),

    CONSTRAINT ID_CATEGORIA_PK PRIMARY KEY (ID)
);

CREATE TABLE DatosPeliculas (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,

    TITULO VARCHAR (80) NOT NULL,
    DIRECTOR VARCHAR (30) NOT NULL,
    FECHAESTRENO DATE,
    PRODUCTORA VARCHAR (30),
    CARTEL VARCHAR (30),
    CATEGORIA INTEGER,
    CALIFICACION CHAR (2),
    RECAUDACION DECIMAL (14,2),
    PROYECTADA BOOLEAN,

    CONSTRAINT ID_PELICULAS_PK PRIMARY KEY (ID),
    CONSTRAINT CAT_PELICULAS_FK FOREIGN KEY (CATEGORIA) REFERENCES DatosCategoria (ID)
);