CREATE TABLE public.conta_pagar_rateio_empresa (
	conta_pagar_rateio_id int4 NOT NULL,
	rateio_empresa_id int4 NOT NULL,
	CONSTRAINT fk_3nqavc2eo68urpnjehnhdedmo FOREIGN KEY (conta_pagar_rateio_id) REFERENCES public.conta_pagar_rateio(id),
	CONSTRAINT fk_gs8gciur1gu6l6c1ixd9cpkm9 FOREIGN KEY (rateio_empresa_id) REFERENCES public.rateio_empresa(id)
);

CREATE INDEX uk_gs8gciur1gu6l6c1ixd9cpkm9 ON public.conta_pagar_rateio_empresa (rateio_empresa_id);
