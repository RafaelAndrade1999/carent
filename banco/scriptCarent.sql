


-- Table estado

CREATE TABLE  estado (
  sigla VARCHAR(2) NOT NULL PRIMARY KEY,
  nome VARCHAR(45) NOT NULL
  );




-- Table cidade

CREATE TABLE  cidade (
  id INT PRIMARY KEY NOT NULL,
  nome VARCHAR(45) NOT NULL,
  sigla VARCHAR(2) NOT NULL,
  FOREIGN KEY (sigla) REFERENCES estado (sigla)
  );


GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public 
TO andre;
--su postgres
-- Table usuario

CREATE TABLE  usuario (
  id serial  PRIMARY KEY,
  nome VARCHAR(60) NOT NULL,
  cpf VARCHAR(11),
	email varchar(50) not null,
	senha varchar(15) not null
);



-- Table cor

CREATE TABLE  cor (
  id serial  PRIMARY KEY,
  nome VARCHAR(45) NOT NULL UNIQUE
);



-- Table fabricante

CREATE TABLE fabricante (
  id int PRIMARY KEY NOT NULL,
  Fabricante varchar (50) NOT NULL 
);



-- Table modelo


CREATE TABLE modelo(
  id serial PRIMARY Key NOT NULL,
  fabricante INT NOT NULL, 
  modelo varchar(255),
  veiculo varchar(255),
  FOREIGN KEY (fabricante) REFERENCES fabricante (id)
);



-- Table cor_has_modelo

CREATE TABLE  cor_modelo (
  cor INT NOT NULL,
  modelo INT NOT NULL,
  FOREIGN KEY (cor) REFERENCES cor(id),
  FOREIGN KEY (modelo) REFERENCES modelo(id)
);



-- Table filial

CREATE TABLE  filial (
  id serial  PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  endereco VARCHAR(200) NOT NULL,
  cnpj VARCHAR(14) NOT NULL,
  telefone VARCHAR(45) NOT NULL,
  email VARCHAR(100) NOT NULL,
  cidade INT NOT NULL,
  FOREIGN KEY (cidade) REFERENCES cidade (id));

-- Table avaliacao_carro

CREATE TABLE  avaliacao_carro (
  id serial  PRIMARY KEY,
  mensagem VARCHAR(200) NOT NULL,
  nota INT NOT NULL);



-- Table carro

CREATE TABLE  carro (
  id serial  PRIMARY KEY,
  disponivel boolean NOT NULL,
  placa CHAR(6) NOT NULL UNIQUE,
  filial INT NOT NULL,
  modelo INT NOT NULL,
  motor VARCHAR(45) NOT NULL,
  combustivel VARCHAR(15) NOT NULL,
  avaliacao INT NOT NULL,
  FOREIGN KEY (filial) REFERENCES filial (id),
  FOREIGN KEY (modelo) REFERENCES modelo (id),
  FOREIGN KEY (avaliacao) REFERENCES avaliacao_carro(id)
  );


-- Table acessorio

CREATE TABLE  acessorio (
  id serial  PRIMARY KEY,
  nome VARCHAR(45) NOT NULL);



-- Table acessorio_has_carro

CREATE TABLE  acessorio_carro (
  acessorio INT NOT NULL,
  carro INT NOT NULL,
  FOREIGN KEY (acessorio) REFERENCES acessorio(id),
  FOREIGN KEY (carro) REFERENCES carro(id));



-- Table aluguel

CREATE TABLE  aluguel (
  id serial  PRIMARY KEY,
  codigo INT NOT NULL,
  valor_total DATE NOT NULL,
  data_fim DATE NOT NULL);
  
-- Table aluguel_carro

CREATE TABLE  aluguel_carro (
  id serial  PRIMARY KEY,
  aluguel INT NOT NULL,
  carro INT NOT NULL,
  data_inicio DATE NOT NULL,
  data_fim DATE NOT NULL,
  kilometragem VARCHAR(45) NOT NULL,
  usuario INT NOT NULL,
  FOREIGN KEY (aluguel) REFERENCES aluguel (id),
  FOREIGN KEY (carro) REFERENCES carro (id),
  FOREIGN KEY (usuario) REFERENCES usuario(id));
  

-- Table  avaliar_aluguel

CREATE TABLE   avaliar_aluguel (
  id serial  PRIMARY KEY,
  mensagem VARCHAR(45) NOT NULL,
  nota INT NOT NULL,
  aluguel INT NOT NULL,
  FOREIGN KEY(aluguel) REFERENCES aluguel(id));

-- Table manutencao

CREATE TABLE  manutencao (
  id serial  PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  setor VARCHAR(45) NOT NULL);


-- Table manutencao_carro

CREATE TABLE  manutencao_carro (
  id serial  PRIMARY KEY,
  manutencao INT NOT NULL,
  carro INT NOT NULL,
  data DATE NOT NULL,
  kilometgragem INT NOT NULL,
  FOREIGN KEY (manutencao) REFERENCES manutencao(id),
  FOREIGN KEY (carro) REFERENCES carro (id)
  );


