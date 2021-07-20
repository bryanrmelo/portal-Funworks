 CREATE TABLE sistema_versao (
    id integer NOT NULL,
    versao character varying(2000) NOT NULL
);

ALTER TABLE ONLY sistema_versao ADD CONSTRAINT sistema_versao_pkey PRIMARY KEY (id);

CREATE SEQUENCE sistema_versao_seq START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;