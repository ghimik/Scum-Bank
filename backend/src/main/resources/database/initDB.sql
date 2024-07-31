BEGIN;


CREATE TABLE IF NOT EXISTS public.account
(
    id bigint NOT NULL DEFAULT nextval('"Users_id_seq"'::regclass),
    username character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    bankaccountid bigint,
    CONSTRAINT account_pkey PRIMARY KEY (id),
    CONSTRAINT username UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS public.bankaccount
(
    id bigserial NOT NULL,
    balance numeric(18, 2),
    CONSTRAINT bankaccount_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.friendpair
(
    first bigint NOT NULL,
    second bigint NOT NULL,
    id bigserial NOT NULL,
    CONSTRAINT friendpair_pkey PRIMARY KEY (id),
    CONSTRAINT unique_friendship_pair UNIQUE (first, second)
);

CREATE TABLE IF NOT EXISTS public.transaction
(
    id bigserial NOT NULL,
    senderid bigint NOT NULL,
    recieverid bigint NOT NULL,
    value numeric(18, 2) NOT NULL,
    date timestamp with time zone,
    CONSTRAINT transaction_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.account
    ADD CONSTRAINT bankaccount_to_account_id FOREIGN KEY (bankaccountid)
    REFERENCES public.bankaccount (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID;


ALTER TABLE IF EXISTS public.friendpair
    ADD CONSTRAINT first_fk FOREIGN KEY (first)
    REFERENCES public.account (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.friendpair
    ADD CONSTRAINT second_fk FOREIGN KEY (second)
    REFERENCES public.account (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.transaction
    ADD CONSTRAINT reciever_fk FOREIGN KEY (recieverid)
    REFERENCES public.account (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.transaction
    ADD CONSTRAINT sender_fk FOREIGN KEY (senderid)
    REFERENCES public.account (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;

END;