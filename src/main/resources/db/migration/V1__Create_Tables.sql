-- V1: Create tables for filament management

CREATE TABLE tb_filament_brand (
                                   cd_filament_brand SERIAL PRIMARY KEY,
                                   nm_brand VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE tb_filament_type (
                                  cd_filament_type SERIAL PRIMARY KEY,
                                  nm_type VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE tb_filament_color (
                                   cd_filament_color SERIAL PRIMARY KEY,
                                   nm_color VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE tb_filament (
                             cd_filament SERIAL PRIMARY KEY,
                             cd_filament_brand INT NOT NULL,
                             cd_filament_type INT NOT NULL,
                             cd_filament_color INT NOT NULL,
                             vl_total_weight INT NOT NULL,
                             vl_remaining_weight INT NOT NULL,
                             vl_cost DECIMAL(10,2),
                             dt_purchase DATE,
                             FOREIGN KEY (cd_filament_brand) REFERENCES tb_filament_brand(cd_filament_brand),
                             FOREIGN KEY (cd_filament_type) REFERENCES tb_filament_type(cd_filament_type),
                             FOREIGN KEY (cd_filament_color) REFERENCES tb_filament_color(cd_filament_color)
);

CREATE TABLE tb_filament_consumption (
                                         cd_filament_consumption SERIAL PRIMARY KEY,
                                         cd_filament INT NOT NULL,
                                         vl_consumed_amount INT NOT NULL,
                                         dt_consumption TIMESTAMP DEFAULT NOW(),
                                         FOREIGN KEY (cd_filament) REFERENCES tb_filament(cd_filament)
);