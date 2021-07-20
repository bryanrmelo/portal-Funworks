CREATE TABLE public.tipo_rateio (
	id int4 NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT tipo_rateio_pkey PRIMARY KEY (id)
);

CREATE INDEX tipo_rateio_pkey ON public.tipo_rateio (id);

INSERT INTO public.tipo_rateio (id,descricao) VALUES (
1,'% �rea Constru�da_Setor Empresarial');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
2,'% �rea Constru�da_Setor Imobili�rio');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
3,'% Igualit�rio Total');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
4,'% Igualit�rio_Setor Empresarial');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
5,'% Igualit�rio_Setor Imobili�rio');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
6,'% N� Pessoas Total');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
7,'% N� Pessoas_Setor Empresarial');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
8,'% N� Pessoas_Setor Imobili�rio');
