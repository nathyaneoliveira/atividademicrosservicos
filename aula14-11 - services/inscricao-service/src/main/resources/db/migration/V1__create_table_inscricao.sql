CREATE TABLE tb_inscricoes (
   id BINARY(16) NOT NULL,
   usuario_id BINARY(16) NOT NULL,
   evento_id BINARY(16) NOT NULL,
   status_inscricao VARCHAR(20) NOT NULL,
   data_hora_inscricao DATETIME(6) NOT NULL,

   CONSTRAINT pk_tb_inscricoes PRIMARY KEY (id),
   INDEX idx_inscricoes_usuario_evento_status (usuario_id, evento_id, status_inscricao),
   INDEX idx_inscricoes_evento_status (evento_id, status_inscricao)
);
