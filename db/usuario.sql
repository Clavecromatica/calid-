CREATE TABLE servicios.usuarios (
   id INT AUTO_INCREMENT NOT NULL,
   nombre_usuario VARCHAR(50) NOT NULL,
   contrasena VARCHAR(255) NOT NULL,
   es_administrador BIT NOT NULL,
   es_coordinador BIT NOT NULL,
   UNIQUE (nombre_usuario),
  PRIMARY KEY (id)
) ENGINE = InnoDB ROW_FORMAT = DEFAULT;