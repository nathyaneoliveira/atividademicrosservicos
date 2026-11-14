CREATE TABLE tb_usuarios (
     id BINARY(16) NOT NULL,
     password VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL,
     role VARCHAR(50) NOT NULL,

     CONSTRAINT pk_tb_usuarios PRIMARY KEY (id),
     CONSTRAINT uk_tb_usuarios_email UNIQUE (email)
);
