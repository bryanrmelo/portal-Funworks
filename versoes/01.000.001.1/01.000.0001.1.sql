
CREATE SEQUENCE lancamento_rateio_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE lancamento_rateio_seq
  OWNER TO postgres;
  

CREATE SEQUENCE conta_receber_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE conta_receber_seq
  OWNER TO postgres;

insert into conta values(130,'RECEITAS',null,1);

update conta set pai=130 where tipo=1 and id<>130;
