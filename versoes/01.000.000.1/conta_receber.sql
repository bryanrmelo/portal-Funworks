CREATE TABLE public.conta_receber (
	id int4 NOT NULL,
	data_recebimento timestamp NOT NULL,
	descricao varchar NOT NULL,
	valor numeric NOT NULL,
	conta_id int4 NOT NULL,
	empresa_id int4 NOT NULL,
	CONSTRAINT conta_receber_pkey PRIMARY KEY (id),
	CONSTRAINT fk_62rfufrmer6jsn4icl7vh4ef6 FOREIGN KEY (empresa_id) REFERENCES public.empresa(id),
	CONSTRAINT fk_e952q4wlbbq67d5r8qarpecwe FOREIGN KEY (conta_id) REFERENCES public.conta(id)
);

CREATE INDEX conta_receber_pkey ON public.conta_receber (id);
