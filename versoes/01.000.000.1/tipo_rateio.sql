CREATE TABLE public.tipo_rateio (
	id int4 NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT tipo_rateio_pkey PRIMARY KEY (id)
);

CREATE INDEX tipo_rateio_pkey ON public.tipo_rateio (id);

INSERT INTO public.tipo_rateio (id,descricao) VALUES (
1,'% Área Construída_Setor Empresarial');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
2,'% Área Construída_Setor Imobiliário');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
3,'% Igualitário Total');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
4,'% Igualitário_Setor Empresarial');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
5,'% Igualitário_Setor Imobiliário');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
6,'% N° Pessoas Total');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
7,'% N° Pessoas_Setor Empresarial');
INSERT INTO public.tipo_rateio (id,descricao) VALUES (
8,'% N° Pessoas_Setor Imobiliário');
