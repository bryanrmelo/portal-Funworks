CREATE TABLE public.conta_pagar_rateio (
	id int4 NOT NULL,
	tipo int4,
	rateio_pre_configurado bool,
	rateio_pre_configurado_id int4,
	CONSTRAINT conta_pagar_rateio_pkey PRIMARY KEY (id),
	CONSTRAINT fk_lyi3kbwc6wewbtfph0ddi24dy FOREIGN KEY (rateio_pre_configurado_id) REFERENCES public.tipo_rateio(id)
);

CREATE INDEX conta_pagar_rateio_pkey ON public.conta_pagar_rateio (id);
