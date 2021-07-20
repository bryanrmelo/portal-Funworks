CREATE TABLE public.tipo_conta (
	id int4 NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT tipo_conta_pkey PRIMARY KEY (id)
);

CREATE INDEX tipo_conta_pkey ON public.tipo_conta (id);


INSERT INTO public.tipo_conta (id,descricao) VALUES (
1,'Receitas');
INSERT INTO public.tipo_conta (id,descricao) VALUES (
2,'Despesas');
